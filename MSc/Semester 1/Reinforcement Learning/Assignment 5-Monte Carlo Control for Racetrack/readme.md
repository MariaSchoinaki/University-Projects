# Racetrack — Monte Carlo Control (Exercise 5.12, Sutton & Barto)

This notebook implements and analyzes the **Racetrack** control problem from  
*Sutton & Barto, Reinforcement Learning: An Introduction (2nd Edition), Exercise 5.12*  
using **On-Policy Monte Carlo Control with ε-greedy exploration**.

The objective is to learn an **optimal driving policy** that completes the track
as fast as possible, while avoiding crashes, **without access to the environment
dynamics**.

---

## Problem Overview

The racetrack problem is formulated as an **episodic Markov Decision Process (MDP)**:

- The agent controls a race car moving on a discrete grid track
- At each time step, the agent selects an **acceleration action**
- The episode ends when the car **crosses the finish line**
- If the car hits the track boundary, it is reset to the start line and the episode continues

The goal is to learn an **optimal policy** that minimizes the expected number of steps
to reach the finish line.

---

## State Representation

Each state is defined as a 4-tuple:

$$
S = (x,\ y,\ v_x,\ v_y)
$$

Where:
- $(x, y)$: the current grid position of the car  
- $(v_x, v_y)$: the velocity components in the vertical and horizontal directions  

Velocity constraints:
- $v_x, v_y \in \{0,1,2,3,4\}$
- $(v_x, v_y) \neq (0,0)$ except at the starting line

---

## Action Space

At each time step, the agent selects an acceleration:

$$
A = (a_x, a_y), \quad a_x, a_y \in \{-1, 0, 1\}
$$

This results in **9 possible actions**, which modify the velocity before movement.

---

## Rewards

- **−1** reward at every time step  
- This includes normal movement, crashes, and the final step crossing the finish line  

This reward structure encourages **shorter trajectories**.

---

## Environment Dynamics

### Track Layout
- The racetrack is provided as a text file:
  - `0`: boundary / wall  
  - `1`: valid track cell  
  - `2`: starting line  
  - `3`: finish line  

Everything outside the grid is treated as a boundary.

---

### Motion and Collision Handling

- Velocity is updated **before movement**
- The car then moves along a straight-line trajectory
- **The entire projected path is checked**:
  - If it intersects the finish line → episode terminates
  - If it intersects the boundary → crash, reset to a random start cell with zero velocity

This follows the exact dynamics described in the book.

---

### Stochasticity (Noise)

To make the task more challenging:
- With probability **0.1**, the intended acceleration is ignored
- Instead, acceleration $(0,0)$ is applied

This noise is enabled during training and **disabled during policy visualization**.

---

## Method Implemented: On-Policy Monte Carlo Control

### 1. ε-Greedy Policy
An **ε-greedy policy** is used:
- With probability **ε = 0.1**, a random action is selected
- With probability **1 − ε**, the greedy action w.r.t. $Q(s,a)$ is selected

The same policy is used for both **action selection and policy improvement**
(on-policy learning).

---

### 2. Episode Generation
Each episode:
- Starts from a **random position on the starting line**
- Begins with zero velocity
- Continues until the finish line is crossed

State–action–reward sequences are recorded for Monte Carlo evaluation.

---

### 3. First-Visit Monte Carlo Updates

For each episode:
- Returns are computed backward
- Only the **first visit** of each $(s,a)$ pair is updated
- Action-value estimates are updated using an incremental mean:

$$
Q(s,a) \leftarrow Q(s,a) + \frac{G - Q(s,a)}{N(s,a)}
$$

where:
- $G$ is the return
- $N(s,a)$ is the visit count

---

### 4. Policy Improvement

After learning:
- The final policy is extracted greedily:

$$
\pi(s) = \arg\max_a Q(s,a)
$$

---

## Outputs & Results Summary

The notebook produces:

- **Learning curves** showing episode length versus training episodes
- **Greedy trajectories** on the racetrack with noise turned off
- Separate results for different racetrack layouts (left and right turns)

### Qualitative Observations
- Episode lengths decrease significantly during training
- The learned policy accelerates aggressively on straight segments
- The agent slows appropriately before sharp turns to avoid crashes
- Final trajectories closely resemble those shown in Figure 5.5 of the book
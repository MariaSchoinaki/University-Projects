# Cliff Walking — TD Control (SARSA, Q-learning, Expected SARSA)

This notebook implements and compares **three temporal-difference (TD) control methods** on the **Cliff Walking** task from *Sutton & Barto, Reinforcement Learning (2nd Edition), Example 6.6*.  
The goal is to evaluate the **interim performance** (in the style of **Figure 6.3**) by sweeping the learning rate **α** and averaging results over many independent runs.

---

## Problem Overview

Cliff Walking is an **episodic gridworld MDP**:

- A **12×4** grid (width = 12, height = 4)
- Start state at **(0,0)** and terminal goal at **(11,0)**
- The bottom row contains cliff states at positions (x, 0) for x = 1 to 10

- The agent must reach the goal while avoiding the cliff

---

## State and Action Representation

### States
Each state is a grid coordinate S = (x, y), with x in {0, ..., 11} and y in {0, ..., 3}.



### Actions
Four discrete actions:
- **0:** left  
- **1:** right  
- **2:** up  
- **3:** down  

---

## Rewards and Episode Termination

Each step yields:

- **−1** for a normal transition  
- **−100** when stepping into the cliff  
  - the agent is reset to **START**
  - the episode **continues**
- Reaching the goal gives **−1** and terminates the episode

Episodes are capped at **500 steps**.

---

## Methods Implemented

Three TD control algorithms are evaluated using an **ε-greedy** behavior policy:

### SARSA (On-policy TD Control)
$$
Q(s,a) \leftarrow Q(s,a) + \alpha \big[r + \gamma Q(s',a') - Q(s,a)\big]
$$

### Q-learning (Off-policy TD Control)
$$
Q(s,a) \leftarrow Q(s,a) + \alpha \big[r + \gamma \max_{a'} Q(s',a') - Q(s,a)\big]
$$

### Expected SARSA

Expected SARSA updates the action-value function using the expected value
under the ε-greedy policy:

Q(s, a) ← Q(s, a) + α [ r + γ · Eπ[ Q(s′, a′) ] − Q(s, a) ]

where π denotes the ε-greedy policy derived from Q.


---

## Behavior Policy (ε-greedy)

At each step:

- with probability ε = 0.1, select a random action
- with probability 1 − ε, select an action that maximizes Q(s, ·)  
  (ties are broken uniformly at random)

---

## Interim Performance Experiment

To reproduce the interim performance curves:

- The learning rate alpha is swept over 19 values, from 0.1 to 1.0
- For each value of alpha:
  - 2000 independent runs are executed (using different random seeds)
  - each run consists of 100 episodes
- The interim score of a run is computed as the average return over the first 100 episodes
- Final curves show the average interim return across runs for each algorithm

> Note: The original figure often uses many more runs (e.g., 50,000) for smoother curves.  
> This implementation uses **2000 runs** for computational practicality.

---

## Outputs

The notebook generates a plot of:

- **SARSA**
- **Q-learning**
- **Expected SARSA**

versus **α**, with the y-axis showing:

**“Interim: average return per episode (first 100 episodes)”**

This closely follows the presentation and intent of **Figure 6.3** in Sutton & Barto.

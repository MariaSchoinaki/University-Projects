# Windy Gridworld — Example 6.5 (Sutton & Barto, 2nd Edition) — ε-greedy SARSA

This notebook reproduces and analyzes the **Windy Gridworld** task from *Sutton & Barto, Reinforcement Learning (2nd Edition), Example 6.5* using **SARSA (on-policy TD control)** with an **ε-greedy** behavior policy.

The objective is to learn a near-optimal control policy that reaches the goal in as few time steps as possible, **without knowing the transition dynamics in advance**.

---

## Problem Overview

Windy Gridworld is an **undiscounted episodic MDP**:

- The agent moves on a **7 × 10** grid
- There is a **vertical wind** (upward push) whose strength depends on the column
- The agent starts at **S** and the episode ends when it reaches **G**
- Rewards are constant per step until termination

Goal: learn an (approximately) optimal policy π\* that minimizes the number of steps to reach the goal.

---

## State Representation

Each state is a 2D grid coordinate:

\[
S = (x, y)
\]

Where:
- **x** = row index (up–down position)
- **y** = column index (left–right position)

**Convention (matching the code & instructions):**
- `State[0]` = **x coordinate** (up-down location)
- `State[1]` = **y coordinate** (left-right location)

---

## Action Space

There are **4 deterministic actions**, ordered exactly as required:

- **0: Left**
- **1: Right**
- **2: Up**
- **3: Down**

---

## Wind Dynamics

The wind pushes the agent **upward** (i.e., decreases `x`).

Wind strengths by column are:

\[
[0, 0, 0, 1, 1, 1, 2, 2, 1, 0]
\]

**Transition rule used in this notebook:**
1. Apply the chosen action (clipped to the grid boundaries)
2. Apply the wind based on the **current column** `y` (as implemented in the code)

---

## Rewards

A constant step reward is used:

- **Reward = −1 per step**, until the goal is reached  
  (This matches the standard formulation where shorter episodes are better.)

The episode terminates immediately when the agent reaches the goal tile.

---

## Method Implemented: ε-greedy SARSA (On-policy TD Control)

This notebook implements **SARSA** with:

- **ε = 0.1**
- **α = 0.5**
- **γ = 1.0**
- **Q(s,a) initialized to 0**

### SARSA Update Rule

$$
Q(s,a) \leftarrow Q(s,a) + \alpha \Big[r + \gamma Q(s',a') - Q(s,a)\Big]
$$

Where:
- \(a'\) is chosen using the same ε-greedy policy (on-policy learning)

---

## Training Setup (as in Example 6.5)

Training is run until a fixed total number of **time steps**:

- **Total time steps = 8000**
- We track how many **episodes** are completed over time

The notebook plots:

- **Episodes vs Time steps**  
  (An increasing slope means episodes are becoming shorter on average — i.e., learning is improving.)

---

## Outputs & Results

The notebook produces:

1. **Learning curve:** Episodes vs cumulative Time steps (up to 8000 steps)
2. **Greedy trajectory after learning (ε = 0):**
   - The learned greedy policy is rolled out from **START**
   - Tie-breaking between equally good actions is randomized but reproducible via a fixed seed

Printed results include:
- total episodes completed by 8000 steps
- wind configuration
- greedy path length and whether it reached the goal

---

## Implementation Details

- The task is **undiscounted** (γ = 1.0)
- Action selection uses **ε-greedy with random tie-breaking**
- The greedy evaluation uses **ε = 0** to visualize the learned policy’s behavior
- A maximum episode length is included as a safety limit (to prevent infinite loops in pathological cases)
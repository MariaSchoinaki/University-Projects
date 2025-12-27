# Blackjack with Monte Carlo Exploring Starts (MC-ES)

This notebook reproduces and analyzes the **Blackjack** control problem from *Sutton & Barto, Reinforcement Learning (2nd Edition)* using **Monte Carlo Control with Exploring Starts (MC-ES)**.  
The objective is to learn an **optimal player policy** (hit/stick strategy) that maximizes **expected return** *without knowing the environment dynamics in advance*.

---

## Problem Overview

Blackjack is modeled as an **episodic Markov Decision Process (MDP)**:

- The agent (player) chooses actions **Hit** or **Stick**
- The dealer follows a fixed rule-based policy
- Episodes end when the player busts, sticks and the dealer finishes, or a terminal outcome occurs

Goal: learn the **optimal policy** π\* by estimating action-values from sampled episodes.

---

## State Representation

Each state is a triplet:

$$
S = (\text{player sum},\ \text{dealer showing},\ \text{usable ace})
$$

Where:
- **player sum** ∈ {12, …, 21}  
- **dealer showing** ∈ {A, 2, …, 10}  
- **usable ace** ∈ {0, 1} (whether an ace can be counted as 11 without busting)

---

## Action Space

Two actions:
- **0: stick**
- **1: hit**

---

## Rewards

Terminal rewards: 
- **+1** if player wins  
- **0** for a draw  
- **−1** if player loses  

---

## Environment Dynamics

### Dealer Policy
The dealer draws until the hand value reaches at least **17**.

### Card Model
Cards are drawn from an **infinite deck**. Face cards (10, J, Q, K) count as **10** and occur with probability **4/13**.

---

## Method Implemented: Monte Carlo Control with Exploring Starts (MC-ES)

### 1. Exploring Starts
Each episode begins with:  
- a **random initial state**
- a **random initial action**

This guarantees every state–action pair can be visited (non-zero probability), enabling MC control.

---

### 2. ε-Greedy Behavior Policy
After the first (forced) exploring-start action, behavior follows an **ε-greedy** strategy w.r.t. current Q(s,a):
- with probability **ε**, choose a random action (explore)  
- with probability **1 − ε**, choose the greedy action (exploit)  

In this implementation: **ε = 0.1**.

---

### 3. First-Visit Monte Carlo Updates
For each episode:
- record visited (s,a) pairs
- update **only the first visit** of each (s,a) in the episode
- use an incremental mean update:

$$
Q(s,a) \leftarrow Q(s,a) + \frac{G - Q(s,a)}{N(s,a)}
$$

where **G** is the terminal return and **N(s,a)** is the visit count.

---

### 4. Policy Improvement
After updating Q, the policy is improved greedily:

$$
\pi(s) = \arg\max_a Q(s,a)
$$

The final value function is computed as:

$$
V^*(s) = \max_a Q(s,a)
$$

---

## Implementation Details

- Trained for **5,000,000 episodes** for stable convergence  
- **Automatic hit** for player sums **< 12** (sticking is suboptimal there)  
- Separate learned outputs are produced for:
  - **usable ace = 1**
  - **usable ace = 0**

---

## Outputs & Results Summary

The notebook produces: 
- Heatmaps of the learned **optimal policy π\*** (Hit vs Stick)
- 3D surface plots of the learned **optimal value function V\***

Key qualitative findings: 
- With a **usable ace**, the policy hits more aggressively at lower sums (safer due to ace flexibility)
- Without a usable ace, the policy is more conservative and tends to stick earlier
- Value surfaces show higher expected returns for strong player sums and weak dealer cards, with a clear advantage when holding a usable ace
# Jack’s Car Rental Problem

This notebook implements **Jack’s Car Rental Problem**, a classic example from *Sutton & Barto, Reinforcement Learning (2nd Edition)*.  
The objective is to use **Policy Iteration** to compute the *optimal nightly car-movement policy* for a two-location rental company.

---

## Problem Overview

Each day:

- Customers request cars at two locations (A and B).
- Cars are returned randomly at each location.
- Overnight, the manager may transfer up to **5 cars** between locations to better prepare for the next day.

The challenge is to determine how many cars to move each night to **maximize long-term revenue**.

---

## State Representation

A state is:

$$
S = (s_A, s_B)
$$

Where:

- $s_A$, $s_B$ = number of cars at locations A and B  
- Each lot has a **capacity of 20 cars**

---

## Action Space

An action $a$ is an integer in $[-5, 5]$:

- $a > 0$: move $a$ cars from A → B  
- $a < 0$: move $|a|$ cars from B → A  
- $a = 0$: no movement

---

## Rewards

- Renting a car: **+10**
- Moving a car: **–2**
- Discount factor: **$\gamma = 0.9$**

---

## Stochastic Demand Model

Demand and returns follow Poisson distributions:

| Event     | A (λ) | B (λ) |
|-----------|--------|--------|
| Requests  | 3      | 4      |
| Returns   | 3      | 2      |

---

## Methods Implemented

### 1. Precomputation of Poisson Probabilities

To avoid repeated expensive Poisson PMF calls, tables of:

- $P(N = k)$  
- $P(N \ge k)$

are precomputed and reused, greatly improving performance.

---

### 2. Transition & Reward Modeling

For each *post-move state*, the program evaluates all possible combinations of:

- rentals at A and B  
- returns at A and B  

From these, it computes:

1. Expected daily rental reward  
2. Transition probabilities for the next day’s state  

These values are cached to accelerate Bellman updates.

---

### 3. Q-Value Computation

For each state–action pair:

$$
Q(s,a) = R(s') - 2|a| + \gamma \mathbb{E}[V(s'')]
$$

Where:

- $R(s')$ is the expected rental reward after moving cars  
- Transition probabilities yield $\mathbb{E}[V(s'')]$

---

### 4. Policy Evaluation

Given a policy $\pi$, values are updated iteratively:

$$
V(s) \leftarrow Q(s, \pi(s))
$$

until convergence:

$$
\Delta < 10^{-4}
$$

---

### 5. Policy Improvement

For each state:

1. Evaluate all feasible actions  
2. Choose the best one:

$$
\pi_{\text{new}}(s) = \arg\max_a Q(s,a)
$$

Policy iteration stops when the policy no longer changes.

---

## Results Summary

### Optimal Policy

- Large central area recommends **no movement**.  
- When A has many more cars than B → move cars A → B.  
- When B has many more cars than A → move cars B → A.  
- The resulting contour plot has threshold-like diagonal boundaries consistent with the book’s optimal solution.

### Value Function

- Values rise as more cars are available overall.  
- Typical range: **420–630**.  
- Highest values occur when both locations start the day with many cars (allowing more rentals).
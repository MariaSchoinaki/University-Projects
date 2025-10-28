# k-Armed Bandit Problem

This repository contains a Jupyter Notebook that explores the **k-Armed Bandit Problem**, a fundamental concept in **reinforcement learning** and **decision-making under uncertainty**.  
It demonstrates how an agent learns to balance **exploration** and **exploitation** to maximize rewards.

---

## Overview

The **k-Armed Bandit Problem** is a simplified model of reinforcement learning where an agent must choose between `k` actions (or "arms") with unknown reward distributions.  
Over time, the agent aims to learn which actions yield the highest expected rewards.

This notebook walks through:
- Defining the multi-armed bandit environment  
- Implementing **ε-greedy**, **optimistic initialization**, and **UCB** algorithms  
- Comparing their performance through simulations and visualizations

---

## Key Concepts

- **Exploration vs. Exploitation**  
  Finding the balance between trying new actions and leveraging known good ones.

- **Action-Value Estimation**  
  Updating the estimated value of each arm based on received rewards.

- **Algorithms Covered**
  - ε-greedy strategy
  - Optimistic initial values
  - Upper Confidence Bound (UCB)
  - Gradient Bandit methods

---

## Dependencies

Make sure you have the following Python packages installed:

```bash
pip install numpy matplotlib

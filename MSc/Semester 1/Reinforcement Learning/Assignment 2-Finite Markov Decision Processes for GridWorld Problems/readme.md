# FMDP GridWorld

This notebook implements a **Finite Markov Decision Process (FMDP)** version of the classic **GridWorld** environment. It demonstrates how to model state transitions, rewards, and policies using factored representations, and provides tools for simulation, policy evaluation, and visualization.

## Features
- **Factored State Representation:**  
  Decomposes the environment into structured variables for more efficient modeling and computation.
- **Transition Model (T):**  
  Defines probabilistic outcomes of agent actions within the grid.
- **Reward Model (R):**  
  Supports deterministic and stochastic reward structures.
- **Policy Evaluation & Improvement:**  
  Tools for computing value functions and improving policies over iterations.
- **Simulation Tools:**  
  Run episodes in the GridWorld using custom or learned policies.

## Notebook Structure
1. **Environment Setup**  
   Defines grid layout, terminal states, actions, and dynamics.

2. **Factored MDP Components**  
   - State variables  
   - Transition function  
   - Reward function  

3. **Value Iteration / Policy Iteration**  
   Algorithms for computing optimal policies.

4. **Simulation**  
   Functions to test and visualize learned policies.

## How to Use
1. Adjust environment parameters (grid size, walls, rewards, etc.).  
2. Run policy evaluation or value iteration to compute a policy.  
3. Use the simulation cell to visualize the agent’s path.  
4. Modify factors to test alternative dynamics or reward structures.

## Requirements
This notebook typically relies on:
- Python 3.x  
- NumPy  

All required imports are included directly in the notebook.
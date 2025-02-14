# Multithreading Implementation of a Real Time Pizza Restaurant Ordering System

The Real Time Pizza Restaurant Ordering System is a multithreading executable C program that simulates a real time pizza restaurant ordering system.
This project in consisted of mutexes and threads accomplishing the aim of the ordering system simulation. Every mutex locks and unlocks each of our restaurant's resources and blocks the access to incoming threads,  giving the feeling of "waiting time". Every thread sleeps and wakes by a specific signal when the resource is available and the process is ready to be completed.

## Features
- Real time processing.
- Adaptable ordering system, based on the availability of the restaurant's resources.
- Prevents multiple threads from accessing the same shared resource simultaneously with the usage of the mutexes.
- Optimized thread structured.
- Simulation of the total restaurant stats.
- User-Friendly implementation that prints informative messages for each step of the process.

## How to Run
1. Clone the project.
2. Make sure you have installed a proper c/c++ compiler (Recommended operating system to run this program macOS / Linux).
3. Open your terminal.
4. Change the working directory to the cloned project's one.
5. Run.
   ```sh
   >>gcc PizzaRestaurant.c -o executable 
   >>./executable 10 100  
   ```
6. The simulation has started!

## Collaborators
- [Maria Schoinaki](https://github.com/MariaSchoinaki)
- [Pavlos Fasois](https://github.com/PavlosFas13)
- [Christos Stamoulos](https://github.com/ChristosStamoulos)

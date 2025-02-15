# **Pizza Restaurant Simulation - POSIX Threads Project**

## **Overview**
This project is a **multi-threaded simulation** of a **pizza restaurant** using **POSIX threads (pthreads)** in C. The program models the order processing system, including order placement, preparation, baking, packaging, and delivery, with proper **synchronization using mutexes and condition variables**.

---

## **Project Description**
The restaurant operates with limited resources and processes **customer orders concurrently**. Each order goes through the following steps:
1. **Order Placement:** Customers place orders online, specifying a random number of pizzas.
2. **Payment Processing:** Orders are approved or declined based on a random probability.
3. **Preparation:** Available pizza makers prepare the order.
4. **Baking:** The pizzas are placed in available ovens and baked for a fixed duration.
5. **Packaging:** Once baked, pizzas are packed by available packers.
6. **Delivery:** A delivery person picks up and delivers the order, returning afterward.

Each stage requires **proper resource management**, ensuring that:
- **Mutex locks** protect shared resources.
- **Condition variables** synchronize access to pizza makers, ovens, packers, and delivery personnel.
- Orders that **cannot proceed immediately wait** for resources to become available.

---

## **Implementation Details**
### **1. POSIX Threads for Concurrency**
- **Each customer order spawns a new thread.**
- Shared resources (pizza makers, ovens, packers, and delivery personnel) are managed using **mutexes and condition variables**.
- **Threads synchronize** using `pthread_cond_wait` and `pthread_cond_signal` to manage resource availability.

### **2. Order Processing Flow**
Each order proceeds as follows:
- **Order is placed** (random number of pizzas, random probability of payment failure).
- **Payment processing:**
  - If **failed**, the order is discarded.
  - If **successful**, the system proceeds to preparation.
- **Order goes through:**
  - **Pizza makers** (mutex-protected queue)
  - **Ovens** (mutex-protected queue)
  - **Packaging** (mutex-protected queue)
  - **Delivery** (mutex-protected queue, delivery personnel return after delivering the order)

### **3. Synchronization Mechanisms Used**
- **Mutexes (`pthread_mutex_t`)**
  - Protect access to shared resources.
- **Condition Variables (`pthread_cond_t`)**
  - Ensure efficient waiting for available resources.
- **Thread Management (`pthread_create` and `pthread_join`)**
  - Creates and joins order threads safely.

### **4. Randomized Order Generation**
- Each customer’s order:
  - **Number of pizzas:** Randomized between **1 and 5**.
  - **Payment failure probability:** **10%**.
  - **Order delay:** Customers arrive in **1 to 3 seconds** intervals.

---

## **Compilation & Execution**
### **Prerequisites**
- Linux environment (Ubuntu recommended)
- **GCC Compiler** with pthread support

### **Compiling the Program**
```sh
gcc -o PizzaRestaurant PizzaRestaurant.c -lpthread
```

### **Running the Simulation**
The program requires two command-line arguments:
```sh
./PizzaRestaurant <Number_of_Customers> <Random_Seed>
```
Example execution:
```sh
./PizzaRestaurant 100 1000
```

---

## **Project Structure**
```
├── PizzaRestaurant.c    # Main implementation
├── PizzaRestaurant.h    # Header file with definitions & mutexes
├── Project.pdf          # Project description
├── Report.pdf           # Explanation of implementation details
├── README.md            # Project documentation
```

---

## **Applications**
- **Concurrency & Parallel Processing:** Managing multiple customer orders concurrently.
- **Operating System Concepts:** Implementing synchronization, mutexes, and condition variables.
- **Real-world Simulations:** Simulating restaurant order management using efficient threading techniques.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)
- **Developer:** [Pavlos Fasois](https://github.com/PavlosFas13)
- **Developer:** [Christos Stamoulos](https://github.com/ChristosStamoulos)
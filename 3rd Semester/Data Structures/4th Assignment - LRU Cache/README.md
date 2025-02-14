# LRU-Cache
The LRU Cache implementation is a general-purpose cache implementation in Java language that applies the Least Recently Used (LRU) replacement policy. The cache uses a combination of a hash table and a double linked list to provide fast access to the data and adaptable solution to data caching with customizable cache size. Our report for this project can be found in [greek](https://github.com/MariaSchoinaki/LRU-Cache/blob/main/report_greek.pdf) and in english.

## Data Structures Implemented:
- Hash Table
- Double Linked List

## Features
- O(n) time complexity for cache operations-fast access
- Adaptable solution to data caching with customizable cache size
- Functional control of the data using the LRU(Least Recently Used) policy

## Results

```
dataset: dataset-1000/data-1000.dat
requests: dataset-1000/requests-10000.dat
cache size: 100 items (10% of all items)
output:
---------------------------------------------------
Read 10000 items in 1523 ms
Stats: lookups 10000, hits 1030, hit-ratio 0.103000
  
***************************************************

dataset: dataset-5000/data-5000.dat
requests: dataset-5000/requests-100000.dat
cache size: 500 items (10% of all items)
output:
---------------------------------------------------
Read 100000 items in 39574 ms
Stats: lookups 100000, hits 10074, hit-ratio 0.100740

```

## How to Run
1. Clone the project.
2. Open your terminal.
3. Change the working directory in to the cloned project's one
4. Run
5. ```sh
   >>javac *.java
   >>java TestCacheSpeed
   ```


## Collaborators
[ChristosStamoulos](https://github.com/ChristosStamoulos)

[MariaSchoinaki](https://github.com/MariaSchoinaki)

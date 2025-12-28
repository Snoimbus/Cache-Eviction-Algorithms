# Cache-Eviction-Algorithms
A cache eviction simulator implementing LRU, FIFO, and a custom Greedy eviction algorithm. 

This project explores and works with different algorithms used for the basis of cache eviction. The cache tracks frequency, recency, and insertion time, as well as shows metrics like hit rate, miss rate, and eviction rate to evaluate the algorithm's behavior.

## Program Architecture

The cache is designed around an eviction policy interface. Each algorithm implements a common "EvictionPolicy" interface, allowing eviction behavior to be swapped without modifying the logic.

Algorithms implemented:
FIFO: First in, First out
LRU: Least Recently Used
Greedy: Hybrid that adapts depending on hit rate

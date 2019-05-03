# Lab 4
Goals: Simulate demand paging and see how the number of page faults depends on page size, program size, replacement algo, and job mix.
We simulate a driver that will read all the inputs and will simulate N memory refs per program
The driver uses
round robin scheduling with quantum q=3 (i.e., 3 references for process 1, then 3 for process 2, etc.).

# Commands
To run: javac Run.javac -> java Run M P S J N R D-> EG: java run 10 10 20 1 10 lru 0

The program is invoked with 6 command line arguments
``` 6 positive integers and one string
M, the machine size in words.
P, the page size in words.
S, the size of each process, i.e., the references are to virtual addresses 0..S-1.
J, the ‘‘job mix’’, which determines A, B, and C, as described below.
N, the number of references for each process.
R, the replacement algorithm, FIFO, RANDOM, or LRU.
D, the debugging level
```

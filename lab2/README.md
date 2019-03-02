## Lab 2
A simulation lab in which we see how the time required depends on scheduling algorithm. and request patterns

## Running the program
* Reads the input from a file given in command line
* Send outputs by printing them
* Accept a verbose flag which if present precede the file name. This will produce a detailed output
* program-name input-filename
* program-name --verbose input-filename
### Background
A process id characterized by four non negative integers A , B, C, IO.
A process execution consists of computation alternating with I0, These will be referred as CPU bursts and IO bursts

### Calculations
* To calculate CPU burst times we make the assumption that for each process the CPU burst times are uniformly distributed among random integers. UDRI's for short. For the interval (0,B]
* To obtain a UDRI in some interval (o, U] we use the random function described below.
* The next CPU burst is random os. If val > total cpu time remanning -> set the next CPY burst to the remanning time.
* We assume that the I/O times are UDRI in the interval (o,IO] and hence the next I/O burst is Random os I/O.

### Instructions
Read a file describing N processes. (IE n quadruples of numbers) and then simulate the n processes until they all terminate.
Keep track of the state of each process (ready, running, block) and advance time. Make any state transitions as needed. At the end of the run print and identification of the tun including the scheduling algorithm used and any param (eg the quantum for RR ) and the number of processes simulated.

### Print the following for each processes
* A,B,C and IO
* End time
* Turn around time [end time - A]
* I/O time [time in blocked state]
* Waiting time in ready state
* All finish time
* CPU utilization
* I/O utilization
* Throughput [expressed in processes completed per hundred time units]
* Average turn around time
* Average waiting times

### Algorithms to simulate
* Assume that a context switch takes 0 time
* Do calculations every time unit
* FCFS
* RR with quantum 2
* Uni programmed just one process active and when it is blocked the system waits
* SJF NOT preemptive and NOT uni-programmed, we do switch on IO bursts
* Note on SJF: It is the shortest job first but not shortest burst. So the time you use to determine priority is the total time remaining (the input value c minus the number of cycles this process has to run)

### Mixes
* For each scheduling algorithm there are several runs with different process mixes, a mix is a value of n followed n var quadruples

### RandomOS(U)
Reads a random non negative integer X from a file named "random-numbers" in the current directory and returns the value 1 + (X mod U)

### Breaking ties
TBD

    public class Process {
        static int arrivalTime;
        static int cpuTimeNeeded;
        static int cpuBurstTime;
        static int ioBurstTime;
        static int processNum;

        static String state = "Unstarted";

        static int runTime = 0;
        static int waitTime = 0;
        static int ioTime = 0;
        static int total = 0;

        static int currCPUBurst = -1;
        static int currIOBurst = -1;

        public Process(int NUM, int A, int B, int C, int IO) {
          //1  0 1 5 1  about as easy as possible
          //Process Num, Arrival time, CPU Burst time, CPU time needed, io Burst Time
          // 1st process, 0, 1, 5, 1
            this.arrivalTime = A;
            this.cpuTimeNeeded = C;
            this.cpuBurstTime = B;
            this.ioBurstTime = IO;
            this.processNum = NUM;
        }

        public void setState(String newState) {
            this.state = newState;
        }

        public String getState() {
            return this.state;
        }

        public int getArrTime() {
            return this.arrivalTime;
        }

        public int getProcessNum() {
            return this.processNum;
        }

        public int getCPUBurstTime() {
            return this.cpuBurstTime;
        }

        public int getIOBurstTime() {
            return this.ioBurstTime;
        }

        public void setio(int x) {
             this.ioTime = x;
        }
        public int getTimeNeeded() {
            return this.cpuTimeNeeded;
        }

        public void passCycle() {
            if (this.state != "Completed") {
                this.total++;

                if (this.state == "Ready") {
                    this.waitTime++;
                } else if (this.state == "Blocked") {
                    this.ioTime++;
                }
            }
        }

        public void setCPUBurst(int x) {
            this.currCPUBurst = x;
        }

        public void setIOBurst(int x) {
            this.currIOBurst = x;
        }

        public int getCurrCPUBurst() {
            return this.currCPUBurst;
        }

        public int getCurrIOBurst() {
            return this.currIOBurst;
        }

        public int getRunTime() {
            return this.runTime;
        }

        public int getWaitTime() {
            return this.waitTime;
        }

        public boolean doneRunning() {
            currCPUBurst--;
            runTime++;

            if (runTime == cpuTimeNeeded || currCPUBurst == 0) {
                currCPUBurst = -1;
                return true;
            }

            return false;
        }

        public boolean doneBlocked() {
            currIOBurst--;

            if (currIOBurst == 0) {
                currIOBurst = -1;
                return true;
            }

            return false;
        }

        public int getTotal() {
            return this.total;
        }
        public void setTotal(int x) {
          this.total = x;
        }

        public String toString() {
            return this.arrivalTime + " " + this.cpuBurstTime + " " + this.cpuTimeNeeded + " " + this.ioBurstTime + " ";
        }

        public void printall() {
            System.out.println("Process " + this.processNum + ":");
            System.out.println("(A,B,C,IO) = (" + this.arrivalTime + ", " +
                this.cpuBurstTime + ", " + this.cpuTimeNeeded + ", " + this.ioBurstTime + ")");
            System.out.println("Finishing Time: " + this.total);
            System.out.println("Turnaround Time: " + (this.total - this.arrivalTime));
            System.out.println("I/O Time: " + this.ioTime);
            System.out.println("Waiting Time: " + this.waitTime + "\n");
        }
    }

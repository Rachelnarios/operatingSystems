    public class Process {
        private int arrivalTime;
        private int cpuTimeNeeded;
        private int cpuBurstTime;
        private int ioBurstTime;
        private int processNum;

        private String state = "Unstarted";

        private int runTime = 0;
        private int waitTime = 0;
        private int ioTime = 0;
        private int total = 0;

        private int currCPUBurst = -1;
        private int currIOBurst = -1;

        public Process(int NUM, int A, int B, int C, int IO) {
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

        public String toString() {
            return this.arrivalTime + " " + this.cpuBurstTime + " " + this.cpuTimeNeeded + " " + this.ioBurstTime + " ";
        }

        public void printStats() {
            System.out.println("Process " + this.processNum + ":");
            System.out.println("\t(A,B,C,IO) = (" + this.arrivalTime + ", " +
                this.cpuBurstTime + ", " + this.cpuTimeNeeded + ", " + this.ioBurstTime + ")");
            System.out.println("\tFinishing Time: " + this.total);
            System.out.println("\tTurnaround Time: " + (this.total - this.arrivalTime));
            System.out.println("\tI/O Time: " + this.ioTime);
            System.out.println("\tWaiting Time: " + this.waitTime + "\n");
        }
    }

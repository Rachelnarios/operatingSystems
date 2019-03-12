public class Pro {
        private int arrivedAt;
        private int cpuT;
        private int cpuB;
        private int ioBurstTime;
        private int globalNum;

        private String state = "Unstarted";

        private int runTime = 0;
        private int waitTime = 0;
        private int ioTime = 0;
        private int total = 0;

        private int currCPUBurst = -1;
        private int currIOBurst = -1;

        public Pro(int NUM, int A, int B, int C, int IO) {
            this.arrivedAt = A;
            this.cpuT = C;
            this.cpuB = B;
            this.ioBurstTime = IO;
            this.globalNum = NUM;
        }

        public void setState(String newState) {
            this.state = newState;
        }

        public String getState() {
            return this.state;
        }

        public int getArrTime() {
            return this.arrivedAt;
        }

        public int getProNum() {
            return this.globalNum;
        }

        public int getcpuB() {
            return this.cpuB;
        }

        public int getIOBurstTime() {
            return this.ioBurstTime;
        }

        public int getTimeNeeded() {
            return this.cpuT;
        }

        public void status() {
            if (this.state != "Completed") {
                this.total++;

                if (this.state == "Ready") {
                    this.waitTime++;
                } else if (this.state == "Blocked") {
                    this.ioTime++;
                }
            }
        }

        public void setCPUB(int x) {
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

            if (runTime == cpuT || currCPUBurst == 0) {
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

        public String INFO() {
            return this.arrivedAt + " " + this.cpuB + " " + this.cpuT + " " + this.ioBurstTime + " ";
        }

        public void printall() {
            System.out.println("Pro " + this.globalNum + ":");
            System.out.println("(A,B,C,IO) = (" + this.arrivedAt + ", " +
                this.cpuB + ", " + this.cpuT + ", " + this.ioBurstTime + ")");
            System.out.println("Finishing T: " + this.total);
            System.out.println("Turnaround T: " + (this.total - this.arrivedAt));
            System.out.println("I/O T: " + this.ioTime);
            System.out.println("Waiting T: " + this.waitTime + "\n");
        }
        public void printmore(int finishTime, float CCused, float ioUsed, float ATT, float AWT, float thu){
                    System.out.println("-Finishing Time: " + finishTime);
                    System.out.println("-CPU Utilization: " + CCused);
                    System.out.println("-I/O Utilization: " + ioUsed);
                    System.out.println("-Average Turnaround Time: " + ATT);
                    System.out.println("-Average Waiting Time: " + AWT);
                    System.out.println("-Throughput: " + thu + " PP 100 cycles");
        }
    }

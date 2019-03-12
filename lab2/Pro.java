public class Pro {
        private int arrivedAt;
        private int cpuT;
        private int cpuB;
        private int ioBurstTime;
        private int globalNum;

        private String state = "3";

        private int runTime = 0;
        private int waitTime = 0;
        private int ioTime = 0;
        private int totz = 0;
        private int currCPUBurst = -2;
        private int currIOBurst = -2;

        public Pro(int NUM, int A, int B, int C, int IO) {
          this.globalNum = NUM;
            this.arrivedAt = A;
            this.cpuB = B;
            this.cpuT = C;
            this.ioBurstTime = IO;
        }

        public void status() {
            if (this.state != "2") {
                this.totz++;

                if (this.state == "1") {
                    this.waitTime++;
                } else if (this.state == "-2") {
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

        public int currentCPUB() {
            return this.currCPUBurst;
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

                public int Tneed() {
                    return this.cpuT;
                }


        public int getCurrIOBurst() {
            return this.currIOBurst;
        }

        public int TRun() {
            return this.runTime;
        }

        public int getWaitTime() {
            return this.waitTime;
        }

        public boolean runDone() {
            currCPUBurst--;
            runTime++;

            if (runTime == cpuT) {
                currCPUBurst = -2;
                return true;
            }
            else if (currCPUBurst == 0) {
                currCPUBurst = -2;
                return true;
            }

            return false;
        }

        public boolean noMoBlox() {
            currIOBurst--;
            if (currIOBurst == 0) {
                currIOBurst = -2;
                return true;
            }

            return false;
        }

        public int totz() {
            return this.totz;
        }

        public String INFO() {
            return this.arrivedAt + " " + this.cpuB + " " + this.cpuT + " " + this.ioBurstTime + " ";
        }

        public void printall() {
            System.out.println("Process " + this.globalNum );
            System.out.println("A,B,C,IO" + this.arrivedAt + ", " +
                this.cpuB + ", " + this.cpuT + ", " + this.ioBurstTime );
            System.out.println("Finishing T: " + this.totz);
            System.out.println("Turnaround T: " + (this.totz - this.arrivedAt));
            System.out.println("I/O T: " + this.ioTime);
            System.out.println("Waiting T: " + this.waitTime + "\n");
        }

    }

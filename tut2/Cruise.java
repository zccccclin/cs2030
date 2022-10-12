class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoaders;
    private final int serviceTime;
    private static final int hourInMin = 60;

    Cruise(String id, int aT, int nL, int sT) {
        this.identifier = id;
        this.arrivalTime = aT;
        this.numOfLoaders = nL;
        this.serviceTime = sT;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    public int getArrivalTime() {
        return this.hourMinToMin(this.arrivalTime);
    }
    
    public int getNumOfLoadersRequired() {
        return this.numOfLoaders;
    }
    
    public int hourMinToMin(double hourMin) {
        int hours = (int) hourMin / 100;
        int mins = (int) hourMin % 100;
        return hours * hourInMin + mins;
    }

    public int minToHourMin(int min) {
        int hours = min / hourInMin;
        int mins = min % hourInMin;
        return hours * 100 + mins;
    }

    @Override
    public String toString() {
        return this.identifier + "@" + String.format("%04d",this.arrivalTime);
    }    
}

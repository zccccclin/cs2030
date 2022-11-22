class Cruise {
    private final String id;
    private final int aT;
    private final int nL;
    private final int sT;
    private static final TimeConverter converter = new TimeConverter();

    Cruise(String id, int aT, int nL, int sT) {
        this.id = id;
        this.aT = aT;
        this.nL = nL;
        this.sT = sT;
    }
    
    public String getId() {
        return this.id;
    }
    
    public int getServiceTime() {
        return this.sT;
    }

    public int getArrivalTime() {
        return converter.hourToMins(this.aT);
    }

    public int getNumOfLoadersRequired() {
        return nL;
    }

    public Cruise serveOne() {
        return new Cruise(this.id, this.aT, this.nL - 1, this.sT);
    }

    @Override
    public String toString() {
        return this.id + "@" + String.format("%04d", this.aT);
    }
}

class JustRide extends Service {
    private static final int rate = 22;
    private static final int surcharge = 500;
   
    JustRide() {
        super();
    }
    
    @Override
    public int computeFare(int distance, int numOfPass, int tos) {
        if (super.isPeakHourRide(tos)) {
            return this.surcharge + distance * this.rate;
        }
        return distance * this.rate;
    }

    @Override
    public String toString() {
        return "JustRide";
    }
}

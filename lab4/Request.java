class Request {
    private final int distance;
    private final int numOfPass;
    private final int time;
    private final int timeInMins;
    private static final TimeConverter converter = new TimeConverter();

    Request(int d, int n, int t) {
        this.distance = d;
        this.numOfPass = n;
        this.time = t;
        this.timeInMins = this.converter.hourToMin(t);
    }
    
    public int computeFare(Service service) {
        return service.computeFare(this.distance, this.numOfPass, this.time);
    }

    public Service computeCheapest(Driver driver) {
        int cheapestFare = Integer.MAX_VALUE;
        Service cheapest = driver.getServices().get(0);
        for (Service s : driver.getServices()) {
            if (this.computeFare(s) < cheapestFare) {
                cheapestFare = this.computeFare(s);
                cheapest = s;
            }
        }
        return cheapest;
    }

    @Override
    public String toString() {
        return this.distance + "km for " + this.numOfPass + "pax @ " + this.time + "hrs";
    }
}

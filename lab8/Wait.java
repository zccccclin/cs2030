class Wait implements Event {
    private final Customer customer;
    private final double time;
    private final int serverIdx;

    Wait(Customer customer, int serverIdx) {
        this.customer = customer;
        this.time = customer.getArrivalTime();
        this.serverIdx = serverIdx;
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        Server server = servers.get(this.serverIdx);
        double timeOfService = server.getFinishTime();
        return new Pair<Event, ImList<Server>>(
            new Serve(this.customer, this.serverIdx, timeOfService, false), servers); 
    }

    public double getWaitTime() {
        return 0.0;
    }

    public int getOrder() {
        return this.customer.getId();
    }

    public double getTime() {
        return this.time;
    }

    public String getEvent() {
        return "WAIT";
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time) + " " + this.customer.getId() + 
            " waits at " + (this.serverIdx + 1);
    }
}

class Leave implements Event {
    private final Customer customer;
    private final double time;

    Leave(Customer customer) {
        this.customer = customer;
        this.time = customer.getArrivalTime();
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        return new Pair<Event, ImList<Server>>(new Exit(), servers);
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
        return "LEAVE";
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time) + " " + this.customer.getId() + " leaves";
    }
}

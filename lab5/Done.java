class Done implements Event {
    private final Customer customer;
    private final int serverIdx;
    private final double time;

    Done(Customer customer, int serverIdx) {
        this.customer = customer;
        this.serverIdx = serverIdx;
        this.time = customer.getTos() + customer.getServiceTime();
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        Server s = servers.get(this.serverIdx);
        s = s.popCustomer();
        servers = servers.set(this.serverIdx, s);
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
        return "DONE";
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time) + " " + this.customer.getId() + 
            " done serving by " + (this.serverIdx + 1);
    }
}

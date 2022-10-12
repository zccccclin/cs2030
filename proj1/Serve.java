class Serve implements Event {
    private final Customer customer;
    private final int serverIdx;
    private final double time;
    
    Serve(Customer customer, int serverIdx) {
        this.customer = customer;
        this.serverIdx = serverIdx;
        this.time = customer.getTos();
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        return new Pair<Event, ImList<Server>>(new Done(this.customer, this.serverIdx), servers);
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
        return "SERVE";
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.time) + " " + this.customer.getId() + 
            " serves by " + (this.serverIdx + 1);
    }
}


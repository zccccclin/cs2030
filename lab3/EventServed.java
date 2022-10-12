class EventServed implements Event {
    private final Customer customer;
    private final Server server;
    private final double time;
    private static final int comparatorIdx = 1;


    EventServed(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
        this.time = customer.getArrivalTime();
    }

    public Event execute(Server server) {
        return new EventDone(this.customer, server);
    }

    public Pair<String, Integer> getEvent() {
        return new Pair<String, Integer>("Served", comparatorIdx);
    }

    public double getTime() {
        return this.time;
    }

    public int compareTo(Event event) {
        return (int) (this.time - event.getTime());
    }

    public int getCustomerNumber() {
        return this.customer.getCustomerNumber();
    }
    
    @Override
    public String toString() {
        return String.format("%.1f ", this.time) + 
        this.customer.toString() + " served by " + this.server.toString();
    }
}

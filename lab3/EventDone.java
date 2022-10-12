class EventDone implements Event {
    private final Customer customer;
    private final Server server;
    private final double time;
    private static final int comparatorIdx = 2;

    
    EventDone(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
        this.time = customer.getFinishTIme();
    }

    public Event execute(Server server) {
        return new EventDone(this.customer, server);
    }

    public Pair<String, Integer> getEvent() {
        return new Pair<String, Integer>("Done", comparatorIdx);
    }

    public double getTime() {
        return this.time;
    }

    public int getCustomerNumber() {
        return this.customer.getCustomerNumber();
    }
    
    @Override
    public String toString() {
        return String.format("%.1f ", this.time) + 
        this.customer.toString() + " done serving by " + this.server.toString();
    }
}

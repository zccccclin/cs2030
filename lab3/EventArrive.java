class EventArrive implements Event {
    private final Customer customer;
    private final double time;
    private static final int comparatorIdx = 0;


    EventArrive(Customer customer) {
        this.customer = customer;
        this.time = customer.getArrivalTime();
        
    }

    public Event execute(Server server) {
        if (!server.isOccupied()) {
            return new EventServed(this.customer, server);
        } else {
            return new EventLeave(this.customer);
        }
    }

    public Pair<String, Integer> getEvent() {
        return new Pair<String, Integer>("Arrived", comparatorIdx);
    }

    public double getTime() {
        return this.time;
    }

    public int getCustomerNumber() {
        return this.customer.getCustomerNumber();
    }

    @Override
    public String toString() {
        return String.format("%.1f ", this.time) + this.customer.toString() + " arrives";
    }
    
}

class Arrive implements Event {
    private final Customer customer;
    private final Server server;

    Arrive(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Event execute() {
        if (this.server.canServe(this.customer)) {
            return new Served(this.customer, this.server);
        } else {
            return new Leave(this.customer);
        }
    }

    @Override
    public String toString() {
        return this.customer.toString() + " arrives";
    }
    
}

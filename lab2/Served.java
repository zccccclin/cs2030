class Served implements Event {
    private final Customer customer;
    private final Server server;

    Served(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Event execute() {
        return new Served(this.customer, this.server);
    }

    @Override
    public String toString() {
        return this.customer.toString() + " served by " + this.server.toString();
    }
}

class Server {
    private final String name;
    private final Customer currCustomer;

    Server(String name) {
        this(name, new Customer(0.0, 0.0, 0));
    }

    Server(String name, Customer currCustomer) {
        this.name = name;
        this.currCustomer = currCustomer;
    }

    public boolean canServe(Customer customer) {
        return this.currCustomer.isAvail(customer);
    }

    public Server answer(Customer customer) {
        if (this.currCustomer.isAvail(customer)) {
            return new Server(this.name, customer);
        } else {
            return new Server(this.name, this.currCustomer);
        }
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}

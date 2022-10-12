class Server {
    private final int id;
    private final ImList<Customer> customers;
    private final int maxQ;
    private final double tos;
    private final double finishTime;

    Server(int id, int maxQ) {
        this.id = id;
        this.maxQ = maxQ;
        this.customers = new ImList<Customer>();
        this.tos = 0.0;
        this.finishTime = 0.0;
    }

    Server(int id, int maxQ, ImList<Customer> customers, double tos, double finishTime) {
        this.id = id;
        this.maxQ = maxQ;
        this.customers = customers;
        this.tos = tos;
        this.finishTime = finishTime;
    }

    public Server addCustomer(Customer customer) {
        ImList<Customer> customers = this.customers;
        customers = customers.add(customer);
        double finishTime = customer.getTos() + customer.getServiceTime();
        return new Server(this.id, this.maxQ, customers, tos, finishTime);
    }
    
    public Server popCustomer() {
        ImList<Customer> customers = this.customers;
        customers = customers.remove(0);
        return new Server(this.id, this.maxQ, customers, this.tos, this.finishTime);
    }

    public double getTos(Customer c) {
        if (this.customers.isEmpty()) {
            return this.finishTime;
        }
        return this.customers.get(customers.indexOf(c)).getTos();
    }

    public double getFinishTime() {
        return this.finishTime;
    }

    public boolean canQueue() {
        return this.customers.size() <= this.maxQ;
    }

    public boolean canServe() {
        return this.customers.isEmpty();
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Server " + this.getId() + ": " + this.customers.toString();
    }
}

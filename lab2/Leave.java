class Leave implements Event {
    private final Customer customer;
    
    Leave(Customer customer) {
        this.customer = customer;
    }

    public Event execute() {
        return new Leave(this.customer);
    }

    @Override
    public String toString() {
        return this.customer.toString() + " leaves";
    }
}


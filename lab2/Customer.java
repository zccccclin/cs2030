class Customer {
    private final double aT;
    private final double sT;
    private final int customerNumber;
    
    Customer(double aT, double sT, int customerNumber) {
        this.aT = aT;
        this.sT = sT;
        this.customerNumber = customerNumber;
    }

    Customer() {
        this(0.0, 0.0, 0);
    }

    public boolean isAvail(Customer nextCustomer) {
        return (nextCustomer.aT >= (this.aT + this.sT));
    }

    @Override
    public String toString() {
        return "customer " + this.customerNumber;
    }
}

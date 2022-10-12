class Customer {
    private final double aT;
    private final double fT;
    private final int customerNumber;
    
    Customer(double aT, double sT, int customerNumber) {
        this.aT = aT;
        this.fT = aT + sT;
        this.customerNumber = customerNumber;
    }

    public double getArrivalTime() {
        return this.aT;
    }

    public double getFinishTIme() {
        return this.fT;
    }

    public int getCustomerNumber() {
        return this.customerNumber;
    }

    @Override
    public String toString() {
        return "customer " + this.customerNumber;
    }
}

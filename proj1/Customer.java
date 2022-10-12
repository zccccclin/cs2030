class Customer {
    private final Pair<Double, Double> data;
    private final int id;
    private final double tos;
    
    Customer(Pair<Double, Double> inputTime, int id) {
        this.data = inputTime;
        this.tos = 0.0;
        this.id = id;
    }

    Customer(Pair<Double, Double> inputTime, int id, double tos) {
        this.data = inputTime;
        this.tos = tos;
        this.id = id;
    }
   
    public Double getArrivalTime() {
        return this.data.first();
    }

    public Double getServiceTime() {
        return this.data.second();
    }

    public Double getTos() {
        return this.tos;
    }
    
    public Customer nextInLine(double time) {
        double newTime = time;
        if (this.getArrivalTime() > time) {
            newTime = this.getArrivalTime();
        }
        return new Customer(this.data, this.id, newTime);
    }

    public int getId() {
        return this.id;
    }
    
    @Override
    public String toString() {
        return "Customer " + this.getId();
    }
}
        

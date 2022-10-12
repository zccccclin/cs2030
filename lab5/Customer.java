import java.util.function.Supplier;

class Customer {
    private final Pair<Double, Supplier<Double>> data;
    private final int id;
    private final double tos;
    private final double sT;
    
    Customer(Pair<Double, Supplier<Double>> inputTime, int id) {
        this.data = inputTime;
        this.tos = 0.0;
        this.id = id;
        this.sT = 0;
    }

    Customer(Pair<Double, Supplier<Double>> inputTime, int id, double tos, double sT) {
        this.data = inputTime;
        this.tos = tos;
        this.id = id;
        this.sT = sT;
    }
   
    public Double getArrivalTime() {
        return this.data.first();
    }

    public Double getServiceTime() {
        return this.sT;
    }

    public Double getTos() {
        return this.tos;
    }
    
    public Customer nextInLine(double time) {
        double newTime = time;
        if (this.getArrivalTime() > time) {
            newTime = this.getArrivalTime();
        }
        double sT = this.data.second().get();
        return new Customer(this.data, this.id, newTime, sT);
    }

    public int getId() {
        return this.id;
    }
    
    @Override
    public String toString() {
        return "Customer " + this.getId();
    }
}
        

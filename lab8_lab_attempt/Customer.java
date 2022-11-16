import java.util.function.Supplier;

class Customer {
    private final Pair<Double, Supplier<Double>> data;
    private final int id;
    
    Customer(Pair<Double, Supplier<Double>> inputTime, int id) {
        this.data = inputTime;
        this.id = id;
    }

    public Double getArrivalTime() {
        return data.first();
    }

    public Double getServiceTime() {
        return data.second().get();
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Customer " + getId();
    }
}
        

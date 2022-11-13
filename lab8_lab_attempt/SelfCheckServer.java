import java.util.function.Supplier;

class SelfCheckServer extends Server {
    SelfCheckServer(int id, int maxQ) {
        super(id, maxQ, () -> 0.0);
    }

    SelfCheckServer(SelfCheckServer server, ImList<Customer> nowServing, ImList<Customer> queue, double finishTime) {
        super(server, nowServing, queue, finishTime);
    }

    public SelfCheckServer setFinishTime(double time) {
        return new SelfCheckServer(this, this.nowServing, this.queue, time);
    }

    public SelfCheckServer addCustomer(Customer customer) {
        ImList<Customer> queue = this.queue;
        queue = queue.add(customer);
        return new SelfCheckServer(this, this.nowServing, queue, this.finishTime);
    }
    
    public SelfCheckServer popCustomer() {
        ImList<Customer> nowServing = this.nowServing;
        nowServing = nowServing.remove(0);
        return new SelfCheckServer(this, nowServing, this.queue, this.finishTime);
    }

    public Pair<Server, Double> serveCustomer(double timeOfService) {
        Customer nowServing = this.queue.get(0);
        ImList<Customer> updatedQueue = this.queue;
        updatedQueue = updatedQueue.remove(0);
        ImList<Customer> updatedNowServing = this.nowServing.add(nowServing);
        double finishTime = timeOfService + nowServing.getServiceTime();
        return new Pair<Server, Double>(
            new SelfCheckServer(this, updatedNowServing, updatedQueue, finishTime), finishTime);
    }

    public SelfCheckServer rest() {
        double restTime = this.restTimes.get();
        return new SelfCheckServer(this, this.nowServing, this.queue, this.finishTime + restTime);
    }

    public double getFinishTime() {
        return this.finishTime;
    }

    public boolean nextInLine(Customer customer) {
        return this.queue.get(0).getId() == customer.getId();
    }
    
    public boolean canQueue() {
        return this.queue.size() < this.maxQ;
    }

    public boolean canServe() {
        return this.nowServing.isEmpty();
    }
    
    public String getIdString() {
        return "self-check " + this.getId();
    }

    @Override
    public boolean isSelfCheck() {
        return true;
    }

    @Override
    public String toString() {
        return "Server " + this.getId() + ": " + this.queue.toString();
    }
}

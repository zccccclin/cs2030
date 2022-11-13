import java.util.function.Supplier;

class Server {
    private final int id;
    private final ImList<Customer> queue;
    private final ImList<Customer> nowServing;
    private final int maxQ;
    private final double finishTime;
    private final Supplier<Double> restTimes;

    Server(int id, int maxQ, Supplier<Double> restTimes) {
        this.id = id;
        this.maxQ = maxQ;
        this.queue = new ImList<Customer>();
        this.nowServing = new ImList<Customer>();
        this.finishTime = 0.0;
        this.restTimes = restTimes;
    }

    Server(Server server, ImList<Customer> nowServing, ImList<Customer> queue, double finishTime) {
        this.id = server.id;
        this.maxQ = server.maxQ;
        this.nowServing = nowServing;
        this.queue = queue;
        this.finishTime = finishTime;
        this.restTimes = server.restTimes;
    }

    public Server setFinishTime(double time) {
        return new Server(this, this.nowServing, this.queue, time);
    }

    public Server addCustomer(Customer customer) {
        ImList<Customer> queue = this.queue;
        queue = queue.add(customer);
        return new Server(this, this.nowServing, queue, this.finishTime);
    }
    
    public Server popCustomer() {
        ImList<Customer> nowServing = this.nowServing;
        nowServing = nowServing.remove(0);
        return new Server(this, nowServing, this.queue, this.finishTime);
    }

    public Pair<Server, Double> serveCustomer(double timeOfService) {
        Customer nowServing = this.queue.get(0);
        ImList<Customer> updatedQueue = this.queue;
        updatedQueue = updatedQueue.remove(0);
        ImList<Customer> updatedNowServing = this.nowServing.add(nowServing);
        double finishTime = timeOfService + nowServing.getServiceTime();
        return new Pair<Server, Double>(
            new Server(this, updatedNowServing, updatedQueue, finishTime), finishTime);
    }

    public Server rest() {
        double restTime = this.restTimes.get();
        return new Server(this, this.nowServing, this.queue, this.finishTime + restTime);
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

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Server " + this.getId() + ": " + this.queue.toString();
    }
}

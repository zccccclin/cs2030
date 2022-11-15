import java.util.function.Supplier;

class Server {
    private final int id;
    protected final ImList<Customer> queue;
    protected final ImList<Customer> nowServing;
    protected final int maxQ;
    protected final double finishTime;
    protected final Supplier<Double> restTimes;

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

    public Server addToQueue(Customer customer) {
        ImList<Customer> queue = this.queue;
        queue = queue.add(customer);
        return new Server(this, this.nowServing, queue, this.finishTime);
    }

    public Server addNowServing(Customer customer) {
        ImList<Customer> nowServing = this.nowServing;
        nowServing = nowServing.add(customer);
        return new Server(this, nowServing, queue, this.finishTime);
    }

    public Server popQueue() {
        ImList<Customer> queue = this.queue;
        queue = queue.remove(0);
        return new Server(this, nowServing, queue, this.finishTime);
    }
    
    public Server popCustomer() {
        ImList<Customer> nowServing = this.nowServing;
        nowServing = nowServing.remove(0);
        return new Server(this, nowServing, this.queue, this.finishTime);
    }

    public Pair<Server, Double> serveCustomer(double timeOfService) {
        ImList<Customer> updatedNowServing = this.nowServing;
        ImList<Customer> updatedQueue = this.queue;
        if (this.canServe()) {
            Customer nowServing = this.queue.get(0);
            updatedQueue = updatedQueue.remove(0);
            updatedNowServing = updatedNowServing.add(nowServing);
        }
        double finishTime = timeOfService + updatedNowServing.get(0).getServiceTime();
        return new Pair<Server, Double>(
            new Server(this, updatedNowServing, updatedQueue, finishTime), finishTime);
    }

    public Server updateQueue(ImList<Customer> q) {
        return new Server(this, this.nowServing, q, this.finishTime);
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
    
    public String getIdString() {
        return ((Integer) this.id).toString();
    }

    public int getId() {
        return this.id;
    }

    public boolean isSelfCheck() {
        return false;
    }

    @Override
    public String toString() {
        return "Server " + this.getId() + ": " + this.queue.toString();
    }
}

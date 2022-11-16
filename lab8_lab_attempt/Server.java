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
        return new Server(this, nowServing, queue, time);
    }

    public Server addToQueue(Customer customer) {
        ImList<Customer> queue = this.queue;
        queue = queue.add(customer);
        return new Server(this, nowServing, queue, finishTime);
    }

    public Server addNowServing(Customer customer) {
        ImList<Customer> nowServing = this.nowServing;
        nowServing = nowServing.add(customer);
        return new Server(this, nowServing, queue, finishTime);
    }

    public Server popQueue() {
        ImList<Customer> queue = this.queue;
        queue = queue.remove(0);
        return new Server(this, nowServing, queue, finishTime);
    }
    
    public Server popCustomer() {
        ImList<Customer> nowServing = this.nowServing;
        nowServing = nowServing.remove(0);
        return new Server(this, nowServing, queue, finishTime);
    }

    public Pair<Server, Double> serveCustomer(double timeOfService) {
        ImList<Customer> updatedNowServing = nowServing;
        ImList<Customer> updatedQueue = queue;
        if (canServe()) {
            Customer nowServing = queue.get(0);
            updatedQueue = updatedQueue.remove(0);
            updatedNowServing = updatedNowServing.add(nowServing);
        }
        double finishTime = timeOfService + updatedNowServing.get(0).getServiceTime();
        return new Pair<Server, Double>(
            new Server(this, updatedNowServing, updatedQueue, finishTime), finishTime);
    }

    public Server updateQueue(ImList<Customer> q) {
        return new Server(this, nowServing, q, finishTime);
    }

    public Server rest() {
        double restTime = restTimes.get();
        return new Server(this, nowServing, queue, finishTime + restTime);
    }

    public double getFinishTime() {
        return finishTime;
    }

    public boolean nextInLine(Customer customer) {
        return queue.get(0).getId() == customer.getId();
    }
    
    public boolean canQueue() {
        return queue.size() < maxQ;
    }

    public boolean canServe() {
        return nowServing.isEmpty();
    }
    
    public String getIdString() {
        return ((Integer) id).toString();
    }

    public int getId() {
        return id;
    }

    public ImList<Customer> getQueue() {
        return queue;
    }
    
    public ImList<Customer> getNowServing() {
        return nowServing;
    }
    
    public boolean isSelfCheck() {
        return false;
    }

    @Override
    public String toString() {
        return "Server " + getId() + ": " + queue.toString();
    }
}

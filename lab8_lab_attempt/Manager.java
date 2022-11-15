import java.util.function.Supplier;

class Manager extends Server {
    private final ImList<Server> serverList;
    private final ImList<String> serverIdList;

    Manager(int numOfServers, int numOfSelfCheck, int maxQ) {
        super(numOfServers + 1, maxQ, () -> 0.0);
        ImList<Server> servers = new ImList<Server>();
        ImList<String> serverIds = new ImList<String>();
        for (int i = numOfServers; i < (numOfServers + numOfSelfCheck); i++) {
            servers = servers.add(new Server(i + 1,0, () -> 0.0));
            serverIds = serverIds.add("self-check " + ((Integer)(i + 1)).toString());
        }
        this.serverList = servers;
        this.serverIdList = serverIds;
    }

    Manager(Manager manager, ImList<Customer> nowServing, ImList<Customer> queue, double finishTime) {
        super(manager, nowServing, queue, finishTime);
        this.serverList = manager.serverList;
        this.serverIdList = manager.serverIdList;
    }

    public Manager setFinishTime(double time) {
        return new Manager(this, this.nowServing, this.queue, time);
    }

    public Manager addCustomer(Customer customer) {
        ImList<Customer> queue = this.queue;
        queue = queue.add(customer);
        return new Manager(this, this.nowServing, queue, this.finishTime);
    }
    
    public Manager popCustomer() {
        ImList<Customer> nowServing = this.nowServing;
        nowServing = nowServing.remove(0);
        return new Manager(this, nowServing, this.queue, this.finishTime);
    }

    public Pair<Server, Double> serveCustomer(double timeOfService) {
        Customer nowServing = this.queue.get(0);
        ImList<Customer> updatedQueue = this.queue;
        updatedQueue = updatedQueue.remove(0);
        ImList<Customer> updatedNowServing = this.nowServing.add(nowServing);
        double finishTime = timeOfService + nowServing.getServiceTime();
        System.out.println(updatedNowServing.toString());
        return new Pair<Server, Double>(
            new Manager(this, updatedNowServing, updatedQueue, finishTime), finishTime);
    }

    public Manager rest() {
        double restTime = this.restTimes.get();
        return new Manager(this, this.nowServing, this.queue, this.finishTime + restTime);
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
        return this.nowServing.size() <= this.serverIdList.size();
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

class Manager extends Server {
    Manager(int id, int maxQ) {
        super(id, maxQ, () -> 0.0);
    }

    Manager(Manager manager, ImList<Customer> nowServing, ImList<Customer> queue, double finishTime) {
        super(manager, nowServing, queue, finishTime);
    }

    public Manager setFinishTime(double time) {
        return new Manager(this, this.nowServing, this.queue, time);
    }

    public Manager addToQueue(Customer customer) {
        ImList<Customer> queue = this.queue;
        queue = queue.add(customer);
        return new Manager(this, this.nowServing, queue, this.finishTime);
    }

    public Manager addNowServing(Customer customer) {
        ImList<Customer> nowServing = this.nowServing;
        nowServing = nowServing.add(customer);
        return new Manager(this, nowServing, queue, this.finishTime);
    }

    public Manager popQueue() {
        ImList<Customer> queue = this.queue;
        queue = queue.remove(0);
        return new Manager(this, nowServing, queue, this.finishTime);
    }
    
    public Manager popCustomer() {
        ImList<Customer> nowServing = this.nowServing;
        nowServing = nowServing.remove(0);
        return new Manager(this, nowServing, this.queue, this.finishTime);
    }

    public Manager updateQueue(ImList<Customer> q) {
        return new Manager(this, this.nowServing, q, this.finishTime);
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
            new Manager(this, updatedNowServing, updatedQueue, finishTime), finishTime);
    }

    public Manager rest() {
        double restTime = this.restTimes.get();
        return new Manager(this, this.nowServing, this.queue, this.finishTime + restTime);
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

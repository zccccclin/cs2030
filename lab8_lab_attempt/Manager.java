class Manager extends Server {
    Manager(int id, int maxQ) {
        super(id, maxQ, () -> 0.0);
    }

    Manager(Manager manager, ImList<Customer> nowServing, ImList<Customer> queue, double finishTime) {
        super(manager, nowServing, queue, finishTime);
    }

    public Manager setFinishTime(double time) {
        return new Manager(this, getNowServing(), getQueue(), time);
    }

    public Manager addToQueue(Customer customer) {
        ImList<Customer> queue = getQueue();
        queue = queue.add(customer);
        return new Manager(this, getNowServing(), queue, getFinishTime());
    }

    public Manager addNowServing(Customer customer) {
        ImList<Customer> nowServing = getNowServing();
        nowServing = nowServing.add(customer);
        return new Manager(this, nowServing, getQueue(), getFinishTime());
    }

    public Manager popQueue() {
        ImList<Customer> queue = getQueue();
        queue = queue.remove(0);
        return new Manager(this, getNowServing(), queue, getFinishTime());
    }
    
    public Manager popCustomer() {
        ImList<Customer> nowServing = getNowServing();
        nowServing = nowServing.remove(0);
        return new Manager(this, nowServing, getQueue(), getFinishTime());
    }

    public Manager updateQueue(ImList<Customer> q) {
        return new Manager(this, getNowServing(), q, getFinishTime());
    }
    
    public Pair<Server, Double> serveCustomer(double timeOfService) {
        ImList<Customer> updatedNowServing = getNowServing();
        ImList<Customer> updatedQueue = getQueue();
        if (canServe()) {
            Customer nowServing = getQueue().get(0);
            updatedQueue = updatedQueue.remove(0);
            updatedNowServing = updatedNowServing.add(nowServing);
        }
        double finishTime = timeOfService + updatedNowServing.get(0).getServiceTime();
        return new Pair<Server, Double>(
            new Manager(this, updatedNowServing, updatedQueue, finishTime), finishTime);
    }

    public Manager rest() {
        double restTime = 0.0;
        return new Manager(this, getNowServing(), getQueue(), getFinishTime() + restTime);
    }
    
    public String getIdString() {
        return "self-check " + getId();
    }

    @Override
    public boolean isSelfCheck() {
        return true;
    }

    @Override
    public String toString() {
        return "Server " + getId() + ": " + getQueue().toString();
    }
}

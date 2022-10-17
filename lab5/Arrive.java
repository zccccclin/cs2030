class Arrive implements Event {
    private final Customer customer;
    private final double time;
        
    Arrive(Customer customer) {
        this.customer = customer;
        this.time = customer.getArrivalTime();
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        boolean queueFlag = true;
        for (int serverIdx = 0; serverIdx < servers.size(); serverIdx++) {
            Server server = servers.get(serverIdx);
            if (server.canServe()) {
                queueFlag = false;
                server = server.addCustomer(this.customer);
                servers = servers.set(serverIdx, server);
                double timeOfService = server.getFinishTime();
                if (server.getFinishTime() < this.customer.getArrivalTime()) {
                    timeOfService = this.customer.getArrivalTime();
                }
                return new Pair<Event, ImList<Server>>(new Serve(this.customer, serverIdx, timeOfService, true), servers);
            }
        }
        for (int serverIdx = 0; serverIdx < servers.size(); serverIdx++) {
            Server server = servers.get(serverIdx);
            if (server.canQueue() && queueFlag) {
                server = server.addCustomer(this.customer);
                servers = servers.set(serverIdx, server);
                return new Pair<Event, ImList<Server>>(new Wait(this.customer, serverIdx), servers);
            }
        }
        return new Pair<Event, ImList<Server>>(new Leave(this.customer), servers);
    }
    
    public double getWaitTime() {
        return 0.0;
    }
    
    public int getOrder() {
        return this.customer.getId();
    }

    public double getTime() {
        return this.time;
    }

    public String getEvent() {
        return "ARRIVE";
    }
        
    @Override
    public String toString() {
        return String.format("%.3f", this.time) + " " + this.customer.getId() + " arrives";
    }
}



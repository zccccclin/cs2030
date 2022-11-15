class Arrive implements Event {
    private final Customer customer;
    private final double time;
        
    Arrive(Customer customer) {
        this.customer = customer;
        this.time = customer.getArrivalTime();
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        for (int serverIdx = 0; serverIdx < servers.size(); serverIdx++) {
            Server server = servers.get(serverIdx);
            if (server.getFinishTime() <= this.customer.getArrivalTime() && server.canServe()) {
                server = server.addNowServing(this.customer);
                double timeOfService = this.customer.getArrivalTime();
                servers = servers.set(serverIdx, server);
                return new Pair<Event, ImList<Server>>(
                    new Serve(this.customer, serverIdx, timeOfService, true, servers), servers);
            }
        }
        for (int serverIdx = 0; serverIdx < servers.size(); serverIdx++) {
            Server server = servers.get(serverIdx);
            if (server.canQueue()) {
                if (server.isSelfCheck()) {
                    for (int idx = 0; idx < servers.size(); idx++) {
                        Server s = servers.get(idx);
                        if (s.isSelfCheck()) {
                            s = s.addToQueue(this.customer);
                            servers = servers.set(idx, s);
                        }
                    }
                    return new Pair<Event, ImList<Server>>(new Wait(this.customer, serverIdx, servers), servers);
                }
                server = server.addToQueue(this.customer);
                servers = servers.set(serverIdx, server);
                return new Pair<Event, ImList<Server>>(new Wait(this.customer, serverIdx, servers), servers);
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



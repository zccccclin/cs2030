class Serve implements Event {
    private final Customer customer;
    private final int serverIdx;
    private final double time;
    private final String eventId;
    
    Serve(Customer customer, int serverIdx, double timeOfService, boolean nowServing) {
        this.customer = customer;
        this.serverIdx = serverIdx;
        this.time = timeOfService;
        if (nowServing) {
            this.eventId = "SERVE";
        } else {
            this.eventId = "FUTURESERVE";
        }
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        Server server = servers.get(this.serverIdx);
        if (server.canServe()) {
            Pair<Server, Double> result = server.serveCustomer(this.time);
            server = result.first();
            double finishTime = result.second();
            servers = servers.set(this.serverIdx, server);
            return new Pair<Event, ImList<Server>>(
                new Done(this.customer, this.serverIdx, finishTime), servers);
        }
        double finishTime = server.getFinishTime();
        boolean nowServing = server.nextInLine(this.customer);
        return new Pair<Event, ImList<Server>>(
            new Serve(this.customer, this.serverIdx, finishTime, nowServing), servers);

    }

    public double getWaitTime() {
        return this.time - this.customer.getArrivalTime();
    }
    
    public int getOrder() {
        return this.customer.getId();
    }

    public double getTime() {
        return this.time;
    }

    public String getEvent() {
        return this.eventId;
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.time) + " " + this.customer.getId() + 
            " serves by " + (this.serverIdx + 1);
    }
}


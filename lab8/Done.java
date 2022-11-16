class Done implements Event {
    private final Customer customer;
    private final int serverIdx;
    private final double time;
    private final ImList<Server> serverList;

    Done(Customer customer, int serverIdx, double finishTime, ImList<Server> serverList) {
        this.customer = customer;
        this.serverIdx = serverIdx;
        this.time = finishTime;
        this.serverList = serverList;
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        Server server = servers.get(serverIdx);
        server = server.popCustomer();
        server = server.rest();
        servers = servers.set(serverIdx, server);
        return new Pair<Event, ImList<Server>>(new Exit(), servers);
    }

    public double getWaitTime() {
        return 0.0;
    }

    public int getOrder() {
        return customer.getId();
    }

    public double getTime() {
        return time;
    }

    public String getEvent() {
        return "DONE";
    }

    @Override
    public String toString() {
        return String.format("%.3f", time) + " " + customer.getId() + 
            " done serving by " + serverList.get(serverIdx).getIdString();
    }
}

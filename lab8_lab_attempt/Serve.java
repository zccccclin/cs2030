class Serve implements Event {
    private final Customer customer;
    private final int serverIdx;
    private final double time;
    private final String eventId;
    private final ImList<Server> serverList;

    Serve(Customer customer, int serverIdx, double timeOfService, boolean nowServing, ImList<Server> serverList) {
        this.customer = customer;
        this.serverIdx = serverIdx;
        this.time = timeOfService;
        if (nowServing) {
            this.eventId = "SERVE";
        } else {
            this.eventId = "FUTURESERVE";
        }
        this.serverList = serverList;
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        Server server = servers.get(serverIdx);
        if (eventId == "SERVE") {
            Pair<Server, Double> result = server.serveCustomer(time);
            server = result.first();
            double finishTime = result.second();
            servers = servers.set(serverIdx, server);
            if (server.isSelfCheck()) {
                for (int idx = 0; idx < servers.size(); idx++) {
                    Server s = servers.get(idx);
                    if (s.isSelfCheck()) {
                        s = s.updateQueue(server.getQueue());
                        servers = servers.set(idx, s);
                    }
                }
            }
            return new Pair<Event, ImList<Server>>(
                new Done(customer, serverIdx, finishTime, servers), servers);
        }

        double finishTime = server.getFinishTime();
        int finalIdx = serverIdx;
        if (server.isSelfCheck()) {
            for (int idx = 0; idx < servers.size(); idx++) {
                Server s = servers.get(idx);
                if (!s.getQueue().isEmpty() && s.isSelfCheck() && s.getFinishTime() < finishTime) {
                    finishTime = s.getFinishTime();
                    finalIdx = idx;
                }
            }
        }
        boolean nowServing = false;
        nowServing = server.nextInLine(customer) && server.canServe();
        return new Pair<Event, ImList<Server>>(
            new Serve(customer, finalIdx, finishTime, nowServing, servers), servers);
    }

    public double getWaitTime() {
        return time - customer.getArrivalTime();
    }
    
    public int getOrder() {
        return customer.getId();
    }

    public double getTime() {
        return time;
    }

    public String getEvent() {
        return eventId;
    }

    @Override 
    public String toString() {
        return String.format("%.3f", time) + " " + customer.getId() + 
            " serves by " + serverList.get(serverIdx).getIdString();
    }
}


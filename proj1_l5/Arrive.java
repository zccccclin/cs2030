class Arrive implements Event {
    private final Customer customer;
    private final double time;
        
    Arrive(Customer customer) {
        this.customer = customer;
        this.time = customer.getArrivalTime();
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        boolean queueFlag = true;
        for (int i = 0; i < servers.size(); i++) {
            Server s = servers.get(i);
            if (s.canServe()) {
                queueFlag = false;
                Customer customer = this.customer.nextInLine(s.getFinishTime());
                s = s.addCustomer(customer);
                servers = servers.set(i, s);
                return new Pair<Event, ImList<Server>>(new Serve(customer, i), servers);
            }
        }
        for (int i = 0; i < servers.size(); i++) {
            Server s = servers.get(i);
            if (s.canQueue() && queueFlag) {
                Customer customer = this.customer.nextInLine(s.getFinishTime());
                s = s.addCustomer(customer);
                servers = servers.set(i, s);
                return new Pair<Event, ImList<Server>>(new Wait(customer, i), servers);
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



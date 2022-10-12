class Exit implements Event {
    private final double time;

    Exit() {
        this.time = 0.0;
    }

    public double getTime() {
        return this.time;
    }
    
    public int getOrder() {
        return 0;
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> servers) {
        return new Pair<Event, ImList<Server>>(new Exit(), servers);
    }

    public double getWaitTime() {
        return this.time;
    }
    
    public String getEvent() {
        return "EXIT";
    }

    @Override
    public String toString() {
        return "End";
    }
}

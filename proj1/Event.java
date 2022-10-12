interface Event {
    public Pair<Event, ImList<Server>> execute(ImList<Server> servers);

    public double getTime();

    public int getOrder();

    public String getEvent();

    public double getWaitTime();
}

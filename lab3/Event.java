interface Event {
    public Event execute(Server server);
    
    public Pair<String, Integer> getEvent();

    public double getTime();

    public int getCustomerNumber();

    public String toString();
}

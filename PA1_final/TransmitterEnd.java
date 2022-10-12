interface TransmitterEnd {
    public PagerAck rcv();

    public String getId();

    public ImList<String> getChain();

    public ImList<Term> getConnection();

    public boolean equals(Object h);

    public void broadcast();

    public String formatString();
}

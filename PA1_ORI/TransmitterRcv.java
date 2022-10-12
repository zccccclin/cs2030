class TransmitterRcv extends Host {
    private final String pagerId;
    private final ImList<Object> infoChain;

    TransmitterRcv(String pagerId, String id) {
        super(id);
        this.pagerId = pagerId;
    }

    public Term rcv() {
        return new PagerAck();
    }

    @Override 
    public String toString() {
        return "";
    }
}



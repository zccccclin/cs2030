class TransmitterEnd extends Host {
    private final String infoChain;
    private final String pagerId;

    TransmitterEnd(String infoChain, String pagerId, String id) {
        super(id);
        this.infoChain = infoChain;
        this.pagerId = pagerId;
    }

    @Override
    public String toString() {
        return "";
    }
}

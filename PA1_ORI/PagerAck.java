class PagerAck extends Term {
    private final ImList<String> infoChain;
    private final String transId;

    PagerAck(String transId, String id) {
        super(id);
        this.transId = transId;
    }

    @Override
    public String toString() {
        return "";
    }
}

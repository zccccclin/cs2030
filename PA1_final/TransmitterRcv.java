class TransmitterRcv extends Host implements TransmitterEnd {
    TransmitterRcv(String id, ImList<String> infoChain, ImList<Term> c) {
        super(id, infoChain, c);
    }

    public PagerAck rcv() {
        String s = " >--rcv--> ";
        String pagerId = this.getChain().get(0);
        return new PagerAck(pagerId, this.getChain().add(s).add(pagerId), this.getConnection());
    }

    @Override 
    public String toString() {
        return this.formatString();
    }
}



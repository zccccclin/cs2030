class PagerAck extends Term {
    PagerAck(String id, ImList<String> i, ImList<Term> c) {
        super(id, i, c);
    }

    Transmitter ack() {
        String s = " >--ack--> ";
        String transmitterId = this.getChain().get(2);
        return new Transmitter(transmitterId, this.getChain().add(s).add(transmitterId), this.getConnection().add(this));
    }

    @Override
    public String toString() {
        return this.formatString();
    }
}

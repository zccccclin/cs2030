class Pager extends Term {
    Pager(String id) {
        super(id, new ImList<String>().add(id), new ImList<Term>());
    }

    public TransmitterEnd snd(Host h) {
        String s = " >--snd--> ";
        ImList<String> newChain = this.getChain().add(s).add(h.getId());
        return new TransmitterRcv(h.getId(), newChain, h.getConnection());
    }

    @Override
    public String toString() {
        return this.formatString();
    }
}

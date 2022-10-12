class Pager implements Term {
    private final String id;
    private final ImList<String> infoChain;

    Pager(String id) {
        this.id = id;
        this.infoChain = new ImList<String>();
    }

    Pager(String id, ImList<String> infoChain) {
        this.id = id;
        this.infoChain = infoChain;
    }

    public Host snd(Host h) {
        ImList<String> newChain = this.infoChain.add(this.id).add(h.getId());
        return h.addInfo(newChain);
    }

    public Term addInfo(ImList<String> infoChain) {
        return new Pager(this.id, infoChain);
    }

    public boolean equals(Term t) {
        return t.getId() == this.getId();
    }
   
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id;
    }
}

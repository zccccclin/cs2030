class Loader {
    private final int id;
    private final int nL;
    
    Loader(int nL) {
        this(1, nL);
    }
    
    Loader(int nextId, int nL) {
        this.nL = nL;
        if (nextId % nL == 0) {
            this.id = nL;
        } else {
            this.id = nextId % nL;
        }
    }

    public Loader nextLoader() {
        return new Loader(this.id + 1, this.nL);
    }

    @Override
    public String toString() {
        return "Loader #" + this.id;
    }
}

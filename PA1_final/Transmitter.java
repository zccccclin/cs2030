class Transmitter extends Host {
    Transmitter(String id) {
        super(id, new ImList<String>().add(id), new ImList<Term>());
    }

    Transmitter(String id, ImList<String> i, ImList<Term> c) {
        super(id, i, c);
    }
    
    @Override
    public String toString() {
        return this.formatString();
    }
}


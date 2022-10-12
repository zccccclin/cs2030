class Transmitter extends Host {
    private final ImList<String> infoChain;

    Transmitter(String id) {
        super(id);
        this.infoChain = new ImList<String>();
    }
    
    @Override
    public String toString() {
        return super.getId();
    }
}


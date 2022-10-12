class Server {
    private final String name;
    private final boolean occupied;

    Server(String name) {
        this(name, false);
    }

    Server(String name, Boolean occupied) {
        this.name = name;
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return this.occupied;
    }
    
    public Server changeState() {
        return new Server(this.name, !this.occupied);
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}

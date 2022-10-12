abstract class Host {
    private final String id;

    Host(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Host h) {
        return this.getId() == h.getId();
    }
}

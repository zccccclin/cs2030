abstract class Host {
    private final String id;
    private final ImList<String> infoChain;
    private final ImList<Term> connectionList;

    Host(String id, ImList<String> i, ImList<Term> c) {
        this.id = id;
        this.infoChain = i;
        this.connectionList = c;
    }

    public String getId() {
        return this.id;
    }

    public ImList<String> getChain() {
        return this.infoChain;
    }

    public ImList<Term> getConnection() {
        return this.connectionList;
    }

    public boolean equals(Object h) {
        if (h instanceof Host) {
            return this.getId() == ((Host)h).getId();
        }
        return false;
    }

    public void broadcast() {
        for (Term t : this.connectionList) {
            t.beep();
        }
    }

    public String formatString() {
        String o = "";
        for (String i : this.infoChain) {
            o += i;
        }
        return o;
    }
}

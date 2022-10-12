abstract class Term {
    private final String id;
    private final ImList<String> infoChain;
    private final ImList<Term> connectionList;
    
    Term(String id, ImList<String> i, ImList<Term> c) {
        this.id = id;
        this.infoChain = i;
        this.connectionList = c;
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Term t) {
        return this.getId() == t.getId();
    }

    public ImList<String> getChain() {
        return this.infoChain;
    }

    public ImList<Term> getConnection() {
        return this.connectionList;
    }
   
    public void beep() {
        System.out.println(this.id + ":beep");
    }

    public String formatString() {
        String o = "";
        for (String i : this.infoChain) {
            o += i;
        }
        return o;
    }
}

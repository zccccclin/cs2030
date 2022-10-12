class Case {
    private final Person person;
    private final ImList<Test> testHistory;
    private final Protocol p;
    
    Case(Person person, PCR test) {
        this.person = person;
        ImList<Test> testHistory = new ImList<Test>();
        this.testHistory = testHistory.add(test);
        this.p = this.initialProtocol(person, test);
    }
    
    Case(Person person, ImList<Test> testHistory, Protocol p) {
        this.person = person;
        this.testHistory = testHistory;
        this.p = p;
    }

    Case(Person person, Test test, Case currCase) {
        this.person = person;
        ImList<Test> testHistory = new ImList<Test>();
        this.testHistory = testHistory.add(test);
        if (test.isPositive()) {
            this.p = this.initialProtocol(person, test);
        } else {
            this.p = new P3();
        }
    }

    private Protocol initialProtocol(Person person, Test test) {
        if (person.isHighRisk()) {
            return new P1();
        }
        if (test.isPositive()) {
            return new P2();
        }
        return new D1();
    }
    
    public Case test(Test test) {
        if (!test.isValid()) {
            return this;
        }

        ImList<Test> newList = this.testHistory.add(test);
        return new Case(this.person, newList, this.p.next(this.person, test, newList.size()));
    }

    public Protocol getCurrentProtocol() {
        return this.p;
    }

    public ImList<Test> getTestHistory() {
        return this.testHistory;
    }

    @Override
    public String toString() {
        return this.person.toString() + " " + this.testHistory.toString() + " " + this.p.toString();
    }
}

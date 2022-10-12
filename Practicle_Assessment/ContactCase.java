class ContactCase extends Case {
    //private final boolean monitoredFlag;
    //private final int monitoredDays;
    private final Case currCase;

    ContactCase(Person person, Test test, Case currCase) {
        super(person, test, currCase);
        //this.monitoredFlag = true;
        //this.monitoredDays = 1;
        this.currCase = currCase;
    }
    
    ContactCase(Person person, ImList<Test> testHistory, Protocol p, Case currCase) {
        super(person, testHistory, p);
        //this.monitoredFlag = flag;
        //this.monitoredDays = monitoredDays;
        this.currCase = currCase;
    }   

    @Override
    public Case test(Test test) {
        if (!test.isValid()) {
            return this;
        }
        ImList<Test> newList = super.getTestHistory().add(test);
        //int newMonitoredDays = this.monitoredDays;
        //boolean newFlag = this.monitoredFlag;
        //if (!test.isPositive() && newFlag) {
        //  newMonitoredDays = newList.size();
        //} else {
        //    newFlag = false;
        //}
        return new ContactCase(super.getPerson(), newList, 
               super.getCurrentProtocol().next(super.getPerson(), 
               test, newList.size()), this.currCase);
    }

    @Override 
    public ImList<Case> getLineage() {
        return new ImList<Case>().addAll(this.currCase.getLineage()).add(this);
    }
}

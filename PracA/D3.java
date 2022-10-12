class D3 implements Protocol {
    private static final int shortIsolation = 7;
    private static final int longIsolation = 14;
    
    public Protocol next(Person person, Test test, int numOfDays) {
        int isolationDays = 0;
        if (person.isVaccinated()) {
            isolationDays = shortIsolation;
        } else {
            isolationDays = longIsolation;
        }

        if (test.isPositive()) {
            if (numOfDays > isolationDays) {
                return new D2();
            }
            return new P2();
        }
        return new D3();
    }

    @Override
    public String toString() {
        return "Discharged: complete";
    }
}

class P2 implements Protocol {
    private final int scenario;
    private static final int shortTest = 7;
    private static final int longTest = 14;
    private static final int isolationDays = 3;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    P2(int scenario) {
        this.scenario = scenario;
    }

    private int testDays(Person person) {
        int testDays = 0;
        if (person.isVaccinated()) {
            testDays = shortTest;
        } else {
            testDays = longTest;
        }
        return testDays;
    }

    public Protocol next(Person person, Test test, int numOfDays) { 
        if (this.scenario == THREE) {
            return new P2(THREE);
        }
        if (this.scenario == TWO) {
            if (test.isPositive()) {
                return new P2(THREE);
            }
            return new P2(TWO);
        }
        if (this.scenario == ONE) {
            if (test.isPositive()) {
                return new P2(0);
            }
            return new P2(ONE);
        }

        if (numOfDays <= isolationDays) {
            return new P2(0);
        } else if (numOfDays > this.testDays(person)) {
            return new P2(TWO);
        }
        if (test.isPositive()) {
            return new P2(0);
        }
        return new P2(1);
    }

    public String formatString() {
        if (this.scenario == 0) {
            return "P2";
        }
        if (this.scenario == THREE) {
            return "Discharged: seek medical attention";
        }
        if (this.scenario == TWO || this.scenario == 1) {
            return "Discharged: complete";
        }
        return "";
    }

    @Override
    public String toString() {
        return this.formatString();
    }
}

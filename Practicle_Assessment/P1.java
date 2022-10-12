class P1 implements Protocol {
    private final int scenario;

    P1(int scenario) {
        this.scenario = scenario;
    }

    public Protocol next(Person person, Test test, int numOfDays) {
        if (this.scenario == 2) {
            return new P1(2);
        }
        if (this.scenario == 1) {
            if (test.isPositive()) {
                return new P1(2);
            }
            return new P1(1);
        }
        if (test.isPositive()) {
            return new P1(0);
        }
        return new P1(1);
    }

    private String formatString() {
        if (this.scenario == 0) {
            return "P1";
        }
        if (this.scenario == 1) {
            return "Discharged: follow up with doctor";
        }
        if (this.scenario == 2) {
            return "Discharged: seek medical attention";
        }
        return "";
    }

    @Override
    public String toString() {
        return this.formatString();
    }
}

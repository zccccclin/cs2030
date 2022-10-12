class P3 implements Protocol {
    private final int scenario;
    private static final int monitoring = 5;

    P3(int scenario) {
        this.scenario = scenario;
    }

    public Protocol next(Person person, Test test, int numOfDays) {
        if (this.scenario == 2) {
            return new P3(2);
        }
        if (this.scenario == 1) {
            if (test.isPositive()) {
                return new P3(2);
            }
            return new P3(1);
        }
        if (numOfDays == monitoring + 1) {
            if (test.isPositive()) {
                if (person.isHighRisk()) {
                    return new P1(0);
                }
                return new P2(0);
            }
            return new P3(1);
        } else if (numOfDays > monitoring + 1) {
            if (test.isPositive()) {
                return new P3(2);
            }
            return new P3(1);
        }
        if (test.isPositive()) {
            if (person.isHighRisk()) {
                return new P1(0);
            }
            return new P2(0);
        }
        return new P3(0);
    }

    private String formatString() {
        if (this.scenario == 0) {
            return "P3";
        }
        if (this.scenario == 2) {
            return "Discharged: seek medical attention";
        }
        if (this.scenario == 1) {
            return "Discharged: complete";
        }
        return "";
    }
    
    @Override
    public String toString() {
        return this.formatString();
    }
}



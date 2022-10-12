class PCR implements Test {
    private static final String ALPHA = "alpha";
    private static final String OMICRON = "omicron";
    private static final String BETA = "beta";
    private static final String DELTA = "delta";
    private final boolean result;

    PCR(String result) {
        if (result == ALPHA || result == BETA || result == DELTA || result == OMICRON) {
            this.result = true;
        } else {
            this.result = false;
        }
    }

    public boolean isValid() {
        return true;
    }

    public boolean isPositive() {
        return this.result;
    }

    public String isPositiveString() {
        if (this.result) {
            return "+";
        }
        return "-";
    }

    @Override
    public String toString() {
        return "P" + this.isPositiveString();
    }
}

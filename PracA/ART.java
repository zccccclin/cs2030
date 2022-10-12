class ART implements Test{
    private final String result;

    ART(String result) {
        this.result = result;
    }

    public boolean isValid() {
        return this.result != "T";
    }

    public boolean isPositive() {
        return this.result == "CT";
    }

    public String isPositiveString() {
        if (!this.isValid()) {
            return "X";
        }
        if (this.isPositive()) {
            return "+";
        }
        return "-";
    }

    @Override
    public String toString() {
        return "A" + this.isPositiveString();
    }
}
           

class Person {
    private final int score;
    private final String id;
    private final String vac;

    Person(String id, String vac, int score) {
        this.score = score;
        this.id = id;
        this.vac = vac;
    }

    public boolean isVaccinated() {
        if (this.vac.length() >= 2) {
            return true;
        }
        return false;
    }

    public boolean isHighRisk() {
        if (this.score >= 8) {
            return true;
        }
        return false;
    }

    public String isHighRiskString() {
        if (isHighRisk()) {
            return "HIGH";
        }
        return "LOW";
    }       
    
    @Override
    public String toString() {
        return this.id + "/" + this.vac + "/" + this.isHighRiskString();
    }
}
          

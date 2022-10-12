class D1 implements Protocol {

    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isPositive()) {
            return new D2();
        }
        return new D1();
    }

    @Override
    public String toString() {
        return "Discharged: follow up with doctor";
    }
}

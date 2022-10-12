class P1 implements Protocol {
    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isPositive()) {
            return new P1();
        }
        return new D1();
    }

    @Override
    public String toString() {
        return "P1";
    }
}

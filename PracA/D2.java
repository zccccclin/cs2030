class D2 implements Protocol {

    public Protocol next(Person person, Test test, int numOfDays) {
        return new D2();
    }

    @Override
    public String toString() {
        return "Discharged: seek medical attention";
    }
}

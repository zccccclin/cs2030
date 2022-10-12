class TimeConverter {
    private static final int minsInHour = 60;

    TimeConverter() {
    }

    public int hourToMin(int time) {
        return ((time / 100) * this.minsInHour + time % 100);
    }
}

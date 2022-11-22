class TimeConverter {
    private static final int hourInMins = 60;
    private static final int hundred = 100;

    public int hourToMins(int hour) {
        return Math.floorDiv(hour, hundred) * hourInMins + hour % hundred;
    }

    public int minsToHour(int mins) {
        return Math.floorDiv(mins, hourInMins) * hundred + mins % hourInMins;
    }
}


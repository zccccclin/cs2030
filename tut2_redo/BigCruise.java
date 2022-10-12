class BigCruise extends Cruise {
    private static final double serviceTimeRate = 50;
    private static final double loaderRatio = 40;

    BigCruise(String id, int aT, int len, int numPass) {
        super(id, aT, (int) Math.ceil(len / loaderRatio),
                (int) Math.ceil(numPass / serviceTimeRate));
    }
}

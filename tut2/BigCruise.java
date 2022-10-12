class BigCruise extends Cruise {
    private static final double loaderRatio = 40;
    private static final double serviceNum = 50;

    BigCruise(String id, int aT, int len, int numP) {  
       super(id, aT, (int) Math.ceil(len / loaderRatio),
                     (int) Math.ceil(numP / serviceNum));
    }
}

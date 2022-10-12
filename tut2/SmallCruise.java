class SmallCruise extends Cruise {
    private static final int serviceTime = 30;
    
    SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, 1, serviceTime);
    }

}

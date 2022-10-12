class Service {
    private final Loader loader;
    private final Cruise cruise;
    private final int timeOfService;

    Service(Loader loader, Cruise cruise, int tosInMin) {
        this.loader = loader;
        this.cruise = cruise;
        this.timeOfService = tosInMin;
    }

    @Override
    public String toString() {
        return  String.format("%04d", this.cruise.minToHourMin(this.timeOfService)) + 
                " : " + this.loader.toString() + " serving " + this.cruise.toString(); 
    }
}

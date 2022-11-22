class Service {
    private final Loader loader;
    private final Cruise cruise;
    private final int tos;
    private static final TimeConverter converter = new TimeConverter();

    Service(Loader loader, Cruise cruise, int tos) {
        this.loader = loader;
        this.cruise = cruise;
        this.tos = converter.minsToHour(tos);
    }

    @Override
    public String toString() {
        return String.format("%04d", this.tos) + " : " + this.loader.toString() + " serving " + 
            this.cruise.toString();
    }
}

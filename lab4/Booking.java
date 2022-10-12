import java.lang.Comparable;

class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;
    private final Service cheapestService;
    private final double cheapestFare;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
        this.cheapestService = request.computeCheapest(driver);
        this.cheapestFare = request.computeFare(this.cheapestService) / 100.0;
    }

    @Override
    public int compareTo(Booking other) {
        return this.driver.shorterWaitTime(other.driver);
    }

    @Override
    public String toString() {
        return "$" + String.format("%.2f",this.cheapestFare) + " using " + this.driver.toString() + " (" + this.cheapestService.toString() + ")";
    }
}

import java.util.Comparator;

class FareAndTimeComparator<Booking> implements Comparator<Booking> {
    private final Request request;

    FareAndTimeComparator() {
    }

    @Override
    public int compare(Booking a, Booking b) {
        return a.compareTo(b);    
    }
}

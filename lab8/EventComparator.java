import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    public int compare(Event i, Event j) {
        if (i.getTime() == j.getTime()) {
            return i.getOrder() - j.getOrder();
        } else if (i.getTime() < j.getTime()) {
            return -1;
        }
        return 1;
    }
}

import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    public int compare(Event i, Event j) {
        if (i.getTime() == j.getTime()) {
            if (i.getEvent().second() < j.getEvent().second()) {
                return 1;
            } else if (i.getEvent().first() == j.getEvent().first()) {
                return i.getCustomerNumber() - j.getCustomerNumber();
            } else {
                return -1;
            }
        } else {
            if (i.getTime() > j.getTime()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}

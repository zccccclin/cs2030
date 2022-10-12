import java.util.Comparator;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Server server = new Server(name);
        int customerNumber = 0;
        Comparator<Event> comparator = new EventComparator();
        PQ<Event> eventQueue = new PQ<Event>(comparator);

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble(); 
            customerNumber = customerNumber + 1;
            Customer customer = new Customer(arrivalTime, serviceTime, customerNumber);
            Event event = new EventArrive(customer);
            eventQueue = eventQueue.add(event);
        }
        sc.close();
        
        while (!eventQueue.isEmpty()) {
            Event firstEvent = eventQueue.poll().first();
            eventQueue = eventQueue.poll().second();
            if (firstEvent.getEvent().first() == "Leave") {
                System.out.println(firstEvent.toString());
                continue;
            } else if (firstEvent.getEvent().first() == "Done") {
                server = server.changeState();
                System.out.println(firstEvent.toString());
                continue;
            } else if (firstEvent.getEvent().first() == "Served") {
                server = server.changeState();
                System.out.println(firstEvent.toString());
                eventQueue = eventQueue.add(firstEvent.execute(server));              
            } else {
                System.out.println(firstEvent.toString());
                eventQueue = eventQueue.add(firstEvent.execute(server));
            }
        }
        
    }
}

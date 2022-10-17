import java.util.Comparator;
import java.util.function.Supplier;

class Simulator {
    private final ImList<Server> servers;
    private final ImList<Customer> customers;

    Simulator(int numOfServers, int qmax, ImList<Pair<Double, Supplier<Double>>> inputTimes, Supplier<Double> restTimes) {
        ImList<Server> servers = new ImList<Server>();
        for (int id = 1; id < numOfServers + 1; id++) {
            Server server = new Server(id, qmax, restTimes);
            servers = servers.add(server);
        }
        this.servers = servers;

        ImList<Customer> customers = new ImList<Customer>();
        int customerNumber = 0;
        for (Pair<Double, Supplier<Double>> inputTime : inputTimes) {
            customerNumber++;
            Customer customer = new Customer(inputTime, customerNumber);
            customers = customers.add(customer);
        }
        this.customers = customers;
    }
    
    public String simulate() {
        String output = "";
        Comparator<Event> cmp = new EventComparator();
        PQ<Event> eventQueue = new PQ<Event>(cmp);
        
        for (Customer c : customers) {
            Event event = new Arrive(c);
            eventQueue = eventQueue.add(event);
        }

        ImList<Server> updatedServers = this.servers;
        int serveCnt = 0;
        int leaveCnt = 0;
        double totalWaitTime = 0;

        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.poll().first();
            eventQueue = eventQueue.poll().second();
            if (event.getEvent() != "EXIT") {
                if (event.getEvent() == "SERVE") {
                    serveCnt += 1;
                    totalWaitTime += event.getWaitTime();
                }
                if (event.getEvent() == "LEAVE") {
                    leaveCnt += 1;
                }
                if (event.getEvent() != "FUTURESERVE") {
                    output = output + event.toString() + "\n";
                }
                Pair<Event, ImList<Server>> result = event.execute(updatedServers);
                event = result.first();
                updatedServers = result.second();
                eventQueue = eventQueue.add(event);
            } 
        }
        output += String.format("[%.3f %d %d]", (totalWaitTime / serveCnt), serveCnt, leaveCnt);
        return output;
    }
}

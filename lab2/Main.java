import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Server server = new Server(name);
        int customerNumber = 0;

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble();
            customerNumber = customerNumber + 1;
            Customer nextCustomer = new Customer(arrivalTime, serviceTime, customerNumber);
            Event event = new Arrive(nextCustomer, server);
            System.out.println(event.toString());
            System.out.println(event.execute().toString());

            server = server.answer(nextCustomer);
        }
        sc.close();
    }
}

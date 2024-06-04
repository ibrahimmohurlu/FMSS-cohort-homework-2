import model.Bill;
import model.Customer;
import model.Order;
import model.Product;
import repository.BillRepository;
import repository.CustomerRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import service.BillService;
import service.CustomerService;

import java.util.List;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) {

        BillRepository billRepo = new BillRepository();
        CustomerRepository customerRepo = new CustomerRepository();
        OrderRepository orderRepo = new OrderRepository();
        ProductRepository productRepo = new ProductRepository();

        generateSeedData(billRepo, customerRepo, orderRepo, productRepo);
        /*
         * Creating Customer and Bill services enough for the homework
         *
         */
        CustomerService customerService = new CustomerService(customerRepo);
        BillService billService = new BillService(billRepo);

        System.out.println("-----All Customers-----");
        customerService.getAllCustomers().forEach(System.out::println);
        System.out.println("----------\n");

        System.out.println("-----All Customers Starts with Letter 'C'-----\n");
        customerService.getAllCustomersFilteredByContainsLetterC().forEach(System.out::println);
        System.out.println("----------\n");

        System.out.println("-----Bill of Customers Registered In June-----\n");
        customerService.getAllCustomersCreatedInJune().forEach(c -> {
            System.out.println(c);
            c.getOrders().stream().map(Order::getBill).forEach(System.out::println);
        });
        System.out.println("----------\n");

        System.out.println("-----All Bills in the System-----\n");
        billService.getAll().forEach(System.out::println);
        System.out.println("----------\n");

        System.out.println("-----All Bills Above 1500-----\n");
        billService.getAll().stream().filter(b -> b.getTotal() > 1500).forEach(System.out::println);
        System.out.println("----------\n");

        System.out.println("-----Average of Bills Above 1500-----\n");
        OptionalDouble average = billService.getAll().stream()
                .mapToDouble(Bill::getTotal)
                .filter(b -> b > 1500)
                .average();
        if (average.isPresent()) {
            System.out.println("Average: " + average.getAsDouble());
        } else {
            System.out.println("List is empty");
        }
        System.out.println("----------\n");

        System.out.println("-----Name of Customers that has Bill Below 500-----\n");
        customerService.getAllCustomers().stream()
                .filter(customer -> {
                    return customer.getOrders().stream().mapToDouble(o -> o.getBill().getTotal()).sum() < 500;
                })
                .map(Customer::getName)
                .forEach(System.out::println);
        System.out.println("----------\n");

    }

    public static void generateSeedData(BillRepository billRepo, CustomerRepository customerRepo, OrderRepository orderRepo, ProductRepository productRepo) {
        /*
         * This method generates test data and saves them to the correct repository
         * */

        Customer c1 = customerRepo.save(new Customer("Haven"));
        Customer c2 = customerRepo.save(new Customer("Cherry"));
        Customer c3 = customerRepo.save(new Customer("Herminia"));
        Customer c4 = customerRepo.save(new Customer("Urbanus"));
        Customer c5 = customerRepo.save(new Customer("Hewet"));

        Product p1 = productRepo.save(new Product("Flour - Corn, Fine", 15.22));
        Product p2 = productRepo.save(new Product("Herb Du Provence - Primerba", 1.94));
        Product p3 = productRepo.save(new Product("Eel Fresh", 19.31));
        Product p4 = productRepo.save(new Product("Pur Value", 17.99));
        Product p5 = productRepo.save(new Product("Straw - Regular", 12.90));


        Order o1 = orderRepo.save(new Order(List.of(p1, p2, p3)));
        Bill b1 = billRepo.save(o1.getBill());

        Order o2 = orderRepo.save(new Order(List.of(p3, p4)));
        Bill b2 = billRepo.save(o2.getBill());

        c1.setOrders(List.of(o1, o2));

        Order o3 = new Order(List.of(p1, p5));
        Bill b3 = o3.getBill();

        c2.setOrders(List.of(o3));

    }
}

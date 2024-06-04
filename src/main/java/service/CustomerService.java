package service;

import model.Customer;
import repository.CustomerRepository;

import java.time.Month;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Customer saveCustomer(Customer c) {
        return repo.save(c);
    }

    public List<Customer> getAllCustomersFiltered(Predicate<? super Customer> predicate) {
        return repo.findAll().stream().filter(predicate).collect(Collectors.toList());
    }

    public List<Customer> getAllCustomersFilteredByContainsLetterC() {
        return getAllCustomersFiltered(customer -> customer.getName().toLowerCase().contains("c"));
    }

    public List<Customer> getAllCustomersCreatedInJune() {
        return getAllCustomersFiltered(customer -> customer.getCreateDate().getMonth().equals(Month.JUNE));
    }
}

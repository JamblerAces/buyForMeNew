package services;

import domain.Customer;

public interface CustomerService extends GenericService<Customer> {
    Customer findByName(String testCustomer);
}

package io.github.gboladeidowu.demo.service;

import io.github.gboladeidowu.demo.model.Customer;
import io.github.gboladeidowu.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create new customer
    public void newCustomer(Customer customer){
        customerRepository.save(customer);
    }

    // Get a list of all customers
    public List<Customer> allCustomers (){
        return customerRepository.findAll();
    }

    // Get a specific customer
    public Optional<Customer> aCustomer (String email){
       Optional<Customer> existingCustomer = customerRepository.findByEmail(email);

       if (existingCustomer.isEmpty()) {
           throw new IllegalStateException("User not found");
       }
        return existingCustomer;
    }


    // Update customer name and phoneNumber
    public  void updateCustomer(Customer customer, String email){
       Optional <Customer> existingCustomer = customerRepository
               .findByEmail(email);
       if (existingCustomer.isPresent()){
           existingCustomer.get().setName(customer.getName());
           existingCustomer.get().setPhoneNumber(customer.getPhoneNumber());
           customerRepository.save(existingCustomer.get());
       }
       else throw new IllegalStateException("User not found");
    }

    // Delete customer details
    public String deleteCustomer(String email){
        Optional<Customer> existingCustomer = customerRepository.findByEmail(email);

        if (existingCustomer.isPresent()){
            customerRepository.deleteByEmail(email);
            return "User successfully deleted";
        }
        else throw new IllegalStateException("User not found!");
    }
}

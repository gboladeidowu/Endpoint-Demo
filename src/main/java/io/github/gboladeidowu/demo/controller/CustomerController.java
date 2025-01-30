package io.github.gboladeidowu.demo.controller;


import io.github.gboladeidowu.demo.model.Customer;
import io.github.gboladeidowu.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> all(){
        return customerService.allCustomers();
    }

    @GetMapping("/{email}")
    public Optional<Customer> aCustomer (@PathVariable String email){
      return customerService.aCustomer(email);
    }

    @PostMapping()
    public String newCustomer (@RequestBody Customer newCustomer){
        customerService.newCustomer(newCustomer);
        return "Customer saved";
    }

    @PutMapping("/{email}")
    public String updateCustomer(@RequestBody Customer updateCustomer, @PathVariable String email){
        customerService.updateCustomer(updateCustomer, email);
        return "Update successful";
    }

    @DeleteMapping()
    public String deleteCustomer(String email){
        customerService.deleteCustomer(email);
        return "Deletion successful";
    }
}

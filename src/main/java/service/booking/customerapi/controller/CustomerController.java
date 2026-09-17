package service.booking.customerapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.booking.customerapi.client.CustomerClient;
import service.booking.customerapi.dto.CreateCustomerDto;
import service.booking.customerapi.dto.LoginCustomerDto;
import service.booking.customerapi.dto.UpdateCustomerDto;

@RestController
@RequestMapping("/connect")
public class CustomerController {

    private final CustomerClient customerClient;

    public CustomerController(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCustomerDto request) {
        return customerClient.createCustomer(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCustomerDto dto) {
        return customerClient.login(dto);
    }

    @GetMapping("/info")
    public ResponseEntity<?> myPageData(@RequestHeader("Authorization") String jwt) {
        return customerClient.getCustomerInfo(jwt);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCustomerInfo(@RequestHeader("Authorization") String jwt, @RequestBody UpdateCustomerDto update) {
        return customerClient.updateCustomer(jwt, update);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestHeader("Authorization") String jwt) {
        return customerClient.deleteAccount(jwt);
    }
}
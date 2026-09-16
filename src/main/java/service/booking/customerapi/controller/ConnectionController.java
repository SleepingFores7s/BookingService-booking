//package service.booking.customerapi.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//
//import org.springframework.http.StreamingHttpOutputMessage.*;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestClient;
//
//import org.springframework.web.client.RestTemplate;
//import service.booking.customerapi.client.CustomerClient;
//import service.booking.dto.CreateCustomerRequest;
//import service.booking.dto.CustomerInfo;
//import service.booking.dto.LoginDto;
//import service.booking.dto.UpdateDto;
//
//@RestController
//@RequestMapping("/connect")
//public class ConnectionController {
//
//    private CustomerClient customerClient;
//
//    public ConnectionController(CustomerClient customerClient) {
//        this.customerClient = customerClient;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<CustomerInfo> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
//        return ResponseEntity.ok().body(customerClient.create(createCustomerRequest));
//    }
//
//    @PostMapping("/login")
//
//
//    @GetMapping("/info")
//
//
//    @PostMapping("/update")
//
//
//    @DeleteMapping("/delete")
//
//}

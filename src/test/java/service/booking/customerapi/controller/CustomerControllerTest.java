package service.booking.customerapi.controller;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    private static String receivedToken;

    @Test
    @Order(1)
    void createNewCustomer_ShouldSaveToDatabase_WhenAuthenticated() throws Exception {

        String customerPayload = """
                        {
                            "firstname": "test",
                            "lastname": "testLast",
                            "identificationNumber": "19900101-1234",
                            "email": "test@test.com",
                            "password": "ASD123asd!",
                            "phoneNumber": "070-1234567"
                        }
                """;

        mockMvc.perform(post("/connect/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerPayload))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void loginCustomer_ShouldReturnJwtTokenFromCustomerRepository_WhenCredentialsAreValid() throws Exception {

        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        String loginPayload = """
                {
                    "email": "test@test.com",
                    "password": "ASD123asd!"
                }
                """;

        receivedToken = mockMvc.perform(post("/connect/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginPayload))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .replace("\"", "");

        // 3. Validate that the token is cryptographically valid and contains the expected subject
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(receivedToken)
                .getPayload();

        org.junit.jupiter.api.Assertions.assertEquals("1", claims.getSubject());

    }

//    @Test
//    @Order(3)
//    void deleteCustomer_ShouldReturnOkIfDeleted() throws Exception {
//
//        var response = mockMvc.perform(delete("/connect/delete")
//                        .header("Authorization", "Bearer " + receivedToken))
//                .andReturn()
//                .getResponse();
//
//        System.out.println("Response Status: " + response.getStatus());
//        System.out.println("Response Body: " + response.getContentAsString());
//
//        org.junit.jupiter.api.Assertions.assertEquals(200, response.getStatus());
//    }
}
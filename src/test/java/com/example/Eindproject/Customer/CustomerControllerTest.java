//package com.example.Eindproject.Customer;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ContextConfiguration
//@WithMockUser(username="admin",roles={"USER","ADMIN"})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CustomerControllerTest {
//    @LocalServerPort
//    private Integer port;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Test
//    void getCustomersTest() {
//        ResponseEntity<String> r = testRestTemplate.getForEntity("http://localhost:" + port + "/customers/", String.class);
//        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(r.getBody()).contains("Klanten");
//    }
//}

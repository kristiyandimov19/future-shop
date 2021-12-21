package com.example.futureshop.web;

import com.example.futureshop.repositories.RestaurantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class RestaurantControllerTest {

    private static final String RESTAURANTS_CONTROLLER_PREFIX = "/restaurants";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @WithMockUser(value = "pesho", authorities = {"ADMIN"})
    void addRestaurant() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(RESTAURANTS_CONTROLLER_PREFIX + "/add")
                .param("name", "test restaurant")
                .param("address", "address1")
                .param("imageUrl", "img")
                .param("dishes", "dish1")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, restaurantRepository.count());
    }
}

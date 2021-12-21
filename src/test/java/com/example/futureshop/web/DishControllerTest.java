package com.example.futureshop.web;

import com.example.futureshop.models.entities.enums.AllergensEnum;
import com.example.futureshop.repositories.AllergenRepository;
import com.example.futureshop.repositories.DishRepository;
import com.example.futureshop.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class DishControllerTest {

    private static final String DISHES_CONTROLLER_PREFIX = "/dishes";

    private Long testDishId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private AllergenRepository allergenRepository;

    private DishesTestData testData;

    @BeforeEach
    public void setup() {
        testData = new DishesTestData(
                userRepository,
                allergenRepository,
                dishRepository
        );
        testData.init();
        testDishId = testData.getTestDishId();
    }

    @AfterEach
    public void tearDown() {
        testData.cleanUp();
    }

    @Test
    @WithMockUser(value = "pesho", authorities = {"ADMIN"})
    void addDish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(DISHES_CONTROLLER_PREFIX + "/add")
                .param("name", "test dish")
                .param("user", "pesho")
                .param("ingredients", "ingredients")
                .param("proportions", "2")
                .param("price", "2")
                .param("allergens", AllergensEnum.EGGS.name())
                .param("imageUrl", "imgUrl")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(3, dishRepository.count());
    }


}

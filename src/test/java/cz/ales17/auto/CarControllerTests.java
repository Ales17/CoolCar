package cz.ales17.auto;

import cz.ales17.auto.controller.CarController;
import cz.ales17.auto.entity.Brand;
import cz.ales17.auto.entity.Car;
import cz.ales17.auto.security.SecurityConfig;
import cz.ales17.auto.service.BrandService;
import cz.ales17.auto.service.CarService;
import cz.ales17.auto.service.StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(CarController.class)
public class CarControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CarService carService;
    @MockBean
    BrandService brandService;
    @MockBean
    StorageService storageService;


    @BeforeEach
    void init() {
        Brand b1 = new Brand("Toyota");

        Car c1 = new Car();
        c1.setBrand(b1);

        List<Car> cars = List.of(c1);

        List<Brand> brands = List.of(b1);

        when(carService.getCarsByCurrentUser()).thenReturn(cars);

        when(brandService.getBrands()).thenReturn(brands);


    }

    @WithMockUser(username = "user")
    @Test
    void shouldReturnCarListPage() throws Exception {
        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(view().name("cars-list"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    @WithMockUser(username = "user")
    void shouldReturnCarFormPage() throws Exception {
        mockMvc.perform(get("/cars/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("cars-create"));
    }









}

package com.pichincha.crd.automotriz.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.crd.automotriz.domain.enums.VehicleState;
import com.pichincha.crd.automotriz.repository.VehicleRepository;
import com.pichincha.crd.automotriz.service.dto.VehicleDto;
import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import com.pichincha.crd.automotriz.service.dto.entity.Vehicle;
import com.pichincha.crd.automotriz.utils.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.pichincha.crd.automotriz.domain.Routes.BASE_VEHICLES;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
public class VehicleControllerTest extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        vehicleRepository.save(getVehicle());
    }

    @AfterEach
    public void tierDown() {
        vehicleRepository.delete(getVehicle());
    }

    @Test
    public void givenARequestThenReturnVehicleList() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get(BASE_VEHICLES)
                        .accept(APPLICATION_JSON_UTF8_VALUE)
                        .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        List<VehicleDto> vehicles = objectMapper.readValue(response, new TypeReference<List<VehicleDto>>() {});
        assertNotNull(response);
        assertTrue(vehicles.size() > 0);
    }

    private Vehicle getVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setPlate("PBC-1987");
        vehicle.setModel("tiggo-7");
        vehicle.setChassisNumber("123dsfgsd3");
        vehicle.setBrand(new Brand(1L,"chery"));
        vehicle.setType("suv");
        vehicle.setEngineDisplacement(1000);
        vehicle.setValuation(25000.123d);
        vehicle.setState(VehicleState.Available);
        return vehicle;
    }

}

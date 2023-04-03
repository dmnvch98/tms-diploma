package com.example.convservice.controllers;

import com.example.convservice.converters.AddressConverter;
import com.example.convservice.facades.AddressFacade;
import com.example.convservice.model.Address;
import com.example.convservice.repositories.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest({AddressController.class})
class AddressControllerTest {
    @MockBean
    AddressRepository addressRepository;
    @MockBean
    AddressConverter addressConverter;
    @MockBean
    AddressFacade addressFacade;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void returnAddressWhenSavingAddress() throws Exception {
        Address address = Address
            .builder()
            .address("Broadway")
            .addressId(1L)
            .tutorId(12L)
            .city("New York")
            .countryId(1L)
            .latitude("123")
            .longitude("456")
            .build();

        given(addressRepository.save(address)).willReturn(address);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addresses/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(address))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }


    @Test
    void shouldReturnAddressByValidTutorId() throws Exception {
        List<Address> addresses = Arrays.asList(Address.builder().build(), Address.builder().build());

        Long tutorId = 12L;

        given(addressRepository.findDistinctByTutorId(tutorId)).willReturn(addresses);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/addresses/tutors/{tutorId}", tutorId)
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());

    }

    @Test
    void shouldNotReturnAddressByInvalidTutorId() throws Exception {
        String tutorId = "invalid";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/addresses/tutors/{tutorId}", tutorId)
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isBadRequest());

        then(addressRepository).shouldHaveZeroInteractions();
    }

}
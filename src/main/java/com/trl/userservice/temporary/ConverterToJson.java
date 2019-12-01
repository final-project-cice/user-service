package com.trl.userservice.temporary;

import com.trl.userservice.repository.entity.AddressEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterToJson {

    private static AddressEntity addressEntity = new AddressEntity();

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        // Initialization
        init();


        try {
            String jsonBookDTO = objectMapper.writeValueAsString(addressEntity);
            System.out.println(jsonBookDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private static void init() {
        addressEntity.setCountry("Spain");
        addressEntity.setCity("Madrid");
        addressEntity.setStreet("Calle");
        addressEntity.setHouseNumber("1");
        addressEntity.setPostcode(111111);
    }
}

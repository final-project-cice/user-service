package com.trl.userservice.temporary;

import com.trl.userservice.repository.entity.AddressEntity;
import com.trl.userservice.repository.entity.BankDataEntity;
import com.trl.userservice.repository.entity.UserEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConverterToJson {

    private static AddressEntity addressEntity = new AddressEntity();
    private static BankDataEntity bankDataEntity = new BankDataEntity();
    private static UserEntity userEntity = new UserEntity();

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        // Initialization
//        initAddress();
//        try {
//            String jsonBookDTO = objectMapper.writeValueAsString(addressEntity);
//            System.out.println(jsonBookDTO);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }


        /*// Initialization
        initBankData();
        try {
            String jsonBookDTO = objectMapper.writeValueAsString(bankDataEntity);
            System.out.println(jsonBookDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/


        // Initialization
        initUser();
        try {
            String jsonBookDTO = objectMapper.writeValueAsString(userEntity);
            System.out.println(jsonBookDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private static void initAddress() {
        addressEntity.setCountry("Spain");
        addressEntity.setCity("Madrid");
        addressEntity.setStreet("Calle");
        addressEntity.setHouseNumber("1");
        addressEntity.setPostcode(111111);
    }

    private static void initBankData() {
        bankDataEntity.setBankAccountNumber("0123456789");
        bankDataEntity.setDateOfExpiry(LocalDate.of(2000, 1, 1));
        bankDataEntity.setCvi(111);
    }

    private static void initUser() {
        userEntity.setFirstName("Tsyupryk");
        userEntity.setLastName("Roman");
        userEntity.setUserName("TRL");
        userEntity.setEmail("tsyupryk.roman@gmail.com");
        userEntity.setPassword("strong password");
        initBankData();
        userEntity.setBankData(new ArrayList<BankDataEntity>());
        initAddress();
        userEntity.setAddress(new ArrayList<AddressEntity>());
        userEntity.setBirthday(LocalDate.of(1988, 6, 26));

    }
}

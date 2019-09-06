package com.trl.user.service.converter;

import com.trl.user.controller.dto.AddressDTO;
import com.trl.user.controller.dto.UserDTO;
import com.trl.user.repository.entity.AddressEntity;
import com.trl.user.repository.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ConverterAddressTest {

    private AddressDTO expected_AddressDTO;

    private AddressEntity expected_AddressEntity;

    private Set<AddressDTO> expected_AddressDTOSet;
    private Set<AddressEntity> expected_AddressEntitySet;

    @Before
    public void setUp() throws Exception {

        expected_AddressDTO = new AddressDTO(1L, "Spain", "Madrid", "Calle", "12", 1111111,
                new UserDTO(1L, "First Name User", "Last Name User",
                        "tsyupryk.roman.lyubomyrovych@gmail.com", "strong_password",
                        LocalDate.of(1970, Month.JANUARY, 1))
        );

        expected_AddressEntity = new AddressEntity(1L, "Spain", "Madrid", "Calle", "12", 1111111,
                new UserEntity(1L, "First Name User", "Last Name User",
                        "tsyupryk.roman.lyubomyrovych@gmail.com", "strong_password",
                        LocalDate.of(1970, Month.JANUARY, 1))
        );

        // =================== Set
        expected_AddressDTOSet = new HashSet<>();
        expected_AddressDTOSet.add(new AddressDTO(1L, "Spain", "Madrid", "Calle", "12", 1111111,
                new UserDTO(1L, "First Name User_1", "Last Name User_1",
                        "user_1@gmail.com", "strong_password",
                        LocalDate.of(1970, Month.JANUARY, 1)))
        );

        expected_AddressDTOSet.add(new AddressDTO(2L, "Spain", "Barcelona", "Calle", "1", 22222222,
                new UserDTO(2L, "First Name User_2", "Last Name User_2",
                        "user_2@gmail.com", "strong_password",
                        LocalDate.of(1970, Month.JANUARY, 1)))
        );


        expected_AddressEntitySet = new HashSet<>();
        expected_AddressEntitySet.add(new AddressEntity(1L, "Spain", "Madrid", "Calle", "12", 1111111,
                new UserEntity(1L, "First Name User_1", "Last Name User_1",
                        "user_1@gmail.com", "strong_password",
                        LocalDate.of(1970, Month.JANUARY, 1)))
        );

        expected_AddressEntitySet.add(new AddressEntity(2L, "Spain", "Barcelona", "Calle", "1", 22222222,
                new UserEntity(2L, "First Name User_2", "Last Name User_2",
                        "user_2@gmail.com", "strong_password",
                        LocalDate.of(1970, Month.JANUARY, 1)))
        );

    }

    //==================================================================================================================
    @Test
    public void mapEntityToDTO() {
        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(expected_AddressEntity);
        assertEquals(expected_AddressDTO, actual_AddressDTO);
    }

    @Test
    public void mapEntityToDTO_Parameter_Null() {
        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(null);
        assertNull(actual_AddressDTO);
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Id() {
        Long incorrectId = 1000000000000000000L;
        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(expected_AddressEntity);
        assertNotEquals(incorrectId, actual_AddressDTO.getId());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Country() {
        String incorrectCountry = "___";

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(expected_AddressEntity);
        assertNotEquals(incorrectCountry, actual_AddressDTO.getCountry());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_City() {
        String incorrectCity = "___";

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(expected_AddressEntity);
        assertNotEquals(incorrectCity, actual_AddressDTO.getCity());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Street() {
        String incorrectStreet = "___";

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(expected_AddressEntity);
        assertNotEquals(incorrectStreet, actual_AddressDTO.getStreet());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_HouseNumber() {
        String incorrectHouseNumber = "___";

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(expected_AddressEntity);
        assertNotEquals(incorrectHouseNumber, actual_AddressDTO.getHouseNumber());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_PostCode() {
        Integer incorrectPostcode = 1000000000;

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(expected_AddressEntity);
        assertNotEquals(incorrectPostcode, actual_AddressDTO.getPostcode());
    }

    @Test
    public void mapSetEntityToSetDTO() {
        Set<AddressDTO> actual_AddressDTOSet = ConverterAddress.mapSetEntityToSetDTO(expected_AddressEntitySet);
        assertEquals(expected_AddressDTOSet, actual_AddressDTOSet);
    }

    @Test
    public void mapSetEntityToSetDTO_Parameter_Null() {
        Set<AddressDTO> actual_AddressDTOSet = ConverterAddress.mapSetEntityToSetDTO(null);
        assertNull(actual_AddressDTOSet);
    }

    //==================================================================================================================
    @Test
    public void mapDTOToEntity() {
        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(expected_AddressDTO);
        assertEquals(expected_AddressEntity, actual_AddressEntity);
    }

    @Test
    public void mapDTOToEntity_Parameter_Null() {
        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(null);
        assertNull(actual_AddressEntity);
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Id() {
        Long incorrectId = 1000000000000000000L;

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(expected_AddressDTO);
        assertNotEquals(incorrectId, actual_AddressEntity.getId());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Country() {
        String incorrectCountry = "___";

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(expected_AddressDTO);
        assertNotEquals(incorrectCountry, actual_AddressEntity.getCountry());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_City() {
        String incorrectCity = "___";

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(expected_AddressDTO);
        assertNotEquals(incorrectCity, actual_AddressEntity.getCity());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Street() {
        String incorrectStreet = "___";

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(expected_AddressDTO);
        assertNotEquals(incorrectStreet, actual_AddressEntity.getStreet());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_HouseNumber() {
        String incorrectHouseNumber = "___";

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(expected_AddressDTO);
        assertNotEquals(incorrectHouseNumber, actual_AddressEntity.getHouseNumber());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_PostCode() {
        Integer incorrectPostcode = 1000000000;

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(expected_AddressDTO);
        assertNotEquals(incorrectPostcode, actual_AddressEntity.getPostcode());
    }

    @Test
    public void mapSetDTOToSetEntity() {
        Set<AddressEntity> actual_AddressEntitySet = ConverterAddress.mapSetDTOToSetEntity(expected_AddressDTOSet);
        assertEquals(expected_AddressEntitySet, actual_AddressEntitySet);
    }

    @Test
    public void mapSetDTOToSetEntity_Parameter_Null() {
        Set<AddressEntity> actual_AddressEntitySet = ConverterAddress.mapSetDTOToSetEntity(null);
        assertNull(actual_AddressEntitySet);
    }

}
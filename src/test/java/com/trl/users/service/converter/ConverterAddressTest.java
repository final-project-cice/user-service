package com.trl.users.service.converter;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.repository.entity.AddressEntity;
import com.trl.users.repository.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

        expected_AddressDTO = new AddressDTO()
                .setId(1L)
                .setCountry("Spain")
                .setCity("Madrid")
                .setStreet("Calle")
                .setHouseNumber("12")
                .setPostcode(1111111)
                .setUserId(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime()));

        expected_AddressEntity = new AddressEntity()
                .setId(1L)
                .setCountry("Spain")
                .setCity("Madrid")
                .setStreet("Calle")
                .setHouseNumber("12")
                .setPostcode(1111111)
                .setUserId(
                        new UserEntity()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime()));

        // =================== Set
        expected_AddressDTOSet = new HashSet<>();
        expected_AddressDTOSet.add(new AddressDTO()
                .setId(1L)
                .setCountry("Spain")
                .setCity("Madrid")
                .setStreet("Calle")
                .setHouseNumber("12")
                .setPostcode(1111111)
                .setUserId(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));

        expected_AddressDTOSet.add(new AddressDTO()
                .setId(2L)
                .setCountry("Spain")
                .setCity("Barcelona")
                .setStreet("Calle")
                .setHouseNumber("1")
                .setPostcode(22222222)
                .setUserId(
                        new UserDTO()
                                .setId(2L)
                                .setFirstName("First Name User_2")
                                .setLastName("Last Name User_2")
                                .setEmail("user_2@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));


        expected_AddressEntitySet = new HashSet<>();
        expected_AddressEntitySet.add(new AddressEntity()
                .setId(1L)
                .setCountry("Spain")
                .setCity("Madrid")
                .setStreet("Calle")
                .setHouseNumber("12")
                .setPostcode(1111111)
                .setUserId(
                        new UserEntity()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));

        expected_AddressEntitySet.add(new AddressEntity()
                .setId(2L)
                .setCountry("Spain")
                .setCity("Barcelona")
                .setStreet("Calle")
                .setHouseNumber("1")
                .setPostcode(22222222)
                .setUserId(
                        new UserEntity()
                                .setId(2L)
                                .setFirstName("First Name User_2")
                                .setLastName("Last Name User_2")
                                .setEmail("user_2@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));

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

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(new AddressEntity()
                .setId(1000L));
        assertNotEquals(expected_AddressDTO.getId(), actual_AddressDTO.getId());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Country() {

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(new AddressEntity()
                .setCountry("Spain***"));
        assertNotEquals(expected_AddressDTO.getCountry(), actual_AddressDTO.getCountry());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_City() {

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(new AddressEntity()
                .setCity("Madrid***"));
        assertNotEquals(expected_AddressDTO.getCity(), actual_AddressDTO.getCity());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Street() {

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(new AddressEntity()
                .setStreet("Calle***"));
        assertNotEquals(expected_AddressDTO.getStreet(), actual_AddressDTO.getStreet());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_HouseNumber() {

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(new AddressEntity()
                .setHouseNumber("12000"));
        assertNotEquals(expected_AddressDTO.getHouseNumber(), actual_AddressDTO.getHouseNumber());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_PostCode() {

        AddressDTO actual_AddressDTO = ConverterAddress.mapEntityToDTO(new AddressEntity()
                .setPostcode(1111111000));
        assertNotEquals(expected_AddressDTO.getPostcode(), actual_AddressDTO.getPostcode());
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

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(new AddressDTO()
                .setId(1000L)
                .setUserId(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));
        assertNotEquals(expected_AddressEntity.getId(), actual_AddressEntity.getId());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Country() {

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(new AddressDTO()
                .setCountry("Spain***")
                .setUserId(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));
        assertNotEquals(expected_AddressEntity.getCountry(), actual_AddressEntity.getCountry());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_City() {

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(new AddressDTO()
                .setId(1L)
                .setCity("Madrid***")
                .setUserId(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));
        assertNotEquals(expected_AddressEntity.getCity(), actual_AddressEntity.getCity());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Street() {

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(new AddressDTO()
                .setId(1L)
                .setStreet("Calle***")
                .setUserId(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));
        assertNotEquals(expected_AddressEntity.getStreet(), actual_AddressEntity.getStreet());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_HouseNumber() {

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(new AddressDTO()
                .setId(1L)
                .setHouseNumber("12***")
                .setUserId(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));
        assertNotEquals(expected_AddressEntity.getHouseNumber(), actual_AddressEntity.getHouseNumber());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_PostCode() {

        AddressEntity actual_AddressEntity = ConverterAddress.mapDTOToEntity(new AddressDTO()
                .setId(1L)
                .setPostcode(1111111000)
                .setUserId(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())));
        assertNotEquals(expected_AddressEntity.getPostcode(), actual_AddressEntity.getPostcode());
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
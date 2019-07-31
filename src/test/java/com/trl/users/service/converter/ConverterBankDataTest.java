package com.trl.users.service.converter;

import com.trl.users.controller.dto.BankDataDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.repository.entity.BankDataEntity;
import com.trl.users.repository.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ConverterBankDataTest {

    private BankDataDTO expected_BankDataDTO;
    private BankDataEntity expected_BankDataEntity;

    private Set<BankDataDTO> expected_BankDataDTOSet;
    private Set<BankDataEntity> expected_BankDataEntitySet;

    @Before
    public void setUp() throws Exception {

        expected_BankDataDTO = new BankDataDTO()
                .setId(1L)
                .setBankAccountNumber("1111111111111111111111111111")
                .setDateOfExpiry(LocalDate.of(1970, Month.JANUARY, 1))
                .setCvi(111)
                .setUser(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1)));

        expected_BankDataEntity = new BankDataEntity()
                .setId(1L)
                .setBankAccountNumber("1111111111111111111111111111")
                .setDateOfExpiry(LocalDate.of(1970, Month.JANUARY, 1))
                .setCvi(111)
                .setUser(
                        new UserEntity()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1)));

        expected_BankDataDTOSet = new HashSet<>();
        expected_BankDataDTOSet.add(new BankDataDTO()
                .setId(1L)
                .setBankAccountNumber("1111111111111111111111111111")
                .setDateOfExpiry(LocalDate.of(1970, Month.JANUARY, 1))
                .setCvi(111)
                .setUser(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        expected_BankDataDTOSet.add(new BankDataDTO()
                .setId(2L)
                .setBankAccountNumber("2222222222222222222222222222")
                .setDateOfExpiry(LocalDate.of(1970, Month.FEBRUARY, 2))
                .setCvi(222)
                .setUser(
                        new UserDTO()
                                .setId(2L)
                                .setFirstName("First Name User_2")
                                .setLastName("Last Name User_2")
                                .setEmail("user_2@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));

        expected_BankDataEntitySet = new HashSet<>();
        expected_BankDataEntitySet.add(new BankDataEntity()
                .setId(1L)
                .setBankAccountNumber("1111111111111111111111111111")
                .setDateOfExpiry(LocalDate.of(1970, Month.JANUARY, 1))
                .setCvi(111)
                .setUser(
                        new UserEntity()
                                .setId(1L)
                                .setFirstName("First Name User_1")
                                .setLastName("Last Name User_1")
                                .setEmail("user_1@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        expected_BankDataEntitySet.add(new BankDataEntity()
                .setId(2L)
                .setBankAccountNumber("2222222222222222222222222222")
                .setDateOfExpiry(LocalDate.of(1970, Month.FEBRUARY, 2))
                .setCvi(222)
                .setUser(
                        new UserEntity()
                                .setId(2L)
                                .setFirstName("First Name User_2")
                                .setLastName("Last Name User_2")
                                .setEmail("user_2@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));

    }

    // =================================================================================================================
    // ================================================================================================ mapEntityToDTO()
    // =================================================================================================================
    @Test
    public void mapEntityToDTO() {
        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(expected_BankDataEntity);
        assertEquals(expected_BankDataDTO, actual_BankDataDTO);
    }

    @Test
    public void mapEntityToDTO_Parameter_Null() {
        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(null);
        assertNull(actual_BankDataDTO);
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Id() {
        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(new BankDataEntity()
                .setId(1000L)
                .setUser(
                        new UserEntity()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        assertNotEquals(expected_BankDataDTO.getId(), actual_BankDataDTO.getId());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_BankAccountNumber() {
        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(new BankDataEntity()
                .setBankAccountNumber("1111111111111111111111111111***")
                .setUser(
                        new UserEntity()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        assertNotEquals(expected_BankDataDTO.getBankAccountNumber(), actual_BankDataDTO.getBankAccountNumber());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_DateOfExpiry() {
        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(new BankDataEntity()
                .setDateOfExpiry(LocalDate.of(1970, Month.JANUARY, 2))
                .setUser(
                        new UserEntity()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        assertNotEquals(expected_BankDataDTO.getDateOfExpiry(), actual_BankDataDTO.getDateOfExpiry());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Cvi() {
        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(new BankDataEntity()
                .setId(1L)
                .setBankAccountNumber("1111111111111111111111111111")
                .setDateOfExpiry(LocalDate.of(1970, Month.JANUARY, 1))
                .setCvi(112)
                .setUser(
                        new UserEntity()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        assertNotEquals(expected_BankDataDTO.getCvi(), actual_BankDataDTO.getCvi());
    }

    // =================================================================================================================
    // ========================================================================================== mapSetEntityToSetDTO()
    // =================================================================================================================
    @Test
    public void mapSetEntityToSetDTO() {
        Set<BankDataDTO> actual_BankDataDTOSet = ConverterBankData.mapSetEntityToSetDTO(expected_BankDataEntitySet);
        assertEquals(expected_BankDataDTOSet, actual_BankDataDTOSet);
    }

    @Test
    public void mapSetEntityToSetDTO_Parameter_Null() {
        // TODO: Terminar este test.
        Set<BankDataDTO> actual_BankDataDTOSet = ConverterBankData.mapSetEntityToSetDTO(null);
        assertNull(actual_BankDataDTOSet);
    }

    // =================================================================================================================
    // ================================================================================================ mapDTOToEntity()
    // =================================================================================================================
    @Test
    public void mapDTOToEntity() {
        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(expected_BankDataDTO);
        assertEquals(expected_BankDataEntity, actual_BankDataEntity);
    }

    @Test
    public void mapDTOToEntity_Parameter_Null() {
        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(null);
        assertNull(actual_BankDataEntity);
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Id() {
        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(new BankDataDTO()
                .setId(1000L)
                .setUser(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        assertNotEquals(expected_BankDataEntity.getId(), actual_BankDataEntity.getId());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_BankAccountNumber() {
        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(new BankDataDTO()
                .setBankAccountNumber("1111111111111111111111111111***")
                .setUser(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        assertNotEquals(expected_BankDataEntity.getBankAccountNumber(), actual_BankDataEntity.getBankAccountNumber());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_DateOfExpiry() {
        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(new BankDataDTO()
                .setDateOfExpiry(LocalDate.of(1970, Month.JANUARY, 2))
                .setUser(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        assertNotEquals(expected_BankDataEntity.getDateOfExpiry(), actual_BankDataEntity.getDateOfExpiry());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Cvi() {
        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(new BankDataDTO()
                .setCvi(112)
                .setUser(
                        new UserDTO()
                                .setId(1L)
                                .setFirstName("First Name User")
                                .setLastName("Last Name User")
                                .setEmail("tsyupryk.roman.lyubomyrovych@gmail.com")
                                .setPassword("strong_password")
                                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1))));
        assertNotEquals(expected_BankDataEntity.getCvi(), actual_BankDataEntity.getCvi());
    }

    // =================================================================================================================
    // ========================================================================================== mapSetDTOToSetEntity()
    // =================================================================================================================
    @Test
    public void mapSetDTOToSetEntity() {
        Set<BankDataEntity> actual_BankDataEntitySet = ConverterBankData.mapSetDTOToSetEntity(expected_BankDataDTOSet);
        assertEquals(expected_BankDataEntitySet, actual_BankDataEntitySet);
    }

    @Test
    public void mapSetDTOToSetEntity_Parameter_Null() {
        Set<BankDataEntity> actual_BankDataEntitySet = ConverterBankData.mapSetDTOToSetEntity(null);
        assertNull(actual_BankDataEntitySet);
    }

}
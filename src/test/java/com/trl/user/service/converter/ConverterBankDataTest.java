package com.trl.user.service.converter;

import com.trl.user.controller.dto.BankDataDTO;
import com.trl.user.controller.dto.UserDTO;
import com.trl.user.repository.entity.BankDataEntity;
import com.trl.user.repository.entity.UserEntity;
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

        expected_BankDataDTO = new BankDataDTO(1L, "1111111111111111111111111111", LocalDate.of(1970, Month.JANUARY, 1), 111,
                new UserDTO(1L, "First Name User", "Last Name User", "tsyupryk.roman.lyubomyrovych@gmail.com", "strong_password", LocalDate.of(1970, Month.JANUARY, 1)));

        expected_BankDataEntity = new BankDataEntity(1L, "1111111111111111111111111111", LocalDate.of(1970, Month.JANUARY, 1), 111,
                new UserEntity(1L, "First Name User", "Last Name User", "tsyupryk.roman.lyubomyrovych@gmail.com", "strong_password", LocalDate.of(1970, Month.JANUARY, 1)));

        expected_BankDataDTOSet = new HashSet<>();
        expected_BankDataDTOSet.add(new BankDataDTO(1L, "1111111111111111111111111111", LocalDate.of(1970, Month.JANUARY, 1), 111,
                new UserDTO(1L, "First Name User_1", "Last Name User_1", "user_1@gmail.com", "strong_password", LocalDate.of(1970, Month.JANUARY, 1))));
        expected_BankDataDTOSet.add(new BankDataDTO(2L, "2222222222222222222222222222", LocalDate.of(1970, Month.FEBRUARY, 2), 222,
                new UserDTO(2L, "First Name User_2", "Last Name User_2", "user_2@gmail.com", "strong_password", LocalDate.of(1970, Month.JANUARY, 1))));

        expected_BankDataEntitySet = new HashSet<>();
        expected_BankDataEntitySet.add(new BankDataEntity(1L, "1111111111111111111111111111", LocalDate.of(1970, Month.JANUARY, 1), 111,
                new UserEntity(1L, "First Name User_1", "Last Name User_1", "user_1@gmail.com", "strong_password", LocalDate.of(1970, Month.JANUARY, 1))));
        expected_BankDataEntitySet.add(new BankDataEntity(2L, "2222222222222222222222222222", LocalDate.of(1970, Month.FEBRUARY, 2), 222,
                new UserEntity(2L, "First Name User_2", "Last Name User_2", "user_2@gmail.com", "strong_password", LocalDate.of(1970, Month.JANUARY, 1))));

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
        Long incorrectId = 1000000000000000000L;

        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(expected_BankDataEntity);
        assertNotEquals(incorrectId, actual_BankDataDTO.getId());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_BankAccountNumber() {
        String incorrectAccountNumber = "___";

        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(expected_BankDataEntity);
        assertNotEquals(incorrectAccountNumber, actual_BankDataDTO.getBankAccountNumber());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_DateOfExpiry() {
        LocalDate incorrectDateOfExpiry = LocalDate.MAX;

        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(expected_BankDataEntity);
        assertNotEquals(incorrectDateOfExpiry, actual_BankDataDTO.getDateOfExpiry());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Cvi() {
        Integer incorrectCvi = 1000000000;

        BankDataDTO actual_BankDataDTO = ConverterBankData.mapEntityToDTO(expected_BankDataEntity);
        assertNotEquals(incorrectCvi, actual_BankDataDTO.getCvi());
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
        Long incorrectId = 1000000000000000000L;

        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(expected_BankDataDTO);
        assertNotEquals(incorrectId, actual_BankDataEntity.getId());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_BankAccountNumber() {
        String incorrectAccountNumber = "___";

        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(expected_BankDataDTO);
        assertNotEquals(incorrectAccountNumber, actual_BankDataEntity.getBankAccountNumber());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_DateOfExpiry() {
        LocalDate incorrectDateOfExpiry = LocalDate.MAX;

        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(expected_BankDataDTO);
        assertNotEquals(incorrectDateOfExpiry, actual_BankDataEntity.getDateOfExpiry());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Cvi() {
        Integer incorrectCvi = 1000000000;

        BankDataEntity actual_BankDataEntity = ConverterBankData.mapDTOToEntity(expected_BankDataDTO);
        assertNotEquals(incorrectCvi, actual_BankDataEntity.getCvi());
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
package com.trl.user.service.converter;

import com.trl.user.controller.dto.UserDTO;
import com.trl.user.repository.entity.UserEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ConverterUserTest {

    private UserDTO expected_UserDTO;
    private UserEntity expected_UserEntity;

    private Set<UserDTO> expected_UserDTOSet;
    private Set<UserEntity> expected_UserEntitySet;

    @Before
    public void setUp() throws Exception {

        expected_UserEntity = new UserEntity(
                1L,
                "First Name User_1",
                "Last Name User_1",
                "user_1@email.com",
                "strong_password",
                LocalDate.of(1970, Month.JANUARY, 1)
        );

        expected_UserDTO = new UserDTO(
                1L,
                "First Name User_1",
                "Last Name User_1",
                "user_1@email.com",
                "strong_password",
                LocalDate.of(1970, Month.JANUARY, 1)
        );

        expected_UserDTOSet = new HashSet<>();
        expected_UserDTOSet.add(new UserDTO(
                1L,
                "First Name User_1",
                "Last Name User_1",
                "user_1@email.com",
                "strong_password",
                LocalDate.of(1970, Month.JANUARY, 1))
        );
        expected_UserDTOSet.add(new UserDTO(
                2L,
                "First Name User_2",
                "Last Name User_2",
                "user_2@email.com",
                "strong_password",
                LocalDate.of(1970, Month.FEBRUARY, 2))
        );

        expected_UserEntitySet = new HashSet<>();
        expected_UserEntitySet.add(new UserEntity(
                1L,
                "First Name User_1",
                "Last Name User_1",
                "user_1@email.com",
                "strong_password",
                LocalDate.of(1970, Month.JANUARY, 1))
        );
        expected_UserEntitySet.add(new UserEntity(
                2L,
                "First Name User_2",
                "Last Name User_2",
                "user_2@email.com",
                "strong_password",
                LocalDate.of(1970, Month.FEBRUARY, 2))
        );

    }

    // =================================================================================================================
    // ================================================================================================ mapEntityToDTO()
    // =================================================================================================================
    @Test
    public void mapEntityToDTO() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(expected_UserEntity);
        Assert.assertEquals(expected_UserDTO, actual_UserDTO);
    }

    @Test
    public void mapEntityToDTO_Parameter_Null() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(null);
        Assert.assertNull(actual_UserDTO);
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Id() {
        Long incorrectId = 1000000000000000000L;

        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(expected_UserEntity);
        assertNotEquals(incorrectId, actual_UserDTO.getId());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_FirstName() {
        String incorrectFirstName = "___";

        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(expected_UserEntity);
        assertNotEquals(incorrectFirstName, actual_UserDTO.getFirstName());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_LastName() {
        String incorrectLastName = "___";

        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(expected_UserEntity);
        assertNotEquals(incorrectLastName, actual_UserDTO.getLastName());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Email() {
        String incorrectEmail = "___";

        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(expected_UserEntity);
        assertNotEquals(incorrectEmail, actual_UserDTO.getEmail());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Password() {
        String incorrectPassword = "___";

        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(expected_UserEntity);
        assertNotEquals(incorrectPassword, actual_UserDTO.getPassword());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Birthday() {
        LocalDate incorrectBirthday = LocalDate.MAX;

        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(expected_UserEntity);
        assertNotEquals(incorrectBirthday, actual_UserDTO.getBirthday());
    }

    // =================================================================================================================
    // ========================================================================================== mapSetEntityToSetDTO()
    // =================================================================================================================
    @Test
    public void mapSetEntityToSetDTO() {
        Set<UserDTO> actual_UserDTOSet = ConverterUser.mapSetEntityToSetDTO(expected_UserEntitySet);
        assertEquals(expected_UserDTOSet, actual_UserDTOSet);
    }

    @Test
    public void mapSetEntityToSetDTO_Parameter_Null() {
        Set<UserDTO> actual_UserDTOSet = ConverterUser.mapSetEntityToSetDTO(null);
        assertNull(actual_UserDTOSet);
    }

    // =================================================================================================================
    // ================================================================================================ mapDTOToEntity()
    // =================================================================================================================
    @Test
    public void mapDTOToEntity() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(expected_UserDTO);
        assertEquals(expected_UserEntity, actual_UserEntity);
    }

    @Test
    public void mapDTOToEntity_Parameter_Null() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(null);
        assertNull(actual_UserEntity);
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Id() {
        Long incorrectId = 1000000000000000000L;

        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(expected_UserDTO);
        assertNotEquals(incorrectId, actual_UserEntity.getId());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_FirstName() {
        String incorrectFirstName = "___";

        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(expected_UserDTO);
        assertNotEquals(incorrectFirstName, actual_UserEntity.getFirstName());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_LastName() {
        String incorrectLastName = "___";

        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(expected_UserDTO);
        assertNotEquals(incorrectLastName, actual_UserEntity.getLastName());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Email() {
        String incorrectEmail = "___";

        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(expected_UserDTO);
        assertNotEquals(incorrectEmail, actual_UserEntity.getEmail());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Password() {
        String incorrectPassword = "___";

        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(expected_UserDTO);
        assertNotEquals(incorrectPassword, actual_UserEntity.getPassword());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Birthday() {
        LocalDate incorrectBirthday = LocalDate.MAX;

        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(expected_UserDTO);
        assertNotEquals(incorrectBirthday, actual_UserEntity.getBirthday());
    }

    // =================================================================================================================
    // ========================================================================================== mapSetDTOToSetEntity()
    // =================================================================================================================
    @Test
    public void mapSetDTOToSetEntity() {
        Set<UserEntity> actual_UserEntitySet = ConverterUser.mapSetDTOToSetEntity(expected_UserDTOSet);
        assertEquals(expected_UserEntitySet, actual_UserEntitySet);
    }

    @Test
    public void mapSetDTOToSetEntity_Parameter_Null() {
        Set<UserEntity> actual_UserEntitySet = ConverterUser.mapSetDTOToSetEntity(null);
        assertNull(actual_UserEntitySet);

    }

}
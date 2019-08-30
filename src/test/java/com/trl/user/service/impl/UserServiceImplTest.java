package com.trl.user.service.impl;

import com.trl.user.controller.dto.UserDTO;
import com.trl.user.exceptions.UserWithEmailExistException;
import com.trl.user.repository.UserRepository;
import com.trl.user.repository.entity.UserEntity;
import com.trl.user.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    private UserEntity userEntity;
    private UserDTO userDTO;
    private UserDTO userDTOExpected;

    @Before
    public void setUp() throws Exception {

        userEntity = new UserEntity(1L, "User_FirstName_1", "User_LastName_1", "user_1@email.com", "strong_password", LocalDate.of(1970, Month.JANUARY, 1));

        userDTO = new UserDTO(1L, "User_FirstName_1", "User_LastName_1", "user_1@email.com", "strong_password", LocalDate.of(1970, Month.JANUARY, 1));

        userDTOExpected = new UserDTO(2L, "User_FirstName_144444444444444444444", "User_LastName_1", "user_1@email.com", "strong_password", LocalDate.of(1970, Month.JANUARY, 1));
    }

    @After
    public void tearDown() throws Exception {
        // TODO: Terminar este test.
    }

    @Test
    public void create() throws UserWithEmailExistException {
        // TODO: Terminar este test.
//        System.out.println(userRepository.findById(1L));

//        UserDTO userCreated = userService.create(userDTO);

//        Mockito.doReturn(userEntity).when(userRepository).save(new UserEntity()
//                .setId(1L)
//                .setFirstName("User_FirstName_1")
//                .setLastName("User_LastName_1")
//                .setEmail("user_1@email.com")
//                .setPassword("strong_password")
//                .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime()));

//        System.out.println("*************************************************" + userCreated);


//        UserDTO userDTOActual = Mockito.verify(userService, Mockito.times(1)).create(userDTO);
//        Assert.assertEquals(userDTOExpected, userDTOActual);
//        UserEntity save = Mockito.verify(userRepository, Mockito.times(1)).save(userEntity);


        UserDTO userDTO = Mockito.doReturn(userDTOExpected).when(userService).create(this.userDTO);
    }

    @Test
    public void update() {
        // TODO: Terminar este test.
    }

    @Test
    public void updateFirstName() {
        // TODO: Terminar este test.
    }

    @Test
    public void updateLastName() {
        // TODO: Terminar este test.
    }

    @Test
    public void updateBankData() {
        // TODO: Terminar este test.
    }

    @Test
    public void updateAddress() {
        // TODO: Terminar este test.
    }

    @Test
    public void updateBirthday() {
        // TODO: Terminar este test.
    }

    @Test
    public void delete() {
        // TODO: Terminar este test.
    }

    @Test
    public void findById() {
        // TODO: Terminar este test.
    }

    @Test
    public void findByFirstName() {
        // TODO: Terminar este test.
    }

    @Test
    public void findByLastName() {
        // TODO: Terminar este test.
    }

    @Test
    public void findByFirstNameAndLastName() {
        // TODO: Terminar este test.
    }

    @Test
    public void findByAddress() {
        // TODO: Terminar este test.
    }

    @Test
    public void findByBirthday() {
        // TODO: Terminar este test.
    }

    @Test
    public void findAll() {
        // TODO: Terminar este test.
    }

}
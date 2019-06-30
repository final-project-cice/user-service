package com.trl.users.service.impl;

import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.ExceptionUserNotHaveAddress;
import com.trl.users.exceptions.ExceptionUserNotHaveBankData;
import com.trl.users.exceptions.ExceptionUserWithEmailExist;
import com.trl.users.exceptions.ExceptionUserWithIdNotExist;
import com.trl.users.repository.UserRepository;
import com.trl.users.repository.entity.UserEntity;
import com.trl.users.service.UserService;
import com.trl.users.service.converter.ConverterUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 */
@AllArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BankDataServiceImpl bankDataService;
    private final AddressServiceImpl addressService;

    /**
     * @param user
     * @return
     */
    @Override
    public UserDTO create(UserDTO user) throws ExceptionUserWithEmailExist {
        UserDTO userResult = null;

        log.debug("************ create() ---> user = " + user);

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.debug("************ updateEmail() ---> user with this email = '" + user.getEmail() + "' exist.");
            throw new ExceptionUserWithEmailExist("User with this email = '" + user.getEmail() + "' exist. It is not allowed to register multiple users with the same e-mail.");
        }

        UserEntity userFromRepositoryById = userRepository.save(ConverterUser.mapDTOToEntity(user));

        log.debug("************ create() ---> userFromRepositoryById = " + userFromRepositoryById);

        userResult = ConverterUser.mapEntityToDTO(userFromRepositoryById);

        log.debug("************ create() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updateFirstName(Long id, String firstName) throws ExceptionUserWithIdNotExist {
        UserDTO userResult = null;

        log.debug("************ updateFirstName() ---> id = " + id + " ---> firstName = " + firstName);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        log.debug("************ updateFirstName() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (firstName != null
                    && (!firstName.equals(""))
                    && (!firstName.equals(userEntityUpdate.getFirstName()))) {

                userRepository.updateFirstName(id, firstName);

                UserEntity savedUserFromRepository = userRepository.findById(id).get();

                log.debug("************ updateFirstName() ---> savedUserFromRepository = " + savedUserFromRepository);

                userResult = ConverterUser.mapEntityToDTO(savedUserFromRepository);
            }
        } else {
            log.debug("************ updateFirstName() ---> user with this id = '" + id + "' not exist.");
            throw new ExceptionUserWithIdNotExist("User with this id = '" + id + "' not exist.");
        }

        log.debug("************ updateFirstName() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updateLastName(Long id, String lastName) throws ExceptionUserWithIdNotExist {
        UserDTO userResult = null;

        log.debug("************ updateLastName() ---> id = " + id + " ---> lastName = " + lastName);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        log.debug("************ updateLastName() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (lastName != null
                    && (!lastName.equals(""))
                    && (!lastName.equals(userEntityUpdate.getLastName()))) {


                userRepository.updateLastName(id, lastName);

                UserEntity savedUserFromRepository = userRepository.findById(id).get();

                log.debug("************ updateLastName() ---> savedUserFromRepository = " + savedUserFromRepository);

                userResult = ConverterUser.mapEntityToDTO(savedUserFromRepository);
            }
        } else {
            log.debug("************ updateLastName() ---> user with this id = '" + id + "' not exist.");
            throw new ExceptionUserWithIdNotExist("User with this id = '" + id + "' not exist.");
        }

        log.debug("************ updateLastName() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updateEmail(Long id, String email) throws ExceptionUserWithIdNotExist, ExceptionUserWithEmailExist {
        UserDTO userResult = null;

        log.debug("************ updateEmail() ---> id = " + id + " ---> email = " + email);

        if (userRepository.findByEmail(email).isPresent()) {
            log.debug("************ updateEmail() ---> user with this email = '" + email + "' exist.");
            throw new ExceptionUserWithEmailExist("User with this email = '" + email + "' exist. It is not allowed to register multiple users with the same e-mail.");
        }

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        log.debug("************ updateEmail() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (email != null
                    && (!email.equals(""))
                    && (!email.equals(userEntityUpdate.getLastName()))) {

                userRepository.updateEmail(id, email);

                Optional<UserEntity> updatedUserFromRepository = userRepository.findById(id);

                log.debug("************ updateEmail() ---> savedUserFromRepository = " + updatedUserFromRepository.get());

                userResult = ConverterUser.mapEntityToDTO(updatedUserFromRepository.get());
            }
        } else {
            log.debug("************ updateEmail() ---> user with this id = '" + id + "' not exist.");
            throw new ExceptionUserWithIdNotExist("User with this id = '" + id + "' not exist.");
        }

        log.debug("************ updateEmail() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updatePassword(Long id, String password) throws ExceptionUserWithIdNotExist {
        UserDTO userResult = null;

        log.debug("************ updatePassword() ---> id = " + id + " ---> password = " + password);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        log.debug("************ updatePassword() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (password != null
                    && (!password.equals(""))
                    && (!password.equals(userEntityUpdate.getLastName()))) {

                userRepository.updatePassword(id, password);

                Optional<UserEntity> updatedUserFromRepository = userRepository.findById(id);

                log.debug("************ updatePassword() ---> updatedUserFromRepository = " + updatedUserFromRepository.get());

                userResult = ConverterUser.mapEntityToDTO(updatedUserFromRepository.get());
            }
        } else {
            log.debug("************ updatePassword() ---> user with this id = '" + id + "' not exist.");
            throw new ExceptionUserWithIdNotExist("User with this id = '" + id + "' not exist.");
        }

        log.debug("************ updatePassword() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updateBirthday(Long id, Date birthday) throws ExceptionUserWithIdNotExist {
        UserDTO userResult = null;

        log.debug("************ updateBirthday() ---> id = " + id + " ---> birthday = " + birthday);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        log.debug("************ updateBirthday() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (birthday != null && (!birthday.equals(userEntityUpdate.getBirthday()))) {

                userRepository.updateBirthday(id, birthday);

                Optional<UserEntity> savedUserFromRepository = userRepository.findById(id);

                log.debug("************ updateBirthday() ---> savedUserFromRepository = " + savedUserFromRepository.get());

                userResult = ConverterUser.mapEntityToDTO(savedUserFromRepository.get());
            }
        } else {
            log.debug("************ updateBirthday() ---> user with this id = '" + id + "' not exist.");
            throw new ExceptionUserWithIdNotExist("User with this id = '" + id + "' not exist.");
        }

        log.debug("************ updateBirthday() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public Boolean delete(Long userId) throws ExceptionUserWithIdNotExist {
        boolean isDeletedUser = false;

        log.debug("************ delete() ---> userId = " + userId);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(userId);

        log.debug("************ delete() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {

            UserDTO userDTO = ConverterUser.mapEntityToDTO(userFromRepositoryById.get());

            Boolean isDeletedBankData = null;
            try {
                isDeletedBankData = bankDataService.deleteByUser(userDTO);
                log.debug("************ delete() ---> isDeletedBankData = " + isDeletedBankData);
            } catch (ExceptionUserNotHaveBankData exceptionUserNotHaveBankData) {
                log.error("User with this id = '" + userId + "' not have bank data.", exceptionUserNotHaveBankData);
            }

            Boolean isDeletedAddress = null;
            try {
                isDeletedAddress = addressService.deleteByUser(userDTO);
                log.debug("************ delete() ---> isDeletedAddress = " + isDeletedAddress);
            } catch (ExceptionUserNotHaveAddress exceptionUserNotHaveAddress) {
                log.error("User with this id = '" + userId + "' not have address.", exceptionUserNotHaveAddress);
            }

            userRepository.deleteById(userId);
            isDeletedUser = true;

        } else {
            log.debug("************ delete() ---> user with this id = '" + userId + "' not exist.");
            throw new ExceptionUserWithIdNotExist("User with this id = '" + userId + "' not exist.");
        }

        log.debug("************ delete() ---> isDeletedUser = " + isDeletedUser);

        return isDeletedUser;
    }

    /**
     *
     */
    @Override
    public UserDTO findById(Long id) {
        UserDTO userResult = null;

        log.debug("************ findById() ---> id = " + id);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        log.debug("************ findById() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) userResult = ConverterUser.mapEntityToDTO(userFromRepositoryById.get());

        log.debug("************ findById() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Override
    public Set<UserDTO> findByFirstName(String firstName) {
        Set<UserDTO> userResultSet = null;

        log.debug("************ findByFirstName() ---> firstName = " + firstName);

        Set<UserEntity> usersFromRepositoryByFirstName = userRepository.findByFirstName(firstName);

        log.debug("************ findByFirstName() ---> usersFromRepositoryByFirstName = " + usersFromRepositoryByFirstName);

        if (!usersFromRepositoryByFirstName.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(usersFromRepositoryByFirstName);

        log.debug("************ findByFirstName() ---> userResultSet = " + userResultSet);

        return userResultSet;
    }

    /**
     *
     */
    @Override
    public Set<UserDTO> findByLastName(String lastName) {
        Set<UserDTO> userResultSet = null;

        log.debug("************ findByLastName() ---> lastName = " + lastName);

        Set<UserEntity> usersFromRepositoryByLastName = userRepository.findByLastName(lastName);

        log.debug("************ findByLastName() ---> usersFromRepositoryByLastName = " + usersFromRepositoryByLastName);

        if (!usersFromRepositoryByLastName.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(usersFromRepositoryByLastName);

        log.debug("************ findByLastName() ---> userResultSet = " + userResultSet);

        return userResultSet;
    }

    /**
     *
     */
    @Override
    public UserDTO findByEmail(String email) {
        UserDTO userResult = null;

        log.debug("************ findByEmail() ---> email = " + email);

        Optional<UserEntity> usersFromRepositoryByEmail = userRepository.findByEmail(email);

        log.debug("************ findByEmail() ---> usersFromRepositoryByEmail = " + usersFromRepositoryByEmail);

        if (usersFromRepositoryByEmail.isPresent())
            userResult = ConverterUser.mapEntityToDTO(usersFromRepositoryByEmail.get());

        log.debug("************ findByEmail() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Override
    public Set<UserDTO> findByFirstNameAndLastName(String firstName, String lastName) {
        Set<UserDTO> userResultSet = null;

        log.debug("************ findByFirstNameAndLastName() ---> firstName = " + firstName + " ---> lastName = " + lastName);

        Set<UserEntity> usersFromRepositoryByFullName = userRepository.findByFirstNameAndLastName(firstName, lastName);

        log.debug("************ findByFirstNameAndLastName() ---> usersFromRepositoryByFullName = " + usersFromRepositoryByFullName);

        if (!usersFromRepositoryByFullName.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(usersFromRepositoryByFullName);

        log.debug("************ findByFirstNameAndLastName() ---> userResultSet = " + userResultSet);

        return userResultSet;
    }

    /**
     *
     */
    @Override
    public Set<UserDTO> findByBirthday(Date birthday) {
        Set<UserDTO> userResultSet = null;

        log.debug("************ findByBirthday() ---> birthday = " + birthday);

        Set<UserEntity> usersFromRepositoryByBirthday = userRepository.findByBirthday(birthday);

        log.debug("************ findByBirthday() ---> usersFromRepositoryByBirthday = " + usersFromRepositoryByBirthday);

        if (!usersFromRepositoryByBirthday.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(usersFromRepositoryByBirthday);

        log.debug("************ findByBirthday() ---> userResultSet = " + userResultSet);

        return userResultSet;
    }

    /**
     *
     */
    @Override
    public Set<UserDTO> findAll() {
        Set<UserDTO> userResultSet = null;

        Set<UserEntity> allUsersFromRepository = new TreeSet<>(Comparator.comparing(UserEntity::getId));
        allUsersFromRepository.addAll(userRepository.findAll());

        log.debug("************ findAll() ---> allUsersFromRepository = " + allUsersFromRepository);

        if (!allUsersFromRepository.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(allUsersFromRepository);

        log.debug("************ findAll() ---> userResultSet = " + userResultSet);

        return userResultSet;
    }

}

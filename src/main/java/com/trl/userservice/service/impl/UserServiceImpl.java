package com.trl.userservice.service.impl;

import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.exceptions.UserNotHaveAddressException;
import com.trl.userservice.exceptions.UserNotHaveBankDataException;
import com.trl.userservice.exceptions.UserWithEmailExistException;
import com.trl.userservice.exceptions.UserWithIdNotExistException;
import com.trl.userservice.repository.UserRepository;
import com.trl.userservice.repository.entity.UserEntity;
import com.trl.userservice.service.UserService;
import com.trl.userservice.service.converter.ConverterUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final BankDataServiceImpl bankDataService;
    private final AddressServiceImpl addressService;

    public UserServiceImpl(UserRepository userRepository, BankDataServiceImpl bankDataService, AddressServiceImpl addressService) {
        this.userRepository = userRepository;
        this.bankDataService = bankDataService;
        this.addressService = addressService;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public UserDTO create(UserDTO user) throws UserWithEmailExistException {
        UserDTO userResult = null;

        LOG.debug("************ create() ---> user = " + user);

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            LOG.debug("************ updateEmail() ---> user with this email = '" + user.getEmail() + "' exist.");
            throw new UserWithEmailExistException("User with this email = '" + user.getEmail() + "' exist. It is not allowed to register multiple users with the same e-mail.");
        }

        UserEntity savedUser = userRepository.save(ConverterUser.mapDTOToEntity(user));

        LOG.debug("************ create() ---> savedUser = " + savedUser);

        userResult = ConverterUser.mapEntityToDTO(savedUser);

        LOG.debug("************ create() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updateFirstName(Long id, String firstName) throws UserWithIdNotExistException {
        UserDTO userResult = null;

        LOG.debug("************ updateFirstName() ---> id = " + id + " ---> firstName = " + firstName);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        LOG.debug("************ updateFirstName() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (firstName != null
                    && (!firstName.equals(""))
                    && (!firstName.equals(userEntityUpdate.getFirstName()))) {

                userRepository.updateFirstName(id, firstName);

                UserEntity savedUserFromRepository = userRepository.findById(id).get();

                LOG.debug("************ updateFirstName() ---> savedUserFromRepository = " + savedUserFromRepository);

                userResult = ConverterUser.mapEntityToDTO(savedUserFromRepository);
            }
        } else {
            LOG.debug("************ updateFirstName() ---> user with this id = '" + id + "' not exist.");
            throw new UserWithIdNotExistException("User with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updateFirstName() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updateLastName(Long id, String lastName) throws UserWithIdNotExistException {
        UserDTO userResult = null;

        LOG.debug("************ updateLastName() ---> id = " + id + " ---> lastName = " + lastName);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        LOG.debug("************ updateLastName() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (lastName != null
                    && (!lastName.equals(""))
                    && (!lastName.equals(userEntityUpdate.getLastName()))) {


                userRepository.updateLastName(id, lastName);

                UserEntity savedUserFromRepository = userRepository.findById(id).get();

                LOG.debug("************ updateLastName() ---> savedUserFromRepository = " + savedUserFromRepository);

                userResult = ConverterUser.mapEntityToDTO(savedUserFromRepository);
            }
        } else {
            LOG.debug("************ updateLastName() ---> user with this id = '" + id + "' not exist.");
            throw new UserWithIdNotExistException("User with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updateLastName() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updateEmail(Long id, String email) throws UserWithIdNotExistException, UserWithEmailExistException {
        UserDTO userResult = null;

        LOG.debug("************ updateEmail() ---> id = " + id + " ---> email = " + email);

        if (userRepository.findByEmail(email).isPresent()) {
            LOG.debug("************ updateEmail() ---> user with this email = '" + email + "' exist.");
            throw new UserWithEmailExistException("User with this email = '" + email + "' exist. It is not allowed to register multiple users with the same e-mail.");
        }

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        LOG.debug("************ updateEmail() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (email != null
                    && (!email.equals(""))
                    && (!email.equals(userEntityUpdate.getLastName()))) {

                userRepository.updateEmail(id, email);

                Optional<UserEntity> updatedUserFromRepository = userRepository.findById(id);

                LOG.debug("************ updateEmail() ---> savedUserFromRepository = " + updatedUserFromRepository.get());

                userResult = ConverterUser.mapEntityToDTO(updatedUserFromRepository.get());
            }
        } else {
            LOG.debug("************ updateEmail() ---> user with this id = '" + id + "' not exist.");
            throw new UserWithIdNotExistException("User with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updateEmail() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updatePassword(Long id, String password) throws UserWithIdNotExistException {
        UserDTO userResult = null;

        LOG.debug("************ updatePassword() ---> id = " + id + " ---> password = " + password);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        LOG.debug("************ updatePassword() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (password != null
                    && (!password.equals(""))
                    && (!password.equals(userEntityUpdate.getLastName()))) {

                userRepository.updatePassword(id, password);

                Optional<UserEntity> updatedUserFromRepository = userRepository.findById(id);

                LOG.debug("************ updatePassword() ---> updatedUserFromRepository = " + updatedUserFromRepository.get());

                userResult = ConverterUser.mapEntityToDTO(updatedUserFromRepository.get());
            }
        } else {
            LOG.debug("************ updatePassword() ---> user with this id = '" + id + "' not exist.");
            throw new UserWithIdNotExistException("User with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updatePassword() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public UserDTO updateBirthday(Long id, LocalDate birthday) throws UserWithIdNotExistException {
        UserDTO userResult = null;

        LOG.debug("************ updateBirthday() ---> id = " + id + " ---> birthday = " + birthday);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        LOG.debug("************ updateBirthday() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {
            UserEntity userEntityUpdate = userFromRepositoryById.get();
            if (birthday != null && (!birthday.equals(userEntityUpdate.getBirthday()))) {

                userRepository.updateBirthday(id, birthday);

                Optional<UserEntity> savedUserFromRepository = userRepository.findById(id);

                LOG.debug("************ updateBirthday() ---> savedUserFromRepository = " + savedUserFromRepository.get());

                userResult = ConverterUser.mapEntityToDTO(savedUserFromRepository.get());
            }
        } else {
            LOG.debug("************ updateBirthday() ---> user with this id = '" + id + "' not exist.");
            throw new UserWithIdNotExistException("User with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updateBirthday() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Transactional
    @Override
    public Boolean delete(Long userId) throws UserWithIdNotExistException {
        boolean isDeletedUser = false;

        LOG.debug("************ delete() ---> user = " + userId);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(userId);

        LOG.debug("************ delete() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) {

            UserDTO userDTO = ConverterUser.mapEntityToDTO(userFromRepositoryById.get());

            Boolean isDeletedBankData = null;
            try {
                isDeletedBankData = bankDataService.deleteByUser(userDTO);
                LOG.debug("************ delete() ---> isDeletedBankData = " + isDeletedBankData);
            } catch (UserNotHaveBankDataException userNotHaveBankDataException) {
                LOG.error("User with this id = '" + userId + "' not have bank data.", userNotHaveBankDataException);
            }

            Boolean isDeletedAddress = null;
            try {
                isDeletedAddress = addressService.deleteByUser(userDTO);
                LOG.debug("************ delete() ---> isDeletedAddress = " + isDeletedAddress);
            } catch (UserNotHaveAddressException userNotHaveAddressException) {
                LOG.error("User with this id = '" + userId + "' not have address.", userNotHaveAddressException);
            }

            userRepository.deleteById(userId);
            isDeletedUser = true;

        } else {
            LOG.debug("************ delete() ---> user with this id = '" + userId + "' not exist.");
            throw new UserWithIdNotExistException("User with this id = '" + userId + "' not exist.");
        }

        LOG.debug("************ delete() ---> isDeletedUser = " + isDeletedUser);

        return isDeletedUser;
    }

    /**
     *
     */
    @Override
    public UserDTO findById(Long id) {
        UserDTO userResult = null;

        LOG.debug("************ findById() ---> id = " + id);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(id);

        LOG.debug("************ findById() ---> userFromRepositoryById = " + userFromRepositoryById);

        if (userFromRepositoryById.isPresent()) userResult = ConverterUser.mapEntityToDTO(userFromRepositoryById.get());

        LOG.debug("************ findById() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Override
    public Set<UserDTO> findByFirstName(String firstName) {
        Set<UserDTO> userResultSet = null;


        LOG.debug("************ findByFirstName() ---> firstName = " + firstName);

        Set<UserEntity> usersFromRepositoryByFirstName = userRepository.findByFirstName(firstName);

        LOG.debug("************ findByFirstName() ---> usersFromRepositoryByFirstName = " + usersFromRepositoryByFirstName);

        if (!usersFromRepositoryByFirstName.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(usersFromRepositoryByFirstName);

        LOG.debug("************ findByFirstName() ---> userResultSet = " + userResultSet);

        return userResultSet;
    }

    /**
     *
     */
    @Override
    public Set<UserDTO> findByLastName(String lastName) {
        Set<UserDTO> userResultSet = null;

        LOG.debug("************ findByLastName() ---> lastName = " + lastName);

        Set<UserEntity> usersFromRepositoryByLastName = userRepository.findByLastName(lastName);

        LOG.debug("************ findByLastName() ---> usersFromRepositoryByLastName = " + usersFromRepositoryByLastName);

        if (!usersFromRepositoryByLastName.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(usersFromRepositoryByLastName);

        LOG.debug("************ findByLastName() ---> userResultSet = " + userResultSet);

        return userResultSet;
    }

    /**
     *
     */
    @Override
    public UserDTO findByEmail(String email) {
        UserDTO userResult = null;

        LOG.debug("************ findByEmail() ---> email = " + email);

        Optional<UserEntity> usersFromRepositoryByEmail = userRepository.findByEmail(email);

        LOG.debug("************ findByEmail() ---> usersFromRepositoryByEmail = " + usersFromRepositoryByEmail);

        if (usersFromRepositoryByEmail.isPresent())
            userResult = ConverterUser.mapEntityToDTO(usersFromRepositoryByEmail.get());

        LOG.debug("************ findByEmail() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     *
     */
    @Override
    public Set<UserDTO> findByFirstNameAndLastName(String firstName, String lastName) {
        Set<UserDTO> userResultSet = null;

        LOG.debug("************ findByFirstNameAndLastName() ---> firstName = " + firstName + " ---> lastName = " + lastName);

        Set<UserEntity> usersFromRepositoryByFullName = userRepository.findByFirstNameAndLastName(firstName, lastName);

        LOG.debug("************ findByFirstNameAndLastName() ---> usersFromRepositoryByFullName = " + usersFromRepositoryByFullName);

        if (!usersFromRepositoryByFullName.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(usersFromRepositoryByFullName);

        LOG.debug("************ findByFirstNameAndLastName() ---> userResultSet = " + userResultSet);

        return userResultSet;
    }

    /**
     *
     */
    @Override
    public Set<UserDTO> findByBirthday(LocalDate birthday) {
        Set<UserDTO> userResultSet = null;

        LOG.debug("************ findByBirthday() ---> birthday = " + birthday);

        Set<UserEntity> usersFromRepositoryByBirthday = userRepository.findByBirthday(birthday);

        LOG.debug("************ findByBirthday() ---> usersFromRepositoryByBirthday = " + usersFromRepositoryByBirthday);

        if (!usersFromRepositoryByBirthday.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(usersFromRepositoryByBirthday);

        LOG.debug("************ findByBirthday() ---> userResultSet = " + userResultSet);

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

        LOG.debug("************ findAll() ---> allUsersFromRepository = " + allUsersFromRepository);

        if (!allUsersFromRepository.isEmpty())
            userResultSet = ConverterUser.mapSetEntityToSetDTO(allUsersFromRepository);

        LOG.debug("************ findAll() ---> userResultSet = " + userResultSet);

        return userResultSet;
    }

}

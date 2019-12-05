package com.trl.userservice.utils;

import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.exceptions.IllegalValueException;
import com.trl.userservice.exceptions.UserNotExistException;
import com.trl.userservice.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

/**
 * This class contains methods utils for {@literal UserDTO}.
 *
 * @author Tsyupryk Roman
 */
@Component
public class UserUtils {

    private final UserRepository userRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UserUtils.class);

    public UserUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Check fields of object {@literal UserDTO}.
     *
     * @param user must not be equal to {@literal null}.
     * @throws IllegalValueException If some field of {@literal UserDTO} contains illegal value.
     */
    public void checkFieldsOfUser(UserDTO user) {
        String message = "Field %s, check the field that it has the 'user' parameter.";

        if (user.getFirstName() == null) {
            LOG.debug("************ add() ---> " + format(message, "'firstName' not be equals to null"));
            throw new IllegalValueException(format(message, "'firstName' not be equals to null"));
        } else if ((deleteWhitespace(user.getFirstName()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'firstName' is empty"));
            throw new IllegalValueException(format(message, "'firstName' is empty"));
        }

        if (user.getLastName() == null) {
            LOG.debug("************ add() ---> " + format(message, "'lastName' not be equals to null"));
            throw new IllegalValueException(format(message, "'lastName' not be equals to null"));
        } else if ((deleteWhitespace(user.getLastName()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'lastName' is empty"));
            throw new IllegalValueException(format(message, "'lastName' is empty"));
        }

        if (user.getUserName() == null) {
            LOG.debug("************ add() ---> " + format(message, "'userName' not be equals to null"));
            throw new IllegalValueException(format(message, "'userName' not be equals to null"));
        } else if ((deleteWhitespace(user.getUserName()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'userName' is empty"));
            throw new IllegalValueException(format(message, "'userName' is empty"));
        }

        if (user.getEmail() == null) {
            LOG.debug("************ add() ---> " + format(message, "'email' not be equals to null"));
            throw new IllegalValueException(format(message, "'email' not be equals to null"));
        } else if ((deleteWhitespace(user.getEmail()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'email' is empty"));
            throw new IllegalValueException(format(message, "'email' is empty"));
        }

        if (user.getPassword() == null) {
            LOG.debug("************ add() ---> " + format(message, "'password' not be equals to null"));
            throw new IllegalValueException(format(message, "'password' not be equals to null"));
        } else if ((deleteWhitespace(user.getPassword()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'password' is empty"));
            throw new IllegalValueException(format(message, "'password' is empty"));
        }

        if (user.getBirthday() == null) {
            LOG.debug("************ add() ---> " + format(message, "'birthday' not be equals to null"));
            throw new IllegalValueException(format(message, "'birthday' not be equals to null"));
        }
    }

    /**
     * Check exist {@literal UserDTO} by {@code userId}.
     *
     * @param userId must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @throws UserNotExistException If {@literal UserDTO} not exist with the {@code userId}.
     */
    public void checkExistsUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            LOG.debug("************ checkExistsUserById() ---> " + "User with this id = " + userId + " not exist.");
            throw new UserNotExistException("User with this id = " + userId + " not exist.");
        }
    }
}

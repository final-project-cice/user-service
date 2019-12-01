package com.trl.userservice.service.impl;

import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.exceptions.DataNotFoundException;
import com.trl.userservice.repository.UserRepository;
import com.trl.userservice.repository.entity.UserEntity;
import com.trl.userservice.service.UserService;

import static com.trl.userservice.service.converter.UserConverter.mapEntityToDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

import java.util.Optional;

/**
 * This class is designed to implementation methods of {@code UserService}.
 *
 * @author Tsyupryk Roman
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.";
    private static final String EXCEPTION_MESSAGE_USER_BY_USER_ID_NOT_EXIST = "User with this userId = %s not exist.";

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves the {@literal UserDTO} by this {@code userId}.
     *
     * @param userId must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @return the {@literal UserDTO} with the given {@code userId}.
     * @throws IllegalArgumentException In case the given {@code userId} is {@literal null} or if {@code userId} is equal or less zero.
     * @throws DataNotFoundException    In case if {@literal UserDTO} not exist with this {@code userId}.
     */
    @Override
    public UserDTO getById(Long userId) {
        UserDTO userResult = null;

        if ((userId == null) || (userId <= 0)) {
            LOG.debug("************ getById(() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getById(() ---> userId = " + userId);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(userId);
        LOG.debug("************ getById(() ---> " +
                "userFromRepositoryByUserId = " + userFromRepositoryById);

        if (userFromRepositoryById.isEmpty()) {
            LOG.debug("************ getById(() ---> " +
                    format(EXCEPTION_MESSAGE_USER_BY_USER_ID_NOT_EXIST, userId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_USER_BY_USER_ID_NOT_EXIST, userId));
        }

        userResult = mapEntityToDTO(userFromRepositoryById.get());

        LOG.debug("************ getById(() ---> userResult = " + userResult);

        return userResult;
    }
}

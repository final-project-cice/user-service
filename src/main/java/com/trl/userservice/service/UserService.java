package com.trl.userservice.service;

import com.trl.userservice.controller.dto.UserDTO;

import org.springframework.data.domain.Page;

/**
 * This interface is designed to support service for {@literal UserDTO}.
 *
 * @author Tsyupryk Roman
 */
public interface UserService {

    UserDTO add(UserDTO user);


    UserDTO getById(Long id);

    Page<UserDTO> getPageOfUsers(Integer startPage, Integer pageSize);

    Page<UserDTO> getPageOfSortedUsers(Integer startPage, Integer pageSize, String sortOrder);


    UserDTO updateById(Long userId, UserDTO user);


    UserDTO deleteById(Long userId);
}
package com.trl.userservice.service;

import com.trl.userservice.controller.dto.UserDTO;

public interface UserService {

    UserDTO getById(Long id);
}
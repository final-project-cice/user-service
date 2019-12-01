package com.trl.userservice.controller;

import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves the {@literal UserDTO} by ths {@code userId}.
     *
     * @param userId must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(UserDTO)} with the given {@code userId}.
     */
    @GetMapping(
            path = "/{userId}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<UserDTO> getById(@PathVariable Long userId) {
        ResponseEntity<UserDTO> response = null;

        UserDTO resultService = userService.getById(userId);

        resultService.add(linkTo(methodOn(UserController.class).getById(userId)).withSelfRel());

        response = ResponseEntity.ok(resultService);

        return response;
    }
}

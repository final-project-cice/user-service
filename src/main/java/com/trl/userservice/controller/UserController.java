package com.trl.userservice.controller;

import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * This class is designed to support controller layout for {@literal UserDTO}.
 *
 * @author Tsyupryk Roman
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Add the {@literal UserDTO}.
     *
     * @param user must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.status(HttpStatus.CREATED).body(UserDTO)}.
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<UserDTO> add(@RequestBody UserDTO user) {
        ResponseEntity<UserDTO> response = null;

        UserDTO resultService = userService.add(user);

        resultService.add(linkTo(methodOn(UserController.class).add(user))
                .withSelfRel());
        resultService.add(linkTo(methodOn(UserController.class).getById(resultService.getUserId()))
                .withRel("getById"));
        resultService.add(linkTo(methodOn(UserController.class).getPageOfUsers(null, null))
                .withRel("getPageOfUsers"));
        resultService.add(linkTo(methodOn(UserController.class).getPageOfSortedUsers(null, null, null))
                .withRel("getPageOfSortedUsers"));
        resultService.add(linkTo(methodOn(UserController.class).updateById(resultService.getUserId(), null))
                .withRel("updateById"));
        resultService.add(linkTo(methodOn(UserController.class).deleteById(resultService.getUserId()))
                .withRel("deleteById"));

        resultService.add(linkTo(methodOn(AddressController.class).add(resultService.getUserId(), null))
                .withRel("addAddress"));
        resultService.add(linkTo(methodOn(AddressController.class).getByAddressId(null))
                .withRel("getByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(resultService.getUserId(), null, null))
                .withRel("getPageOfAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(resultService.getUserId(), null, null, null))
                .withRel("getPageOfSortedAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).updateByAddressId(null, null))
                .withRel("updateByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteByAddressId(null))
                .withRel("deleteByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(resultService.getUserId()))
                .withRel("deleteAllAddressesByUserId"));

        resultService.add(linkTo(methodOn(BankDataController.class).add(resultService.getUserId(), null))
                .withRel("addBankData"));
        resultService.add(linkTo(methodOn(BankDataController.class).getByBankDataId(null))
                .withRel("getByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(resultService.getUserId(), null, null))
                .withRel("getPageOfBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(resultService.getUserId(), null, null, null))
                .withRel("getPageOfSortedBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(null, null))
                .withRel("updateByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(null))
                .withRel("deleteByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(resultService.getUserId()))
                .withRel("deleteAllBankDataByUserId"));

        response = ResponseEntity.status(HttpStatus.CREATED).body(resultService);

        return response;
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

        resultService.add(linkTo(methodOn(UserController.class).getById(userId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(UserController.class).add(null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(UserController.class).getPageOfUsers(null, null))
                .withRel("getPageOfUsers"));
        resultService.add(linkTo(methodOn(UserController.class).getPageOfSortedUsers(null, null, null))
                .withRel("getPageOfSortedUsers"));
        resultService.add(linkTo(methodOn(UserController.class).updateById(userId, null))
                .withRel("updateById"));
        resultService.add(linkTo(methodOn(UserController.class).deleteById(userId))
                .withRel("deleteById"));

        resultService.add(linkTo(methodOn(AddressController.class).add(userId, null))
                .withRel("addAddress"));
        resultService.add(linkTo(methodOn(AddressController.class).getByAddressId(null))
                .withRel("getByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(userId, null, null))
                .withRel("getPageOfAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(userId, null, null, null))
                .withRel("getPageOfSortedAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).updateByAddressId(null, null))
                .withRel("updateByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteByAddressId(null))
                .withRel("deleteByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(userId))
                .withRel("deleteAllAddressesByUserId"));

        resultService.add(linkTo(methodOn(BankDataController.class).add(userId, null))
                .withRel("addBankData"));
        resultService.add(linkTo(methodOn(BankDataController.class).getByBankDataId(null))
                .withRel("getByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(userId, null, null))
                .withRel("getPageOfBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(userId, null, null, null))
                .withRel("getPageOfSortedBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(null, null))
                .withRel("updateByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(null))
                .withRel("deleteByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(userId))
                .withRel("deleteAllBankDataByUserId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve page of {@literal UserDTOs}.
     *
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal ResponseEntity.ok(Page<UserDTO>)}.
     */
    @GetMapping(
            path = "/{startPage}/{pageSize}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<UserDTO>> getPageOfUsers(@PathVariable Integer startPage,
                                                        @PathVariable Integer pageSize) {
        ResponseEntity<Page<UserDTO>> response = null;

        Page<UserDTO> resultService = userService.getPageOfUsers(startPage, pageSize);

        for (UserDTO user : resultService) {
            user.add(linkTo(methodOn(UserController.class).getPageOfUsers(startPage, pageSize))
                    .withSelfRel());
            user.add(linkTo(methodOn(UserController.class).add(null))
                    .withRel("add"));
            user.add(linkTo(methodOn(UserController.class).getById(user.getUserId()))
                    .withRel("getById"));
            user.add(linkTo(methodOn(UserController.class).getPageOfSortedUsers(startPage, pageSize, null))
                    .withRel("getPageOfSortedUsers"));
            user.add(linkTo(methodOn(UserController.class).updateById(user.getUserId(), null))
                    .withRel("updateById"));
            user.add(linkTo(methodOn(UserController.class).deleteById(user.getUserId()))
                    .withRel("deleteById"));

            user.add(linkTo(methodOn(AddressController.class).add(user.getUserId(), null))
                    .withRel("addAddress"));
            user.add(linkTo(methodOn(AddressController.class).getByAddressId(null))
                    .withRel("getByAddressId"));
            user.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(user.getUserId(), null, null))
                    .withRel("getPageOfAddressesByUserId"));
            user.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(user.getUserId(), null, null, null))
                    .withRel("getPageOfSortedAddressesByUserId"));
            user.add(linkTo(methodOn(AddressController.class).updateByAddressId(null, null))
                    .withRel("updateByAddressId"));
            user.add(linkTo(methodOn(AddressController.class).deleteByAddressId(null))
                    .withRel("deleteByAddressId"));
            user.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(user.getUserId()))
                    .withRel("deleteAllAddressesByUserId"));

            user.add(linkTo(methodOn(BankDataController.class).add(user.getUserId(), null))
                    .withRel("addBankData"));
            user.add(linkTo(methodOn(BankDataController.class).getByBankDataId(null))
                    .withRel("getByBankDataId"));
            user.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(user.getUserId(), null, null))
                    .withRel("getPageOfBankDataByUserId"));
            user.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(user.getUserId(), null, null, null))
                    .withRel("getPageOfSortedBankDataByUserId"));
            user.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(null, null))
                    .withRel("updateByBankDataId"));
            user.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(null))
                    .withRel("deleteByBankDataId"));
            user.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(user.getUserId()))
                    .withRel("deleteAllBankDataByUserId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve page of sorted {@literal UserDTOs}.
     *
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder the value by which the sorted user will be. Must not be {@literal null}.
     * @return the {@literal ResponseEntity.ok(Page<UserDTO>)}.
     */
    @GetMapping(
            path = "/{startPage}/{pageSize}/{sortOrder}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<UserDTO>> getPageOfSortedUsers(@PathVariable Integer startPage,
                                                              @PathVariable Integer pageSize,
                                                              @PathVariable String sortOrder) {
        ResponseEntity<Page<UserDTO>> response = null;

        Page<UserDTO> resultService = userService.getPageOfSortedUsers(startPage, pageSize, sortOrder);

        for (UserDTO user : resultService) {
            user.add(linkTo(methodOn(UserController.class).getPageOfSortedUsers(startPage, pageSize, sortOrder))
                    .withSelfRel());
            user.add(linkTo(methodOn(UserController.class).add(null))
                    .withRel("add"));
            user.add(linkTo(methodOn(UserController.class).getById(user.getUserId()))
                    .withRel("getById"));
            user.add(linkTo(methodOn(UserController.class).getPageOfUsers(startPage, pageSize))
                    .withRel("getPageOfUsers"));
            user.add(linkTo(methodOn(UserController.class).updateById(user.getUserId(), null))
                    .withRel("updateById"));
            user.add(linkTo(methodOn(UserController.class).deleteById(user.getUserId()))
                    .withRel("deleteById"));

            user.add(linkTo(methodOn(AddressController.class).add(user.getUserId(), null))
                    .withRel("addAddress"));
            user.add(linkTo(methodOn(AddressController.class).getByAddressId(null))
                    .withRel("getByAddressId"));
            user.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(user.getUserId(), null, null))
                    .withRel("getPageOfAddressesByUserId"));
            user.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(user.getUserId(), null, null, null))
                    .withRel("getPageOfSortedAddressesByUserId"));
            user.add(linkTo(methodOn(AddressController.class).updateByAddressId(null, null))
                    .withRel("updateByAddressId"));
            user.add(linkTo(methodOn(AddressController.class).deleteByAddressId(null))
                    .withRel("deleteByAddressId"));
            user.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(user.getUserId()))
                    .withRel("deleteAllAddressesByUserId"));

            user.add(linkTo(methodOn(BankDataController.class).add(user.getUserId(), null))
                    .withRel("addBankData"));
            user.add(linkTo(methodOn(BankDataController.class).getByBankDataId(null))
                    .withRel("getByBankDataId"));
            user.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(user.getUserId(), null, null))
                    .withRel("getPageOfBankDataByUserId"));
            user.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(user.getUserId(), null, null, null))
                    .withRel("getPageOfSortedBankDataByUserId"));
            user.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(null, null))
                    .withRel("updateByBankDataId"));
            user.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(null))
                    .withRel("deleteByBankDataId"));
            user.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(user.getUserId()))
                    .withRel("deleteAllBankDataByUserId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Update the {@literal UserDTO} by this {@code userId}.
     *
     * @param userId must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @param user   must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.ok(UserDTO)} with the given {@code userId}.
     */
    @PatchMapping(path = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<UserDTO> updateById(@PathVariable Long userId, @RequestBody UserDTO user) {
        ResponseEntity<UserDTO> response = null;

        UserDTO resultService = userService.updateById(userId, user);

        resultService.add(linkTo(methodOn(UserController.class).updateById(userId, user))
                .withSelfRel());
        resultService.add(linkTo(methodOn(UserController.class).add(null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(UserController.class).getById(userId))
                .withRel("getById"));
        resultService.add(linkTo(methodOn(UserController.class).getPageOfUsers(null, null))
                .withRel("getPageOfUsers"));
        resultService.add(linkTo(methodOn(UserController.class).getPageOfSortedUsers(null, null, null))
                .withRel("getPageOfSortedUsers"));
        resultService.add(linkTo(methodOn(UserController.class).deleteById(userId))
                .withRel("deleteById"));

        resultService.add(linkTo(methodOn(AddressController.class).add(userId, null))
                .withRel("addAddress"));
        resultService.add(linkTo(methodOn(AddressController.class).getByAddressId(null))
                .withRel("getByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(userId, null, null))
                .withRel("getPageOfAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(userId, null, null, null))
                .withRel("getPageOfSortedAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).updateByAddressId(null, null))
                .withRel("updateByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteByAddressId(null))
                .withRel("deleteByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(userId))
                .withRel("deleteAllAddressesByUserId"));

        resultService.add(linkTo(methodOn(BankDataController.class).add(userId, null))
                .withRel("addBankData"));
        resultService.add(linkTo(methodOn(BankDataController.class).getByBankDataId(null))
                .withRel("getByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(userId, null, null))
                .withRel("getPageOfBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(userId, null, null, null))
                .withRel("getPageOfSortedBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(null, null))
                .withRel("updateByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(null))
                .withRel("deleteByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(userId))
                .withRel("deleteAllBankDataByUserId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal UserDTO} with the given {@code userId}.
     *
     * @param userId must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(UserDTO)} if {@literal UserDTO} is deleted correctly.
     */
    @DeleteMapping(
            path = "/{userId}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<UserDTO> deleteById(@PathVariable Long userId) {
        ResponseEntity<UserDTO> response = null;

        UserDTO resultService = userService.deleteById(userId);

        resultService.add(linkTo(methodOn(UserController.class).deleteById(userId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(UserController.class).add(null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(UserController.class).getById(userId))
                .withRel("getById"));
        resultService.add(linkTo(methodOn(UserController.class).getPageOfUsers(null, null))
                .withRel("getPageOfUsers"));
        resultService.add(linkTo(methodOn(UserController.class).getPageOfSortedUsers(null, null, null))
                .withRel("getPageOfSortedUsers"));
        resultService.add(linkTo(methodOn(UserController.class).updateById(userId, null))
                .withRel("updateById"));

        resultService.add(linkTo(methodOn(AddressController.class).add(userId, null))
                .withRel("addAddress"));
        resultService.add(linkTo(methodOn(AddressController.class).getByAddressId(null))
                .withRel("getByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(userId, null, null))
                .withRel("getPageOfAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(userId, null, null, null))
                .withRel("getPageOfSortedAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).updateByAddressId(null, null))
                .withRel("updateByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteByAddressId(null))
                .withRel("deleteByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(userId))
                .withRel("deleteAllAddressesByUserId"));

        resultService.add(linkTo(methodOn(BankDataController.class).add(userId, null))
                .withRel("addBankData"));
        resultService.add(linkTo(methodOn(BankDataController.class).getByBankDataId(null))
                .withRel("getByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(userId, null, null))
                .withRel("getPageOfBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(userId, null, null, null))
                .withRel("getPageOfSortedBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(null, null))
                .withRel("updateByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(null))
                .withRel("deleteByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(userId))
                .withRel("deleteAllBankDataByUserId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }
}
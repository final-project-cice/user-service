package com.trl.userservice.controller;

import com.trl.userservice.controller.dto.AddressDTO;
import com.trl.userservice.service.AddressService;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

/**
 * This class is designed to support controller layout for {@literal AddressDTO}.
 *
 * @author Tsyupryk Roman
 */
@RestController
@RequestMapping(path = "/users")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Add the {@literal AddressDTO}.
     *
     * @param userId  must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @param address must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.status(HttpStatus.CREATED).body(AddressDTO)}.
     */
    @PostMapping(
            path = "/{userId}/addresses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<AddressDTO> add(@PathVariable Long userId, @RequestBody AddressDTO address) {
        ResponseEntity<AddressDTO> response = null;

        AddressDTO resultService = addressService.add(userId, address);

        resultService.add(linkTo(methodOn(AddressController.class).add(userId, null))
                .withSelfRel());
        resultService.add(linkTo(methodOn(AddressController.class).getByAddressId(resultService.getAddressId()))
                .withRel("getByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(userId, null, null))
                .withRel("getPageOfAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(userId, null, null, null))
                .withRel("getPageOfSortedAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).updateByAddressId(resultService.getAddressId(), null))
                .withRel("updateByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteByAddressId(resultService.getAddressId()))
                .withRel("deleteByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(userId))
                .withRel("deleteAllAddressesByUserId"));

        response = ResponseEntity.status(HttpStatus.CREATED).body(resultService);

        return response;
    }

    /**
     * Retrieves the {@literal AddressDTO} by ths {@code addressId}.
     *
     * @param addressId must not be equals to {@literal null}, and {@code addressId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(AddressDTO)} with the given {@code addressId}.
     */
    @GetMapping(
            path = "/addresses/{addressId}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<AddressDTO> getByAddressId(@PathVariable Long addressId) {
        ResponseEntity<AddressDTO> response = null;

        AddressDTO resultService = addressService.getByAddressId(addressId);

        resultService.add(linkTo(methodOn(AddressController.class).getByAddressId(addressId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(AddressController.class).add(null, null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(null, null, null))
                .withRel("getPageOfAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(null, null, null, null))
                .withRel("getPageOfSortedAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).updateByAddressId(addressId, null))
                .withRel("updateByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteByAddressId(addressId))
                .withRel("deleteByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(null))
                .withRel("deleteAllAddressesByUserId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve page of {@literal AddressDTOs} by this {@code userId}.
     *
     * @param userId    must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal ResponseEntity.ok(Page<AddressDTO>)} with the given {@code userId}.
     */
    @GetMapping(
            path = "/{userId}/addresses/{startPage}/{pageSize}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<AddressDTO>> getPageOfAddressesByUserId(@PathVariable Long userId,
                                                                       @PathVariable Integer startPage,
                                                                       @PathVariable Integer pageSize) {
        ResponseEntity<Page<AddressDTO>> response = null;

        Page<AddressDTO> resultService = addressService.getPageOfAddressesByUserId(userId, startPage, pageSize);

        for (AddressDTO address : resultService) {
            address.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(userId, startPage, pageSize))
                    .withSelfRel());
            address.add(linkTo(methodOn(AddressController.class).add(userId, null))
                    .withRel("add"));
            address.add(linkTo(methodOn(AddressController.class).getByAddressId(null))
                    .withRel("getByAddressId"));
            address.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(userId, startPage, pageSize, null))
                    .withRel("getPageOfSortedAddressesByUserId"));
            address.add(linkTo(methodOn(AddressController.class).updateByAddressId(null, null))
                    .withRel("updateByAddressId"));
            address.add(linkTo(methodOn(AddressController.class).deleteByAddressId(null))
                    .withRel("deleteByAddressId"));
            address.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(userId))
                    .withRel("deleteAllAddressesByUserId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve page of sorted {@literal AddressDTOs} by this {@code userId}.
     *
     * @param userId    must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder the value by which the sorted address will be. Must not be {@literal null}.
     * @return the {@literal ResponseEntity.ok(Page<AddressDTO>)} with the given {@code userId}.
     */
    @GetMapping(
            path = "/{userId}/addresses/{startPage}/{pageSize}/{sortOrder}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<AddressDTO>> getPageOfSortedAddressesByUserId(@PathVariable Long userId,
                                                                             @PathVariable Integer startPage,
                                                                             @PathVariable Integer pageSize,
                                                                             @PathVariable String sortOrder) {
        ResponseEntity<Page<AddressDTO>> response = null;

        Page<AddressDTO> resultService = addressService.getPageOfSortedAddressesByUserId(userId, startPage, pageSize, sortOrder);

        response = ResponseEntity.ok(resultService);

        for (AddressDTO address : resultService) {
            address.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(userId, startPage, pageSize, sortOrder))
                    .withSelfRel());
            address.add(linkTo(methodOn(AddressController.class).add(userId, null))
                    .withRel("add"));
            address.add(linkTo(methodOn(AddressController.class).getByAddressId(null))
                    .withRel("getByAddressId"));
            address.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(userId, startPage, pageSize))
                    .withRel("getPageOfAddressesByUserId"));
            address.add(linkTo(methodOn(AddressController.class).updateByAddressId(null, null))
                    .withRel("updateByAddressId"));
            address.add(linkTo(methodOn(AddressController.class).deleteByAddressId(null))
                    .withRel("deleteByAddressId"));
            address.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(userId))
                    .withRel("deleteAllAddressesByUserId"));
        }

        return response;
    }

    /**
     * Update the {@literal AddressDTO} by this {@code addressId}.
     *
     * @param addressId must not be equals to {@literal null}, and {@code addressId} must be greater than zero.
     * @param address   must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.ok(AddressDTO)} with the given {@code addressId}.
     */
    @PatchMapping(path = "/addresses/{addressId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<AddressDTO> updateByAddressId(@PathVariable Long addressId, @RequestBody AddressDTO address) {
        ResponseEntity<AddressDTO> response = null;

        AddressDTO resultService = addressService.updateByAddressId(addressId, address);

        resultService.add(linkTo(methodOn(AddressController.class).updateByAddressId(addressId, null))
                .withSelfRel());
        resultService.add(linkTo(methodOn(AddressController.class).add(null, null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(AddressController.class).getByAddressId(addressId))
                .withRel("getByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(null, null, null))
                .withRel("getPageOfAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(null, null, null, null))
                .withRel("getPageOfSortedAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteByAddressId(addressId))
                .withRel("deleteByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(null))
                .withRel("deleteAllAddressesByUserId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal AddressDTO} with the given {@code addressId}.
     *
     * @param addressId must not be equals to {@literal null}, and {@code addressId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(AddressDTO)} if {@literal AddressDTO} is deleted correctly.
     */
    @DeleteMapping(
            path = "/addresses/{addressId}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<AddressDTO> deleteByAddressId(@PathVariable Long addressId) {
        ResponseEntity<AddressDTO> response = null;

        AddressDTO resultService = addressService.deleteByAddressId(addressId);

        resultService.add(linkTo(methodOn(AddressController.class).deleteByAddressId(addressId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(AddressController.class).add(null, null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(AddressController.class).getByAddressId(addressId))
                .withRel("getByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(null, null, null))
                .withRel("getPageOfAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(null, null, null, null))
                .withRel("getPageOfSortedAddressesByUserId"));
        resultService.add(linkTo(methodOn(AddressController.class).updateByAddressId(addressId, null))
                .withRel("updateByAddressId"));
        resultService.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(null))
                .withRel("deleteAllAddressesByUserId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes all {@literal AddressDTO} with the given {@code userId}.
     *
     * @param userId must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(List<AddressDTO>)} if {@literal List<AddressDTO>} is deleted correctly.
     */
    @DeleteMapping(
            path = "/{userId}/addresses",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<List<AddressDTO>> deleteAllAddressesByUserId(@PathVariable Long userId) {
        ResponseEntity<List<AddressDTO>> response = null;

        List<AddressDTO> resultService = addressService.deleteAllAddressesByUserId(userId);

        for (AddressDTO address : resultService) {
            address.add(linkTo(methodOn(AddressController.class).deleteAllAddressesByUserId(userId))
                    .withSelfRel());
            address.add(linkTo(methodOn(AddressController.class).add(userId, null))
                    .withRel("add"));
            address.add(linkTo(methodOn(AddressController.class).getByAddressId(null))
                    .withRel("getByAddressId"));
            address.add(linkTo(methodOn(AddressController.class).getPageOfAddressesByUserId(userId, null, null))
                    .withRel("getPageOfAddressesByUserId"));
            address.add(linkTo(methodOn(AddressController.class).getPageOfSortedAddressesByUserId(userId, null, null, null))
                    .withRel("getPageOfSortedAddressesByUserId"));
            address.add(linkTo(methodOn(AddressController.class).updateByAddressId(null, null))
                    .withRel("updateByAddressId"));
            address.add(linkTo(methodOn(AddressController.class).deleteByAddressId(null))
                    .withRel("deleteByAddressId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }
}
package com.trl.userservice.controller;

import com.trl.userservice.controller.dto.BankDataDTO;
import com.trl.userservice.service.BankDataService;

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
 * This class is designed to support controller layout for {@literal BankDataDTO}.
 *
 * @author Tsyupryk Roman
 */
@RestController
@RequestMapping(path = "/users")
public class BankDataController {

    private final BankDataService bankDataService;

    public BankDataController(BankDataService bankDataService) {
        this.bankDataService = bankDataService;
    }

    /**
     * Add the {@literal BankDataDTO}.
     *
     * @param userId   must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @param bankData must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.status(HttpStatus.CREATED).body(BankDataDTO)}.
     */
    @PostMapping(
            path = "/{userId}/bankData",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<BankDataDTO> add(@PathVariable Long userId, @RequestBody BankDataDTO bankData) {
        ResponseEntity<BankDataDTO> response = null;

        BankDataDTO resultService = bankDataService.add(userId, bankData);

        resultService.add(linkTo(methodOn(BankDataController.class).add(userId, null))
                .withSelfRel());
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

        response = ResponseEntity.status(HttpStatus.CREATED).body(resultService);

        return response;
    }

    /**
     * Retrieves the {@literal BankDataDTO} by ths {@code bankDataId}.
     *
     * @param bankDataId must not be equals to {@literal null}, and {@code bankDataId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(BankDataDTO)} with the given {@code bankDataId}.
     */
    @GetMapping(
            path = "/bankData/{bankDataId}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<BankDataDTO> getByBankDataId(@PathVariable Long bankDataId) {
        ResponseEntity<BankDataDTO> response = null;

        BankDataDTO resultService = bankDataService.getByBankDataId(bankDataId);

        resultService.add(linkTo(methodOn(BankDataController.class).getByBankDataId(bankDataId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(BankDataController.class).add(null, null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(null, null, null))
                .withRel("getPageOfBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(null, null, null, null))
                .withRel("getPageOfSortedBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(bankDataId, null))
                .withRel("updateByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(bankDataId))
                .withRel("deleteByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(null))
                .withRel("deleteAllBankDataByUserId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve page of {@literal BankDataDTOs} by this {@code userId}.
     *
     * @param userId    must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal ResponseEntity.ok(Page<BankDataDTO>)} with the given {@code userId}.
     */
    @GetMapping(
            path = "/{userId}/bankData/{startPage}/{pageSize}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<BankDataDTO>> getPageOfBankDataByUserId(@PathVariable Long userId,
                                                                       @PathVariable Integer startPage,
                                                                       @PathVariable Integer pageSize) {
        ResponseEntity<Page<BankDataDTO>> response = null;

        Page<BankDataDTO> resultService = bankDataService.getPageOfBankDataByUserId(userId, startPage, pageSize);

        for (BankDataDTO bankData : resultService) {
            bankData.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(userId, startPage, pageSize))
                    .withSelfRel());
            bankData.add(linkTo(methodOn(BankDataController.class).add(userId, null))
                    .withRel("add"));
            bankData.add(linkTo(methodOn(BankDataController.class).getByBankDataId(null))
                    .withRel("getByBankDataId"));
            bankData.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(userId, startPage, pageSize, null))
                    .withRel("getPageOfSortedBankDataByUserId"));
            bankData.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(null, null))
                    .withRel("updateByBankDataId"));
            bankData.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(null))
                    .withRel("deleteByBankDataId"));
            bankData.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(userId))
                    .withRel("deleteAllBankDataByUserId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve page of sorted {@literal BankDataDTOs} by this {@code userId}.
     *
     * @param userId    must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder the value by which the sorted bankData will be. Must not be {@literal null}.
     * @return the {@literal ResponseEntity.ok(Page<BankDataDTO>)} with the given {@code userId}.
     */
    @GetMapping(
            path = "/{userId}/bankData/{startPage}/{pageSize}/{sortOrder}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<BankDataDTO>> getPageOfSortedBankDataByUserId(@PathVariable Long userId,
                                                                             @PathVariable Integer startPage,
                                                                             @PathVariable Integer pageSize,
                                                                             @PathVariable String sortOrder) {
        ResponseEntity<Page<BankDataDTO>> response = null;

        Page<BankDataDTO> resultService = bankDataService.getPageOfSortedBankDataByUserId(userId, startPage, pageSize, sortOrder);

        for (BankDataDTO bankData : resultService) {
            bankData.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(userId, startPage, pageSize, sortOrder))
                    .withSelfRel());
            bankData.add(linkTo(methodOn(BankDataController.class).add(userId, null))
                    .withRel("add"));
            bankData.add(linkTo(methodOn(BankDataController.class).getByBankDataId(null))
                    .withRel("getByBankDataId"));
            bankData.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(userId, startPage, pageSize))
                    .withRel("getPageOfBankDataByUserId"));
            bankData.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(null, null))
                    .withRel("updateByBankDataId"));
            bankData.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(null))
                    .withRel("deleteByBankDataId"));
            bankData.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(userId))
                    .withRel("deleteAllBankDataByUserId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Update the {@literal BankDataDTO} by this {@code bankDataId}.
     *
     * @param bankDataId must not be equals to {@literal null}, and {@code bankDataId} must be greater than zero.
     * @param bankData   must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.ok(BankDataDTO)} with the given {@code bankDataId}.
     */
    @PatchMapping(path = "/bankData/{bankDataId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<BankDataDTO> updateByBankDataId(@PathVariable Long bankDataId, @RequestBody BankDataDTO bankData) {
        ResponseEntity<BankDataDTO> response = null;

        BankDataDTO resultService = bankDataService.updateByBankDataId(bankDataId, bankData);

        resultService.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(bankDataId, bankData))
                .withSelfRel());
        resultService.add(linkTo(methodOn(BankDataController.class).add(null, null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(BankDataController.class).getByBankDataId(bankDataId))
                .withRel("getByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(null, null, null))
                .withRel("getPageOfBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(null, null, null, null))
                .withRel("getPageOfSortedBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(bankDataId))
                .withRel("deleteByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(null))
                .withRel("deleteAllBankDataByUserId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal BankDataDTO} with the given {@code bankDataId}.
     *
     * @param bankDataId must not be equals to {@literal null}, and {@code bankDataId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(BankDataDTO)} if {@literal BankDataDTO} is deleted correctly.
     */
    @DeleteMapping(
            path = "/bankData/{bankDataId}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<BankDataDTO> deleteByBankDataId(@PathVariable Long bankDataId) {
        ResponseEntity<BankDataDTO> response = null;

        BankDataDTO resultService = bankDataService.deleteByBankDataId(bankDataId);

        resultService.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(bankDataId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(BankDataController.class).add(null, null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(BankDataController.class).getByBankDataId(bankDataId))
                .withRel("getByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(null, null, null))
                .withRel("getPageOfBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(null, null, null, null))
                .withRel("getPageOfSortedBankDataByUserId"));
        resultService.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(bankDataId, null))
                .withRel("updateByBankDataId"));
        resultService.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(null))
                .withRel("deleteAllBankDataByUserId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes all {@literal BankDataDTO} with the given {@code userId}.
     *
     * @param userId must not be equals to {@literal null}, and {@code userId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(List<BankDataDTO>)} if {@literal List<BankDataDTO>} is deleted correctly.
     */
    @DeleteMapping(
            path = "/{userId}/bankData",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<List<BankDataDTO>> deleteAllBankDataByUserId(@PathVariable Long userId) {
        ResponseEntity<List<BankDataDTO>> response = null;

        List<BankDataDTO> resultService = bankDataService.deleteAllBankDataByUserId(userId);

        for (BankDataDTO bankData : resultService) {
            bankData.add(linkTo(methodOn(BankDataController.class).deleteAllBankDataByUserId(userId))
                    .withSelfRel());
            bankData.add(linkTo(methodOn(BankDataController.class).add(userId, null))
                    .withRel("add"));
            bankData.add(linkTo(methodOn(BankDataController.class).getByBankDataId(null))
                    .withRel("getByBankDataId"));
            bankData.add(linkTo(methodOn(BankDataController.class).getPageOfBankDataByUserId(userId, null, null))
                    .withRel("getPageOfBankDataByUserId"));
            bankData.add(linkTo(methodOn(BankDataController.class).getPageOfSortedBankDataByUserId(userId, null, null, null))
                    .withRel("getPageOfSortedBankDataByUserId"));
            bankData.add(linkTo(methodOn(BankDataController.class).updateByBankDataId(null, null))
                    .withRel("updateByBankDataId"));
            bankData.add(linkTo(methodOn(BankDataController.class).deleteByBankDataId(null))
                    .withRel("deleteByBankDataId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }
}
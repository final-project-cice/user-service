package com.trl.userservice.controller.dto;

import java.util.Objects;

/**
 * This class is designed to represent DTO object of error details.
 *
 * @author Tsyupryk Roman
 */
public class ErrorDetailsDTO {

    private String errorMessage;
    private Integer errorCode;
    private String documentation;

    private String description;

    public ErrorDetailsDTO() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDetailsDTO that = (ErrorDetailsDTO) o;
        return Objects.equals(errorMessage, that.errorMessage) &&
                Objects.equals(errorCode, that.errorCode) &&
                Objects.equals(documentation, that.documentation) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage, errorCode, documentation, description);
    }

    @Override
    public String toString() {
        return "ErrorDetailsDTO{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorCode=" + errorCode +
                ", documentation='" + documentation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

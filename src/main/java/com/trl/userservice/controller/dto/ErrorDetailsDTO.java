package com.trl.userservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDateTime timestamp;

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage, errorCode, documentation, timestamp, description);
    }

    @Override
    public String toString() {
        return "ErrorDetailsDTO{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorCode=" + errorCode +
                ", documentation='" + documentation + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                '}';
    }
}

package com.crud.companyemployee.error.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"errorCode", "developerMessages", "userMessage"})
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 3286287681294812674L;
    @Singular
    private List<String> developerMessages;
    private String errorCode;
    private String userMessage;
}

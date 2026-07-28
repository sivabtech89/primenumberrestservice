package com.natwest.interview.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class ErrorResponse {

  @NotNull
  @Schema(format = "int32", maxLength = 3, description = "This is the http status code returned from the service")
  private Integer httpErrorCode;

  @NotNull
  @Schema(format = "int32", maxLength = 5, description = "This is the http status code returned from the service")
  private Integer errorCode;

  @NotNull
  @Schema(format = "string", description = "This is the http status error message returned from the service")
  private String errorMessage;

  @NotNull
  @Schema(format = "string", description = "This is the http status error message returned from the service")
  private String origin;

}

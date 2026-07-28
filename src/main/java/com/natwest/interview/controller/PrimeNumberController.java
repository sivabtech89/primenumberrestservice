package com.natwest.interview.controller;

import com.natwest.interview.model.ResponseObj;
import com.natwest.interview.service.GeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/primes")
@Slf4j
public class PrimeNumberController {

  @Autowired
  GeneratorService generatorService;

  @GetMapping(value = "/{number}", produces = "application/hal+json")
  @ResponseStatus(HttpStatus.OK)
  @Parameters(value = {
      @Parameter(name = "HTTP_AUTH_TOKEN", description = "Auth Token is required to protect the APIs, disabled for now", required = false, in = ParameterIn.HEADER)})
  @Operation(summary = "Return all the prime numbers up to and including a number provided")
  @ApiResponses(value =
      {@ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = ResponseObj.class))),
          @ApiResponse(responseCode = "400", description = "Bad Request, number should not be null or string"),
          @ApiResponse(responseCode = "401", description = "Unauthorized!"),
          @ApiResponse(responseCode = "403", description = "Forbidden!"),
          @ApiResponse(responseCode = "405", description = "Method Not Allowed!"),
          @ApiResponse(responseCode = "500", description = "Service Unavailable")})
  public ResponseEntity<ResponseObj> getPrimes(
      @PathParam("number") @PathVariable final int number) {
    log.info("Controller class to get all Prime Number up-to {} ", number);
    return new ResponseEntity<>(generatorService.primesRange(number), HttpStatus.OK);

  }
}

package com.betrybe.agrix.advice;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type General controller advice.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Handle invalid coordinate exception response entity.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFoundException(
      FarmNotFoundException e) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Fazenda não encontrada!");
  }

  /**
   * Handle crop not found exception response entity.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(
      CropNotFoundException e) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Plantação não encontrada!");
  }

  /**
   * Handle fertilizer not found exception response entity.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleFertilizerNotFoundException(
      FertilizerNotFoundException e) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Fertilizante não encontrado!");
  }

  /**
   * Handle exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }
}

package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.FertilizerCreationDto;
import com.betrybe.agrix.controllers.dtos.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer response entity.
   *
   * @param newFertilizer the new fertilizer
   * @return the response entity
   */
  @PostMapping()
  public ResponseEntity<FertilizerDto> createFertilizer(
      @RequestBody FertilizerCreationDto newFertilizer) {
    Fertilizer fertilizer = fertilizerService.createFertilizer(newFertilizer.toFertilizer());
    FertilizerDto fertilizerDto = new FertilizerDto(fertilizer.getId(), fertilizer.getName(),
        fertilizer.getBrand(), fertilizer.getComposition());
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerDto);
  }

  /**
   * Gets fertilizer.
   *
   * @return the fertilizer
   */
  @Secured("ADMIN")
  @GetMapping()
  public ResponseEntity<List<FertilizerDto>> getFertilizer() {
    List<Fertilizer> allFertilizers = fertilizerService.getFertilizers();
    List<FertilizerDto> fertilizersDtos = allFertilizers.stream()
        .map(fertilizer -> new FertilizerDto(fertilizer.getId(),
            fertilizer.getName(), fertilizer.getBrand(),
            fertilizer.getComposition())).toList();
    return ResponseEntity.status(HttpStatus.OK).body(fertilizersDtos);
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(id);
    FertilizerDto fertilizerDto = new FertilizerDto(fertilizer.getId(), fertilizer.getName(),
        fertilizer.getBrand(), fertilizer.getComposition());
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerDto);
  }
}

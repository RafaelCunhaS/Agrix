package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.CropDto;
import com.betrybe.agrix.controllers.dtos.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping()
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();
    List<CropDto> cropsDto = allCrops.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(),
            crop.getPlantedArea(), crop.getPlantedDate(),
            crop.getHarvestDate(), crop.getFarm().getId())).toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }

  /**
   * Gets crop.
   *
   * @param id the id
   * @return the crop
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCrop(@PathVariable Long id) {
    Crop crop = cropService.getCrop(id);
    CropDto cropDto = new CropDto(crop.getId(), crop.getName(),
        crop.getPlantedArea(), crop.getPlantedDate(),
        crop.getHarvestDate(), crop.getFarm().getId());
    return ResponseEntity.status(HttpStatus.OK).body(cropDto);
  }

  /**
   * Search crop by date response entity.
   *
   * @param start the start
   * @param end   the end
   * @return the response entity
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> searchCropByDate(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> allCrops = cropService.searchCropByDate(start, end);
    List<CropDto> cropsDto = allCrops.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(),
            crop.getPlantedArea(), crop.getPlantedDate(),
            crop.getHarvestDate(), crop.getFarm().getId())).toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }

  /**
   * Sets crop to fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop to fertilizer
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> setCropToFertilizer(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    cropService.setCropToFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Gets crop fertilizers.
   *
   * @param cropId the crop id
   * @return the crop fertilizers
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getCropFertilizers(@PathVariable Long cropId) {
    List<Fertilizer> allFertilizers = cropService.getCropFertilizers(cropId);
    List<FertilizerDto> fertilizerDtos = allFertilizers.stream()
        .map(fertilizer -> new FertilizerDto(fertilizer.getId(),
            fertilizer.getName(), fertilizer.getBrand(),
            fertilizer.getComposition())).toList();
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerDtos);
  }
}

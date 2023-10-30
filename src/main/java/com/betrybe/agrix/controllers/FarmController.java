package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.CropCreationDto;
import com.betrybe.agrix.controllers.dtos.CropDto;
import com.betrybe.agrix.controllers.dtos.FarmCreationDto;
import com.betrybe.agrix.controllers.dtos.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
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
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm response entity.
   *
   * @param newFarm the new farm
   * @return the response entity
   */
  @PostMapping()
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmCreationDto newFarm) {
    Farm farm = farmService.createFarm(newFarm.toFarm());
    FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());

    return ResponseEntity.status(HttpStatus.CREATED).body(farmDto);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @Secured({"ADMIN", "MANAGER", "USER"})
  @GetMapping()
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    List<FarmDto> farmsDto = allFarms.stream()
        .map(farm -> new FarmDto(farm.getId(), farm.getName(), farm.getSize())).toList();
    return ResponseEntity.status((HttpStatus.OK)).body(farmsDto);
  }

  /**
   * Gets farm.
   *
   * @param id the id
   * @return the farm
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarm(@PathVariable Long id) {
    Farm farm = farmService.getFarm(id);
    FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
    return ResponseEntity.status(HttpStatus.OK).body(farmDto);
  }

  /**
   * Sets crop.
   *
   * @param farmId  the farm id
   * @param newCrop the new crop
   * @return the crop
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> setCrop(@PathVariable Long farmId,
      @RequestBody CropCreationDto newCrop) {
    Crop crop = farmService.createCrop(farmId, newCrop.toCrop());
    CropDto cropDto = new CropDto(crop.getId(), crop.getName(),
        crop.getPlantedArea(), crop.getPlantedDate(),
        crop.getHarvestDate(), crop.getFarm().getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto);
  }

  /**
   * Gets all farm crops.
   *
   * @param farmId the farm id
   * @return the all farm crops
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getAllFarmCrops(@PathVariable Long farmId) {
    List<Crop> allCrops = farmService.getAllFarmCrops(farmId);
    List<CropDto> cropsDto = allCrops.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(),
            crop.getPlantedArea(), crop.getPlantedDate(),
            crop.getHarvestDate(), crop.getFarm().getId())).toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }
}

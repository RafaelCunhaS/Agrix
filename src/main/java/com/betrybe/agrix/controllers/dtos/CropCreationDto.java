package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * The type Crop creation dto.
 */
public record CropCreationDto(String name, double plantedArea,
                              LocalDate plantedDate, LocalDate harvestDate) {

  /**
   * To crop crop.
   *
   * @return the crop
   */
  public Crop toCrop() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}

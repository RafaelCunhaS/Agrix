package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * The type Fertilizer creation dto.
 */
public record FertilizerCreationDto(String name, String brand, String composition) {

  /**
   * To fertilizer fertilizer.
   *
   * @return the fertilizer
   */
  public Fertilizer toFertilizer() {
    return new Fertilizer(name, brand, composition);
  }
}

package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Farm;

/**
 * The type Farm creation dto.
 */
public record FarmCreationDto(String name, double size) {

  /**
   * To farm farm.
   *
   * @return the farm
   */
  public Farm toFarm() {
    return new Farm(name, size);
  }
}

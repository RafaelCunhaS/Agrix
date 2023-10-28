package com.betrybe.agrix.controllers.dtos;

import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(Long id, String name, double plantedArea,
                      LocalDate plantedDate, LocalDate harvestDate, Long farmId) {}

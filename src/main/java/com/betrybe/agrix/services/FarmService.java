package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRespository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRespository cropRespository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository  the farm repository
   * @param cropRespository the crop respository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRespository cropRespository) {
    this.farmRepository = farmRepository;
    this.cropRespository = cropRespository;
  }

  /**
   * Create farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Gets farm.
   *
   * @param id the id
   * @return the farm
   */
  public Farm getFarm(Long id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);
    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return optionalFarm.get();
  }

  /**
   * Create crop crop.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop
   */
  public Crop createCrop(Long farmId, Crop crop) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);
    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    Farm farm = optionalFarm.get();
    crop.setFarm(farm);

    Crop createdCrop = cropRespository.save(crop);
    farm.setCrop(createdCrop);
    return createdCrop;
  }

  /**
   * Gets all farm crops.
   *
   * @param farmId the farm id
   * @return the all farm crops
   */
  public List<Crop> getAllFarmCrops(Long farmId) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);
    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return optionalFarm.get().getCrops();
  }
}

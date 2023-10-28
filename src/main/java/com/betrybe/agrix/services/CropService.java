package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRespository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final FarmRepository farmRepository;
  private final CropRespository cropRespository;
  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository       the farm repository
   * @param cropRespository      the crop respository
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public CropService(FarmRepository farmRepository, CropRespository cropRespository,
      FertilizerRepository fertilizerRepository) {
    this.farmRepository = farmRepository;
    this.cropRespository = cropRespository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<Crop> getAllCrops() {
    List<Farm> farms = farmRepository.findAll();
    List<Crop> crops = new ArrayList<>();
    for (Farm farm : farms) {
      crops.addAll(farm.getCrops());
    }

    return crops;
  }

  /**
   * Gets crop.
   *
   * @param id the id
   * @return the crop
   */
  public Crop getCrop(Long id) {
    Optional<Crop> optionalCrop = cropRespository.findById(id);
    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }
    return optionalCrop.get();
  }

  /**
   * Search crop by date list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  public List<Crop> searchCropByDate(LocalDate start, LocalDate end) {
    return cropRespository.findByHarvestDateBetween(start, end);
  }

  /**
   * Sets crop to fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   */
  @Transactional
  public void setCropToFertilizer(Long cropId, Long fertilizerId) {
    Crop crop = cropRespository.findById(cropId).orElseThrow(CropNotFoundException::new);
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId).orElseThrow(
        FertilizerNotFoundException::new);

    crop.addFertilizer(fertilizer);
    cropRespository.save(crop);
    fertilizer.addCrop(crop);
    fertilizerRepository.save(fertilizer);
  }

  /**
   * Gets crop fertilizers.
   *
   * @param cropId the crop id
   * @return the crop fertilizers
   */
  public List<Fertilizer> getCropFertilizers(Long cropId) {
    Crop crop = cropRespository.findById(cropId).orElseThrow(CropNotFoundException::new);
    return crop.getFertilizers();
  }
}

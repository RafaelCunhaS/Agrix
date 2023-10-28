package com.betrybe.agrix.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Crop.
 */
@Entity
@Table(name = "crops")
public class Crop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(name = "planted_area")
  private double plantedArea;
  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;
  @Column(name = "planted_date")
  private LocalDate plantedDate;
  @Column(name = "harvest_date")
  private LocalDate harvestDate;
  @ManyToMany(mappedBy = "crops")
  private List<Fertilizer> fertilizers;

  /**
   * Instantiates a new Crop.
   */
  public Crop() {}

  /**
   * Instantiates a new Crop.
   *
   * @param name        the name
   * @param plantedArea the planted area
   */
  public Crop(String name, double plantedArea, LocalDate plantedDate, LocalDate harvestDate) {
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets planted area.
   *
   * @return the planted area
   */
  public double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Sets planted area.
   *
   * @param plantedArea the planted area
   */
  public void setPlantedArea(double plantedArea) {
    this.plantedArea = plantedArea;
  }

  /**
   * Gets farm.
   *
   * @return the farm
   */
  public Farm getFarm() {
    return farm;
  }

  /**
   * Sets farm.
   *
   * @param farm the farm
   */
  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void addFertilizer(Fertilizer fertilizer) {
    this.fertilizers.add(fertilizer);
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }
}

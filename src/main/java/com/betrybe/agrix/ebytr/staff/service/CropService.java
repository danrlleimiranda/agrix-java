package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository       the crop repository
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }


  /**
   * Create crop crop.
   *
   * @param crop the crop
   * @return the crop
   */
  public Crop createCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  /**
   * Gets specific crops.
   *
   * @param farmId the farm id
   * @return the specific crops
   */
  public List<Crop> getSpecificCrops(Long farmId) {

    List<Crop> crops = cropRepository.findAll();

    return crops.stream().filter(crop -> Objects.equals(crop.getFarm().getId(), farmId)).toList();
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop getCropById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
  }

  /**
   * Gets crop by date.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by date
   */
  public List<Crop> getCropByDate(LocalDate start, LocalDate end) {
    return cropRepository.findAllByHarvestDateBetween(start, end);
  }

  /**
   * Associate crop and fertilizer.
   *
   * @param cropId     the crop id
   * @param fertilizer the fertilizer
   * @throws CropNotFoundException the crop not found exception
   */
  @Transactional
  public void associateCropAndFertilizer(Long cropId, Fertilizer fertilizer)
      throws CropNotFoundException {
    Crop crop = getCropById(cropId);
    crop.setFertilizers(fertilizer);
  }

}

package com.betrybe.agrix.ebytr.staff.controller;


import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService       the crop service
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }


  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public List<CropDto> getAllCrops() {
    return cropService.getAllCrops().stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public CropDto getCropById(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.fromEntity(cropService.getCropById(id));
  }

  /**
   * Gets crop by date.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by date
   */
  @GetMapping("/search")
  public List<CropDto> getCropByDate(@RequestParam LocalDate start, @RequestParam LocalDate end) {
    return cropService.getCropByDate(start, end).stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Associate crop and fertilizer string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String associateCropAndFertilizer(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) throws CropNotFoundException, FertilizerNotFoundException {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);

    cropService.associateCropAndFertilizer(cropId, fertilizer);

    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Find all fertilizers by crop id list.
   *
   * @param cropId the crop id
   * @return the list
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> findAllFertilizersByCropId(@PathVariable Long cropId)
      throws CropNotFoundException {
    return cropService.getCropById(cropId).getFertilizers().stream().map(FertilizerDto::fromEntity)
        .toList();
  }
}

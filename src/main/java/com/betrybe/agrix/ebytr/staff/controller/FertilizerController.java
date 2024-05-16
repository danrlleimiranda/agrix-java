package com.betrybe.agrix.ebytr.staff.controller;


import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import com.betrybe.agrix.ebytr.staff.service.exception.FertilizerNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  /**
   * The Fertilizer service.
   */
  FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer fertilizer dto.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(@RequestBody FertilizerCreationDto fertilizer) {
    return FertilizerDto.fromEntity(fertilizerService.createFertilizer(fertilizer.toEntity()));
  }

  /**
   * Gets fertilizers.
   *
   * @return the fertilizers
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<FertilizerDto> getFertilizers() {
    return fertilizerService.getFertilizers().stream().map(FertilizerDto::fromEntity).toList();
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FertilizerDto getFertilizerById(@PathVariable Long id) throws FertilizerNotFoundException {
    return FertilizerDto.fromEntity(fertilizerService.getFertilizerById(id));
  }
}

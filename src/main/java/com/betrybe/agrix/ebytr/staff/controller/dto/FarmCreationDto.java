package com.betrybe.agrix.ebytr.staff.controller.dto;


import com.betrybe.agrix.ebytr.staff.entity.Farm;

/**
 * The type Farm creation dto.
 */
public record FarmCreationDto(String name, Double size) {


  /**
   * To entity farm.
   *
   * @return the farm
   */
  public Farm toEntity() {
    return new Farm(name, size);
  }
}

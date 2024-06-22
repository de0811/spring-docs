package com.sdm.docs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

//@Setter
@Getter
@NoArgsConstructor
public class SwaggerDto {
  Long id;
  String name;
  String clazz;

  public SwaggerDto(Long id, String name, String clazz) {
    this.id = id;
    this.name = name;
    this.clazz = clazz;
  }
}

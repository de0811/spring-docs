package com.sdm.docs.controller;

import com.sdm.docs.dto.SwaggerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class SwaggerTestController {
  @RequestMapping(value = "/swaggers", method = RequestMethod.GET)
  public List<SwaggerDto> getSwaggers(
    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
    @RequestParam(value = "size", required = false, defaultValue = "10") int size,
    @RequestParam(value = "sort", required = false, defaultValue = "clazz,desc") String sort
  ) {
    log.info("getSwagers");
    List<SwaggerDto> swaggers = new ArrayList<>();

    // 샘플로 전달
    swaggers.add(new SwaggerDto(1L, "Swagger1", "com.nshc.tiket.tiket0"));
    swaggers.add(new SwaggerDto(2L, "Swagger2", "com.nshc.tiket.tiket1"));
    swaggers.add(new SwaggerDto(3L, "Swagger3", "com.nshc.tiket.tiket2"));
    swaggers.add(new SwaggerDto(4L, "Swagger4", "com.nshc.tiket.tiket3"));

    return swaggers;
  }

  @RequestMapping(value = "/swaggers/{id}", method = RequestMethod.GET)
  public SwaggerDto getSwagger(@PathVariable(value = "id") Long id) {
    log.info("getSwager");
    return new SwaggerDto(id, "Swagger", "com.nshc.tiket.tiket" + (id -1));
  }

  @RequestMapping(value = "/swaggers", method = RequestMethod.POST)
  public SwaggerDto createSwagger(@RequestBody SwaggerDto swaggerDto) {
    log.info("createSwager");
    return swaggerDto;
  }

  @RequestMapping(value = "/swaggers/{id}", method = RequestMethod.PUT)
  public SwaggerDto updateSwagger(@PathVariable(value = "id") Long id, @RequestBody SwaggerDto swaggerDto) {
    log.info("updateSwager");
    return swaggerDto;
  }

  @RequestMapping(value = "/swaggers/{id}", method = RequestMethod.DELETE)
  public String deleteSwagger(@PathVariable(value = "id") Long id) {
    log.info("deleteSwager");
    return "delete success";
  }
}

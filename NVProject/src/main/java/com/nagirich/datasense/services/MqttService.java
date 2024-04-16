package com.nagirich.datasense.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagirich.datasense.converter.EndpointConverter;
import com.nagirich.datasense.dto.modelEndpoint.EndpointDto;
import com.nagirich.datasense.repository.EndpointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MqttService {

  private final EndpointRepository endpointRepository;

  public void datta(String json)  {
    EndpointDto endpointDto;
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      endpointDto = objectMapper.readValue(json, EndpointDto.class);
      System.out.println(endpointDto);
      endpointRepository.save(EndpointConverter.toEntity(endpointDto));
      System.out.println("Saved!");

    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}

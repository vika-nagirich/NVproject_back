package com.nagirich.datasense.converter;

import com.nagirich.datasense.dto.modelEndpoint.EndpointDto;
import com.nagirich.datasense.entity.EndpointEntity;

public class EndpointConverter {
  public static EndpointEntity toEntity(EndpointDto endpointDto){
    return EndpointEntity.builder()
        .deviceId(endpointDto.getDeviceId())
        .userId(endpointDto.getUserId())
        .accessToken(endpointDto.getAccessToken())
        .timestamp(endpointDto.getTimestamp())
        .comments(endpointDto.getComments())
        .name(endpointDto.getName())
        .measurementType(endpointDto.getMeasurementType())
        .rawValue(endpointDto.getRawValue())
        .convertedValue(endpointDto.getConvertedValue())
        .units(endpointDto.getUnits())
        .build();
  }
}

package com.nagirich.datasense.dto.modelEndpoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EndpointDto {
  private String deviceId;
  private String userId;
  private String accessToken;
  private long timestamp;
  private String comments;
  private String name;
  private String measurementType;
  private int rawValue;
  private double convertedValue;
  private String units;
}

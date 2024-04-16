package com.nagirich.datasense.dto.modelEndpoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDataDto {
  public String deviceId;
  public String userId;
  public String accessToken;
  public long timestamp;
  public LocationDto location;
  public String comments;
  public PayloadDto payloadDto;
}

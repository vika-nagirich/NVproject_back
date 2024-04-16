package com.nagirich.datasense.dto.modelEndpoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayloadDto {
  public String name;
  public String measurementType;
  public int rawValue;
  public double convertedValue;
  public String units;
}

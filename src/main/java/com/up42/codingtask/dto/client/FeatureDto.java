package com.up42.codingtask.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class FeatureDto
{

    private FeaturePropertiesDto properties;
}

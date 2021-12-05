package com.up42.codingtask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class FeatureDto
{

    private FeaturePropertiesDto properties;
}

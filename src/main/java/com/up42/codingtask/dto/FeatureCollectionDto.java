package com.up42.codingtask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class FeatureCollectionDto
{

    private List<FeatureDto> features;
}

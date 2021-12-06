package com.up42.codingtask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class FeaturePropertiesDto
{
    private String id;

    private Long timestamp;

    private FeatureAcquisitionDto acquisition;

    private String quicklook;
}

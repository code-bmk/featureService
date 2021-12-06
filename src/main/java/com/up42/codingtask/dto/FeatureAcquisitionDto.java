package com.up42.codingtask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class FeatureAcquisitionDto
{

    private Long beginViewingDate;

    private Long endViewingDate;

    private String missionName;
}

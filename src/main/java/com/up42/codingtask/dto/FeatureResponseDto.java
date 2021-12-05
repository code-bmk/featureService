package com.up42.codingtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FeatureResponseDto
{

    private String id;

    private Long timestamp;

    private Long beginViewingDate;

    private Long endViewingDate;

    private String missionName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String quickLook;
}

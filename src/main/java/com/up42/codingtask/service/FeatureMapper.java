package com.up42.codingtask.service;

import com.up42.codingtask.dto.FeatureDto;
import com.up42.codingtask.dto.FeatureCollectionDto;
import com.up42.codingtask.dto.FeatureResponseDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeatureMapper
{
    public static FeatureResponseDto makeFeatureToResponse(FeatureDto featureDto)
    {
        FeatureResponseDto.FeatureResponseDtoBuilder featureResponseDTOBuilder = FeatureResponseDto.builder();
        return featureResponseDTOBuilder
            .id(featureDto.getProperties().getId())
            .timestamp(featureDto.getProperties().getTimestamp())
            .beginViewingDate(featureDto.getProperties().getAcquisition().getBeginViewingDate())
            .endViewingDate(featureDto.getProperties().getAcquisition().getEndViewingDate())
            .missionName(featureDto.getProperties().getAcquisition().getMissionName())
            .quickLook(featureDto.getProperties().getQuicklook())
            .build();
    }


    public static List<FeatureResponseDto> getFeatureResponseList(List<FeatureCollectionDto> featureCollectionDtos)
    {
        return featureCollectionDtos
            .stream()
            .map(featureCollectionDto -> makeFeatureToResponse(featureCollectionDto.getFeatures().get(0)))
            .collect(Collectors.toList());
    }


    public static Map<String, FeatureCollectionDto> getFeatureDataMap(List<FeatureCollectionDto> featureCollectionDtos)
    {
        return featureCollectionDtos.stream().collect(Collectors.toMap(
            featureCollectionDto -> featureCollectionDto.getFeatures().get(0).getProperties().getId(),
            featureCollectionDto -> featureCollectionDto
        ));
    }
}

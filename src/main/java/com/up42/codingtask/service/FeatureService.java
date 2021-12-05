package com.up42.codingtask.service;

import com.up42.codingtask.dto.FeatureResponseDto;
import java.util.List;

public interface FeatureService
{
    List<FeatureResponseDto> findAllFeatures();
    FeatureResponseDto findFeatureById(String id);
}

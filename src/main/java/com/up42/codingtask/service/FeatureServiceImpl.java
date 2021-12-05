package com.up42.codingtask.service;

import com.up42.codingtask.client.FeatureClient;
import com.up42.codingtask.dto.FeatureCollectionDto;
import com.up42.codingtask.dto.FeatureResponseDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FeatureServiceImpl implements FeatureService
{
    private List<FeatureCollectionDto> featureCollectionDtos = new ArrayList<>();
    private Map<String, FeatureCollectionDto> featureResponseDtoMap = new HashMap<>();

    private final FeatureClient featureClient;


    @Autowired
    public FeatureServiceImpl(final FeatureClient featureClient)
    {
        this.featureClient = featureClient;
    }


    /**
     * This method is responsible for initialising the feature data from the static resource
     * during the boot up of the application.
     */
    @PostConstruct
    public void init()
    {
        try
        {
            featureCollectionDtos = featureClient.loadFeatureData();
            featureResponseDtoMap = FeatureMapper.getFeatureDataMap(featureCollectionDtos);

        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }
    }


    @Override
    public List<FeatureResponseDto> findAllFeatures()
    {
        return FeatureMapper.getFeatureResponseList(featureCollectionDtos);
    }


    @Override
    public FeatureResponseDto findFeatureById(String id)
    {
        if(featureResponseDtoMap.containsKey(id))
            return FeatureMapper.makeFeatureToResponse(featureResponseDtoMap.get(id).getFeatures().get(0));
        return null;
    }
}

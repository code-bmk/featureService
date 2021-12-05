package com.up42.codingtask.service;

import com.up42.codingtask.client.FeatureClient;
import com.up42.codingtask.dto.FeatureCollectionDto;
import com.up42.codingtask.dto.FeatureResponseDto;
import com.up42.codingtask.exception.DataReadingException;
import com.up42.codingtask.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Base64;
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
    private static final String DATA_NOT_LOADED_MESSAGE = "Unable to load the feature data {}";
    private static final String ENTITY_NOT_FOUND_MESSAGE = "Feature entity not found with id: ";

    private List<FeatureCollectionDto> featureCollectionList = new ArrayList<>();
    private Map<String, FeatureCollectionDto> featureResponseDtoMap = new HashMap<>();

    private final FeatureClient featureClient;


    @Autowired
    public FeatureServiceImpl(final FeatureClient featureClient)
    {
        this.featureClient = featureClient;
    }


    /**
     * This method is responsible for initialising the feature data from the static resource
     * into both map and list during the boot up of the application.
     * @throws DataReadingException if there is error in reading data, the application will not start
     */
    @PostConstruct
    public void init() throws DataReadingException
    {
        featureCollectionList = featureClient.loadFeatureData();
        featureResponseDtoMap = FeatureMapper.getFeatureDataMap(featureCollectionList);

    }


    /**
     * This method finds the list of features
     *
     * @return List<FeatureResponseDto>
     */
    @Override
    public List<FeatureResponseDto> findAllFeatures()
    {
        return FeatureMapper.getFeatureResponseList(featureCollectionList);
    }


    /**
     * This method finds a feature by id
     *
     * @param id String
     * @return FeatureResponseDto
     * @throws EntityNotFoundException if a feature is not found with given id
     */
    @Override
    public FeatureResponseDto findFeatureById(String id) throws EntityNotFoundException
    {
        return findFeature(id);
    }


    /**
     * This method returns the satellite image for a given feature id
     *
     * @param id String
     * @return byte[]
     * @throws EntityNotFoundException if a feature is not found with given id
     */
    @Override
    public byte[] getFeatureQuicklook(String id) throws EntityNotFoundException
    {
        FeatureResponseDto featureResponseDto = findFeature(id);
        return Base64.getDecoder().decode(featureResponseDto.getQuickLook());
    }


    /**
     * This utility method finds a feature from the map loaded with feature data
     * Map is used so that access is O(1), instead of filtering the List by id
     *
     * @param id String
     * @return FeatureResponseDto
     * @throws EntityNotFoundException if a feature is not found with given id
     */
    private FeatureResponseDto findFeature(String id) throws EntityNotFoundException
    {
        if (featureResponseDtoMap.containsKey(id))
        {
            return FeatureMapper.makeFeatureToResponse(featureResponseDtoMap.get(id).getFeatures().get(0));
        }
        else
        {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id);
        }
    }
}

package com.up42.codingtask.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.codingtask.dto.client.FeatureCollectionDto;
import com.up42.codingtask.exception.DataReadingException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FeatureClient
{
    private static final String DATA_READING_EXCEPTION_MESSAGE = "Unable to read data from the source ";

    @Value("${app.data.static.path}")
    Resource resource;


    /**
     * This method loads the feature data from the source
     *
     * @return List<FeatureCollectionDto>
     * @throws DataReadingException if there is error reading data from the source
     */
    public List<FeatureCollectionDto> loadFeatureData() throws DataReadingException
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resource.getInputStream(), new TypeReference<>(){});
        }
        catch (IOException e)
        {
            throw new DataReadingException(DATA_READING_EXCEPTION_MESSAGE + e);
        }
    }

}

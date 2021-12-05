package com.up42.codingtask.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.codingtask.dto.FeatureCollectionDto;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FeatureClient
{
    @Value("classpath:source-data.json")
    Resource resource;


    public List<FeatureCollectionDto> loadFeatureData() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(resource.getInputStream(), new TypeReference<>(){});
    }

}

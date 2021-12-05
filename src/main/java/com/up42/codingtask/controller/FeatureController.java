package com.up42.codingtask.controller;

import com.up42.codingtask.dto.FeatureResponseDto;
import com.up42.codingtask.exception.EntityNotFoundException;
import com.up42.codingtask.service.FeatureService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with the feature will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/features")
public class FeatureController
{

    private final FeatureService featureService;


    @Autowired
    public FeatureController(final FeatureService featureService)
    {
        this.featureService = featureService;
    }


    @GetMapping
    public List<FeatureResponseDto> findAllFeatures()
    {
        return featureService.findAllFeatures();
    }


    @GetMapping("/{id}")
    public FeatureResponseDto findFeature(@PathVariable String id) throws EntityNotFoundException
    {
        return featureService.findFeatureById(id);
    }


    @GetMapping(value = "/{id}/quicklook", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getFeatureQuicklook(@PathVariable String id) throws EntityNotFoundException
    {
        return featureService.getFeatureQuicklook(id);
    }

}

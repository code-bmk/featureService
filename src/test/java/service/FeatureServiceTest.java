package service;

import com.up42.codingtask.client.FeatureClient;
import com.up42.codingtask.dto.FeatureAcquisitionDto;
import com.up42.codingtask.dto.FeatureCollectionDto;
import com.up42.codingtask.dto.FeatureDto;
import com.up42.codingtask.dto.FeaturePropertiesDto;
import com.up42.codingtask.dto.FeatureResponseDto;
import com.up42.codingtask.exception.DataReadingException;
import com.up42.codingtask.exception.EntityNotFoundException;
import com.up42.codingtask.service.FeatureServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class FeatureServiceTest
{
    @InjectMocks
    private FeatureServiceImpl featureService;

    @Mock
    private FeatureClient featureClient;

    private static String id;
    private static String base64DecodedValue;
    private static String base64EncodedValue;

    @BeforeClass
    public static void init()
    {
        id = "39c2f29e-c0f8-4a39-a98b-deed547d6aea";
        base64DecodedValue = "TestImageData";
        base64EncodedValue = "VGVzdEltYWdlRGF0YQ==";
    }

    @Before
    public void setup() throws DataReadingException
    {
        Mockito.when(featureClient.loadFeatureData()).thenReturn(getMockFeatureCollectionData());
        featureService.init();
    }

    @Test
    public void testWhenFindAllFeatures(){
        List<FeatureResponseDto> result = featureService.findAllFeatures();
        assertNotNull(result);
        assertEquals(result.size(), 1);
    }

    @Test
    public void testWhenFindFeatureById() throws EntityNotFoundException
    {
        FeatureResponseDto result = featureService.findFeatureById(id);
        assertNotNull(result);
        assertEquals(result.getId(), id);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testWhenFindFeatureByIdDuringException() throws EntityNotFoundException
    {
        featureService.findFeatureById("1234");
    }
    @Test
    public void testWhenGetFeatureQuicklook() throws EntityNotFoundException
    {
        byte[] result = featureService.getFeatureQuicklook(id);
        assertEquals(new String(result), base64DecodedValue);
    }

    private List<FeatureCollectionDto> getMockFeatureCollectionData(){
        List<FeatureCollectionDto> featureCollections = new ArrayList<>();
        FeatureCollectionDto featureCollectionDto = new FeatureCollectionDto();
        List<FeatureDto> featureDtos = new ArrayList<>();
        FeatureDto featureDto = new FeatureDto();
        featureDto.setProperties(getMockFeaturePropertiesDto(getMockFeatureAcquisitionDto()));
        featureDtos.add(featureDto);
        featureCollectionDto.setFeatures(featureDtos);
        featureCollections.add(featureCollectionDto);
        return featureCollections;
    }

    private FeatureAcquisitionDto getMockFeatureAcquisitionDto(){
        FeatureAcquisitionDto featureAcquisitionDto = new FeatureAcquisitionDto();
        featureAcquisitionDto.setBeginViewingDate(1554831167697L);
        featureAcquisitionDto.setEndViewingDate(1564831167607L);
        featureAcquisitionDto.setMissionName("Sentinel-B1");
        return  featureAcquisitionDto;
    }
    private FeaturePropertiesDto getMockFeaturePropertiesDto(FeatureAcquisitionDto featureAcquisitionDto){
        FeaturePropertiesDto featurePropertiesDto = new FeaturePropertiesDto();
        featurePropertiesDto.setId(id);
        featurePropertiesDto.setTimestamp(1534831167607L);
        featurePropertiesDto.setQuicklook(base64EncodedValue);
        featurePropertiesDto.setAcquisition(featureAcquisitionDto);
        return  featurePropertiesDto;
    }


}

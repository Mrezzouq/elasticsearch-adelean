package org.adelean.elasticsearch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.adelean.elasticsearch.model.Product;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IndexationServiceTest {
    @Mock
    private ElasticsearchPort elasticsearchPort;

    @Mock
    private RestHighLevelClient client;

    @Mock
    private BulkResponse bulkResponse;

    @InjectMocks
    private IndexationService indexationService;

    private static Product buildProduct() {
        return Product.builder()
                .withArticleSku("1")
                .withBrand("Brand")
                .withBrandReference("BrandReference")
                .withCategory("Category")
                .withEan13("EAN13")
                .withFullDescription("a full Description")
                .withLongTitle("LongTitle")
                .withMediaUrl1("MediaUrl1")
                .withMediaUrl2("MediaUrl2")
                .withMediaUrl3("MediaUrl3")
                .withMediaUrl4("MediaUrl4")
                .withMediaUrl5("MediaUrl5")
                .withShortDescription("Short Description")
                .withWarranty("Warranty")
                .withWarrantyScope("Warranty Scope")
                .withItBluetoothVersion("Bluetooth Version")
                .withItWithWifiConnection("true")
                .withColor("Color")
                .withWeightG("100")
                .withItDisplaySizePouces("5")
                .withItIsTactile("true")
                .withItWithApn("true")
                .withItWithNfcConnection("true")
                .withItMemoryRamInstalledGo("4")
                .withItBatteryCapacityMah("3000")
                .withItTabletteOs("OS")
                .withItWithSenior("true")
                .withItResolutionSecondCameraMpixels("12")
                .withItSensorResolutionMpixels("16")
                .withItNbrSimMobile("2")
                .withItSimMobileType("SIM Type")
                .withItSmartphoneType("Smartphone Type")
                .withItWithAutofocus("true")
                .withItWithMemoryCardSlot("true")
                .withItWithNormed3gUmts("true")
                .withItWithOutdoor("true")
                .withIt4gVersion("4G Version")
                .withItFlashType("Flash Type")
                .withItMemoryRomIntegratedGo("64")
                .withItNbrCpuCore("8")
                .withItOsVersion("OS Version")
                .withItResolutionScreenPixels("Resolution")
                .withItCpuFrequencyGhz("2.5")
                .withItCpuSmartphoneType("CPU Type")
                .withWeightKg("0.2")
                .withDimensionsLengthCm("15")
                .withDimensionsHeightCm("7")
                .withChargeConsumptionWatts("10")
                .withItResponseFrequencyMaxiHz("20000")
                .withItResponseFrequencyMiniHz("20")
                .withItWirelessNetworkType("Wireless Network Type")
                .withItConverter("Converter")
                .withShortTitle("a short title")
                .build();
    }

    @BeforeEach
    void setUp() {
        when(elasticsearchPort.getClient()).thenReturn(client);
        lenient().when(elasticsearchPort.getObjectMapper()).thenReturn(new ObjectMapper());
    }

    @Test
    void should_index_products_successfully() throws IOException {
        // Arrange
        when(bulkResponse.hasFailures()).thenReturn(false);
        when(client.bulk(any(BulkRequest.class), eq(RequestOptions.DEFAULT))).thenReturn(bulkResponse);

        // Act
        indexationService.indexProducts(Collections.singletonList(buildProduct()));

        // Assert
        verify(client, times(1)).bulk(any(BulkRequest.class), eq(RequestOptions.DEFAULT));
        assertThat(bulkResponse.hasFailures()).isFalse();
    }

    @Test
    void should_handle_bulk_request_execution_failure() throws IOException {
        // Arrange
        when(client.bulk(any(BulkRequest.class), eq(RequestOptions.DEFAULT))).thenThrow(new IOException("Bulk request error"));

        // Act
        indexationService.indexProducts(Collections.singletonList(buildProduct()));

        // Assert
        verify(client, times(1)).bulk(any(BulkRequest.class), eq(RequestOptions.DEFAULT));
    }

    @Test
    void should_handle_bulk_response_with_failures() throws IOException {
        // Arrange
        when(bulkResponse.hasFailures()).thenReturn(true);
        when(client.bulk(any(BulkRequest.class), eq(RequestOptions.DEFAULT))).thenReturn(bulkResponse);

        // Act
        indexationService.indexProducts(Collections.singletonList(buildProduct()));

        // Assert
        verify(client, times(1)).bulk(any(BulkRequest.class), eq(RequestOptions.DEFAULT));
        assertThat(bulkResponse.hasFailures()).isTrue();
    }

    @Test
    void should_close_client() throws IOException {
        // Act
        indexationService.close();

        // Assert
        verify(client, times(1)).close();
    }

    @Test
    void should_handle_client_close_failure() throws IOException {
        // Arrange
        doThrow(new IOException("Close error")).when(client).close();

        // Act
        indexationService.close();

        // Assert
        verify(client, times(1)).close();
    }

}
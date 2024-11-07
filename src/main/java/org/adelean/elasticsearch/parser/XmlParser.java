package org.adelean.elasticsearch.parser;

import org.adelean.elasticsearch.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class XmlParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlParser.class);
    private final XMLInputFactory xmlInputFactory;

    public XmlParser(XMLInputFactory xmlInputFactory) {this.xmlInputFactory = xmlInputFactory;}

    public List<Product> parseXmlFile(InputStream inputStream) {
        List<Product> products = new ArrayList<>();
        try {
            var reader = xmlInputFactory.createXMLStreamReader(inputStream);
            Product.Builder productBuilder = null;
            var currentElement = "";
            var elementSetters = createElementSetters();

            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement = reader.getLocalName();
                        productBuilder = handleStartElement(currentElement, productBuilder);
                        break;

                    case XMLStreamConstants.CHARACTERS,
                         XMLStreamConstants.CDATA:
                        handleCharacters(reader, productBuilder, currentElement, elementSetters);
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        productBuilder = handleEndElement(reader, products, productBuilder);
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to parse XML file", e);
        }
        return products;
    }

    private Product.Builder handleStartElement(String currentElement, Product.Builder productBuilder) {
        if ("product".equals(currentElement)) {
            productBuilder = Product.builder();
            LOGGER.info("Started parsing a new product.");
        }
        return productBuilder;
    }

    private void handleCharacters(XMLStreamReader reader, Product.Builder productBuilder, String currentElement, Map<String, BiConsumer<Product.Builder, String>> elementSetters) {
        if (productBuilder != null && elementSetters.containsKey(currentElement)) {
            String text = reader.getText().trim();
            if (!text.isEmpty()) {
                elementSetters.get(currentElement).accept(productBuilder, text);
                LOGGER.info("Set {} to {}", currentElement, text);
            }
        }
    }

    private Product.Builder handleEndElement(XMLStreamReader reader, List<Product> products, Product.Builder productBuilder) {
        if ("product".equals(reader.getLocalName()) && productBuilder != null) {
            products.add(productBuilder.build());
            LOGGER.info("Finished parsing a product.");
            productBuilder = null;
        }
        return productBuilder;
    }

    private Map<String, BiConsumer<Product.Builder, String>> createElementSetters() {
        Map<String, BiConsumer<Product.Builder, String>> elementSetters = new HashMap<>();
        elementSetters.put("article_sku", Product.Builder::withArticleSku);
        elementSetters.put("brand", Product.Builder::withBrand);
        elementSetters.put("brand_reference", Product.Builder::withBrandReference);
        elementSetters.put("category", Product.Builder::withCategory);
        elementSetters.put("ean13", Product.Builder::withEan13);
        elementSetters.put("full_description", Product.Builder::withFullDescription);
        elementSetters.put("long_title", Product.Builder::withLongTitle);
        elementSetters.put("media_url_1", Product.Builder::withMediaUrl1);
        elementSetters.put("media_url_2", Product.Builder::withMediaUrl2);
        elementSetters.put("media_url_3", Product.Builder::withMediaUrl3);
        elementSetters.put("media_url_4", Product.Builder::withMediaUrl4);
        elementSetters.put("media_url_5", Product.Builder::withMediaUrl5);
        elementSetters.put("short_description", Product.Builder::withShortDescription);
        elementSetters.put("warranty", Product.Builder::withWarranty);
        elementSetters.put("warranty_scope", Product.Builder::withWarrantyScope);
        elementSetters.put("it_bluetooth_version", Product.Builder::withItBluetoothVersion);
        elementSetters.put("it_with_wifi_connection", Product.Builder::withItWithWifiConnection);
        elementSetters.put("color", Product.Builder::withColor);
        elementSetters.put("weight_g", Product.Builder::withWeightG);
        elementSetters.put("it_display_size_pouces", Product.Builder::withItDisplaySizePouces);
        elementSetters.put("it_is_tactile", Product.Builder::withItIsTactile);
        elementSetters.put("it_with_apn", Product.Builder::withItWithApn);
        elementSetters.put("it_with_nfc_connection", Product.Builder::withItWithNfcConnection);
        elementSetters.put("it_memory_ram_installed_go", Product.Builder::withItMemoryRamInstalledGo);
        elementSetters.put("it_battery_capacity_mah", Product.Builder::withItBatteryCapacityMah);
        elementSetters.put("it_tablette_os", Product.Builder::withItTabletteOs);
        elementSetters.put("it_with_senior", Product.Builder::withItWithSenior);
        elementSetters.put("it_resolution_second_camera_mpixels", Product.Builder::withItResolutionSecondCameraMpixels);
        elementSetters.put("it_sensor_resolution_mpixels", Product.Builder::withItSensorResolutionMpixels);
        elementSetters.put("it_nbr_sim_mobile", Product.Builder::withItNbrSimMobile);
        elementSetters.put("it_sim_mobile_type", Product.Builder::withItSimMobileType);
        elementSetters.put("it_smartphone_type", Product.Builder::withItSmartphoneType);
        elementSetters.put("it_with_autofocus", Product.Builder::withItWithAutofocus);
        elementSetters.put("it_with_memory_card_slot", Product.Builder::withItWithMemoryCardSlot);
        elementSetters.put("it_with_normed_3g_umts", Product.Builder::withItWithNormed3gUmts);
        elementSetters.put("it_with_outdoor", Product.Builder::withItWithOutdoor);
        elementSetters.put("it_4g_version", Product.Builder::withIt4gVersion);
        elementSetters.put("it_flash_type", Product.Builder::withItFlashType);
        elementSetters.put("it_memory_rom_integrated_go", Product.Builder::withItMemoryRomIntegratedGo);
        elementSetters.put("it_nbr_cpu_core", Product.Builder::withItNbrCpuCore);
        elementSetters.put("it_os_version", Product.Builder::withItOsVersion);
        elementSetters.put("it_resolution_screen_pixels", Product.Builder::withItResolutionScreenPixels);
        elementSetters.put("it_cpu_frequency_ghz", Product.Builder::withItCpuFrequencyGhz);
        elementSetters.put("it_cpu_smartphone_type", Product.Builder::withItCpuSmartphoneType);
        elementSetters.put("weight_kg", Product.Builder::withWeightKg);
        elementSetters.put("dimensions_length_cm", Product.Builder::withDimensionsLengthCm);
        elementSetters.put("dimensions_height_cm", Product.Builder::withDimensionsHeightCm);
        elementSetters.put("charge_consumption_watts", Product.Builder::withChargeConsumptionWatts);
        elementSetters.put("it_response_frequency_maxi_hz", Product.Builder::withItResponseFrequencyMaxiHz);
        elementSetters.put("it_response_frequency_mini_hz", Product.Builder::withItResponseFrequencyMiniHz);
        elementSetters.put("it_wireless_network_type", Product.Builder::withItWirelessNetworkType);
        elementSetters.put("it_converter", Product.Builder::withItConverter);
        return elementSetters;
    }
}

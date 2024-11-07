package org.adelean.elasticsearch.model;

public record Product(
        String articleSku,
        String brand,
        String brandReference,
        String category,
        String ean13,
        String fullDescription,
        String longTitle,
        String mediaUrl1,
        String mediaUrl2,
        String mediaUrl3,
        String mediaUrl4,
        String mediaUrl5,
        String shortDescription,
        String shortTitle,
        String warranty,
        String warrantyScope,
        String itBluetoothVersion,
        String itWithWifiConnection,
        String color,
        String weightG,
        String itDisplaySizePouces,
        String itIsTactile,
        String itWithApn,
        String itWithNfcConnection,
        String itMemoryRamInstalledGo,
        String itBatteryCapacityMah,
        String itTabletteOs,
        String itWithSenior,
        String itResolutionSecondCameraMpixels,
        String itSensorResolutionMpixels,
        String itNbrSimMobile,
        String itSimMobileType,
        String itSmartphoneType,
        String itWithAutofocus,
        String itWithMemoryCardSlot,
        String itWithNormed3gUmts,
        String itWithOutdoor,
        String it4gVersion,
        String itFlashType,
        String itMemoryRomIntegratedGo,
        String itNbrCpuCore,
        String itOsVersion,
        String itResolutionScreenPixels,
        String itCpuFrequencyGhz,
        String itCpuSmartphoneType,
        String weightKg,
        String dimensionsLengthCm,
        String dimensionsHeightCm,
        String chargeConsumptionWatts,
        String itResponseFrequencyMaxiHz,
        String itResponseFrequencyMiniHz,
        String itWirelessNetworkType,
        String itConverter
) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String articleSku;
        private String brand;
        private String brandReference;
        private String category;
        private String ean13;
        private String fullDescription;
        private String longTitle;
        private String mediaUrl1;
        private String mediaUrl2;
        private String mediaUrl3;
        private String mediaUrl4;
        private String mediaUrl5;
        private String shortDescription;
        private String shortTitle;
        private String warranty;
        private String warrantyScope;
        private String itBluetoothVersion;
        private String itWithWifiConnection;
        private String color;
        private String weightG;
        private String itDisplaySizePouces;
        private String itIsTactile;
        private String itWithApn;
        private String itWithNfcConnection;
        private String itMemoryRamInstalledGo;
        private String itBatteryCapacityMah;
        private String itTabletteOs;
        private String itWithSenior;
        private String itResolutionSecondCameraMpixels;
        private String itSensorResolutionMpixels;
        private String itNbrSimMobile;
        private String itSimMobileType;
        private String itSmartphoneType;
        private String itWithAutofocus;
        private String itWithMemoryCardSlot;
        private String itWithNormed3gUmts;
        private String itWithOutdoor;
        private String it4gVersion;
        private String itFlashType;
        private String itMemoryRomIntegratedGo;
        private String itNbrCpuCore;
        private String itOsVersion;
        private String itResolutionScreenPixels;
        private String itCpuFrequencyGhz;
        private String itCpuSmartphoneType;
        private String weightKg;
        private String dimensionsLengthCm;
        private String dimensionsHeightCm;
        private String chargeConsumptionWatts;
        private String itResponseFrequencyMaxiHz;
        private String itResponseFrequencyMiniHz;
        private String itWirelessNetworkType;
        private String itConverter;

        private Builder() {}

        public Builder withArticleSku(String articleSku) {
            this.articleSku = articleSku;
            return this;
        }

        public Builder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder withBrandReference(String brandReference) {
            this.brandReference = brandReference;
            return this;
        }

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withEan13(String ean13) {
            this.ean13 = ean13;
            return this;
        }

        public Builder withFullDescription(String fullDescription) {
            this.fullDescription = fullDescription;
            return this;
        }

        public Builder withLongTitle(String longTitle) {
            this.longTitle = longTitle;
            return this;
        }

        public Builder withMediaUrl1(String mediaUrl1) {
            this.mediaUrl1 = mediaUrl1;
            return this;
        }

        public Builder withMediaUrl2(String mediaUrl2) {
            this.mediaUrl2 = mediaUrl2;
            return this;
        }

        public Builder withMediaUrl3(String mediaUrl3) {
            this.mediaUrl3 = mediaUrl3;
            return this;
        }

        public Builder withMediaUrl4(String mediaUrl4) {
            this.mediaUrl4 = mediaUrl4;
            return this;
        }

        public Builder withMediaUrl5(String mediaUrl5) {
            this.mediaUrl5 = mediaUrl5;
            return this;
        }

        public Builder withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder withShortTitle(String shortTitle) {
            this.shortTitle = shortTitle;
            return this;
        }

        public Builder withWarranty(String warranty) {
            this.warranty = warranty;
            return this;
        }

        public Builder withWarrantyScope(String warrantyScope) {
            this.warrantyScope = warrantyScope;
            return this;
        }

        public Builder withItBluetoothVersion(String itBluetoothVersion) {
            this.itBluetoothVersion = itBluetoothVersion;
            return this;
        }

        public Builder withItWithWifiConnection(String itWithWifiConnection) {
            this.itWithWifiConnection = itWithWifiConnection;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withWeightG(String weightG) {
            this.weightG = weightG;
            return this;
        }

        public Builder withItDisplaySizePouces(String itDisplaySizePouces) {
            this.itDisplaySizePouces = itDisplaySizePouces;
            return this;
        }

        public Builder withItIsTactile(String itIsTactile) {
            this.itIsTactile = itIsTactile;
            return this;
        }

        public Builder withItWithApn(String itWithApn) {
            this.itWithApn = itWithApn;
            return this;
        }

        public Builder withItWithNfcConnection(String itWithNfcConnection) {
            this.itWithNfcConnection = itWithNfcConnection;
            return this;
        }

        public Builder withItMemoryRamInstalledGo(String itMemoryRamInstalledGo) {
            this.itMemoryRamInstalledGo = itMemoryRamInstalledGo;
            return this;
        }

        public Builder withItBatteryCapacityMah(String itBatteryCapacityMah) {
            this.itBatteryCapacityMah = itBatteryCapacityMah;
            return this;
        }

        public Builder withItTabletteOs(String itTabletteOs) {
            this.itTabletteOs = itTabletteOs;
            return this;
        }

        public Builder withItWithSenior(String itWithSenior) {
            this.itWithSenior = itWithSenior;
            return this;
        }

        public Builder withItResolutionSecondCameraMpixels(String itResolutionSecondCameraMpixels) {
            this.itResolutionSecondCameraMpixels = itResolutionSecondCameraMpixels;
            return this;
        }

        public Builder withItSensorResolutionMpixels(String itSensorResolutionMpixels) {
            this.itSensorResolutionMpixels = itSensorResolutionMpixels;
            return this;
        }

        public Builder withItNbrSimMobile(String itNbrSimMobile) {
            this.itNbrSimMobile = itNbrSimMobile;
            return this;
        }

        public Builder withItSimMobileType(String itSimMobileType) {
            this.itSimMobileType = itSimMobileType;
            return this;
        }

        public Builder withItSmartphoneType(String itSmartphoneType) {
            this.itSmartphoneType = itSmartphoneType;
            return this;
        }

        public Builder withItWithAutofocus(String itWithAutofocus) {
            this.itWithAutofocus = itWithAutofocus;
            return this;
        }

        public Builder withItWithMemoryCardSlot(String itWithMemoryCardSlot) {
            this.itWithMemoryCardSlot = itWithMemoryCardSlot;
            return this;
        }

        public Builder withItWithNormed3gUmts(String itWithNormed3gUmts) {
            this.itWithNormed3gUmts = itWithNormed3gUmts;
            return this;
        }

        public Builder withItWithOutdoor(String itWithOutdoor) {
            this.itWithOutdoor = itWithOutdoor;
            return this;
        }

        public Builder withIt4gVersion(String it4gVersion) {
            this.it4gVersion = it4gVersion;
            return this;
        }

        public Builder withItFlashType(String itFlashType) {
            this.itFlashType = itFlashType;
            return this;
        }

        public Builder withItMemoryRomIntegratedGo(String itMemoryRomIntegratedGo) {
            this.itMemoryRomIntegratedGo = itMemoryRomIntegratedGo;
            return this;
        }

        public Builder withItNbrCpuCore(String itNbrCpuCore) {
            this.itNbrCpuCore = itNbrCpuCore;
            return this;
        }

        public Builder withItOsVersion(String itOsVersion) {
            this.itOsVersion = itOsVersion;
            return this;
        }

        public Builder withItResolutionScreenPixels(String itResolutionScreenPixels) {
            this.itResolutionScreenPixels = itResolutionScreenPixels;
            return this;
        }

        public Builder withItCpuFrequencyGhz(String itCpuFrequencyGhz) {
            this.itCpuFrequencyGhz = itCpuFrequencyGhz;
            return this;
        }

        public Builder withItCpuSmartphoneType(String itCpuSmartphoneType) {
            this.itCpuSmartphoneType = itCpuSmartphoneType;
            return this;
        }

        public Builder withWeightKg(String weightKg) {
            this.weightKg = weightKg;
            return this;
        }

        public Builder withDimensionsLengthCm(String dimensionsLengthCm) {
            this.dimensionsLengthCm = dimensionsLengthCm;
            return this;
        }

        public Builder withDimensionsHeightCm(String dimensionsHeightCm) {
            this.dimensionsHeightCm = dimensionsHeightCm;
            return this;
        }

        public Builder withChargeConsumptionWatts(String chargeConsumptionWatts) {
            this.chargeConsumptionWatts = chargeConsumptionWatts;
            return this;
        }

        public Builder withItResponseFrequencyMaxiHz(String itResponseFrequencyMaxiHz) {
            this.itResponseFrequencyMaxiHz = itResponseFrequencyMaxiHz;
            return this;
        }

        public Builder withItResponseFrequencyMiniHz(String itResponseFrequencyMiniHz) {
            this.itResponseFrequencyMiniHz = itResponseFrequencyMiniHz;
            return this;
        }

        public Builder withItWirelessNetworkType(String itWirelessNetworkType) {
            this.itWirelessNetworkType = itWirelessNetworkType;
            return this;
        }

        public Builder withItConverter(String itConverter) {
            this.itConverter = itConverter;
            return this;
        }

        public Product build() {
            return new Product(
                    articleSku,
                    brand,
                    brandReference,
                    category,
                    ean13,
                    fullDescription,
                    longTitle,
                    mediaUrl1,
                    mediaUrl2,
                    mediaUrl3,
                    mediaUrl4,
                    mediaUrl5,
                    shortDescription,
                    shortTitle,
                    warranty,
                    warrantyScope,
                    itBluetoothVersion,
                    itWithWifiConnection,
                    color,
                    weightG,
                    itDisplaySizePouces,
                    itIsTactile,
                    itWithApn,
                    itWithNfcConnection,
                    itMemoryRamInstalledGo,
                    itBatteryCapacityMah,
                    itTabletteOs,
                    itWithSenior,
                    itResolutionSecondCameraMpixels,
                    itSensorResolutionMpixels,
                    itNbrSimMobile,
                    itSimMobileType,
                    itSmartphoneType,
                    itWithAutofocus,
                    itWithMemoryCardSlot,
                    itWithNormed3gUmts,
                    itWithOutdoor,
                    it4gVersion,
                    itFlashType,
                    itMemoryRomIntegratedGo,
                    itNbrCpuCore,
                    itOsVersion,
                    itResolutionScreenPixels,
                    itCpuFrequencyGhz,
                    itCpuSmartphoneType,
                    weightKg,
                    dimensionsLengthCm,
                    dimensionsHeightCm,
                    chargeConsumptionWatts,
                    itResponseFrequencyMaxiHz,
                    itResponseFrequencyMiniHz,
                    itWirelessNetworkType,
                    itConverter
            );
        }
    }
}

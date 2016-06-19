package com.android.builderpattern.builder;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-19
 */
public class Computer {
    private String CPU;
    private String GPU;
    private String memoryType;
    private int memorySize;
    private String storageType;
    private int storageSize;
    private String screenType;
    private float screenSize;
    private String OSType;

    public static class Builder {
        // Optional parameters - initialize with default values
        private String CPU = "inter-i3";
        private String GPU = "GTX-960";
        private String memoryType = "ddr3 1666MHz";
        private int memorySize = 8;//8GB
        private String storageType = "hdd";
        private int storageSize = 1024;//1TB
        private String screenType = "IPS";
        private float screenSize = 23.8f;
        private String OSType = "Windows 10";

        public Builder() {
        }

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }
        public Builder setMemoryType(String memoryType) {
            this.memoryType = memoryType;
            return this;
        }

        public Builder setMemorySize(int memorySize) {
            this.memorySize = memorySize;
            return this;
        }
        public Builder setStorageType(String storageType) {
            this.storageType = storageType;
            return this;
        }

        public Builder setStorageSize(int storageSize) {
            this.storageSize = storageSize;
            return this;
        }

        public Builder setScreenType(String screenType) {
            this.screenType = screenType;
            return this;
        }
        public Builder setScreenSize(float screenSize) {
            this.screenSize = screenSize;
            return this;
        }

        public Builder setOSType(String OSType) {
            this.OSType = OSType;
            return this;
        }


        public Computer create() {
            return new Computer(this);
        }

    }

    private Computer(Builder builder) {
        CPU = builder.CPU;
        GPU = builder.GPU;
        memoryType = builder.memoryType;
        memorySize = builder.memorySize;
        storageType = builder.storageType;
        storageSize = builder.storageSize;
        screenType = builder.screenType;
        screenSize = builder.screenSize;
        OSType = builder.OSType;
    }
}
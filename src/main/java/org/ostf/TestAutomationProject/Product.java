package org.ostf.TestAutomationProject;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Product {
    private String id;
    private String name;
    private ProductData data;
;
    public Product() {}

    public Product(String id, String name, ProductData data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductData getData() {
        return data;
    }

    public void setData(ProductData data) {
        this.data = data;
    }


    public static class ProductData {
        @JsonProperty("capacity GB")
        private String capacityGB;

        // 2 different colors for two different devices
        @JsonProperty("Color")
        private String Color;
        private String color;

        // Again a problem with case sensitivity,
        @JsonProperty("Capacity")
        private String Capacity;
        @JsonProperty("capacity")
        private String capacity;
        @JsonProperty("Price")
        private String Price;

        private Double price;
        private String generation;
        private Integer year;
        @JsonProperty("CPU model")
        private String cpuModel;
        @JsonProperty("Hard disk size")
        private String hardDiskSize;
        @JsonProperty("Strap Colour")
        private String strapColour;
        @JsonProperty("Case Size")
        private String caseSize;
        @JsonProperty("Description")
        private String description;
        @JsonProperty("Screen size")
        private Double screenSize;
        @JsonProperty("Generation")
        private String Generation;


        public ProductData() {}

        // TODO This model needs refactoring somehow, perhaps with dynamic JSon parsing or some such
        public ProductData(Map<String, Object> dataMap) {
            this.Color = (String) dataMap.get("Color");
            this.color = (String) dataMap.get("color");

            this.Price = (String) dataMap.get("Price");

            this.Generation = (String) dataMap.get("Generation");

            this.capacity = (String) dataMap.get("capacity");
            this.Capacity = (String) dataMap.get("Capacity");
            this.capacityGB = (String) dataMap.get("capacity GB");

            //this.price = dataMap.get("price") != null ? Float.parseFloat(dataMap.get("price").toString()) : null;
            this.price = dataMap.get("price") != null ? Double.parseDouble(dataMap.get("price").toString()) : null;
            this.generation = (String) dataMap.get("generation");
            this.year = dataMap.get("year") != null ? (Integer) dataMap.get("year") : null;
            this.cpuModel = (String) dataMap.get("CPU model");
            this.hardDiskSize = (String) dataMap.get("Hard disk size");
            this.strapColour = (String) dataMap.get("Strap Colour");
            this.caseSize = (String) dataMap.get("Case Size");
            this.description = (String) dataMap.get("Description");
            this.screenSize = dataMap.get("Screen size") != null ? Double.parseDouble(dataMap.get("Screen size").toString()) : null;
        }

        public String getCapacityGB() {return capacityGB;}

        public void setCapacityGB() { this.capacityGB = capacityGB;}

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getCapacity() {
            return capacity;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getGeneration() {
            return generation;
        }

        public void setGeneration(String generation) {
            this.generation = generation;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public String getCpuModel() {
            return cpuModel;
        }

        public void setCpuModel(String cpuModel) {
            this.cpuModel = cpuModel;
        }

        public String getHardDiskSize() {
            return hardDiskSize;
        }

        public void setHardDiskSize(String hardDiskSize) {
            this.hardDiskSize = hardDiskSize;
        }

        public String getStrapColour() {
            return strapColour;
        }

        public void setStrapColour(String strapColour) {
            this.strapColour = strapColour;
        }

        public String getCaseSize() {
            return caseSize;
        }

        public void setCaseSize(String caseSize) {
            this.caseSize = caseSize;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getScreenSize() {
            return screenSize;
        }

        public void setScreenSize(Double screenSize) {
            this.screenSize = screenSize;
        }
    }
}

package org.ostf.TestAutomationProject;
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
        private String capacityGB;
        private String color;
        private String capacity;
        private Double price;
        private String generation;
        private Integer year;
        private String cpuModel;
        private String hardDiskSize;
        private String strapColour;
        private String caseSize;
        private String description;
        private Double screenSize;


        public ProductData() {}

        public ProductData(Map<String, Object> dataMap) {
            this.color = (String) dataMap.get("color");
            this.capacity = (String) dataMap.get("capacity");
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

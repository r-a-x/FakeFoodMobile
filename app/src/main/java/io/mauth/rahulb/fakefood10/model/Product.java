package io.mauth.rahulb.fakefood10.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rahulb on 1/11/17.
 */

public class Product implements Serializable {

    private String name;
    private String companyName;
    private Long companyId;
    private String productType;
    private String weight ;
    private String image;
    private List<String> flavours;
    private List<String> sizes;
    private Double price;
    private FoodType foodType;
    private String description;

    public Product() {
    }

    public Product(String name, String companyName, String productType, String weight, String image, List<String> flavours, List<String> sizes, Double price, FoodType foodType, String description) {
        this.name = name;
        this.companyName = companyName;
        this.productType = productType;
        this.weight = weight;
        this.image = image;
        this.flavours = flavours;
        this.sizes = sizes;
        this.price = price;
        this.foodType = foodType;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public String getData() {
        return name + " \n " + companyName ;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getFlavours() {
        return flavours;
    }

    public void setFlavours(List<String> flavours) {
        this.flavours = flavours;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}

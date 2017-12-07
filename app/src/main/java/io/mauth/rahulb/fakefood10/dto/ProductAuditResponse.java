package io.mauth.rahulb.fakefood10.dto;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

import io.mauth.rahulb.fakefood10.model.FoodType;
import io.mauth.rahulb.fakefood10.model.ListableProduct;
import io.mauth.rahulb.fakefood10.model.ProductAuditRequest;
import io.mauth.rahulb.fakefood10.model.RequestStatus;

/**
 * Created by rahulb on 5/12/17.
 */

public class ProductAuditResponse implements Serializable,ListableProduct {

    private Long id; // It will be stored in the local db, just tells the request made by the user
    private Long dbId;  // In the server DB
    private String androidId;
    private RequestStatus status;
    private String name;
    private Long companyId;
    private String company;
    private String size;
    private String flavour;
    private ProductAuditRequest.PurchasePlaceEnum purchasePlaceEnum;
    private String placeOfPurchase;
    private String lotNumber;
    private FoodType foodType;
    private Date expirationDate;
    private String barCode;
    private String frontCanisterImageName;
    private String backCanisterImageName;
    private String logoImageName;

    @Override
    public Bitmap getImage() {
        return null;
    }

    @Override
    public String getData() {
        return name + " " + barCode + " " + status.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public ProductAuditRequest.PurchasePlaceEnum getPurchasePlaceEnum() {
        return purchasePlaceEnum;
    }

    public void setPurchasePlaceEnum(ProductAuditRequest.PurchasePlaceEnum purchasePlaceEnum) {
        this.purchasePlaceEnum = purchasePlaceEnum;
    }

    public String getPlaceOfPurchase() {
        return placeOfPurchase;
    }

    public void setPlaceOfPurchase(String placeOfPurchase) {
        this.placeOfPurchase = placeOfPurchase;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getFrontCanisterImageName() {
        return frontCanisterImageName;
    }

    public void setFrontCanisterImageName(String frontCanisterImageName) {
        this.frontCanisterImageName = frontCanisterImageName;
    }

    public String getBackCanisterImageName() {
        return backCanisterImageName;
    }

    public void setBackCanisterImageName(String backCanisterImageName) {
        this.backCanisterImageName = backCanisterImageName;
    }

    public String getLogoImageName() {
        return logoImageName;
    }

    public void setLogoImageName(String logoImageName) {
        this.logoImageName = logoImageName;
    }
}

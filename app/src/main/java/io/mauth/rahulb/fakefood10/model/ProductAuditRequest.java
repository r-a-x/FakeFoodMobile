package io.mauth.rahulb.fakefood10.model;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.entity.mime.content.FileBody;
import io.mauth.rahulb.fakefood10.util.Util;

/**
 * Created by rahulb on 1/11/17.
 */

public class ProductAuditRequest implements Serializable,ListableProduct {

    /*
    *
    * Product name:
    * Company
    * Size:  Next Field Size Auto populate
    * Flavor:
    * Name of the place of purchase and location (address or URL): Seller Name or item identification number (if purchased from an online marketplace such as ebay or snapdeal.com):
    * Lot number:
    * Expiration date:
    * The last 5 digits of the bar code:
    * Pictures of the batch number/exp/mfg dates
    * Pictures of the front and back of the canister
    *
    * */

    private Long id; // It will be stored in the local db, just tells the request made by the user
    private Long dbId;  // In the server DB
    private Long androidId;
    private RequestStatus status;
    private String name;
    private Long companyId;
    private String company;
    private String size;
    private String flavour;
    private PurchasePlaceEnum purchasePlaceEnum;
    private String placeOfPurchase;
    private String lotNumber;
    private FoodType foodType;
    private Date expirationDate;
    private String barCode;
    private String frontImageName;
    private String backImageName;
    private String logoImageName;
    private String description;


    public ProductAuditRequest(JSONObject jsonObject) throws JSONException, ParseException {


        this.dbId = jsonObject.getLong("dbId");
        this.status = RequestStatus.valueOf( jsonObject.getString("status") );
        this.name = jsonObject.getString("name");
        this.companyId = jsonObject.getLong("companyId");
        this.company = jsonObject.getString("comany");
        this.size = jsonObject.getString("size");
        this.flavour = jsonObject.getString("flavour");
        this.purchasePlaceEnum = PurchasePlaceEnum.valueOf(jsonObject.getString("purchasePlaceEnum"));
        this.placeOfPurchase = jsonObject.getString("placeOfPurchase");
        this.lotNumber = jsonObject.getString("lotNumber");
        this.foodType = (FoodType) jsonObject.get("foodType");
        this.expirationDate = Util.getDate(jsonObject.getString("expirationDate"));
        this.barCode = jsonObject.getString("barCode");

//        Add the checks for the image
    }


    public static List<ProductAuditRequest> getListFromJSONArray(JSONArray jsonArray) throws JSONException, ParseException {
        List<ProductAuditRequest> result = new ArrayList<>();
        for ( int i=0; i < jsonArray.length();i++){
            result.add( new ProductAuditRequest( jsonArray.getJSONObject(i) ));
        }
            return result;
    }

    public ProductAuditRequest() {
        status = RequestStatus.PENDING;
    }

    public ProductAuditRequest(Long id, Long dbId) {

        this.id = id;
        this.dbId = dbId;
        this.status = RequestStatus.PENDING;

    }

    public Long getAndroidId() {
        return androidId;
    }

    public void setAndroidId(Long androidId) {
        this.androidId = androidId;
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

    @Override
    public Bitmap getImage() {
//        if(frontCanisterImage == null)
            return null;
//        return Util.base64ToBitmap(frontCanisterImage);
    }

    @Override
    public String getData() {
        return name + " " + barCode + " " + status.toString();
    }

    public enum PurchasePlaceEnum {
        ONLINE,
        OFFLINE
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public PurchasePlaceEnum getPurchasePlaceEnum() {
        return purchasePlaceEnum;
    }

    public void setPurchasePlaceEnum(PurchasePlaceEnum purchasePlaceEnum) {
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

    public String getFrontImageName() {
        return frontImageName;
    }

    public void setFrontImageName(String frontImageName) {
        this.frontImageName = frontImageName;
    }

    public String getBackImageName() {
        return backImageName;
    }

    public void setBackImageName(String backImageName) {
        this.backImageName = backImageName;
    }

    public String getLogoImageName() {
        return logoImageName;
    }

    public void setLogoImageName(String logoImageName) {
        this.logoImageName = logoImageName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


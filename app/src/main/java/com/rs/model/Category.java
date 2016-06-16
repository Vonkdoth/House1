package com.rs.model;

import java.util.ArrayList;

/**
 * Created by richasaxena on 12/04/16.
 */
public class Category {
    String catId;
    String catName;
    ArrayList<SubCategory> subCategoryArrayList;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public ArrayList<SubCategory> getSubCategoryArrayList() {
        return subCategoryArrayList;
    }

    public void setSubCategoryArrayList(ArrayList<SubCategory> subCategoryArrayList) {
        this.subCategoryArrayList = subCategoryArrayList;
    }
}

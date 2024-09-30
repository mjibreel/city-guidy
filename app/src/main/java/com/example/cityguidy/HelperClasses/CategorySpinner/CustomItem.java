package com.example.cityguidy.HelperClasses.CategorySpinner;

public class CustomItem {

    private String spinnerText;
    private int spinnerImage;

    public CustomItem(String spinnerText, int spinnerImage) {
        this.spinnerText = spinnerText;
        this.spinnerImage = spinnerImage;
    }

    public String getSpinnerText() {
        return spinnerText;
    }

    public int getSpinnerImage() {
        return spinnerImage;
    }
}

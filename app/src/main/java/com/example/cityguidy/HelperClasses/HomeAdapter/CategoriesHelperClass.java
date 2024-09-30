package com.example.cityguidy.HelperClasses.HomeAdapter;

import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;

public class CategoriesHelperClass {
    int image ;
    String textTittle;

    public CategoriesHelperClass(int image, String textTittle) {
        this.image = image;
        this.textTittle = textTittle;
    }

    public int getImage() {
        return image;
    }

    public String getTextTittle() {
        return textTittle;
    }
}

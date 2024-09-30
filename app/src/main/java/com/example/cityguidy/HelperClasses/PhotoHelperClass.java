package com.example.cityguidy.HelperClasses;

import android.net.Uri;

public class PhotoHelperClass {

    Uri image;

    public PhotoHelperClass(Uri image) {
        this.image = image;
    }

    public Uri getImage() {
        return image;
    }
}

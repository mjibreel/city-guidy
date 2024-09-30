package com.example.cityguidy.HelperClasses;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cityguidy.HelperClasses.AddsList.AddsListAdapter;

public class AddListProfileAdaptor extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}

package com.example.cityguidy.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cityguidy.R;

import java.util.ArrayList;

public class ImageViewAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.download_1, R.drawable.download_1, R.drawable.download_1, R.drawable.download_1};

    public ImageViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (images != null) {
            return images.length;

        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_image_slider, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_ViewPager);
        imageView.setImageResource(images[position]);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}

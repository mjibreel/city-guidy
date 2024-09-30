 package com.example.cityguidy.HelperClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguidy.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {


    private Context mContext;
    private List<PhotoHelperClass> mListPhoto;

    public PhotoAdapter(Context mContext, List<PhotoHelperClass> mListPhoto) {
        this.mContext = mContext;
        this.mListPhoto = mListPhoto;
    }
/* public PhotoAdapter(Context mContext) {
        this.mContext = mContext;

    }*/

//    public void setData(List<Uri> list) {
//        this.mListPhoto = list;
//        notifyDataSetChanged();
//    }


    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.itam_photo, parent, false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
      /*  Uri uri = mListPhoto.get(position);
        if (uri == null) {
            return;
        }
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
            if (bitmap != null) {
                holder.imgPhoto.setImageBitmap(bitmap);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        PhotoHelperClass photoHelperClass = mListPhoto.get(position);

//        holder.imgPhoto.setImageURI(photoHelperClass.getImage());
        Picasso.get().load(mListPhoto.get(position).getImage()).
                into(holder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        if (mListPhoto == null) {
            return 0;

        }else {
            return mListPhoto.size();

        }
//                    return mListPhoto.size();

    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

         ImageView imgPhoto;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);

        }
    }
 }



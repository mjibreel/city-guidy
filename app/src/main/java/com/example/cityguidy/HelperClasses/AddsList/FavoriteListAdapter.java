/*
package com.example.cityguidy.HelperClasses.AddsList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.LocationOuner.FavoriteItem;
import com.example.cityguidy.R;
import com.example.cityguidy.User.AddListProfile;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>{

    private Context mContext;
    private List<FavoriteItem> mFavorite;
    private FavDB favDB;

    public FavoriteListAdapter(Context context, List<FavoriteItem> mFavorite) {
        this.mContext = context;
       this. mFavorite = mFavorite;
    }

    @NonNull
    @Override
    public FavoriteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_list_carde_desing,
                parent, false);

        return new FavoriteListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteListAdapter.ViewHolder holder, final int position) {
        Upload uploadsAdds = mFavorite.get(position);

        holder.aName.setText(uploadsAdds.getName());
//        holder.aDetail.setText(uploadsAdds.getDetail());
        holder.aCity.setText(uploadsAdds.getCity());
        holder.aPrice.setText(uploadsAdds.getPrice());
//        holder.aCategory.setText(uploadsAdds.getCategory());
//        holder.aSection.setText(uploadsAdds.getSection());
//        holder.aPhone.setText(uploadsAdds.getPhone());
//        holder.aImage.setImageResource(uploadsAdds.getImageUri());
        Picasso.get().load(mFavorite.get(position).getImageUri()).placeholder(R.mipmap.ic_launcher).centerCrop().fit().
                into(holder.aImage);
//        mContext.load(uploadsAdds.getImageUri()).fit().centerCrop().setImageURI(aImage);



    }

    @Override
    public int getItemCount() {
        return mFavorite.size();
    }

  */
/*  public void UpdateData(Upload upload){
        mUploads.clear();
        mUploads.addAll((Collection<? extends Upload>) upload);
        notifyDataSetChanged();
    }*//*


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView aImage;
        public TextView aName;
        public TextView aCity;
        public TextView aPrice;
        public TextView aTime;
        public TextView aCategory;
        public TextView aDetail;
        public TextView aPhone;
        public ImageButton aLike;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aImage = itemView.findViewById(R.id.add_list_image);
            aName = itemView.findViewById(R.id.add_list_title);
            aCity = itemView.findViewById(R.id.add_list_location);
//            aCategory = itemView.findViewById(R.id.add_list_Category);
            aPrice = itemView.findViewById(R.id.add_list_price);
            aLike = itemView.findViewById(R.id.add_list_border);
//            aCategory = itemView.findViewById(R.id.add_list_profile_Category_name);
//            aSection = itemView.findViewById(R.id.add_list_profile_section_name);
//            aPhone = itemView.findViewById(R.id.add_list_profile_phone_name);
//            aDetail = itemView.findViewById(R.id.add_list_profile_detail_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Intent intent = new Intent(mContext, AddListProfile.class);
                    intent.putExtra("image", mFavorite.get(position).getImageUri());
                    intent.putExtra("Name", mFavorite.get(position).getName());
                    intent.putExtra("City", mFavorite.get(position).getCity());
                    intent.putExtra("Price", mFavorite.get(position).getPrice());
                    intent.putExtra("Category", mFavorite.get(position).getCategory());
                    intent.putExtra("Phone", mFavorite.get(position).getPhone());
                    intent.putExtra("Detail", mFavorite.get(position).getDetail());


                    mContext.startActivity(intent);
                }
            });

        }


    }
}

}
*/

package com.example.cityguidy.HelperClasses.AddsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguidy.LocationOuner.AddList;
import com.example.cityguidy.R;
import com.example.cityguidy.User.UserDashboard;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryItemListAdapter  {
  /*  private Context mContext;
    private List<Upload> mUploads;
    public CategoryItemListAdapter(Context context,List<Upload> uploads){
        mContext=context; mUploads=uploads;
    }

    @NonNull
    @Override
    public CategoryItemListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_list_carde_desing,
                parent, false);

        return new CategoryItemListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemListHolder holder, int position) {
        Upload uploadsAdds=mUploads.get(position);

        holder.aName.setText(uploadsAdds.getName());
        holder.aDetail.setText(uploadsAdds.getDetail());
        holder.aCity.setText(uploadsAdds.getCity());
        holder.aPrice.setText(uploadsAdds.getPrice());
        holder.aCategory.setText(uploadsAdds.getCategory());
        holder.aSection.setText(uploadsAdds.getSection());
        holder.aPhone.setText(uploadsAdds.getPhone());
        Picasso.get().load(uploadsAdds.getImageUri()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().
                into(holder.aImage);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class CategoryItemListHolder extends RecyclerView.ViewHolder {

        public ImageView aImage;
        public TextView aName;
        public TextView aCity;
        public TextView aPrice;
        public TextView aTime;
        public TextView aCategory;
        public TextView aDetail;
        public TextView aSection;
        public TextView aPhone;
        public ImageButton aLike;
        public CategoryItemListHolder(@NonNull View itemView) {
            super(itemView);
            aImage = itemView.findViewById(R.id.add_list_image);
            aName = itemView.findViewById(R.id.add_list_title);
            aCity = itemView.findViewById(R.id.add_list_location);
            aTime = itemView.findViewById(R.id.add_list_time);
            aPrice = itemView.findViewById(R.id.add_list_price);
            aLike = itemView.findViewById(R.id.add_list_border);
            aCategory = itemView.findViewById(R.id.add_list_profile_Category_name);
            aSection = itemView.findViewById(R.id.add_list_profile_section_name);
            aPhone = itemView.findViewById(R.id.add_list_profile_phone_name);
            aDetail = itemView.findViewById(R.id.add_list_profile_detail_name);
        }


  *//*  @Override
    public int getCount() {
        return AddList.mUploads.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
        convertView=LayoutInflater.from(mContext).inflate(R.layout.add_list_carde_desing,null);
        public ImageView aImage;
        public TextView aName;
        public TextView aCity;
        public TextView aPrice;
        public TextView aTime;
        public TextView aCategory;
        public TextView aDetail;
        public TextView aSection;
        public TextView aPhone;
        public ImageButton aLike;

        aImage = itemView.findViewById(R.id.add_list_image);
        aName = itemView.findViewById(R.id.add_list_title);
        aCity = itemView.findViewById(R.id.add_list_location);
        aTime = itemView.findViewById(R.id.add_list_time);
        aPrice = itemView.findViewById(R.id.add_list_price);
        aLike = itemView.findViewById(R.id.add_list_border);
        aCategory = itemView.findViewById(R.id.add_list_profile_Category_name);
        aSection = itemView.findViewById(R.id.add_list_profile_section_name);
        aPhone = itemView.findViewById(R.id.add_list_profile_phone_name);
        aDetail = itemView.findViewById(R.id.add_list_profile_detail_name);
    }*/
}

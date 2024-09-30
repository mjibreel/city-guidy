/*
package com.example.cityguidy.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguidy.HelperClasses.AddsList.AddNewPostAdapter;
import com.example.cityguidy.R;
import com.example.cityguidy.User.AddListProfile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddNewPostAdapter extends RecyclerView.Adapter<AddNewPostAdapter.AddNewPostHolder>{
    Context mContext;
    List<Upload> mUploads;
    List<Upload> filteredmUploads;
//    ImageButton fvrt_btn;
//    DatabaseReference favRev;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public AddNewPostAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
        filteredmUploads = uploads;
    }

    @NonNull
    @Override
    public AddNewPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_list_carde_desing,
                parent, false);

        return new AddNewPostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddNewPostHolder holder, final int position) {

     */
/*   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();*//*


        Upload uploadsAdds = filteredmUploads.get(position);

        holder.aName.setText(uploadsAdds.getName());
//        holder.aDetail.setText(uploadsAdds.getDetail());
        holder.aCity.setText(uploadsAdds.getCity());
        holder.aPrice.setText(uploadsAdds.getPrice());
//        holder.aTime.setText(uploadsAdds.getTime());
//        holder.fvrt_btn.setImageResource(R.drawable.bookmark_border_icon);
//        holder.fvrt_btn.setImageResource(R.drawable.bookmark_icon_black);
//        holder.aCategory.setText(uploadsAdds.getCategory());
//        holder.aSection.setText(uploadsAdds.getSection());
//        holder.aPhone.setText(uploadsAdds.getPhone());
//        holder.aImage.setImageResource(uploadsAdds.getImageUri());
        Picasso.get().load(mUploads.get(position).getImageUri()).placeholder(R.mipmap.ic_launcher).centerCrop().fit().
                into(holder.aImage);
//        mContext.load(uploadsAdds.getImageUri()).fit().centerCrop().setImageURI(aImage);


    }

    @Override
    public int getItemCount() {
        return filteredmUploads.size();
    }


    public class AddNewPostHolder extends RecyclerView.ViewHolder {

        public ImageView aImage;
        public TextView aName;
        public TextView aCity;
        public TextView aPrice;
        public TextView aTime;
        public TextView aCategory;
        public TextView aDetail;
        public TextView aPhone;
//        public ImageButton fvrt_btn;


        public AddNewPostHolder(@NonNull View itemView) {
            super(itemView);
            aImage = itemView.findViewById(R.id.add_list_image);
            aName = itemView.findViewById(R.id.add_list_title);
            aCity = itemView.findViewById(R.id.add_list_location);
//            aCategory = itemView.findViewById(R.id.add_list_Category);
            aPrice = itemView.findViewById(R.id.add_list_price);
//            fvrt_btn = itemView.findViewById(R.id.add_list_border);
            aTime = itemView.findViewById(R.id.add_list_time);
//            aCategory = itemView.findViewById(R.id.add_list_profile_Category_name);
//            aSection = itemView.findViewById(R.id.add_list_profile_section_name);
//            aPhone = itemView.findViewById(R.id.add_list_profile_phone_name);
//            aDetail = itemView.findViewById(R.id.add_list_profile_detail_name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Intent intent = new Intent(mContext, AddListProfile.class);
                    intent.putExtra("image", mUploads.get(position).getImageUri());
                    intent.putExtra("Name", mUploads.get(position).getName());
                    intent.putExtra("City", mUploads.get(position).getCity());
                    intent.putExtra("Price", mUploads.get(position).getPrice());
                    intent.putExtra("Category", mUploads.get(position).getCategory());
                    intent.putExtra("Phone", mUploads.get(position).getPhone());
                    intent.putExtra("Detail", mUploads.get(position).getDetail());
//                    intent.putExtra("Time", mUploads.get(position).getTime());
//                    intent.putExtra("favourites", mUploads.get(position).getLikeItem());


                    mContext.startActivity(intent);
                }
            });

       */
/*     fvrt_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(fvrt_btn.equals(true)){
                        fvrt_btn = v.findViewById(R.id.add_list_border);

                    }else {
                        aPrice = v.findViewById(R.id.add_list_price);

                    }
                }
            });*//*


        }

    }


    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String key = constraint.toString();
                if (key.isEmpty()) {
                    filteredmUploads = mUploads;

                } else {
                    List<Upload> listFilter = new ArrayList<>();
                    for (Upload row : mUploads) {
                        if (row.getName().toLowerCase().contains(key.toLowerCase()) ||
                                row.getCity().toLowerCase().contains(key.toLowerCase()) ||
                                row.getCategory().toLowerCase().contains(key.toLowerCase()) ||
                                row.getPrice().toLowerCase().contains(key.toLowerCase())) {
                            listFilter.add(row);
                        }
                    }
                    filteredmUploads = listFilter;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredmUploads;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredmUploads = (List<Upload>) results.values;
                notifyDataSetChanged();
            }
        };
    }

  */
/*  public String getCountry(){
        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();

    }*//*



}
*/

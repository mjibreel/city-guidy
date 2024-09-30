package com.example.cityguidy.HelperClasses.AddsList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.R;
import com.example.cityguidy.User.AddListProfile;

import java.util.ArrayList;
import java.util.List;

public class AddsListAdapter extends RecyclerView.Adapter<AddsListAdapter.AddsListHolder> {

    private Context mContext;
    private List<Upload> mUploads;
    private List<Upload> filteredmUploads;
//    ImageButton fvrt_btn;
//    DatabaseReference favRev;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public AddsListAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
        filteredmUploads = new ArrayList<Upload>();
        this.filteredmUploads.addAll(mUploads);
    }

    @NonNull
    @Override
    public AddsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.add_list_carde_desing,
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_list_carde_desing,
                parent, false);

        return new AddsListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddsListHolder holder, final int position) {

        Upload uploadsAdds = mUploads.get(position);

        holder.aName.setText(filteredmUploads.get(position).getaName());
        holder.aCity.setText(filteredmUploads.get(position).getaCity());
        holder.aCategory.setText(filteredmUploads.get(position).getaCategory());

        Glide.with(mContext).load(mUploads.get(position).getUserName()).placeholder(R.mipmap.ic_launcher).centerCrop().
                into(holder.aImage);
        Glide.with(mContext).load(mUploads.get(position).getUserPhoto()).
                into(holder.user_photo);

//        String postKey = mUploads.get(position).getPostKey();
//        String name = mUploads.get(position).getaName();
//        String city = mUploads.get(position).getaCity();
//        String userName = mUploads.get(position).getUserName();
//        String userPhoto = mUploads.get(position).getUserPhoto();
//        String userId = mUploads.get(position).getUserId();
//        String image = mUploads.get(position).getmImageUri();
//        String category = mUploads.get(position).getaCategory();
//        String detail = mUploads.get(position).getaDetail();
//        String phone = mUploads.get(position).getaPhone();
//        String time = mUploads.get(position).getTimestamp();
//
//        Calendar calendar = Calendar.getInstance(Locale.getDefault());
//        calendar.setTimeInMillis(Long.parseLong(time));
//        String date = DateFormat.format("dd/MM/yyyy", calendar).toString();
//        String date = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();

//        holder.aName.setText(name);
//        holder.aCity.setText(city);
//        holder.aCategory.setText(category);
//        holder.aTime.setText(date);
//        Picasso.get().load(image).placeholder(R.mipmap.ic_launcher).fit().
//                into(holder.aImage);
//        Picasso.get().load(userPhoto).into(holder.user_photo);

    }

    @Override
    public int getItemCount() {
        return filteredmUploads.size();
    }

    public class AddsListHolder extends RecyclerView.ViewHolder {

        public ImageView aImage;
        public ImageView user_photo;
        public TextView userName;
        public TextView aName;
        public TextView aCity;
        public TextView aLocation;
        public TextView aTime;
        public TextView aCategory;
        public TextView aDetail;
        public TextView aPhone;
//        public ImageButton fvrt_btn;


        public AddsListHolder(@NonNull View itemView) {
            super(itemView);
            aImage = itemView.findViewById(R.id.add_list_image);
            user_photo = itemView.findViewById(R.id.user_photo);
            aName = itemView.findViewById(R.id.add_list_title);
            aCity = itemView.findViewById(R.id.add_list_location);
            aCategory = itemView.findViewById(R.id.add_list_category);
//            aLocation = itemView.findViewById(R.id.add_post_location);
//            fvrt_btn = itemView.findViewById(R.id.add_list_border);
            aTime = itemView.findViewById(R.id.list_time);
//            aCategory = itemView.findViewById(R.id.add_list_profile_Category_name);
//            aSection = itemView.findViewById(R.id.add_list_profile_section_name);
//            aPhone = itemView.findViewById(R.id.add_list_profile_phone_name);
//            aDetail = itemView.findViewById(R.id.add_list_profile_detail_name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Intent intent = new Intent(mContext, AddListProfile.class);
                    intent.putExtra("mImageUri", filteredmUploads.get(position).getmImageUri());
                    intent.putExtra("aName", filteredmUploads.get(position).getaName());
                    intent.putExtra("aCity", filteredmUploads.get(position).getaCity());
                    intent.putExtra("aCategory", filteredmUploads.get(position).getaCategory());
                    intent.putExtra("aPhone", filteredmUploads.get(position).getaPhone());
                    intent.putExtra("aDetail", filteredmUploads.get(position).getaDetail());
                    intent.putExtra("postKey", filteredmUploads.get(position).getPostKey());
                    intent.putExtra("aLocation", filteredmUploads.get(position).getaLocation());
//                    intent.putExtra("userId", filteredmUploads.get(position).getUserId());
                    intent.putExtra("userPhoto", filteredmUploads.get(position).getUserPhoto());
                    intent.putExtra("userName", filteredmUploads.get(position).getUserName());
//                    intent.putExtra("countPost", filteredmUploads.get(position).getCountPost());
//                    long timestamp = (long) mUploads.get(position).getTimestamp();
//                    intent.putExtra("timestamp", mUploads.get(position).getTimestamp());
//                    intent.putExtra("Time", (dateTime(mUploads.get(position).getTimestamp())));
//                    intent.putExtra("favourites", mUploads.get(position).getLikeItem());


                    mContext.startActivity(intent);
                }
            });

       /*     fvrt_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(fvrt_btn.equals(true)){
                        fvrt_btn = v.findViewById(R.id.add_list_border);

                    }else {
                        aPrice = v.findViewById(R.id.add_list_price);

                    }
                }
            });*/

        }

    }

//public void filterList(ArrayList<Upload> filteredList){
//
//        mUploads=filteredList;
//        notifyDataSetChanged();
//}


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
                        if (row.getaName().toLowerCase().contains(key.toLowerCase()) ||
                                row.getaCity().toLowerCase().contains(key.toLowerCase()) ||
                                row.getaCategory().toLowerCase().contains(key.toLowerCase())
                        ) {
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


/*
    public Filter getFilter(String newText) {

        newText = newText.toLowerCase(Locale.getDefault());
        mUploads.clear();
        if (newText.length() == 0) {
            mUploads.addAll(filteredmUploads);
        } else {
            for (Upload row : mUploads) {
                if (row.getaName().toLowerCase().contains(newText) ||
                        row.getaCity().toLowerCase().contains(newText) ||
                        row.getaCategory().toLowerCase().contains(newText) ||
                        row.getaPrice().toLowerCase().contains(newText)) {
                    mUploads.add(row);
                }
            }
        }
        notifyDataSetChanged();
    }
*/


//    public String timestampToString(long time) {
//
//        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
//        calendar.setTimeInMillis(time);
//        String date = DateFormat.format("yyyy-MM-dd'T' HH:mm:", calendar).toString();
////        String date = DateFormat.format("hh:mm", calendar).toString();
////    String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
//        return date;
//    }
//
//    public String dateTime(String t) {
//        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
//        String time = null;
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T' HH:mm:", Locale.ENGLISH);
//            Date date = simpleDateFormat.parse(t);
//            time = prettyTime.format(date);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return time;
//    }
//
//    public String getCountry() {
//        Locale locale = Locale.getDefault();
//        String country = locale.getCountry();
//        return country.toLowerCase();
//    }

}



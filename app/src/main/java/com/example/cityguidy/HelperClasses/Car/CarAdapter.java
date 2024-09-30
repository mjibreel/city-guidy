package com.example.cityguidy.HelperClasses.Car;

public class CarAdapter {

/*
    private Context mContext;
    private List<Upload> mUploads;

    public CarAdapter(Context context,List<Upload> uploads){
        mContext=context;
        mUploads=uploads;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_house_cards_desing, parent,
                false);
//        CarAdapter.CarViewHolder CarViewHolder = new CarAdapter.CarViewHolder(view);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
//        CarHelperClass carHelperClass = carLocation.get(position);
        Upload uploadsAdds=mUploads.get(position);

//        holder.image.setImageResource(carHelperClass.getImage());
        Picasso.get().load(uploadsAdds.getImageUri()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().
                into(holder.image);
        holder.name.setText(uploadsAdds.getName());
        holder.city.setText(uploadsAdds.getCity());
        holder.price.setText(uploadsAdds.getPrice());


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }


    public static class CarViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, city,price;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.car_house_list_image);
            name = itemView.findViewById(R.id.car_house_list_name);
            city = itemView.findViewById(R.id.car_house_list_city);
            price = itemView.findViewById(R.id.car_house_list_time);
        }
    }*/
}






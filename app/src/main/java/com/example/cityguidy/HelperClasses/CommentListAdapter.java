package com.example.cityguidy.HelperClasses;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cityguidy.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentListHolder> {

    Context mContext;
    List<CommentHelperClass> mData;

    public CommentListAdapter(Context context, List<CommentHelperClass> Data) {
        mContext = context;
        mData = Data;
    }

    @NonNull
    @Override
    public CommentListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card_desing,
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_card_desing,
                parent, false);

        return new CommentListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListHolder holder, final int position) {


//        String commentContact = mData.get(position).getCommentContact();
//        String uid = mData.get(position).getUid();
//        String uName = mData.get(position).getuName();
//        String uImg = mData.get(position).getuImg();
//
//        holder.user_comment.setText(commentContact);
//        holder.userName.setText(uName);

        holder.userName.setText(mData.get(position).getuImg());
        holder.user_comment.setText(mData.get(position).getCommentContact());
        holder.commentRate.setText(mData.get(position).getuName());
//        holder.commentDate.setText(timestampToString((Long) mData.get(position).getTimestamp()));
//
        Glide.with(mContext).load(mData.get(position).getRateText()).placeholder(R.drawable.user_96px).centerCrop().
                into(holder.userImage);
//        mContext.load(uploadsAdds.getImageUri()).fit().centerCrop().setImageURI(aImage);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class CommentListHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView userName, user_comment,commentRate;


        public CommentListHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_img);
            userName = itemView.findViewById(R.id.username);
            user_comment = itemView.findViewById(R.id.comment_text);
            commentRate = itemView.findViewById(R.id.comment_rate);


        }


    }


//    private String timestampToString(long time) {
//
//        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
//        calendar.setTimeInMillis(time);
//        String date = DateFormat.format("hh:mm", calendar).toString();
////    String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
//        return date;
//    }

}

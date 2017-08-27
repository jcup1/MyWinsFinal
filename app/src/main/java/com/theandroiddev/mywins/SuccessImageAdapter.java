package com.theandroiddev.mywins;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by grazyna on 2017-08-23.
 */

public class SuccessImageAdapter extends RecyclerView.Adapter<SuccessImageAdapter.ViewHolder> {

    private List<SuccessImage> successImages;
    private OnSuccessImageClickListener listener;
    private int successImageLayout;
    private Context context;
    private DrawableSelector drawableSelector;

    public SuccessImageAdapter(List<SuccessImage> successImages, OnSuccessImageClickListener listener, int successImageLayout, Context context, DrawableSelector drawableSelector) {
        this.successImages = successImages;
        this.listener = listener;
        this.successImageLayout = successImageLayout;
        this.context = context;
        this.drawableSelector = drawableSelector;
    }

    @Override
    public SuccessImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(successImageLayout, parent, false);
        return new SuccessImageAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SuccessImageAdapter.ViewHolder holder, int position) {
        holder.successImageIv.setImageBitmap(successImages.get(position).getImageDataBitmap());
        holder.bind(successImages.get(position), position, listener);
    }

    @Override
    public int getItemCount() {
        return successImages.size();
    }

    public interface OnSuccessImageClickListener {
        void onSuccessImageClick(SuccessImage successImage, ImageView successImageIv, int position, ConstraintLayout constraintLayout,
                                 CardView cardView);

        void onSuccessImageLongClick(SuccessImage successImage, ImageView successImageIv, int position, ConstraintLayout constraintLayout,
                                     CardView cardView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView successImageIv;
        ConstraintLayout constraintLayout;
        CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            successImageIv = itemView.findViewById(R.id.success_image_iv);
            constraintLayout = itemView.findViewById(R.id.success_image_constraint_layout);
            cardView = itemView.findViewById(R.id.success_image_card_view);

        }

        private void bind(final SuccessImage successImage, final int position, final OnSuccessImageClickListener onClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSuccessImageClick(successImage, successImageIv, position, constraintLayout, cardView);
                    listener.onSuccessImageLongClick(successImage, successImageIv, position, constraintLayout, cardView);
                }
            });
        }
    }
}
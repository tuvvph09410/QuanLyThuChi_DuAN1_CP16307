package com.dung.quanlythuchi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.R;

import java.util.List;

public class LoaiChiRecyclerViewAdapter extends RecyclerView.Adapter<LoaiChiRecyclerViewAdapter.LoaiChiViewHolder> {
    private LayoutInflater mlayoutInflater;
    private List<LoaiChi> loaiChiList;

    public static ItemClickListener itemDeleteClickListener;
    public static ItemClickListener itemUpdateClickListener;
    public static ItemClickListener itemViewClickListener;

    public LoaiChiRecyclerViewAdapter(Context context) {
        mlayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemUpdateClickListener(ItemClickListener itemUpdateClickListener) {
        LoaiChiRecyclerViewAdapter.itemUpdateClickListener = itemUpdateClickListener;
    }

    public void setOnItemDeleteClickListener(ItemClickListener itemDeleteClickListener) {
        LoaiChiRecyclerViewAdapter.itemDeleteClickListener = itemDeleteClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiChiRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.recyclerview_loai_chi_item, parent, false);

        return new LoaiChiViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, int position) {
        if (loaiChiList != null) {
            holder.tvName.setText(loaiChiList.get(position).getNameLC());
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if (loaiChiList == null) {
            return 0;
        }
        return loaiChiList.size();
    }

    public LoaiChi getItem(int position) {
        if (this.loaiChiList == null || position >= this.loaiChiList.size()) {
            return null;
        }
        return this.loaiChiList.get(position);
    }

    public void setLoaiChiList(List<LoaiChi> mloaiChiList) {
        loaiChiList = mloaiChiList;
        notifyDataSetChanged();
    }

    public static class LoaiChiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivEdit, ivView, ivRemove;
        public CardView cardView;
        public int position;

        public LoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tV_nameLC);
            ivEdit = itemView.findViewById(R.id.iV_updateLC);
            ivView = itemView.findViewById(R.id.iV_viewLC);
            ivRemove = itemView.findViewById(R.id.iV_deleteLC);
            cardView = (CardView) itemView;
            ivRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemDeleteClickListener != null) {
                        itemDeleteClickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemUpdateClickListener != null) {
                        itemUpdateClickListener.onItemClick(position);
                    }
                }
            });
            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemViewClickListener != null) {
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}

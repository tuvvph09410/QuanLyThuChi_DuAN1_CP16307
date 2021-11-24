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
import com.dung.quanlythuchi.DTO.LoaiThu;
import com.dung.quanlythuchi.R;

import java.util.List;

public class LoaiThuRecyclerViewAdapter extends RecyclerView.Adapter<LoaiThuRecyclerViewAdapter.LoaiThuViewHolder> {
    private LayoutInflater mlayoutInflaterThu;
    private List<LoaiThu> loaiThuList;

    public static ItemClickListener itemDeleteClickListener;
    public static ItemClickListener itemUpdateClickListener;
    public static ItemClickListener itemViewClickListener;

    public LoaiThuRecyclerViewAdapter(Context context) {
        mlayoutInflaterThu = LayoutInflater.from(context);
    }

    public void setOnItemUpdateClickListener(ItemClickListener itemUpdateClickListener) {
        LoaiThuRecyclerViewAdapter.itemUpdateClickListener = itemUpdateClickListener;
    }

    public void setOnItemDeleteClickListener(ItemClickListener itemDeleteClickListener) {
        LoaiThuRecyclerViewAdapter.itemDeleteClickListener = itemDeleteClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiThuRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public LoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflaterThu.inflate(R.layout.recyclerview_loai_thu_item, parent, false);

        return new LoaiThuViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThuViewHolder holder, int position) {
        if (loaiThuList != null) {
            holder.tvName.setText(loaiThuList.get(position).getNameLT());
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if (loaiThuList == null) {
            return 0;
        }
        return loaiThuList.size();
    }

    public LoaiThu getItem(int position) {
        if (this.loaiThuList == null || position >= this.loaiThuList.size()) {
            return null;
        }
        return this.loaiThuList.get(position);
    }

    public void setLoaiThuList(List<LoaiThu> mloaiThuList) {
        loaiThuList = mloaiThuList;
        notifyDataSetChanged();
    }

    public static class LoaiThuViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivEdit, ivView, ivRemove;
        public CardView cardView;
        public int position;

        public LoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tV_nameLT);
            ivEdit = itemView.findViewById(R.id.iV_updateLT);
            ivView = itemView.findViewById(R.id.iV_viewLT);
            ivRemove = itemView.findViewById(R.id.iV_deleteLT);
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

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

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.R;

import java.util.List;

public class KhoanChiRecyclerViewAdapter extends RecyclerView.Adapter<KhoanChiRecyclerViewAdapter.KhoanChiViewHolder> {
    private LayoutInflater layoutInflater;
    private List<KhoanChi> khoanChiList;

    public static ItemClickListener itemDeleteClickListener;
    public static ItemClickListener itemUpdateClickListener;
    public static ItemClickListener itemViewClickListener;

    public KhoanChiRecyclerViewAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemUpdateClickListener(ItemClickListener itemUpdateClickListener) {
        KhoanChiRecyclerViewAdapter.itemUpdateClickListener = itemUpdateClickListener;
    }

    public void setOnItemDeleteClickListener(ItemClickListener itemDeleteClickListener) {
        KhoanChiRecyclerViewAdapter.itemDeleteClickListener = itemDeleteClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        KhoanChiRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public KhoanChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_khoan_chi_item, parent, false);

        return new KhoanChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiViewHolder holder, int position) {
        if (this.khoanChiList != null) {
            holder.tvNameKC.setText(this.khoanChiList.get(position).getNameKC());
            holder.tvTienKC.setText(this.khoanChiList.get(position).getTienKC().toString()+ " Đồng");
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if (this.khoanChiList == null) {
            return 0;
        }
        return this.khoanChiList.size();
    }

    public KhoanChi getItem(int position) {
        if (this.khoanChiList == null || position >= this.khoanChiList.size()) {
            return null;
        }
        return this.khoanChiList.get(position);
    }

    public void setKhoanChiList(List<KhoanChi> mkhoanChiList) {
        this.khoanChiList = mkhoanChiList;
        notifyDataSetChanged();
    }

    public static class KhoanChiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameKC, tvTienKC;
        public ImageView ivEdit, ivView, ivRemove;
        public CardView cardView;
        public int position;

        public KhoanChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameKC = itemView.findViewById(R.id.ed_NameKC);
            tvTienKC = itemView.findViewById(R.id.tV_tienKC);
            ivEdit = itemView.findViewById(R.id.iV_updateKC);
            ivView = itemView.findViewById(R.id.iV_viewKC);
            ivRemove = itemView.findViewById(R.id.iV_deleteKC);
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

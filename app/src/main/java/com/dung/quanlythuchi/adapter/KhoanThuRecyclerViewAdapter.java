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
import com.dung.quanlythuchi.DTO.KhoanThu;
import com.dung.quanlythuchi.R;

import java.util.List;

public class KhoanThuRecyclerViewAdapter extends RecyclerView.Adapter<KhoanThuRecyclerViewAdapter.KhoanThuViewHolder> {
    private LayoutInflater layoutInflaterThu;
    private List<KhoanThu> khoanThuList;

    public static ItemClickListener itemDeleteClickListener;
    public static ItemClickListener itemUpdateClickListener;
    public static ItemClickListener itemViewClickListener;

    public KhoanThuRecyclerViewAdapter(Context context) {
        this.layoutInflaterThu = LayoutInflater.from(context);
    }

    public void setOnItemUpdateClickListener(ItemClickListener itemUpdateClickListener) {
        KhoanThuRecyclerViewAdapter.itemUpdateClickListener = itemUpdateClickListener;
    }

    public void setOnItemDeleteClickListener(ItemClickListener itemDeleteClickListener) {
        KhoanThuRecyclerViewAdapter.itemDeleteClickListener = itemDeleteClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        KhoanThuRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public KhoanThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflaterThu.inflate(R.layout.recyclerview_khoan_thu_item, parent, false);

        return new KhoanThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanThuViewHolder holder, int position) {
        if (this.khoanThuList != null) {
            holder.tvNameKT.setText(this.khoanThuList.get(position).getNameKT());
            holder.tvTienKT.setText(this.khoanThuList.get(position).getTienKT().toString()+ " Đồng");
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if (this.khoanThuList == null) {
            return 0;
        }
        return this.khoanThuList.size();
    }

    public KhoanThu getItem(int position) {
        if (this.khoanThuList == null || position >= this.khoanThuList.size()) {
            return null;
        }
        return this.khoanThuList.get(position);
    }

    public void setKhoanThuList(List<KhoanThu> mkhoanThuList) {
        this.khoanThuList = mkhoanThuList;
        notifyDataSetChanged();
    }

    public static class KhoanThuViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameKT, tvTienKT;
        public ImageView ivEdit, ivView, ivRemove;
        public CardView cardView;
        public int position;

        public KhoanThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameKT = itemView.findViewById(R.id.ed_NameKT);
            tvTienKT = itemView.findViewById(R.id.tV_tienKT);
            ivEdit = itemView.findViewById(R.id.iV_updateKT);
            ivView = itemView.findViewById(R.id.iV_viewKT);
            ivRemove = itemView.findViewById(R.id.iV_deleteKT);
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

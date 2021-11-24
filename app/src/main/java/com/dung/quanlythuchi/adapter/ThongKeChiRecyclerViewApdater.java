package com.dung.quanlythuchi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ThongKeChiRecyclerViewApdater extends RecyclerView.Adapter<ThongKeChiRecyclerViewApdater.ThongKeChiViewHolder> {
    private LayoutInflater inflater;
    private List<KhoanChi> khoanChiList;

    private static ItemClickListener itemClickListener;

    public ThongKeChiRecyclerViewApdater(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(ItemClickListener onItemClickListener) {
        ThongKeChiRecyclerViewApdater.itemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ThongKeChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.recyclerview_thongke_chi, parent, false);
        return new ThongKeChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeChiViewHolder holder, int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        if (this.khoanChiList != null) {
            holder.tvNameChi.setText(this.khoanChiList.get(position).getNameKC());
            holder.tvPriceChi.setText(String.valueOf(this.khoanChiList.get(position).getTienKC()));
            holder.tvDateChi.setText(simpleDateFormat.format(this.khoanChiList.get(position).getDateKC()));
            holder.position = position;
        }
    }

    public KhoanChi getItem(int position) {
        if (this.khoanChiList == null || position >= this.khoanChiList.size()) {
            return null;
        }
        return this.khoanChiList.get(position);
    }

    @Override
    public int getItemCount() {
        if (this.khoanChiList == null) {
            return 0;
        }
        return this.khoanChiList.size();

    }

    public void setList(List<KhoanChi> khoanChiList) {
        this.khoanChiList = khoanChiList;
        notifyDataSetChanged();
    }

    public static class ThongKeChiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameChi, tvPriceChi, tvDateChi;
        private int position;
        private CardView cardView;

        public ThongKeChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameChi = itemView.findViewById(R.id.tv_NameTKChi);
            tvPriceChi = itemView.findViewById(R.id.tv_PriceTKChi);
            tvDateChi = itemView.findViewById(R.id.tv_DateTKChi);
            cardView = (CardView) itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if (itemClickListener !=null){
                    itemClickListener.onItemClick(position);
                }
                }
            });

        }
    }
}

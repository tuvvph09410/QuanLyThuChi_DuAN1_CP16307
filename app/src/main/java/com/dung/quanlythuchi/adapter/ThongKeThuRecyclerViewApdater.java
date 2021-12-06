package com.dung.quanlythuchi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dung.quanlythuchi.DTO.KhoanThu;
import com.dung.quanlythuchi.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ThongKeThuRecyclerViewApdater extends RecyclerView.Adapter<ThongKeThuRecyclerViewApdater.ThongKeThuViewHolder> {
    private LayoutInflater inflater;
    private List<KhoanThu> khoanThuList;

    public static ItemClickListener itemClickListener;

    public ThongKeThuRecyclerViewApdater(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(ItemClickListener onItemClickListener) {
        ThongKeThuRecyclerViewApdater.itemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ThongKeThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.recyclerview_thongke_thu, parent, false);
        return new ThongKeThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeThuViewHolder holder, int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        if (this.khoanThuList != null) {
            holder.tvNameThu.setText(this.khoanThuList.get(position).getNameKT());
            DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
            holder.tvPriceThu.setText(decimalFormat.format(this.khoanThuList.get(position).getTienKT()));
            holder.tvDateThu.setText(simpleDateFormat.format(this.khoanThuList.get(position).getDateKT()));
            holder.position = position;
        }

    }

    public KhoanThu getItem(int position) {
        if (this.khoanThuList == null || position >= this.khoanThuList.size()) {
            return null;
        }
        return this.khoanThuList.get(position);
    }

    @Override
    public int getItemCount() {
        if (this.khoanThuList == null) {
            return 0;
        }
        return this.khoanThuList.size();
    }

    public void setList(List<KhoanThu> khoanThuList) {
        this.khoanThuList = khoanThuList;
        notifyDataSetChanged();
    }

    public static class ThongKeThuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameThu, tvPriceThu, tvDateThu;
        private CardView cardView;
        private int position;

        public ThongKeThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameThu = itemView.findViewById(R.id.tv_NameTKTHU);
            tvPriceThu = itemView.findViewById(R.id.tv_PriceTKThu);
            tvDateThu = itemView.findViewById(R.id.tv_DateTKThu);
            cardView = (CardView) itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(position);
                    }
                }
            });

        }
    }
}

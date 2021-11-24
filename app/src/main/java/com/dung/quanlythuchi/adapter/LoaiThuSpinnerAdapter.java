package com.dung.quanlythuchi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.DTO.LoaiThu;
import com.dung.quanlythuchi.R;

import java.util.List;

public class LoaiThuSpinnerAdapter extends BaseAdapter {
    private List<LoaiThu> LoaiThuList;
    private LayoutInflater layoutInflaterThu;

    public LoaiThuSpinnerAdapter(Context context) {
        layoutInflaterThu = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (this.LoaiThuList == null) {
            return 0;
        }
        return this.LoaiThuList.size();
    }

    @Override
    public Object getItem(int position) {
        if (this.LoaiThuList == null) {
            return null;
        }
        return this.LoaiThuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setLoaiThuList(List<LoaiThu> LoaiThuList) {
        this.LoaiThuList = LoaiThuList;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LoaiThuSpinnerViewHolder holder;
        if (view == null) {
            view = layoutInflaterThu.inflate(R.layout.spinner_khoanthu_item, null, false);
            holder = new LoaiThuSpinnerViewHolder(view);
            view.setTag(holder);

        } else {
            holder = (LoaiThuSpinnerViewHolder) view.getTag();
        }
        holder.tv_spinnerIDLT.setText(LoaiThuList.get(position).getNameLT());
        return view;
    }

    public static class LoaiThuSpinnerViewHolder {
        public TextView tv_spinnerIDLT;

        public LoaiThuSpinnerViewHolder(View view) {
            tv_spinnerIDLT = view.findViewById(R.id.tv_spinerLT);
        }
    }
}

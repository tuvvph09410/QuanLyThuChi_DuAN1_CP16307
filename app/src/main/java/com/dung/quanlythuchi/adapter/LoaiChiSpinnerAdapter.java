package com.dung.quanlythuchi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.R;

import java.util.List;

public class LoaiChiSpinnerAdapter extends BaseAdapter {
    private List<LoaiChi> LoaiChiList;
    private LayoutInflater layoutInflater;

    public LoaiChiSpinnerAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);

    }

    public void setLoaiChiList(List<LoaiChi> loaiChiList) {
        LoaiChiList = loaiChiList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (this.LoaiChiList == null) {
            return 0;
        }
        return this.LoaiChiList.size();
    }

    @Override
    public Object getItem(int position) {
        if (this.LoaiChiList == null) {
            return null;
        }
        return this.LoaiChiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        LoaiChi loaiChi=this.LoaiChiList.get(position);
        return loaiChi.getIdLC();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LoaiChiSpinnerViewHolder holder;
        LoaiChi loaiChi = this.LoaiChiList.get(position);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.spinner_khoanchi_item, null, false);
            holder = new LoaiChiSpinnerViewHolder(view);
            view.setTag(holder);

        } else {
            holder = (LoaiChiSpinnerViewHolder) view.getTag();
        }

        holder.tv_spinnerIDLC.setText(loaiChi.getNameLC());
        return view;
    }

    public static class LoaiChiSpinnerViewHolder {
        public TextView tv_spinnerIDLC;

        public LoaiChiSpinnerViewHolder(View view) {
            tv_spinnerIDLC = view.findViewById(R.id.tv_spinerLC);
        }
    }
}

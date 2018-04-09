package com.xuhao.android.oksocket.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xuhao.android.oksocket.R;
import com.xuhao.android.oksocket.data.LogBean;
import com.xuhao.android.oksocket.data.MessageTypeBean;

import java.util.ArrayList;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ItemHolder> {

    private List<MessageTypeBean> mDataList = new ArrayList<>();

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.log_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        MessageTypeBean bean = mDataList.get(position);

        holder.mLog.setVisibility(View.GONE);
        holder.mImg.setVisibility(View.GONE);
        if (bean.type == 0) {
            holder.mLog.setVisibility(View.VISIBLE);
            holder.mTime.setText(bean.mTime);
            holder.mLog.setText(bean.content);
        } else {
            holder.mImg.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView.getContext()).load(bean.imgData).into(holder.mImg);
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager cmb = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                MessageTypeBean log = mDataList.get(holder.getAdapterPosition());
                String msg = log.mTime + " " + log.content;
                cmb.setPrimaryClip(ClipData.newPlainText(null, msg));
                Toast.makeText(v.getContext(), "已复制到剪贴板", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView mTime;
        public TextView mLog;
        public ImageView mImg;

        public ItemHolder(View itemView) {
            super(itemView);
            mTime = (TextView) itemView.findViewById(R.id.time);
            mLog = (TextView) itemView.findViewById(R.id.logtext);
            mImg = (ImageView) itemView.findViewById(R.id.logImg);
        }
    }

    public List<MessageTypeBean> getDataList() {
        return mDataList;
    }
}
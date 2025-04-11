package com.example.aplikasiku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    private List<String> memoList;

    public MemoAdapter(List<String> memoList) {
        this.memoList = memoList;
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Pakai layout bawaan Android, bisa diganti custom nanti
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_memo, parent, false);
        return new MemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        String memo = memoList.get(position);
        holder.memoText.setText(memo);
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    static class MemoViewHolder extends RecyclerView.ViewHolder {
        TextView memoText;

        public MemoViewHolder(@NonNull View itemView) {
            super(itemView);
            memoText = itemView.findViewById(R.id.tvMemoItem);

        }
    }
}

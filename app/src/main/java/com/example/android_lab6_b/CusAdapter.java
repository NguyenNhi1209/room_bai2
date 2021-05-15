package com.example.android_lab6_b;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CusAdapter extends RecyclerView.Adapter<CusViewHolder> {
    private final List<Customer> list;
    Context context;
    LayoutInflater mInflater;
    private AppDatabase database;
    Customer all;

    public CusAdapter(List<Customer> list, Context context) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.item_cus, parent,false);
        CusViewHolder viewHolder = new CusViewHolder(v, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CusViewHolder holder, int position) {
        Customer c = list.get(position);
        holder.diachi.setText(c.id + ". " + c.diaChi);
        database = AppDatabase.getInstance(context);
        int id = c.id;
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer c = list.get(holder.getAdapterPosition());
                database.customerDao().delete(c);

                list.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), list.size());
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                all = list.get(holder.getAdapterPosition());
//                Toast.makeText(context, all.diaChi, Toast.LENGTH_SHORT).show();

            }
        });
    }
    public Customer getPlace(){
        return all;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


}

class CusViewHolder extends RecyclerView.ViewHolder{
    public final TextView diachi;
    ImageButton remove;
    ImageButton update;
    final CusAdapter cusAdapter;

    public CusViewHolder(@NonNull View itemView, CusAdapter cusAdapter) {
        super(itemView);
        diachi = itemView.findViewById(R.id.diachi);
        remove = itemView.findViewById(R.id.remove);
        update = itemView.findViewById(R.id.update);
        this.cusAdapter = cusAdapter;
    }
}

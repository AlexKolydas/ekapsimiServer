package com.example.alex.e_kapsimiserver.ViewHolder;


import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alex.e_kapsimiserver.Model.Order;
import com.example.alex.e_kapsimiserver.R;

import java.util.List;

class MyViewHolder extends RecyclerView.ViewHolder{

    public TextView name,quantity,price,discount;

    public MyViewHolder(View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.product_name);
        quantity=(TextView)itemView.findViewById(R.id.product_quantity);
        price=(TextView)itemView.findViewById(R.id.product_price);


    }
}

public class OrderDetailsAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Order> myOrders;

    public OrderDetailsAdapter(List<Order> myOrders) {
        this.myOrders = myOrders;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Order order=myOrders.get(position);
        holder.name.setText(String.format("Όνομα : %s",order.getProductName()));
        holder.quantity.setText(String.format("Ποσότητα : %s",order.getQuantity()));
        holder.price.setText(String.format("Τιμή : %s",order.getPrice()));


    }

    @Override
    public int getItemCount() {
        return myOrders.size();
    }
}

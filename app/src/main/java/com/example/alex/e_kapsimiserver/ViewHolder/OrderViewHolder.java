package com.example.alex.e_kapsimiserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alex.e_kapsimiserver.Interface.ItemClickListener;
import com.example.alex.e_kapsimiserver.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {//implements View.OnClickListener,View.OnCreateContextMenuListener {

    public TextView txtOrderId;
    public TextView txtOrderStatus;
    public TextView txtOrderPhone;
    public TextView txtOrderSurname;

    public Button btnDelete,btnDetails;

//    private ItemClickListener mItemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderId=(TextView)itemView.findViewById(R.id.order_id);
        txtOrderPhone=(TextView)itemView.findViewById(R.id.order_phone);
        txtOrderStatus=(TextView)itemView.findViewById(R.id.order_status);
        txtOrderSurname=(TextView)itemView.findViewById(R.id.order_surname);

        btnDelete=(Button)itemView.findViewById(R.id.btnDelete);
        btnDetails=(Button)itemView.findViewById(R.id.btnDetails);

//        itemView.setOnClickListener(this);
//        itemView.setOnCreateContextMenuListener(this);
    }

//    public void setItemClickListener(ItemClickListener itemClickListener) {
//        mItemClickListener = itemClickListener;
//    }
//
//    @Override
//    public void onClick(View v) {
//        mItemClickListener.onClick(v,getAdapterPosition(),false);
//    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        menu.setHeaderTitle("Select Action");
//
//        menu.add(0,0,getAdapterPosition(),"Update");
//        menu.add(0,1,getAdapterPosition(),"Delete");
//
//    }
}

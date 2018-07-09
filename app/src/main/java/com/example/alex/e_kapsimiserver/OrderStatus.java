package com.example.alex.e_kapsimiserver;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alex.e_kapsimiserver.Common.Common;
import com.example.alex.e_kapsimiserver.Interface.ItemClickListener;
import com.example.alex.e_kapsimiserver.Model.MyResponse;
import com.example.alex.e_kapsimiserver.Model.Notification;
import com.example.alex.e_kapsimiserver.Model.Sender;
import com.example.alex.e_kapsimiserver.Model.Token;
import com.example.alex.e_kapsimiserver.R;
import com.example.alex.e_kapsimiserver.Remote.APIService;
import com.example.alex.e_kapsimiserver.ViewHolder.OrderDetails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.alex.e_kapsimiserver.Model.Request;
import com.example.alex.e_kapsimiserver.ViewHolder.OrderViewHolder;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderStatus extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;

    FirebaseDatabase db;
    DatabaseReference requests;

    MaterialSpinner spinner;

    APIService mAPIService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        //Firebase
        db=FirebaseDatabase.getInstance();
        requests=db.getReference("Requests");
        //Init service
        mAPIService=Common.getFCMService();
        //init
        recyclerView=(RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        
        //load all orders
        loadOrders();
    }

    private void loadOrders() {

        adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requests
        ) {

            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, final Request model, final int position) {

                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txtOrderSurname.setText(model.getName());
                viewHolder.txtOrderPhone.setText(model.getPhone());

                //New event button

                viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteOrder(adapter.getRef(position).getKey());

                    }
                });

                viewHolder.btnDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent orderDetail=new Intent(OrderStatus.this, OrderDetails.class);
                        Common.currentRequest =model; //<------------------------------- PROSOXI
                        orderDetail.putExtra("OrderId",adapter.getRef(position).getKey());
                        startActivity(orderDetail);
                    }
                });

            }
        };

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        if(item.getTitle().equals(Common.UPDATE))
//        {
//            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(),adapter.getItem(item.getOrder()));
//        }
//        else if(item.getTitle().equals(Common.DELETE))
//        {
//            deleteOrder(adapter.getRef(item.getOrder()).getKey());
//        }
//        return super.onContextItemSelected(item);
//    }

    private void deleteOrder(String key) {
        requests.child(key).removeValue();
        adapter.notifyDataSetChanged(); //add to update item size

    }

    private void showUpdateDialog(String key, final Request item) {
        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(OrderStatus.this);
        alertDialog.setTitle("Ενημέρωση Παραγγελίας");
        alertDialog.setMessage("Ενημέρωσε Κατάσταση Παραγγελίας");

        LayoutInflater inflater=this.getLayoutInflater();
        final View view=inflater.inflate(R.layout.update_order_layout,null);

        spinner=(MaterialSpinner)findViewById(R.id.statusSpinner);
        spinner.setItems("Placed","On My Way","Shipped");

        alertDialog.setView(view);

        final String localKey=key;
        alertDialog.setPositiveButton("ΝΑΙ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                item.setStatus(String.valueOf(spinner.getSelectedIndex()));


                requests.child(localKey).setValue(item);
                sendOrderStatusToUser(localKey,item);

            }
        });
        alertDialog.setNegativeButton("ΟΧΙ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void sendOrderStatusToUser(final String key,final Request item) {
        DatabaseReference tokens= db.getReference("Tokens");
        tokens.orderByKey().equalTo(item.getPhone()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot: dataSnapshot.getChildren())
                {
                    Token token = postSnapShot.getValue(Token.class);
                    //Make raw payload
                    Notification notification=new Notification("e-ΚΨΜ","Η παραγγελία "+key+" ενημερώθηκε");
                    Sender content =new Sender(token.getToken(),notification);

                    mAPIService.sendNotification(content).enqueue(new Callback<MyResponse>() {
                        @Override
                        public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                            if(response.body().success==1)
                            {
                                Toast.makeText(OrderStatus.this,"Η παραγγελία ενημερώθηκε",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(OrderStatus.this,"Η παραγγελία ενημερώθηκε αλλά δεν στάλθηκε ειδοποίηση",Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<MyResponse> call, Throwable t) {
                            Log.e("Error",t.getMessage());

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}

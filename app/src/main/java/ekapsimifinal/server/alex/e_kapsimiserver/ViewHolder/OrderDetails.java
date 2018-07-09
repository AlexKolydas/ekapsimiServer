package ekapsimifinal.server.alex.e_kapsimiserver.ViewHolder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import ekapsimifinal.server.alex.e_kapsimiserver.Common.Common;
import ekapsimifinal.server.alex.e_kapsimiserver.R;

public class OrderDetails extends AppCompatActivity {

    TextView orderid,orderName,orderPhone,orderTotal,orderSurname,orderComment;
    String order_id_value="";
    RecyclerView lstfoods;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        orderid=(TextView)findViewById(R.id.order_id);
        orderPhone=(TextView)findViewById(R.id.order_phone);
        orderTotal=(TextView)findViewById(R.id.order_total);
        orderSurname=(TextView)findViewById(R.id.order_surname); //einai ta sxolia tis paraggelias
        orderName=(TextView)findViewById(R.id.order_name);

        lstfoods=(RecyclerView)findViewById(R.id.lstFoods);
        lstfoods.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        lstfoods.setLayoutManager(mLayoutManager);


        if(getIntent()!=null)
        {
            order_id_value=getIntent().getStringExtra("OrderId");
        }

        orderid.setText(order_id_value);
        orderPhone.setText(Common.currentRequest.getPhone());
        orderTotal.setText(Common.currentRequest.getTotal());
        orderSurname.setText(Common.currentRequest.getSurname());
        orderName.setText(Common.currentRequest.getName());

        OrderDetailsAdapter adapter=new OrderDetailsAdapter(Common.currentRequest.getFoods());
        adapter.notifyDataSetChanged();
        lstfoods.setAdapter(adapter);

    }
}

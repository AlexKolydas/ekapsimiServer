package ekapsimifinal.server.alex.e_kapsimiserver.Remote;

import ekapsimifinal.server.alex.e_kapsimiserver.Model.MyResponse;
import ekapsimifinal.server.alex.e_kapsimiserver.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    //to key einai apo to firebase->cloud messaging->server key
                    "Content-Type:application/json",
                    "Authorization:key=AAAAx_iRlkw:APA91bEozQW1yeZ8n6XIDrC8zn9jHCelHGF2s6wxeD6KiPTMHNcg-BMBEaCycOyw2izYkdCngTEWFIIk8pmcI3MP8aWtgMOGRl-zsvf1dK-4O16pRIwOwswRDgO9RKmqjBZdY2COI3RWv4CroiiktnMnkLZ05RYJBw"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}

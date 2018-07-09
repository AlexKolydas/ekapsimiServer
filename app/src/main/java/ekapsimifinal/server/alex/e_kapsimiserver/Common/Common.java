package ekapsimifinal.server.alex.e_kapsimiserver.Common;

import ekapsimifinal.server.alex.e_kapsimiserver.Model.Request;
import ekapsimifinal.server.alex.e_kapsimiserver.Model.User;
import ekapsimifinal.server.alex.e_kapsimiserver.Remote.APIService;
import ekapsimifinal.server.alex.e_kapsimiserver.Remote.FCMRetrofitClient;

public class Common {

    //Gia na krataei se olo to app ta stoixeia tou logkarismenou xristi (San to $Session tis php)

    public static User current_user;
    public static Request currentRequest;


    public static final String UPDATE="Update";
    public static final String DELETE="Delete";
    public static int PICK_IMAGE_REQUEST=71;
    private static final String BASE_URL="https://fcm.googleapis.com/";


    public static APIService getFCMService()
    {
        return FCMRetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static String convertCodeToStatus(String code){

        if(code.equals("0"))
            return "Placed";
        else if(code.equals("1"))
            return "On My Way";
        else
            return "Shipped";
    }


}

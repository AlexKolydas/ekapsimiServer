package ekapsimifinal.server.alex.e_kapsimiserver.Service;

import ekapsimifinal.server.alex.e_kapsimiserver.Common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import ekapsimifinal.server.alex.e_kapsimiserver.Model.Token;

public class MyFirebaseIdService extends FirebaseInstanceIdService {
    //gia na paro to token apo firebase to opoio tto dinei tin proti fora otan trexo to app
    //opote prepeii na kano uninstall to app apo to emulator kai ksana install
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken=FirebaseInstanceId.getInstance().getToken();
        updateToServer(refreshedToken);
    }

    private void updateToServer(String refreshedToken) {

        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference tokens=db.getReference("Tokens");
        Token data=new Token(refreshedToken,true);//true because it's server false is on client
        tokens.child(Common.current_user.getPhone()).setValue(data);
    }
}

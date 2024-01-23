package phinhph25802.example.appfastfood_admin_main.database;

import com.google.firebase.database.FirebaseDatabase;

import hainb21127.poly.appfastfood_admin.url.URL;

;

public class FirebaseDB {
    public static FirebaseDatabase firebaseDatabase;
    public static FirebaseDatabase getDatabaseInstance(){
        if(firebaseDatabase == null)
            firebaseDatabase = FirebaseDatabase.getInstance(URL.URL_API);
        return firebaseDatabase;
    }
}

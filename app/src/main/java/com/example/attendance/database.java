package com.example.attendance;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLTransientException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class database {
    Context mainActivity;
    public database(Context mainActivity){
        this.mainActivity = mainActivity;
    }
    private static final String TAG = "hello";
    SimpleDateFormat date1 = new SimpleDateFormat("dd:MM:yyyy");
    SimpleDateFormat date2 = new SimpleDateFormat("HH:mm:ss");
    String format = date1.format(new Date());
    String format1 = date2.format(new Date());
    FirebaseFirestore firestore;
    Map<String,Object> user = new HashMap<>();
    int t;
    boolean t1;
    public void fun(boolean h){
        t1 = h;
    }
    public int arrival(String X){
        firestore = FirebaseFirestore.getInstance();
        user.put("Arrival time",format1);
        user.put("Departure time",format1);
        try{
        firestore.collection(format).document(X).set(user);
        return 1;
        }catch (Exception e){
            return 0;
        }
    }
    public int departure(String X){
        firestore = FirebaseFirestore.getInstance();
        DocumentReference Ref = firestore.collection(format).document(X);
        try{
        Ref.update("Departure time",format1);
        return 1;
        }catch (Exception e) {
            return 0;
        }
    }
    public void read(String X){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(X)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int val = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String k = document.getId();
                                String ob = ""+document.getData();
                                String at = ob.substring(14,22);
                                String dt = ob.substring(39,47);
                                Toast.makeText(mainActivity,""+k +" "+" "+at+" "+dt,Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mainActivity,"error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}

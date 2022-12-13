package com.example.attendance;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class rltdatabase {
    public void add_node(String user){
        SimpleDateFormat date1 = new SimpleDateFormat("dd:MM:yyyy");
        String format1 = date1.format(new Date());
        dataholder obj=new dataholder("","","");
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        int gr = user.indexOf("GR:");
        int dept = user.indexOf("DEPT");
        String Gr = user.substring(gr+3,dept-1);
        DatabaseReference node= db.getReference("Dec").child(format1).child(Gr);
        node.setValue(obj);
    }
    public void at_node(String user){
        SimpleDateFormat date1 = new SimpleDateFormat("dd:MM:yyyy");
        String format1 = date1.format(new Date());
        SimpleDateFormat date2 = new SimpleDateFormat("HH:mm:ss");
        String format = date2.format(new Date());
        int len = user.length();
        int n =  user.indexOf("Name:");
        int gr = user.indexOf("GR:");
        int dept = user.indexOf("DEPT");
        String name = user.substring(n+5,gr-1);
        String Dept = user.substring(dept+5,len);
        String Gr = user.substring(gr+3,dept-1);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/Dec/"+format1+"/"+Gr);
        Map<String, Object> updates = new HashMap<String,Object>();
        updates.put("at", format);
        updates.put("name",name);
        updates.put("Department",Dept);
        ref.updateChildren(updates);
    }
    public void dt_node(String user){
        int gr = user.indexOf("GR:");
        int dept = user.indexOf("DEPT");
        String Gr = user.substring(gr+3,dept-1);
        SimpleDateFormat date1 = new SimpleDateFormat("dd:MM:yyyy");
        String format1 = date1.format(new Date());
        SimpleDateFormat date2 = new SimpleDateFormat("HH:mm:ss");
        String format = date2.format(new Date());
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/Dec/"+format1+"/"+Gr);
        Map<String, Object> updates = new HashMap<String,Object>();
        updates.put("dt", format);
        ref.updateChildren(updates);
    }

}

package com.example.attendance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teacher extends AppCompatActivity implements View.OnClickListener{
    Button scanBtn,contBtn,crex;
    TextView messageText, messageFormat;
    RadioGroup radioGroup;
    RadioButton a,d;
    FirebaseFirestore firestore;
    ImageView ivqr;
    int ct;
    private File filePath = new File("/storage/emulated/0/DATA.xls");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        database sendContext = new database(getApplicationContext());
        scanBtn = findViewById(R.id.button);
        contBtn = findViewById(R.id.button2);
        crex = findViewById(R.id.button3);
        radioGroup = findViewById(R.id.radioGroup);
        ivqr = findViewById(R.id.imageView);
        scanBtn.setOnClickListener(this::onClick);
        contBtn.setOnClickListener(this::onCont);
        crex.setOnClickListener(this::excelcreator);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
    }
    private void excelcreator(View view) {
//        int dimen = ivqr.getWidth() < ivqr.getHeight() ? ivqr.getWidth() : ivqr.getHeight();
//        dimen = dimen * 3 / 4;
//        QRGEncoder qrgEncoder = new QRGEncoder("hello world", null, QRGContents.Type.TEXT, dimen);
//        Bitmap bitmap = qrgEncoder.getBitmap();
//        ivqr.setImageBitmap(bitmap);
        Toast.makeText(Teacher.this, "no functions yet", Toast.LENGTH_SHORT).show();
//        SimpleDateFormat date1 = new SimpleDateFormat("dd:MM:yyyy");
//        String format = date1.format(new Date());
//         rltdatabase r = new rltdatabase(); ////important
//         r.add_node("04:12:2022","Dec","rlt");
//        String format = "04:12:2022";
//        String format1 = "03:12:2022";
//        database f = new database(getApplicationContext());
//        f.read(format);
//        f.read(format1);
//        String replace = format.replace("04", "05");
//        f.read(replace);


//        try{
//            String format = "03:12:2022";
//            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
//            HSSFSheet hssfSheet = hssfWorkbook.createSheet(format);
//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//            int i = 1;
//
//            while (i<=31){
//                int finalI = i;
//                db.collection(format).get();
//                db.collection(format)
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                        String k  = document.getId();
//                                        DocumentReference docRef = db.collection(format).document(k+"Arrival time");
//                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                                if (task.isSuccessful()) {
//                                                    DocumentSnapshot document1 = task.getResult();
//                                                    if (document1.exists()) {
//                                                        String at = ""+document1.getData();
//                                                        int val = finalI;
//                                                        HSSFRow hssfRow = hssfSheet.createRow(val);
//                                                        val = val+1;
//                                                        HSSFCell hssfCell1 = hssfRow.createCell(0);
//                                                        HSSFCell hssfCell2 = hssfRow.createCell(1);
//                                                        HSSFCell hssfCell3 = hssfRow.createCell(2);
//                                                        hssfCell1.setCellValue(format);
//                                                        hssfCell2.setCellValue(k);
//                                                        hssfCell3.setCellValue(at);
//                                                        try{
//                                                            if(!filePath.exists()){
//                                                                filePath.createNewFile();
//                                                            }
//                                                            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
//                                                            hssfWorkbook.write(fileOutputStream);
//                                                            if(fileOutputStream!=null){
//                                                                fileOutputStream.flush();
//                                                                fileOutputStream.close();
//                                                            }
//                                                        }catch (Exception e){
//                                                            e.printStackTrace();
//                                                        }
//                                                    } else {
//                                                        Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
//                                                    }
//                                                } else {
//                                                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
//                                                }
//                                            }
//                                        });
//                                    }
//                                } else {
//                                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//                i = i+1;
//            }
//        }catch (Exception e){
//            Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
//        }
    }

    private void onCont(View view) {
        try{
            int selectedId=radioGroup.getCheckedRadioButtonId();
            a=(RadioButton)findViewById(selectedId);
            String s = String.valueOf(a.getText());
            ct = 1;
            scanCode();}
        catch (Exception e){
            Toast.makeText(Teacher.this,"please first select arrival/departure option",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        try{
            int selectedId=radioGroup.getCheckedRadioButtonId();
            a=(RadioButton)findViewById(selectedId);
            String s = String.valueOf(a.getText());
            ct = 0;
            scanCode();}
        catch (Exception e){
            Toast.makeText(Teacher.this,"please select arrival/departure option",Toast.LENGTH_LONG).show();
        }
    }

    private void scanCode(){

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning code");
        integrator.initiateScan();
    }
    public void tmethod(int t){
        if(t==1){
            Toast.makeText(Teacher.this,"success",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Teacher.this,"failure",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result  = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() !=null){
                String X = result.getContents();
//                builder.setMessage(result.getContents());

                if(ct==1){
                    rltdatabase r = new rltdatabase();
                    firestore = FirebaseFirestore.getInstance();
                    int selectedId=radioGroup.getCheckedRadioButtonId();
                    a=(RadioButton)findViewById(selectedId);
                    String s = String.valueOf(a.getText());
                    SimpleDateFormat date1 = new SimpleDateFormat("dd:MM:yyyy");
                    SimpleDateFormat date2 = new SimpleDateFormat("HH:mm:ss");
                    String format = date1.format(new Date());
                    String format1 = date2.format(new Date());
                    if(s.equals("Arrival")){
//                        database db = new database(getApplicationContext());
//                        int k = db.arrival(X);
//                        tmethod(k);
                        r.add_node(X);
                        r.at_node(X);
                    }
                    if(s.equals("Departure")){
//                        database db = new database(getApplicationContext());
//                        int k = db.departure(X);
//                        tmethod(k);
                        r.dt_node(X);
                    }
                    scanCode();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("scanning result");
                    builder.setPositiveButton("scan Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            scanCode();
                        }
                    }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            firestore = FirebaseFirestore.getInstance();
                            int selectedId=radioGroup.getCheckedRadioButtonId();
                            a=(RadioButton)findViewById(selectedId);
                            String s = String.valueOf(a.getText());
                            rltdatabase r = new rltdatabase();
//                        r.node_check();

                            if(s.equals("Arrival")){
//                            database db = new database(getApplicationContext());
//                            int k = db.arrival(X);
//                            tmethod(k);
                                Toast.makeText(Teacher.this, X, Toast.LENGTH_SHORT).show();
                                r.add_node(X);
                                r.at_node(X);

                            }
                            if(s.equals("Departure")){
//                            database db = new database(getApplicationContext());
//                            int k = db.departure(X);
//                            tmethod(k);
                                r.dt_node(X);
                            }
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
            else{
                Toast.makeText(this,"No Results",Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }

    }
}
package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class qrgene extends AppCompatActivity {
    private ImageView ivqr;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgene);
        ivqr = findViewById(R.id.imageView);
        b = findViewById(R.id.button8);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("attendance",MODE_PRIVATE);
                String s = preferences.getString("gr","");
                int dimen = ivqr.getWidth() < ivqr.getHeight() ? ivqr.getWidth() : ivqr.getHeight();
                dimen = dimen * 3 / 4;
                QRGEncoder qrgEncoder = new QRGEncoder(s, null, QRGContents.Type.TEXT, dimen);
                Bitmap bitmap = qrgEncoder.getBitmap();
                ivqr.setImageBitmap(bitmap);
            }
        });
    }
}
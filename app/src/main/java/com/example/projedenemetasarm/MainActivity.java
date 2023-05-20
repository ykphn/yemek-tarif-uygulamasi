package com.example.projedenemetasarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    String eMail;
    String sifre;
    private TextView textKayit;
    private EditText txtEmail,txtSifre;
    private Button giris;
    private FirebaseAuth mAuth;
    private RelativeLayout layout;
    private FirebaseUser mUser;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.ly);
        mAuth = FirebaseAuth.getInstance();
        textKayit = findViewById(R.id.tiklaKayit);
        txtEmail = findViewById(R.id.email);
        txtSifre = findViewById(R.id.sifre);
        giris = findViewById(R.id.giris);
        signIn();
        textKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KayitEkrani.class);
                finish();
                startActivity(intent);
            }
        });
    }
        public void girisYap(){
            eMail=txtEmail.getText().toString();
            sifre=txtSifre.getText().toString();

            if(!TextUtils.isEmpty(eMail)&& !TextUtils.isEmpty(sifre)){
                mAuth.signInWithEmailAndPassword(eMail,sifre)
                        .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                mUser=mAuth.getCurrentUser();
                                Intent intent=new Intent(MainActivity.this,MainActivity222.class);
                                finish();
                                startActivity(intent);
                            }
                        }).addOnFailureListener(this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }else
                Toast.makeText(this, "Email ve Sifre boş olamaz", Toast.LENGTH_SHORT).show();
        }

    public void signIn(){
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girisYap();
            }
        });
    }
}
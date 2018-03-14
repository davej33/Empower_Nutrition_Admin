package com.example.davidjusten.empower_nutrition_admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UTFDataFormatException;

public class LoginActivity extends AppCompatActivity {

    private EditText mLoginUser, mLoginPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mLoginPassword = findViewById(R.id.loginPassword);
        mLoginUser = findViewById(R.id.loginUsername);
        mDbRef = FirebaseDatabase.getInstance().getReference().child("users");

    }

    public void signinClicked(View view) {

        String user_text = mLoginUser.getText().toString().trim();
        String user_pass = mLoginPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(user_pass) && !TextUtils.isEmpty(user_text)) {
            mAuth.signInWithEmailAndPassword(user_text, user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        checkUserExists();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void checkUserExists() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        final String id = mAuth.getCurrentUser().getUid();
        mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(id)) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

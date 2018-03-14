package com.example.davidjusten.empower_nutrition_admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText mUsername, mPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = findViewById(R.id.signupUsername);
        mPassword = findViewById(R.id.signupPassword);
        mAuth = FirebaseAuth.getInstance();
        mDbRef = FirebaseDatabase.getInstance().getReference().child("users");

    }

    public void loginClicked(View view) {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }

    public void signupClicked(View view) {

        final String user_text = mUsername.getText().toString().trim();
        String user_pass = mPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(user_text) && !TextUtils.isEmpty(user_pass)){
            // create login with give info
            mAuth.createUserWithEmailAndPassword(user_text, user_pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() { // set listener for when complete
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String user_id = mAuth.getCurrentUser().getUid(); // if successful, get UID
                                DatabaseReference currentUser = mDbRef.child(user_id);
                                currentUser.child("name").setValue(user_text);
                            }
                        }
                    });
        }
    }
}

package com.example.doyde.dewidar;

import android.app.ProgressDialog;
import android.drm.ProcessedData;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextEmaile;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        editTextEmaile=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        textViewSignin=(TextView)findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }
    private void registerUser(){
        String email=editTextEmaile.getText().toString().trim();
        String passowrd=editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empaty
            Toast.makeText(this,"please enter email",Toast.LENGTH_SHORT).show();
            //stop function excution
            return;
        }
        if(TextUtils.isEmpty(passowrd)){
            //password is empity
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,passowrd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //start the profile activity here
                            progressDialog.hide();
                            Toast.makeText(MainActivity.this,"Regestered Successfully",Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.hide();
                            Toast.makeText(MainActivity.this,"could not register try again",Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }


    @Override
    public void onClick(View v) {
        if(v== buttonRegister){
            registerUser();
        }
        if (v== textViewSignin){

            //will open login activity here
        }

    }
}

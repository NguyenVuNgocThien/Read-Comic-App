package com.example.greedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greedapp.Interface.RegisterInterface;
import com.example.greedapp.Presenter.RegisterPresenter;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements RegisterInterface {
    private RegisterPresenter mRegisterPresenter;
    private Button btReg;
    private EditText edUser,edPass,edRetype;
    private FirebaseAuth mAuth;
    public static final String EXTRA_MESSAGE1="com.ltđ.newapp.View.MESSAGE";
    public static final String EXTRA_MESSAGE2="com.ltđ.newapp.View.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth= FirebaseAuth.getInstance();
        btReg=findViewById(R.id.btReg);
        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickReg();
            }
        });
    }
    public void clickReg(){
        edPass=findViewById(R.id.edPassReg);
        edRetype=findViewById(R.id.edRetype);
        edUser=findViewById(R.id.edUserReg);
            mRegisterPresenter = new RegisterPresenter(this);
            mRegisterPresenter.Regist(edRetype.getText().toString(),edUser.getText().toString(),edPass.getText().toString(), Register.this);

    }

    @Override
    public void RegSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, LoginActivity.class);
        String txtUsername=edUser.getText().toString();
        String txtPass=edPass.getText().toString();
        intent.putExtra(EXTRA_MESSAGE2,txtPass);
        intent.putExtra(EXTRA_MESSAGE1,txtUsername);
        startActivity(intent);
    }

    @Override
    public void RegError() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }
}
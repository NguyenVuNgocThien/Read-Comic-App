package com.example.greedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greedapp.Interface.LoginInterface;
import com.example.greedapp.Presenter.LoginPresenter;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginInterface{

    private FirebaseOptions firebaseOptions;
    private FirebaseAuth firebaseAuth;
    private EditText edUser,edPass;
    private Button btLogin,btRegFromLoginSite;
    private LoginPresenter mLoginPresenter;
    private ShapeableImageView fblogin;
    private ShapeableImageView gglogin;
    private CallbackManager callbackManager;
    private GoogleSignInClient client;
    private GoogleSignInOptions sign;
    private boolean isClickfb,isClickgg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        mLoginPresenter=new LoginPresenter(this);
        edPass= findViewById(R.id.edPass);
        edUser=findViewById(R.id.edUser);
        btLogin=findViewById(R.id.btLogin);
        fblogin=findViewById(R.id.fblogin);
        gglogin=findViewById(R.id.gglogin);
        isClickfb=false;
        isClickgg=false;
        btRegFromLoginSite=findViewById(R.id.btRegFromLoginSite);
        sign=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        client= GoogleSignIn.getClient(this,sign);
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null)
        {
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }


        btRegFromLoginSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        Toast.makeText(this, Register.EXTRA_MESSAGE1, Toast.LENGTH_SHORT).show();
        edUser.setText(intent.getStringExtra(Register.EXTRA_MESSAGE1));
        edPass.setText(intent.getStringExtra(Register.EXTRA_MESSAGE2));
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
        fblogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.Loginwithfb(callbackManager, LoginActivity.this);
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
                isClickfb=true;
                isClickgg=false;
            }
        });
        gglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=client.getSignInIntent();
                startActivityForResult(intent,1000);
                isClickgg=true;
                isClickfb=false;
            }
        });
    }
    public void clickLogin(){
        mLoginPresenter=new LoginPresenter(this);
        mLoginPresenter.login(edUser.getText().toString(),edPass.getText().toString(),this);
    }
    @Override
    public void loginSucess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void LoginError() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(isClickfb) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }
        else if(isClickgg){
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==1000){
                Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    task.getResult(ApiException.class);
                    finish();
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (ApiException e) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
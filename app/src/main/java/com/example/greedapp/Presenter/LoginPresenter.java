package com.example.greedapp.Presenter;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greedapp.Interface.LoginInterface;
import com.example.greedapp.Model.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter extends AppCompatActivity {
    private LoginInterface mLoginInterface;
    private FirebaseAuth mAuth;


    public LoginPresenter(LoginInterface mLoginInterface) {
        this.mLoginInterface = mLoginInterface;
    }

    public void login(String User , String Pass, Activity activity){
        mAuth=FirebaseAuth.getInstance();
        User user=new User(User,Pass);
        if(user.isValidPass() && user.isValidUser() && user.checkSignin(User,Pass)){
            mAuth.signInWithEmailAndPassword(User,Pass).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        //Go to Home
                        mLoginInterface.loginSucess();

                    }
                    else
                        Toast.makeText(activity, "Sign in Failed,Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            mLoginInterface.LoginError();
        }
    }
    public void Loginwithfb(CallbackManager callbackManager,Activity activity) {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mLoginInterface.loginSucess();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {

            }
        });


    }


}

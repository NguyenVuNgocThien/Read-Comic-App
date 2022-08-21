package com.example.greedapp.Presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.greedapp.Interface.RegisterInterface;
import com.example.greedapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPresenter {
    private FirebaseAuth mAuth;
    private RegisterInterface mRegisterInterface;
    public RegisterPresenter(RegisterInterface mRegisterInterface){
        this.mRegisterInterface=mRegisterInterface;
    }
    public void Regist(String Retype,String Username,String Pass, Activity activity){
        mAuth=FirebaseAuth.getInstance();
        User user1=new User(Username,Pass);
        if(user1.isValidPass() && user1.isValidUser() && user1.CheckRegist(Username,Pass,Retype,activity)){
            mAuth.createUserWithEmailAndPassword(Username, Pass)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(activity, String.valueOf(task.isSuccessful()), Toast.LENGTH_SHORT).show();
                            if(task.isSuccessful()){
                                User user=new User(mAuth.getCurrentUser().getUid(),Username,Pass);
                                DatabaseReference dataRef= FirebaseDatabase.getInstance().getReference();
                                dataRef.child("Users").child(user.getUserID()).setValue(user);
                                mRegisterInterface.RegSuccess();
                                return;
                            }
                            else
                                Toast.makeText(activity, "Sign up failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else
        {

            mRegisterInterface.RegError();

        }
    }
}

package com.example.greedapp.Presenter;

import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.greedapp.Interface.UploadInterface;
import com.example.greedapp.Model.Category;
import com.example.greedapp.Model.Chap;
import com.example.greedapp.Model.Comic;
import com.example.greedapp.Model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

public class UploadPresenter {
    private UploadInterface mUploadInterface;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private StorageReference mStorageReference;
    private User user;
    private ContentResolver contentResolver=null;

    public UploadPresenter(UploadInterface mUploadInterface,ContentResolver contentResolver) {
        this.mUploadInterface = mUploadInterface;
        this.contentResolver=contentResolver;
    }

    public void UploadComic(String ComicName, String AnotherName, String Description, String Status, List<Chap> chaps, List<Category> list, Uri imageUri, List<Category> categories, Activity activity){
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        Calendar calendar=Calendar.getInstance();
        String comicID=calendar.getTimeInMillis()+currentUser.getUid();
        Comic comic=new Comic();
        for (int i=0;i<categories.size();i++){
            reference.child("Categories").child(i+mAuth.getCurrentUser().getUid()).setValue(categories.get(i));
        }
        Calendar calendar1 = Calendar.getInstance();
        String name = currentUser.getUid() + calendar1.getTimeInMillis();
        StorageReference storage = FirebaseStorage.getInstance().getReference();
        StorageReference imgRef = storage.child("AnhBia/" + name + ".png");
        UploadTask uploadTask=imgRef.putFile(imageUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri uri=taskSnapshot.getUploadSessionUri();
                Toast.makeText(activity, uri.toString(), Toast.LENGTH_SHORT).show();
                comic.setComicID(comicID);
                comic.setComicName(ComicName);
                comic.setComicStatus(Status);
                comic.setComicAuthor("Akira");
                comic.setComicDescription(Description);
                comic.setUserID(mAuth.getCurrentUser().getUid());
                comic.setAnotherName(AnotherName);
                comic.setList(list);
                comic.setChaps(chaps);
                comic.setComicView(0);
                comic.setImageView(uri.toString());
                reference.child("Comics").child(comicID).setValue(comic).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(activity, "Upload Success", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(activity, "Upload Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
//    private String getImage(Uri uri){
//        MimeTypeMap mime=MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
    }


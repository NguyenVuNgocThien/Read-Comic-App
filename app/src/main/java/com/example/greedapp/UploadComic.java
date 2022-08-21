package com.example.greedapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greedapp.Interface.UploadInterface;
import com.example.greedapp.Model.Category;
import com.example.greedapp.Model.Chap;
import com.example.greedapp.Presenter.UploadPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UploadComic extends AppCompatActivity implements UploadInterface {

    private String list_category[]={"Action","Adventure","Comedy","Comic","Cooking","Drama","Fantasy","Historical","Horror","Martial Arts","Mature","Mystery","Romance","School","Slice of Life","Sports","Supernatural","Webtoon"};
    private ListView listView;
    private List<Category> list;
    private ImageView mImageView,imagebacktohome;
    private EditText edComicName,edAnotherName,edMota,edStatus;
    private CheckBox cb1,cb2,cb3;
    private Button btUpload;
    private UploadPresenter uploadPresenter;
    private Uri mImageUri;
    private ArrayList<CheckBox> checkBoxes;
    private UploadPresenter mUploadPresenter;
    private ContentResolver contentResolver;
    private static final int PICK_IMAGE_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_comic);
        listView=(ListView) findViewById(R.id.listTheLoaiCuaTruyen);
        mImageView=(ImageView) findViewById(R.id.imgAnhBia);
        imagebacktohome=(ImageView) findViewById(R.id.logohome);
        edComicName=(EditText) findViewById(R.id.edComicName);
        edAnotherName=(EditText) findViewById(R.id.edAnotherName);
        edMota=(EditText) findViewById(R.id.edDescription);
        edStatus=(EditText) findViewById(R.id.edStatus);
        btUpload=(Button) findViewById(R.id.btUpComic);
        list=new ArrayList<Category>();
        checkBoxes=new ArrayList<CheckBox>();
        contentResolver=getContentResolver();
        uploadPresenter=new UploadPresenter(this,contentResolver);

        AdapterCategory adapterCategory=new AdapterCategory(checkBoxes,this,list_category,list);
        listView.setAdapter(adapterCategory);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseImage();
            }
        });
        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                cb1=(CheckBox) findViewById(R.id.checkBox1);
                for(int i=0;i<checkBoxes.size();i++) {
                    if (checkBoxes.get(i).isChecked()) {
                        Category category = new Category(i, checkBoxes.get(i).getText().toString());
                        list.add(category);
                    }
                }
                UpComic();
            }
        });

    }
    public void UpComic(){
        mUploadPresenter=new UploadPresenter(this,contentResolver);
        List<Chap> chaps=new ArrayList<>();
        List<Category> categories=new ArrayList<Category>();
        for(int i=0;i<list_category.length;i++){
            categories.add(new Category(i,list_category[i]));
        }
        mUploadPresenter.UploadComic(edComicName.getText().toString(),edAnotherName.getText().toString()
                ,edMota.getText().toString(),edStatus.getText().toString(),chaps,list,mImageUri,categories,this);
    }
    public void ChooseImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&& data!=null&& data.getData()!=null){
            mImageUri=data.getData();
            Picasso.get().load(mImageUri).into(mImageView);
        }
    }
    @Override
    public void UpSuccess() {

    }

    @Override
    public void UpError() {

    }
}
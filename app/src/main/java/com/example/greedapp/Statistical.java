package com.example.greedapp;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Statistical#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Statistical extends Fragment {
    ImageView imgAnhdaidien,imgUpcomic,imgLogout,imgeditname,imgeditmail,imgeditfblink;
    TextView txtName,txtMail,txtFb,txtThunhap,txtSotruyen,txtSochap,txtComment,txtFollow,txtTienTuTruyen,txtTienTuChap,txtTongTien;
    ListView listView;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ScrollView scrollView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Statistical() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Statistical.
     */
    // TODO: Rename and change types and number of parameters
    public static Statistical newInstance(String param1, String param2) {
        Statistical fragment = new Statistical();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        listView=(ListView) getView().findViewById(R.id.listcomic);
        imgeditfblink=(ImageView) getView().findViewById(R.id.imgeditfblink);
        imgeditmail=(ImageView) getView().findViewById(R.id.imgeditmail);
        imgeditname=(ImageView) getView().findViewById(R.id.imgeditname);
        imgAnhdaidien=(ImageView) getView().findViewById(R.id.imgAnhdaidien);
        imgLogout=(ImageView) getView().findViewById(R.id.imglogout);
        imgUpcomic=(ImageView) getView().findViewById(R.id.imgupcomic);
        txtComment=(TextView) getView().findViewById(R.id.txtSLBinhLuan);
        txtFb=(TextView) getView().findViewById(R.id.txtfblink);
        txtFollow=(TextView) getView().findViewById(R.id.txtSLTheodoi);
        txtMail=(TextView) getView().findViewById(R.id.txtMail);
        txtName=(TextView) getView().findViewById(R.id.txtUsername);
        txtSochap=(TextView) getView().findViewById(R.id.txtSLChap);
        txtSotruyen=(TextView) getView().findViewById(R.id.txtSLTruyen);
        txtThunhap=(TextView) getView().findViewById(R.id.txtThunhap);
        txtTienTuChap=(TextView) getView().findViewById(R.id.txtMoneyFromChap);
        txtTienTuTruyen=(TextView) getView().findViewById(R.id.txtMoneyFromComic);
        txtTongTien=(TextView) getView().findViewById(R.id.txtTotalIncom);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signout=new Intent(getApplicationContext(), LoginActivity.class);
                signout.putExtra("signout",true);
                mAuth.signOut();
                startActivity(signout);
            }
        });
        imgUpcomic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent upcomic=new Intent(getApplicationContext(),UploadComic.class);
                startActivity(upcomic);
            }
        });



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_statistical, container, false);
    }
}
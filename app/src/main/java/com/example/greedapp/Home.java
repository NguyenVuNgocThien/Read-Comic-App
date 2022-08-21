package com.example.greedapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.greedapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    DatabaseReference dataRef;
    User mainUser;
    View view;
    String list[]={"Thien","Thang"};
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


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
        view=inflater.inflate(R.layout.activity_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = view.findViewById(R.id.listcomic);
        listView.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,list));
    }
    //    public boolean onOptionsItemSelected( MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu_profile:
//                Intent intent=new Intent(Home.this, Profile.class);
//                startActivity(intent);
//                break;
//            case R.id.menu_exit:
//                AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
//                dialog.setTitle("Wait");
//                dialog.setMessage("Exit");
//                dialog.setIcon(R.drawable.icon_crying);
//                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                        startActivity(intent);
//
//                        Intent startMain = new Intent(Intent.ACTION_MAIN);
//                        startMain.addCategory(Intent.CATEGORY_HOME);
//                        startActivity(startMain);
//                        finish();
//                    }
//                });
//                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                dialog.show();
//                break;
//            case R.id.menu_signout:
//                Intent signout=new Intent(getApplicationContext(), LoginActivity.class);
//                signout.putExtra("signout",true);
//                mAuth.signOut();
//                startActivity(signout);
//                finish();
//                break;
//            case R.id.menu_Contribute:
//                Intent contri=new Intent(Home.this, Statistical.class);
//                startActivity(contri);
//                break;
//            case R.id.menu_uploadcomic:
//                Intent up=new Intent(Home.this,UploadComic.class);
//                startActivity(up);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
package com.example.secure;

import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register_screen extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore mFirestore;
    private DatabaseReference mDatabase;
    private ProgressBar mProgressBar;
    private Handler mHandler = new Handler();

    EditText edt1, edt2, edt3, edt4;
    String idLib, addLib, emaiLib, nameLib;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        auth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        edt1 = (EditText) findViewById(R.id.registerlibraryid);
        edt2 = (EditText) findViewById(R.id.registerlibraryname);
        edt3 = (EditText) findViewById(R.id.registeremail);
        edt4 = (EditText) findViewById(R.id.registeraddress);
        b = (Button) findViewById(R.id.b_submit);
        mProgressBar =(ProgressBar) findViewById(R.id.progressBar);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgressBar.setVisibility(View.VISIBLE);

                idLib = edt1.getText().toString().trim();
                nameLib = edt2.getText().toString().trim();
                emaiLib = edt3.getText().toString().trim();
                addLib = edt4.getText().toString().trim();

                Map<String,String> detailsMap = new HashMap<>();
                 detailsMap.put("Library_Id",idLib);
                 detailsMap.put("Library_name", nameLib);
                 detailsMap.put("Library_Address",addLib);
                 detailsMap.put("Library_Email",emaiLib);

                 mFirestore.collection("Details").add(detailsMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                     @Override
                     public void onSuccess(DocumentReference documentReference) {
                         mProgressBar.setVisibility(View.GONE);
                         Toast.makeText(Register_screen.this, "Data Entry Successful. " +
                                 "Admin will verify it within 24hrs.", Toast.LENGTH_SHORT).show();
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(Register_screen.this, "Error in Uploading ", Toast.LENGTH_SHORT).show();
                     }
                 });
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mProgressBar.setVisibility(View.GONE);
    }
}

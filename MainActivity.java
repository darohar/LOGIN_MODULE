package com.example.secure;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    Button b,b2;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private ProgressBar mProgressBar;
    private Handler mHandler = new Handler();
    String emai,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar =(ProgressBar) findViewById(R.id.progressBar);

        edt1 = (EditText) findViewById(R.id.email);
        edt2 = (EditText) findViewById(R.id.password);
        b = (Button) findViewById(R.id.b_login);
        b2 = (Button) findViewById(R.id.b_signup);

        Log.v("errorNull","going well");
        mAuth = FirebaseAuth.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                pass = edt2.getText().toString().trim();
                emai = edt1.getText().toString().trim();
                Log.v("errorNull",emai);
                Log.v("errorNull",pass);
                mAuth.signInWithEmailAndPassword(emai,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mProgressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "sign in success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,manuscriptscreen.class));
                        }
                    }
                });

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent(MainActivity.this, Register_screen.class);
                MainActivity.this.startActivity(homepage);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mProgressBar.setVisibility(View.GONE);
    }
}


////
////
////        final Button button = findViewById(R.id.b_login);
////        button.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////
////                EditText email1 = findViewById(R.id.email);
////                String emailVer = email1.getText().toString();
////
////                EditText pass = findViewById(R.id.password);
////                String passVer = pass.getText().toString();
////
////                // Access a Cloud Firestore instance from your Activity
////                FirebaseFirestore db = FirebaseFirestore.getInstance();
////                CollectionReference usersCollectionRef = db.collection("Details");
////
////                db.collection("Details")
////                        .whereEqualTo("Library_Email", emailVer)
////                        .whereEqualTo("Password", passVer)
////                        .get()
////                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
////                            @TargetApi(Build.VERSION_CODES.KITKAT)
////                            @Override
////                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
////                                if (task.isSuccessful()) {
////
////                                        Context context = getApplicationContext();
////                                        CharSequence text = "Login Passed";
////                                        int duration = Toast.LENGTH_SHORT;
////                                        Toast toast = Toast.makeText(context,  text, duration);
////                                        toast.show();
////                                    }
////                                else {
////                                    Context context = getApplicationContext();
////                                    CharSequence text = "Login Failed";
////                                    int duration = Toast.LENGTH_SHORT;
////                                    Toast toast = Toast.makeText(context,  text, duration);
////                                    toast.show();                                  }
////                            }
////
////                //Context context = getApplicationContext();
////                //CharSequence text = "Hello";
////                //int duration = Toast.LENGTH_SHORT;
////                //Toast toast = Toast.makeText(context,  result, duration);
////                //toast.show();            }
////            });
////        }
////    });


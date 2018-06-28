//package com.example.mcflurry.bmfun;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.example.mcflurry.bmfun.Model.User;
//import com.rengwuxian.materialedittext.MaterialEditText;
//
//public class Login extends AppCompatActivity{
//    private static final String TAG = "Login";
//
//    MaterialEditText edtNewUser, edtNewPassword, edtNewEmail; // For sign up
//    MaterialEditText edtUser, edtPassword; // For sign in
//
//    Button btnSignUp, btnSignIn;
//
//    DatabaseHelper dbhelper = new DatabaseHelper(this);
//
////    FirebaseDatabase database;
////    DatabaseReference users;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        // Firebase
////        database = FirebaseDatabase.getInstance();
////        users = database.getReference("Users");
//
//        edtUser = (MaterialEditText) findViewById(R.id.edtUser);
//        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
//
//        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
//        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
//
//        SignIn();
//        SignUp();
//
//
//
//    }
//
//    public void SignIn() {
//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String uname = edtUser.getText().toString();
//                String pwd = edtPassword.getText().toString();
//
//                Log.d(TAG, "onClick: "+ String.valueOf(dbhelper.checkUser(uname, pwd)));
//
//                if (dbhelper.checkUser(uname,pwd)) {
//                    // Correct password
//                    Log.d(TAG, "onClick: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ");
//                    //Toast.makeText(Login.this, "WELCOME!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Login.this, "PASSWORD INCORRECT!", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }
//
//
//    public void SignUp() {
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //showSignUpDialog();
//                User user = new User();
//                String strUser = edtUser.getText().toString();
//                String strPass = edtPassword.getText().toString();
//
//                user.setUserName(strUser);
//                user.setPassword(strPass);
//
//                boolean insertData = dbhelper.insertData(user);
//                if (insertData) {
//                    Toast.makeText(Login.this, "SIGN-UP SUCCESS!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Login.this, "SIGN-UP FAIL!", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }
//
//}

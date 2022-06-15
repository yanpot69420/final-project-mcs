package com.example.finalprojectk.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.finalprojectk.R;
import com.example.finalprojectk.database.Database;
import com.example.finalprojectk.database.DatabaseHelper;
import com.example.finalprojectk.object.Users;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class RegisterFragment extends Fragment {
    EditText regUsername, regEmail, regPhone, regPassword;
    Button btnRegister;
    String email, username, phone, password;
    TextInputLayout emailLayout, usernameLayout, phoneLayout, passwordLayout;
    private static ArrayList<Users> userList;
    DatabaseHelper dhRegister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        dhRegister = new DatabaseHelper(getActivity());
        btnRegister.setOnClickListener(v -> {
            resetError();
            initVar();
            userList = Database.getUserData(getActivity());
            boolean check = checkError(email, username, phone, password);
            if(check){
                Users user = new Users(-1,email, username, phone, password);
                boolean checkInsert = dhRegister.addUser(user);
                if(!checkInsert)
                    Toast.makeText(getActivity(), "Attempt Failed!", Toast.LENGTH_SHORT).show();
                else {
                    emptyEditText();
                    Toast.makeText(getActivity(), "Your account has been created!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void emptyEditText() {
        regUsername.setText("");
        regEmail.setText("");
        regPhone.setText("");
        regPassword.setText("");
    }

    private void initView(@NonNull View view){
        regEmail = view.findViewById(R.id.inputEmail);
        regUsername = view.findViewById(R.id.inputUsername);
        regPhone = view.findViewById(R.id.inputPhone);
        regPassword = view.findViewById(R.id.inputPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        emailLayout = view.findViewById(R.id.etEmail);
        usernameLayout = view.findViewById(R.id.etUsername);
        phoneLayout = view.findViewById(R.id.etPhone);
        passwordLayout = view.findViewById(R.id.etPassword);
    }

    private void initVar() {
        email = regEmail.getText().toString();
        username = regUsername.getText().toString();
        phone = regPhone.getText().toString();
        password = regPassword.getText().toString();
    }

    private boolean checkError(String email, String username, String phone, String password){
        boolean temp = true;
        if(!email.endsWith(".com")){
            temp = false;
            emailLayout.setError("Email must ends with '.com'");
        }
        if(email.length()==0){
            temp = false;
            emailLayout.setError("Field cannot be empty");
        }
        if(username.length()<3 || username.length()>20){
            temp = false;
            usernameLayout.setError("Username must be between 3-20 characters");
        }

        if(checkEmail(email)){
            temp = false;
            emailLayout.setError("Email already in use");
        }

        if(checkUsername(username)){
            temp = false;
            usernameLayout.setError("Username already in use");
        }

        if(username.length()==0){
            temp = false;
            usernameLayout.setError("Field cannot be empty!");
        }
        if(phone.length()==0){
            temp = false;
            phoneLayout.setError("Field cannot be empty!");
        }
        if(password.length()==0){
            temp = false;
            passwordLayout.setError("Field cannot be empty!");
        }
        return temp;
    }

    private void resetError(){
        emailLayout.setError(null);
        usernameLayout.setError(null);
        phoneLayout.setError(null);
        passwordLayout.setError(null);
    }

    private Boolean checkEmail(String email){

        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUserEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    private Boolean checkUsername(String username){

        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUserUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
}
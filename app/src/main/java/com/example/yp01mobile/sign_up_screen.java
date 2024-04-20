package com.example.yp01mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yp01mobile.databinding.ActivitySingUpScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_up_screen extends AppCompatActivity {
    private ActivitySingUpScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingUpScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_up_screen.this, sign_in_screen.class));
            }
        });
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.email.length() != 0 || binding.password.length() != 0 || binding.fullName.length() != 0 || binding.phone.length() != 0)
                {
                    if(validate("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",binding.email.getText().toString()))
                    {
                        if(validate("^[А-ЯЁA-Z][а-яёa-z]+ [А-ЯЁA-Z][а-яёa-z]+ [А-ЯЁA-Z][а-яёa-z]+$",binding.fullName.getText().toString())){
                            if(validate("^\\+7\\d{10}$",binding.phone.getText().toString())){
                                if(binding.password.getText().length() > 6)
                                {
                                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString())
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()){
                                                        HashMap<String, String> userInfo = new HashMap<>();
                                                        userInfo.put("email", binding.email.getText().toString());
                                                        userInfo.put("fullName", binding.fullName.getText().toString());
                                                        userInfo.put("phone", binding.phone.getText().toString());
                                                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .setValue(userInfo);

                                                        startActivity(new Intent(sign_up_screen.this, LaunchScreen.class));
                                                        finish();
                                                    }
                                                }
                                            });
                                }
                                else Toast.makeText(v.getContext(), "Длина пароля должна быть больше 6 симворлов", Toast.LENGTH_SHORT).show();

                            }
                            else Toast.makeText(v.getContext(), "Телефон введен не корректно", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(v.getContext(), "ФИО введено не корректно", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(v.getContext(), "Не верная почта", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(v.getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public static boolean validate(String REGEX,String email) {
        Matcher matcher = Pattern.compile(REGEX).matcher(email);
        return matcher.matches();
    }
}
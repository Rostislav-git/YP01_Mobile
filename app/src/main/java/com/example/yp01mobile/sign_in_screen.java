package com.example.yp01mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yp01mobile.databinding.ActivitySignInScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_in_screen extends AppCompatActivity {
    private ActivitySignInScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            binding.login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(binding.email.length() != 0 && binding.password.length() != 0) {
                        if(validate("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",binding.email.getText().toString()))
                        {
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(sign_in_screen.this, LaunchScreen.class));
                                                finish();
                                            }
                                            else Toast.makeText(v.getContext(), "Польватель не найден", Toast.LENGTH_SHORT).show();
                                        }
                                    });
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
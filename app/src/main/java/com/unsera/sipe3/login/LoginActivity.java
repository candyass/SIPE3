package com.unsera.sipe3.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.unsera.sipe3.R;
import com.unsera.sipe3.admin.Admin2Activity;
import com.unsera.sipe3.admin.AdminActivity;
import com.unsera.sipe3.daftar.DaftarActivity;
import com.unsera.sipe3.dialog.DialogPesan;
import com.unsera.sipe3.model.User;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton, daftarButton;
    private EditText editTextId;
    private EditText editTextPassword;

    private static final int REQUEST_DAFTAR = 201;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextId = findViewById(R.id.login_id_textField);
        editTextPassword = findViewById(R.id.login_password_textField);
        daftarButton = findViewById(R.id.login_daftar_btn);
        loginButton = findViewById(R.id.login_login_btn);

        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);


        loginButton.setOnClickListener((v) -> {

            String noKTP = editTextId.getText().toString();
            String password = editTextPassword.getText().toString();

            if(noKTP.isEmpty() || password.isEmpty()) {
                DialogPesan.newInstance("Login Gagal", "User Id / Password Kosong")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            User user = viewModel.getUser(noKTP, password);
            if(user == null) {
                DialogPesan.newInstance("Login Gagal", "User Id / Password Salah")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            editTextId.getText().clear();
            editTextPassword.getText().clear();

            if(user.getUserType() == User.USER_PEMOHON) {
                Intent intent = AdminActivity.newIntent(getBaseContext(), user.getNoKTP());
                startActivity(intent);
            }else if(user.getUserType() == User.USER_PENERIMA) {
                Intent intent = Admin2Activity.newIntent(getBaseContext());
                startActivity(intent);
            }
        });

        daftarButton.setOnClickListener(v -> {
            Intent intent = DaftarActivity.newIntent(getBaseContext());
            startActivityForResult(intent, REQUEST_DAFTAR);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_DAFTAR && resultCode == Activity.RESULT_OK) {
            Toast.makeText(getBaseContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
        }
    }
}

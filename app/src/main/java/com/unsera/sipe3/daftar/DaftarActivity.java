package com.unsera.sipe3.daftar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.unsera.sipe3.R;
import com.unsera.sipe3.dialog.DialogPesan;
import com.unsera.sipe3.model.User;

import java.util.concurrent.ExecutionException;

public class DaftarActivity extends AppCompatActivity {

    private EditText editNoKTP, editPassword, editNama, editEmail, editAlamat;
    private Button simpanButton;
    private DaftarViewModel viewModel;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, DaftarActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        editNoKTP = findViewById(R.id.daftar_no_ktp_editText);
        editPassword = findViewById(R.id.daftar_password_editText);
        editNama = findViewById(R.id.daftar_nama_editText);
        editEmail = findViewById(R.id.daftar_email_editText);
        editAlamat = findViewById(R.id.daftar_alamat_editText);
        simpanButton = findViewById(R.id.daftar_simpan_btn);

        viewModel = ViewModelProviders.of(this).get(DaftarViewModel.class);



        simpanButton.setOnClickListener(v -> {
            if(isDataKosong()) {
                DialogPesan.newInstance("Daftar Gagal", "Field Tidak Boleh Kosong")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            String noKTP = editNoKTP.getText().toString();
            String password = editPassword.getText().toString();
            String nama = editNama.getText().toString();
            String email = editEmail.getText().toString();
            String alamat = editAlamat.getText().toString();

            User user = new User(noKTP, password, nama, 1, email, alamat);
            int result = -1;
            try {
                result = viewModel.saveUser(user);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(result == 1) {
                setResult(Activity.RESULT_OK);
                finish();
            }else {
                DialogPesan.newInstance("Daftar Gagal", "No KTP Telah Terdaftar")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }
        });
    }

    public boolean isDataKosong() {
        return editNoKTP.getText().toString().isEmpty()
                || editPassword.getText().toString().isEmpty()
                || editEmail.getText().toString().isEmpty()
                || editNama.getText().toString().isEmpty()
                || editAlamat.getText().toString().isEmpty();
    }
}

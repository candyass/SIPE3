package com.unsera.sipe3.inputkandidat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.squareup.picasso.Picasso;
import com.unsera.sipe3.R;
import com.unsera.sipe3.dialog.DialogPesan;
import com.unsera.sipe3.model.Daerah;
import com.unsera.sipe3.model.Kandidat;
import com.unsera.sipe3.util.MyLogger;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.concurrent.ExecutionException;

public class InputKandidatActivity extends AppCompatActivity {

    private static final int REQUST_PHOTO  = 100;

    private EditText editTextNoUrut;
    private EditText editTextNama;
    private EditText editTextNamaWakil;
    private Spinner spinner;
    private Button simpanButton;
    private Button uploadButton;
    private CircleImageView circleImageView;

    private InputKandidatViewModel viewModel;
    private boolean isPhotoSelected;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, InputKandidatActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kandidat);

        editTextNoUrut = findViewById(R.id.input_activity_edit_no_urut);
        editTextNama = findViewById(R.id.input_activity_edit_nama_kandidat);
        editTextNamaWakil = findViewById(R.id.input_kandidat_namaWakil_text);
        spinner = findViewById(R.id.input_kandidat_spinner);
        circleImageView = findViewById(R.id.circleImageView);
        simpanButton = findViewById(R.id.input_kandidat_simpan_button);
        uploadButton = findViewById(R.id.input_kandidat_upload_button);

         viewModel = ViewModelProviders.of(this).get(InputKandidatViewModel.class);

        viewModel.getAllDaerah().observe(this, list -> {
            if(!list.isEmpty()) {
                MyLogger.log(list.toString());
                ArrayAdapter<Daerah> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });


        simpanButton.setOnClickListener(v -> {

            if(editTextNoUrut.getText().toString().isEmpty() || editTextNama.getText().toString().isEmpty()
                    || editTextNamaWakil.getText().toString().isEmpty() ) {
                DialogPesan.newInstance("Tambah Kandidat Gagal", "Field Harus Diisi")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            if(isTextBukanAngka(editTextNoUrut)) {
                DialogPesan.newInstance("Tambah Kandidat Gagal", "No Urut Tidak Valid")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            int noUrut = Integer.parseInt(editTextNoUrut.getText().toString());
            String nama = editTextNama.getText().toString();
            String namaWakil = editTextNamaWakil.getText().toString();
            Daerah daerah = (Daerah) spinner.getSelectedItem();
            Kandidat kandidat = new Kandidat(noUrut, nama, namaWakil, daerah.getId());
            if(isPhotoSelected) {
                Bitmap bitmap = ((BitmapDrawable)circleImageView.getDrawable()).getBitmap();
                kandidat.setFoto(bitmap);
            }
            viewModel.insertKandidat(kandidat);
            finish();
        });

        uploadButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            Intent choser = Intent.createChooser(intent, "Upload File Dari");
            startActivityForResult(choser, REQUST_PHOTO);
        });

    }

    private boolean isTextBukanAngka(EditText editText) {
        try {
            Integer.parseInt(editText.getText().toString());
        }catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUST_PHOTO) {
            if(resultCode == Activity.RESULT_OK) {
                Uri photoUri = data.getData();
                if(photoUri != null) {
                    Picasso.get().load(photoUri).into(circleImageView);
                    isPhotoSelected = true;
                }
            }
        }
    }
}

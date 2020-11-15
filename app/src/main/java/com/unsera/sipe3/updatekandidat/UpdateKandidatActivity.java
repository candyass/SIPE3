package com.unsera.sipe3.updatekandidat;

import android.content.Context;
import android.content.Intent;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.unsera.sipe3.R;
import com.unsera.sipe3.dialog.DialogPesan;
import com.unsera.sipe3.inputkandidat.InputKandidatViewModel;
import com.unsera.sipe3.model.Daerah;
import com.unsera.sipe3.model.Kandidat;
import com.unsera.sipe3.util.MyLogger;
import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateKandidatActivity extends AppCompatActivity {

    private static final String EXTRA_ID = "com.unsera.sipe3.updatekandidat.extra.id";
    private static final String EXTRA_NO_URUT = "com.unsera.sipe3.updatekandidat.extra.nourut";
    private static final String EXTRA_NAMA = "com.unsera.sipe3.updatekandidat.extra.nama";
    private static final String EXTRA_NAMA_WAKIL = "com.unsera.sipe3.updatekandidat.extra.namawakil";
    private static final String EXTRA_DAERAH_ID = "com.unsera.sipe3.updatekandidat.extra.daerahid";

    private EditText editTextNoUrut;
    private EditText editTextNama;
    private EditText editTextNamaWakil;
    private Spinner spinner;
    private Button updateButton;
    private Button hapusButton;

    private InputKandidatViewModel viewModel;


    public static Intent newIntent(Context context, long id, String noUrut, String nama, String namaWakil, int daerahId) {
        Intent intent = new Intent(context, UpdateKandidatActivity.class);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_NO_URUT, noUrut);
        intent.putExtra(EXTRA_NAMA, nama);
        intent.putExtra(EXTRA_NAMA_WAKIL, namaWakil);
        intent.putExtra(EXTRA_DAERAH_ID, daerahId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kandidat);


        editTextNoUrut = findViewById(R.id.update_activity_edit_no_urut);
        editTextNama = findViewById(R.id.update_activity_edit_nama_kandidat);
        editTextNamaWakil = findViewById(R.id.update_kandidat_namaWakil_text);
        spinner = findViewById(R.id.update_kandidat_spinner);
        updateButton = findViewById(R.id.update_kandidat_simpan_button);
        hapusButton = findViewById(R.id.update_kandidat_hapus_button);

        viewModel = ViewModelProviders.of(this).get(InputKandidatViewModel.class);

        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        String noUrut = getIntent().getStringExtra(EXTRA_NO_URUT);
        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        String namaWakil = getIntent().getStringExtra(EXTRA_NAMA_WAKIL);
        int daerahId = getIntent().getIntExtra(EXTRA_DAERAH_ID, -1);


        editTextNoUrut.setText(noUrut);
        editTextNama.setText(nama);
        editTextNamaWakil.setText(namaWakil);

        viewModel.getAllDaerah().observe(this, list -> {
            if(!list.isEmpty()) {
                MyLogger.log(list.toString());
                ArrayAdapter<Daerah> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setSelection((int) (daerahId-1));
            }
        });



        updateButton.setOnClickListener(v -> {
            if(editTextNoUrut.getText().toString().isEmpty() || editTextNama.getText().toString().isEmpty()
                    || editTextNamaWakil.getText().toString().isEmpty() ) {
                DialogPesan.newInstance("Update Kandidat Gagal", "Field Harus Diisi")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            if(isTextBukanAngka(editTextNoUrut)) {
                DialogPesan.newInstance("Update Kandidat Gagal", "No Urut Tidak Valid")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            int fieldNoUrut = Integer.parseInt(editTextNoUrut.getText().toString());
            String fieldNama = editTextNama.getText().toString();
            String fieldNamaWakil = editTextNamaWakil.getText().toString();
            Daerah daerah = (Daerah) spinner.getSelectedItem();
            Kandidat kandidat = new Kandidat(id, fieldNoUrut, fieldNama, fieldNamaWakil, daerah.getId());
            viewModel.updateKandidat(kandidat);
            Toast.makeText(getBaseContext(), "Kandidat Berhasil diubah", Toast.LENGTH_SHORT).show();
            finish();
        });

        hapusButton.setOnClickListener(v -> {
            MyLogger.log("Kandidat ID : " + id);
            if(id > 0) {
                Kandidat kandidat = new Kandidat();
                kandidat.setId(id);
                viewModel.hapusKandidat(kandidat);
                finish();
            }
        });




    }

    private boolean isTextBukanAngka(EditText editText) {
        try {
            int i = Integer.parseInt(editText.getText().toString());
            if(i <= 0)
                return true;
        }catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
}

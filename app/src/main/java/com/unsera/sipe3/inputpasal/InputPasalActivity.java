package com.unsera.sipe3.inputpasal;

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
import com.unsera.sipe3.model.Pasal;

public class InputPasalActivity extends AppCompatActivity {

    private EditText editNamaPasal;
    private EditText editKeterangan;
    private InputPasalViewModel viewModel;
    private long id;

    public static final int REQUEST_INPUT_PASAL = 101;
    public static final int REQUEST_EDIT_PASAL = 102;

    private static final String EXTRA_REQUEST_CODE = "com.unsera.sipe3.inputpasal.extra.requestcode";
    private static final String EXTRA_ID = "com.unsera.sipe3.inputpasal.extra.id";
    private static final String EXTRA_NO_PASAL = "com.unsera.sipe3.inputpasal.extra.nopasal";
    private static final String EXTRA_KETERANGAN = "com.unsera.sipe3.inputpasal.extra.keterangan";

    public static Intent newIntent(Context context, long requestCode) {
        Intent intent = new Intent(context, InputPasalActivity.class);
        intent.putExtra(EXTRA_REQUEST_CODE, requestCode);
        return intent;
    }

    public static Intent newIntent(Context context, long requestCode, long id,  String noPasal, String keterangan) {
        Intent intent = new Intent(context, InputPasalActivity.class);
        intent.putExtra(EXTRA_REQUEST_CODE, requestCode);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_NO_PASAL, noPasal);
        intent.putExtra(EXTRA_KETERANGAN, keterangan);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pasal);


        viewModel = ViewModelProviders.of(this).get(InputPasalViewModel.class);

        editNamaPasal = findViewById(R.id.input_pasal_nama_pasal);
        editKeterangan = findViewById(R.id.input_pasal_keterangan);

        long requestCode = getIntent().getLongExtra(EXTRA_REQUEST_CODE, -1);

        if(requestCode == REQUEST_EDIT_PASAL) {
            id = getIntent().getLongExtra(EXTRA_ID, -1);
            editNamaPasal.setText(getIntent().getStringExtra(EXTRA_NO_PASAL));
            editKeterangan.setText(getIntent().getStringExtra(EXTRA_KETERANGAN));
        }



        Button simpanButton = findViewById(R.id.input_pasal_simpan_button);
        simpanButton.setOnClickListener(v -> {
            if(editNamaPasal.getText().toString().isEmpty() || editKeterangan.getText().toString().isEmpty()) {
                DialogPesan.newInstance("Tambah Pasal Gagal", "Field Tidak Boleh Kosong")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            if(Integer.parseInt(editNamaPasal.getText().toString()) <= 0) {
                DialogPesan.newInstance("Tambah Pasal Gagal", "No Pasal Tidak Valid")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }



            String namaPasal = editNamaPasal.getText().toString();
            String keterangan = editKeterangan.getText().toString();

            if(requestCode == REQUEST_EDIT_PASAL) {
                Pasal pasal = new Pasal(id, namaPasal, keterangan);
                viewModel.updatePasal(pasal);
            }else {
                Pasal pasal = new Pasal(namaPasal, keterangan);
                viewModel.insertPasal(pasal);
            }
            setResult(Activity.RESULT_OK);
            finish();
        });
    }
}

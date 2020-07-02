package com.unsera.sipe3.detailkandidat;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.unsera.sipe3.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailKandidatActivity extends AppCompatActivity {

    private static final String EXTRA_ID = "com.unsera.sipe3.detailkandidat.extra.id";

    private CircleImageView foto;
    private TextView textNoUrut;
    private TextView textNama;
    private TextView textNamaWakil;
    private TextView textDaerah;

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, DetailKandidatActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kandidat);

        foto = findViewById(R.id.detail_kandidat_foto);
        textNoUrut = findViewById(R.id.detail_kandidat_no_urut_text);
        textNama = findViewById(R.id.detail_kandidat_nama_text);
        textNamaWakil = findViewById(R.id.detail_kandidat_nama_wakil_text);
        textDaerah = findViewById(R.id.detail_kandidat_daerah_text);

        DetailKandidatViewModel viewModel = ViewModelProviders.of(this).get(DetailKandidatViewModel.class);

        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        if(id != -1) {
            viewModel.getKandidatView(id).observe(this, kandidatView -> {
                if(kandidatView.getFoto() != null) {
                    foto.setImageBitmap(kandidatView.getFoto());
                }
                textNoUrut.setText(String.valueOf(kandidatView.getNoUrut()));
                textNama.setText(kandidatView.getNama());
                textNamaWakil.setText(kandidatView.getNamaWakil());
                textDaerah.setText("Kota " + kandidatView.getKota() + " / Provinsi " + kandidatView.getProvinsi());
            });
        }
    }
}

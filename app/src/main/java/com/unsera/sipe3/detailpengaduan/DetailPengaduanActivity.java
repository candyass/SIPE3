package com.unsera.sipe3.detailpengaduan;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.unsera.sipe3.R;
import com.unsera.sipe3.model.PasalView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailPengaduanActivity extends AppCompatActivity {

    private static final  String EXTRA_ID = "com.unsera.sipe3.detailpengaduanactivity.extra.id";
    private static final DateFormat sDateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

    private TextView textTanggal;
    private TextView textNama;
    private TextView textNamaWakil;
    private TextView textDaerah1;
    private TextView textDaerah2;
    private TextView textJumlahPasal;
    private RecyclerView recyclerView;
    private TextView textNamaPelapor;
    private TextView textNoKTP;
    private TextView textEmail;
    private ImageView foto;

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, DetailPengaduanActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengaduan);

        DetailPengaduanViewModel viewModel = ViewModelProviders.of(this).get(DetailPengaduanViewModel.class);

        textTanggal = findViewById(R.id.detail_pengaduan_tanggal);
        textNama = findViewById(R.id.detail_pengaduan_nama_text);
        textNamaWakil = findViewById(R.id.detail_pengaduan_nama_wakil_text);
        textDaerah1 = findViewById(R.id.detail_pengaduan_daerah1_text);
        textDaerah2 = findViewById(R.id.detail_pengaduan_daerah2_text);
        textJumlahPasal = findViewById(R.id.detail_pengaduan_jumlah_pasal);
        recyclerView = findViewById(R.id.detail_pengaduan_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        textNamaPelapor = findViewById(R.id.detail_pengaduan_nama_pelapor);
        textNoKTP = findViewById(R.id.detail_pengaduan_no_ktp_pelapor);
        textEmail = findViewById(R.id.detail_pengaduan_email_text);
        foto = findViewById(R.id.detail_pengaduan_foto);


        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        if(id != -1) {
            viewModel.getPengaduanView(id).observe(this, view -> {
                textTanggal.setText(sDateFormat.format(view.getTanggal()));
                textNama.setText(view.getNama());
                textNamaWakil.setText(view.getNamaWakil());
                textDaerah1.setText(view.getProvinsi() + "/" + view.getKota());
                textDaerah2.setText(view.getProvinsi() + "/" + view.getKota());
                textJumlahPasal.setText(String.valueOf(view.getJumlahPasal()) + " Pelanggaran");
                textNamaPelapor.setText(view.getNamaPelapor());
                textNoKTP.setText(view.getNoKTPUUser());
                textEmail.setText(view.getEmail());
                if(view.getFoto() != null) {
                    foto.setImageBitmap(view.getFoto());
                }
            });

            viewModel.getListPasalView(id).observe(this, pasalViews -> {
                recyclerView.setAdapter(new PasalAdapter(pasalViews));
            });
        }
    }

    class PasalHolder extends  RecyclerView.ViewHolder {

        private TextView noPasalTextView;
        private TextView keteranganTextView;

        public PasalHolder(@NonNull View itemView) {
            super(itemView);
            noPasalTextView = itemView.findViewById(R.id.list_pasal_text_no_pasal);
            keteranganTextView = itemView.findViewById(R.id.list_pasal_text_keterangan_pasal);
        }

        public void bindItem(PasalView pasal) {
            noPasalTextView.setText(pasal.getNamaPasal());
            keteranganTextView.setText(pasal.getKeterangan());
        }
    }

    class PasalAdapter extends RecyclerView.Adapter<PasalHolder> {

        private List<PasalView> list;

        public PasalAdapter(List<PasalView> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public PasalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.list_pasal, parent, false);
            PasalHolder holder = new PasalHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull PasalHolder holder, int position) {
            holder.bindItem(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

    }
}

package com.unsera.sipe3.inputpelanggaran;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.unsera.sipe3.R;
import com.unsera.sipe3.inputpengaduan.InputPengaduanViewModel;
import com.unsera.sipe3.model.PasalView;
import com.unsera.sipe3.util.MyLogger;

import java.util.List;

public class InputPelanggaranActivity extends AppCompatActivity {


    private static final String EXTRA_ID = "com.unsera.sipe3.inputpelanggaran.id";

    private TextView textNama;
    private TextView textNamaWakil;
    private TextView textDaerah1;
    private TextView textDaerah2;
    private RecyclerView recyclerView;
    private Button tolakButton;
    private Button terimaButton;
    private TextView textNamaPelapor;
    private TextView textNoKTP;
    private TextView textEmail;
    private ImageView foto;

    private long idPengaduan;


    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, InputPelanggaranActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pelanggaran);

        InputPelanggaranViewModel viewModel = ViewModelProviders.of(this).get(InputPelanggaranViewModel.class);


        textNama = findViewById(R.id.input_pelanggaran_nama_text);
        textNamaWakil = findViewById(R.id.input_pelanggaran_nama_wakil_text);
        textDaerah1 = findViewById(R.id.input_pelanggaran_daerah1_text);
        textDaerah2 = findViewById(R.id.input_pelanggaran_daerah2_text);
        recyclerView = findViewById(R.id.input_pelanggaran_recyclerView);
        tolakButton = findViewById(R.id.input_pelanggaran_total_btn);
        terimaButton = findViewById(R.id.input_pelanggaran_terima_btn);
        textNamaPelapor = findViewById(R.id.input_pelanggaran_nama_pelapor_text);
        textNoKTP = findViewById(R.id.input_pelanggaran_no_ktp);
        textEmail = findViewById(R.id.input_pelanggaran_email_text);
        foto = findViewById(R.id.input_pelanggaran_foto);


        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        idPengaduan = getIntent().getLongExtra(EXTRA_ID, -1);
        if(idPengaduan != -1) {
            viewModel.getPengaduanView(idPengaduan).observe(this, view -> {
                textNama.setText(view.getNama());
                textNamaWakil.setText(view.getNamaWakil());
                textDaerah1.setText(view.getProvinsi() + "/" + view.getKota());
                textDaerah2.setText(view.getProvinsi() + "/" + view.getKota());
                textNamaPelapor.setText(view.getNamaPelapor());
                textNoKTP.setText(view.getNoKTPUUser());
                textEmail.setText(view.getEmail());
                if(view.getFoto() != null) {
                    foto.setImageBitmap(view.getFoto());
                }
            });

            viewModel.getListPasalView(idPengaduan).observe(this, list -> {
                recyclerView.setAdapter(new PasalAdapter(list));
            });
        }

        tolakButton.setOnClickListener(v -> {
            MyLogger.log("idPengaduan : " + idPengaduan);
            viewModel.tolakPengaduan(idPengaduan);
            finish();
        });

        terimaButton.setOnClickListener(v -> {
            MyLogger.log("idPengaduan : " + idPengaduan);
            viewModel.terimaPengaduan(idPengaduan);
            finish();
        });
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

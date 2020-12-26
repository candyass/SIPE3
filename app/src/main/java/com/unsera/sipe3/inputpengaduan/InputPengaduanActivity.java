package com.unsera.sipe3.inputpengaduan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.unsera.sipe3.R;
import com.unsera.sipe3.dialog.DialogKandidatFragment;
import com.unsera.sipe3.dialog.DialogPasalFragment;
import com.unsera.sipe3.dialog.DialogPesan;
import com.unsera.sipe3.event.KandidatEvent;
import com.unsera.sipe3.model.Pasal;
import com.unsera.sipe3.model.Pelanggaran;
import com.unsera.sipe3.model.Pengaduan;
import com.unsera.sipe3.ui.pasal.PasalFragment;
import com.unsera.sipe3.util.MyLogger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class InputPengaduanActivity extends AppCompatActivity {

    private static final String KANDIDAT_DIALOG_TAG =  "com.unsera.sipe3.dialogkandidatfragment";
    private static final String PASAL_DIALOG_TAG = "com.unsera.sipe3.dialogpasalfragment";
    private static final String EXTRA_NO_KTP = "com.unsera.sipe3.inputpengaduanactivity.extra.noktp";

    private static final int PICK_IMAGE = 1001;
    private boolean isFotoDipilih = false;

    private TextView textNama;
    private TextView textNamaWakil;
    private TextView textDaerah1;
    private TextView textDaerah2;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private Button prosesButton;
    private View root;
    private Button uploadButton;
    private ImageView foto;


    private long idKandidat;
    private PasalAdapter pasalAdapter;
    private boolean isPilihKandidat = false;
    private boolean isPilihPasal = false;


    public static Intent newIntent(Context context, String noKTP) {
        Intent intent = new Intent(context, InputPengaduanActivity.class);
        intent.putExtra(EXTRA_NO_KTP, noKTP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pengaduan);

        InputPengaduanViewModel viewModel = ViewModelProviders.of(this).get(InputPengaduanViewModel.class);

        root = findViewById(R.id.input_pengaduan_cardView);
        textNama = findViewById(R.id.input_pengaduan_nama_text);
        textNamaWakil = findViewById(R.id.input_pengaduan_nama_wakil_text);
        textDaerah1 = findViewById(R.id.input_pengaduan_daerah1_text);
        textDaerah2 = findViewById(R.id.input_pengaduan_daerah2_text);
        recyclerView = findViewById(R.id.input_pengaduan_recyclerView);
        fab = findViewById(R.id.input_pengaduan_tambah_fab);
        prosesButton = findViewById(R.id.input_pengaduan_simpan_btn);
        uploadButton = findViewById(R.id.input_pengaduan_upload_button);
        foto = findViewById(R.id.input_pengaduan_upload_foto);

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        pasalAdapter = new PasalAdapter();
        recyclerView.setAdapter(pasalAdapter);

        root.setOnClickListener(v -> {
            DialogFragment dialogFragment = DialogKandidatFragment.newInstance();
            dialogFragment.show(getSupportFragmentManager(), KANDIDAT_DIALOG_TAG);
        });

        fab.setOnClickListener(v -> {
            DialogFragment dialogFragment = DialogPasalFragment.newInstance();
            dialogFragment.show(getSupportFragmentManager(), PASAL_DIALOG_TAG);
        });

        uploadButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Upload Bukti Pengaduan"), PICK_IMAGE);
        });

        prosesButton.setOnClickListener(v -> {

            if(!isPilihKandidat) {
                DialogPesan.newInstance("Pengaduan Gagal", "Kandidat Belum Dipilih")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }
            if(!isPilihPasal) {
                DialogPesan.newInstance("Pengaduan Gagal", "Pelanggaran Belum Dipilih")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            if(!isFotoDipilih) {
                DialogPesan.newInstance("Pengaduan Gagal", "Bukti Laporan Belum Diupload")
                        .show(getSupportFragmentManager(), DialogPesan.PESAN_DIALOG_TAG);
                return;
            }

            // tambahin nanti
            String noKTP = getIntent().getStringExtra(EXTRA_NO_KTP);
            MyLogger.log("NO KTP : " + noKTP);

            foto.invalidate();
            BitmapDrawable drawable = (BitmapDrawable) foto.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            Pengaduan pengaduan = new Pengaduan(idKandidat, noKTP);
            if(bitmap != null) {
                MyLogger.log("Bitmap is not null");
                pengaduan.setFoto(bitmap);
            }else {
                MyLogger.log("Bitmap is null");
            }
            List<Pelanggaran> listPelanggaran = new ArrayList<>();
            for(Pasal p : pasalAdapter.list) {
                listPelanggaran.add(new Pelanggaran(p.getId()));
            }
            viewModel.pengajuanPengaduan(pengaduan, listPelanggaran);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                Uri uri = data.getData();
                Picasso.get().load(uri).error(R.drawable.debate).into(foto);
                isFotoDipilih = true;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventKandidat(KandidatEvent event) {
        idKandidat = event.getId();
        textNama.setText(event.getNama());
        textNamaWakil.setText(event.getNamaWakil());
        textDaerah1.setText(event.getProvinsi() + "/" + event.getKota());
        textDaerah2.setText(event.getProvinsi() + "/" + event.getKota());
        isPilihKandidat = true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventPasal(Pasal pasal) {
        pasalAdapter.addItem(pasal);
        isPilihPasal = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    class PasalHolder extends  RecyclerView.ViewHolder {

        private TextView noPasalTextView;
        private TextView keteranganTextView;

        public PasalHolder(@NonNull View itemView) {
            super(itemView);
            noPasalTextView = itemView.findViewById(R.id.list_pasal_text_no_pasal);
            keteranganTextView = itemView.findViewById(R.id.list_pasal_text_keterangan_pasal);
        }

        public void bindItem(Pasal pasal) {
            noPasalTextView.setText(pasal.getNamaPasal());
            keteranganTextView.setText(pasal.getKeterangan());
        }
    }

    class PasalAdapter extends RecyclerView.Adapter<PasalHolder> {

        private List<Pasal> list;

        public PasalAdapter() {
            list = new ArrayList<>();
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

        public void addItem(Pasal pasal) {
            if(!list.contains(pasal)) {
                list.add(pasal);
                notifyDataSetChanged();
            }
        }
    }
}

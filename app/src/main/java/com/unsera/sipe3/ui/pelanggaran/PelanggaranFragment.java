package com.unsera.sipe3.ui.pelanggaran;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unsera.sipe3.R;
import com.unsera.sipe3.detailpengaduan.DetailPengaduanActivity;
import com.unsera.sipe3.model.Pengaduan;
import com.unsera.sipe3.model.PengaduanView;

import java.text.DateFormat;
import java.util.List;

public class PelanggaranFragment extends Fragment {

    private ViewSwitcher viewSwitcher;
    private TextView textJudul;
    private FloatingActionButton fab;
    private ViewGroup stub;
    private TextView textHeader;
    private RecyclerView recyclerView;

    private static final DateFormat sDateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PelanggaranViewModel pelanggaranViewModel =
                ViewModelProviders.of(this).get(PelanggaranViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pelanggaran, container, false);

        viewSwitcher = root.findViewById(R.id.content_list_switcher);
        textJudul = root.findViewById(R.id.content_list_text);
        stub = root.findViewById(R.id.content_list_stub);
        textHeader = stub.findViewById(R.id.content_list_header);
        recyclerView = stub.findViewById(R.id.content_list_recyclerView);

        textHeader.setText("Daftar Pelanggaran");
        textJudul.setText(getResources().getString(R.string.view_title_pelanggaran));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        pelanggaranViewModel.getListPengaduanViewTerpilih().observe(this, list -> {
            if(list.size() > 0) {
                recyclerView.setAdapter(new PengaduanViewAdapter(list));
                tampilkanData(true);
            }
        });


        return root;
    }

    private void tampilkanData(boolean value) {
        if(value) {
            if(viewSwitcher.getNextView().getId() == R.id.content_list_stub) {
                viewSwitcher.showNext();
            }
        }
    }


    class PengaduanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textTanggal;
        private TextView textNama;
        private TextView textNamaWakil;
        private TextView textStatus;
        private TextView textJumlahPasal;
        private TextView textDaerah1;
        private TextView textDaerah2;
        private TextView textNamaPelapor;
        private TextView textNoKTP;

        private PengaduanView pengaduanView;

        public PengaduanViewHolder(@NonNull View itemView) {
            super(itemView);
            textTanggal = itemView.findViewById(R.id.list_pengaduan_tanggal_text);
            textNama = itemView.findViewById(R.id.list_pengaduan_nama_text);
            textNamaWakil = itemView.findViewById(R.id.list_pengaduan_nama_wakil_text);
            textJumlahPasal = itemView.findViewById(R.id.list_pengaduan_pasal_text);
            textStatus = itemView.findViewById(R.id.list_pengaduan_status_text);
            textDaerah1 = itemView.findViewById(R.id.list_pengaduan_daerah1_text);
            textDaerah2 = itemView.findViewById(R.id.list_pengaduan_daerah2_text);
            textNamaPelapor = itemView.findViewById(R.id.list_pengaduan_nama_pelapor);
            textNoKTP = itemView.findViewById(R.id.list_pengaduan_no_ktp);
            itemView.setOnClickListener(this);

        }

        public void bindItem(PengaduanView view) {
            this.pengaduanView = view;
            textTanggal.setText(sDateFormat.format(pengaduanView.getTanggal()));
            textNama.setText(pengaduanView.getNama());
            textNamaWakil.setText(pengaduanView.getNamaWakil());
            textJumlahPasal.setText("Jumlah Pasal : " + pengaduanView.getJumlahPasal());
            textStatus.setText(pengaduanView.getStatus());
            textNamaPelapor.setText(pengaduanView.getNamaPelapor());
            textNoKTP.setText(pengaduanView.getNoKTP());
            textDaerah1.setText(view.getProvinsi() + "/" + view.getKota());
            textDaerah2.setText(view.getProvinsi() + "/" + view.getKota());
            if(view.getPidana() == Pengaduan.PIDANA_PROSES) {
                textStatus.setTextColor(getResources().getColor(R.color.colorDiproses));
            }else if(view.getPidana() == Pengaduan.PIDANA_DITOLAK) {
                textStatus.setTextColor(getResources().getColor(R.color.colorDitolak));
            }else if(view.getPidana() == Pengaduan.PIDANA_DITERIMA) {
                textStatus.setTextColor(getResources().getColor(R.color.colorDiterima));
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = DetailPengaduanActivity.newIntent(getContext(), pengaduanView.getId());
            startActivity(intent);
        }
    }

    class PengaduanViewAdapter extends RecyclerView.Adapter<PengaduanViewHolder> {

        private List<PengaduanView> list;

        public PengaduanViewAdapter(List<PengaduanView> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public PengaduanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.list_pengaduan, parent, false);
            PengaduanViewHolder holder = new PengaduanViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull PengaduanViewHolder holder, int position) {
            holder.bindItem(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
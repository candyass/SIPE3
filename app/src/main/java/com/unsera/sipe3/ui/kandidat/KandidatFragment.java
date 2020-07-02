package com.unsera.sipe3.ui.kandidat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unsera.sipe3.R;
import com.unsera.sipe3.admin.Admin2Activity;
import com.unsera.sipe3.admin.AdminActivity;
import com.unsera.sipe3.detailkandidat.DetailKandidatActivity;
import com.unsera.sipe3.inputkandidat.InputKandidatActivity;
import com.unsera.sipe3.model.KandidatView;

import java.util.List;

public class KandidatFragment extends Fragment {

    private KandidatViewModel kandidatViewModel;
    private ViewGroup stub;
    private RecyclerView recyclerView;
    private KandidatAdapter adapter;
    private ViewSwitcher viewSwitcher;
    private TextView textJudul;
    private TextView textHeader;



    @SuppressLint("RestrictedApi")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kandidatViewModel =
                ViewModelProviders.of(this).get(KandidatViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kandidat, container, false);


        viewSwitcher = root.findViewById(R.id.content_list_switcher);
        textJudul = root.findViewById(R.id.content_list_text);
        FloatingActionButton fab = root.findViewById(R.id.fragment_kandidat_fab);
        stub = root.findViewById(R.id.content_list_stub);
        textHeader = stub.findViewById(R.id.content_list_header);
        recyclerView = stub.findViewById(R.id.content_list_recyclerView);


        textJudul.setText(getResources().getString(R.string.view_title_kandidat));
        textHeader.setText("Daftar Kandidat");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = InputKandidatActivity.newIntent(getContext());
                startActivity(intent);
            }
        });

        kandidatViewModel.getAllKandidat().observe(this, kandidats -> {
            if(kandidats != null) {
                adapter = new KandidatAdapter(kandidats);
                recyclerView.setAdapter(adapter);
                tampilkanData(true);
            }
        });

        // check user pemohon
        if(getActivity() instanceof Admin2Activity) {
            fab.setVisibility(View.GONE);
        }


        return root;
    }



    private void tampilkanData(boolean value) {
        if(value) {
            if(viewSwitcher.getNextView().getId() == R.id.content_list_stub) {
                viewSwitcher.showNext();
            }
        }
    }


    class KandidatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textNama;
        private TextView textNamaWakil;
        private TextView textNoUrut;
        private TextView textDaerah1;
        private TextView textDaerah2;
        private KandidatView view;


        public KandidatViewHolder(@NonNull View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.list_data_nama_text);
            textNamaWakil = itemView.findViewById(R.id.list_data_nama_wakil_text);
            textNoUrut = itemView.findViewById(R.id.list_data_noUrut_text);
            textDaerah1 = itemView.findViewById(R.id.list_data_daerah_text1);
            textDaerah2 = itemView.findViewById(R.id.list_data_daerah_text2);
            itemView.setOnClickListener(this);
        }

        public void setModel(KandidatView kandidatView) {
            view = kandidatView;
            textNama.setText(view.getNama());
            textNamaWakil.setText(view.getNamaWakil());
            textNoUrut.setText("Pasangan No Urut " + view.getNoUrut());
            textDaerah1.setText(view.getProvinsi() + "/" + view.getKota());
            textDaerah2.setText(view.getProvinsi() + "/" + view.getKota());
        }

        @Override
        public void onClick(View v) {
            Intent intent = DetailKandidatActivity.newIntent(getContext(), view.getId());
            startActivity(intent);
        }
    }

    class KandidatAdapter extends RecyclerView.Adapter<KandidatViewHolder> {

        private List<KandidatView> list;

        public KandidatAdapter(List<KandidatView> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public KandidatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View root = getLayoutInflater().inflate(R.layout.list_data, parent, false);
            KandidatViewHolder holder = new KandidatViewHolder(root);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull KandidatViewHolder holder, int position) {
            holder.setModel(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
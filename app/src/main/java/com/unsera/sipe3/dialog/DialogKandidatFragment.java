package com.unsera.sipe3.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.unsera.sipe3.R;
import com.unsera.sipe3.event.KandidatEvent;
import com.unsera.sipe3.model.KandidatView;
import com.unsera.sipe3.util.MyLogger;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class DialogKandidatFragment extends DialogFragment {


    public static DialogFragment newInstance() {
        DialogFragment dialog = new DialogKandidatFragment();
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DialogKandidatViewModel viewModel = ViewModelProviders.of(this).get(DialogKandidatViewModel.class);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_pengaduan,null);
        RecyclerView recyclerView = view.findViewById(R.id.dialog_recylcerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getAllKandidat().observe(this, kandidatViews -> {
            if(!kandidatViews.isEmpty()) {
                MyLogger.log(String.valueOf(kandidatViews.size()));
                recyclerView.setAdapter(new KandidatViewAdapter(kandidatViews));
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Daftar Kandidat");
        builder.setMessage("Pilih Kandidat");
        builder.setIcon(R.drawable.kpu_logo);
        builder.setView(view);
        return builder.create();
    }

    class KandidatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textNama;
        private TextView textNamaWakil;
        private TextView textDaerah1;
        private TextView textDaerah2;
        private TextView textNoUrut;

        private KandidatView kandidatView;

        public KandidatViewHolder(@NonNull View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.list_data_nama_text);
            textNamaWakil = itemView.findViewById(R.id.list_data_nama_wakil_text);
            textDaerah1 = itemView.findViewById(R.id.list_data_daerah_text1);
            textDaerah2 = itemView.findViewById(R.id.list_data_daerah_text2);
            textNoUrut = itemView.findViewById(R.id.list_data_noUrut_text);
            itemView.setOnClickListener(this);
        }

        public void bindModel(KandidatView kandidatView) {
            this.kandidatView = kandidatView;
            textNama.setText(this.kandidatView.getNama());
            textNamaWakil.setText(this.kandidatView.getNamaWakil());
            textDaerah1.setText(this.kandidatView.getProvinsi() + "/" + kandidatView.getKota());
            textDaerah2.setText(this.kandidatView.getProvinsi() + "/" + kandidatView.getKota());
            textNoUrut.setText("No Urut " + kandidatView.getNoUrut());
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new KandidatEvent(kandidatView.getId(), kandidatView.getNama(),
                    kandidatView.getNamaWakil(), kandidatView.getProvinsi(), kandidatView.getKota()));
            dismiss();
        }
    }

    class KandidatViewAdapter extends RecyclerView.Adapter<KandidatViewHolder> {

        private List<KandidatView> list;

        public KandidatViewAdapter(List<KandidatView> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public KandidatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.list_data, parent, false);
            KandidatViewHolder holder = new KandidatViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull KandidatViewHolder holder, int position) {
            holder.bindModel(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}

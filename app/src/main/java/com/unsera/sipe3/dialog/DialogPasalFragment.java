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
import com.unsera.sipe3.model.Pasal;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class DialogPasalFragment extends DialogFragment {

    public static DialogFragment newInstance()  {
        DialogFragment fragment = new DialogPasalFragment();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DialogPasalViewModel model = ViewModelProviders.of(this).get(DialogPasalViewModel.class);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_pengaduan, null);
        RecyclerView recyclerView = view.findViewById(R.id.dialog_recylcerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        model.getAllPasal().observe(this, list -> {
            if(!list.isEmpty()) {
                recyclerView.setAdapter(new PasalAdapter(list));
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Daftar Pelanggaran");
        builder.setMessage("Pilih Pelanggaran");
        builder.setIcon(R.drawable.kpu_logo);
        builder.setView(view);
        return builder.create();
    }

    class PasalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView noPasalTextView;
        private TextView keteranganTextView;
        private Pasal pasal;

        public PasalHolder(@NonNull View itemView) {
            super(itemView);
            noPasalTextView = itemView.findViewById(R.id.list_pasal_text_no_pasal);
            keteranganTextView = itemView.findViewById(R.id.list_pasal_text_keterangan_pasal);
            itemView.setOnClickListener(this);
        }

        public void bindItem(Pasal pasal) {
            this.pasal = pasal;
            noPasalTextView.setText(this.pasal.getNamaPasal());
            keteranganTextView.setText(this.pasal.getKeterangan());
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(pasal);
            dismiss();
        }
    }


    class PasalAdapter extends RecyclerView.Adapter<PasalHolder> {

        private List<Pasal> list;

        public PasalAdapter(List<Pasal> list) {
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

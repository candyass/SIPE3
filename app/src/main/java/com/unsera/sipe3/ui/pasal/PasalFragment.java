package com.unsera.sipe3.ui.pasal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.unsera.sipe3.R;
import com.unsera.sipe3.admin.AdminActivity;
import com.unsera.sipe3.inputpasal.InputPasalActivity;
import com.unsera.sipe3.model.Pasal;

import java.util.List;

public class PasalFragment extends Fragment {

    private FloatingActionButton fab;
    private CoordinatorLayout root;

    PasalViewModel viewModel;
    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this).get(PasalViewModel.class);

        View v = inflater.inflate(R.layout.fragment_pasal, container, false);
        root = v.findViewById(R.id.fragment_pasal_cordinator);
        TextView textHeader = v.findViewById(R.id.content_list_header);
        textHeader.setText("Daftar Pasal");
        fab = v.findViewById(R.id.fragment_pasal_fab);
        RecyclerView recyclerView = v.findViewById(R.id.content_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getAllPasal().observe(this, list -> {
            if(!list.isEmpty()) {
                PasalAdapter adapter = new PasalAdapter(list);
                recyclerView.setAdapter(adapter);
            }
        });

        // check user pemohon
        if(isPelapor()) {
            fab.setVisibility(View.GONE);
        }

        fab.setOnClickListener(v1 -> {
            Intent intent = InputPasalActivity.newIntent(getContext(), InputPasalActivity.REQUEST_INPUT_PASAL);
            startActivityForResult(intent, InputPasalActivity.REQUEST_INPUT_PASAL);
        });


        return v;
    }

    private boolean isPelapor() {
        if(getActivity() instanceof AdminActivity) {
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == InputPasalActivity.REQUEST_INPUT_PASAL) {
            Snackbar.make(root, "Pasal Ditambahkan", Snackbar.LENGTH_SHORT).show();
        }else if(resultCode == Activity.RESULT_OK && requestCode == InputPasalActivity.REQUEST_EDIT_PASAL) {
            Snackbar.make(root, "Pasal Diubah", Snackbar.LENGTH_SHORT).show();
        }
    }

    class PasalHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

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
            noPasalTextView.setText("Pasal " + this.pasal.getNamaPasal());
            keteranganTextView.setText(this.pasal.getKeterangan());
        }

        @Override
        public void onClick(View v) {
            if(!isPelapor()) {
                Intent intent = InputPasalActivity.newIntent(getContext(), InputPasalActivity.REQUEST_EDIT_PASAL, pasal.getId(), pasal.getNamaPasal(), pasal.getKeterangan());
                startActivityForResult(intent, InputPasalActivity.REQUEST_EDIT_PASAL);
            }
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

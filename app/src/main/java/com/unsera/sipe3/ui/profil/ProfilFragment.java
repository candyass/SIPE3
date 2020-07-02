package com.unsera.sipe3.ui.profil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.unsera.sipe3.R;
import com.unsera.sipe3.admin.AdminActivity;

public class ProfilFragment extends Fragment {

    private ProfilViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profil, container, false);

        viewModel = ViewModelProviders.of(this).get(ProfilViewModel.class);

        AdminActivity activity = (AdminActivity) getActivity();
        String noKTP = activity.getNoKTP();

        TextView textNoKTP = root.findViewById(R.id.profil_no_ktp_text);
        TextView textNama = root.findViewById(R.id.profil_nama_text);
        TextView textEmail = root.findViewById(R.id.profil_email_text);

        viewModel.getUser(noKTP).observe(this, user -> {
            textNoKTP.setText(user.getNoKTP());
            textNama.setText(user.getNama());
            textEmail.setText(user.getEmail());
        });


        return root;
    }
}

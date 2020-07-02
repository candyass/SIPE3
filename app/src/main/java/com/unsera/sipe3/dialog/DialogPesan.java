package com.unsera.sipe3.dialog;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.unsera.sipe3.R;

public class DialogPesan extends DialogFragment {

    private String judulText;
    private String pesanText;

    public static final String PESAN_DIALOG_TAG = "com.unsera.sipe3.pesandialog";

    public static DialogFragment newInstance(String judulText, String pesanText) {
        DialogPesan dialogFragment = new DialogPesan();
        dialogFragment.judulText = judulText;
        dialogFragment.pesanText = pesanText;
        return dialogFragment;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(judulText);
        builder.setMessage(pesanText);
        builder.setIcon(R.drawable.kpu_logo);
        builder.setPositiveButton("Ok Siip", null);
        return builder.create();
    }


}

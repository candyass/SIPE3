package com.unsera.sipe3.ui.hasil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.unsera.sipe3.R;
import com.unsera.sipe3.model.KandidatChart;
import com.unsera.sipe3.util.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class HasilFragment extends Fragment {

    private BarChart barChart;
    private LinearLayout linearLayout;
    private HasilViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hasil, container, false);

        viewModel = ViewModelProviders.of(this).get(HasilViewModel.class);


        barChart = root.findViewById(R.id.fragment_hasil_chart);
        linearLayout = root.findViewById(R.id.fragment_hasil_linear_layout);

        viewModel.getChartKandidat().observe(this, kandidatCharts -> {
            ArrayList<BarEntry> listEntry = new ArrayList<>();
            ArrayList<String> listTitle = new ArrayList<>();
            int index = 1;
            for(KandidatChart chart : kandidatCharts) {

                CardView kandidatItem = (CardView) inflater.inflate(R.layout.list_hasil_pengaduan, null);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 0, 0, 16);
                kandidatItem.setLayoutParams(params);
                bindItemKandidat(kandidatItem, chart, index);
                linearLayout.addView(kandidatItem);

                listTitle.add("Kandidat " + index);
                listEntry.add(new BarEntry(chart.getTotalAduan(), index - 1));
                MyLogger.log("Total Aduan : " + chart.getTotalAduan());
                MyLogger.log("Index : " + index);
                index++;
            }

            BarDataSet barDataSet = new BarDataSet(listEntry, "List Kandidat");
            barChart.animateY(5000);
            BarData barData = new BarData( listTitle, barDataSet);
            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            barChart.setData(barData);


        });


        return root;
    }

    private void bindItemKandidat(View kandidatItem, KandidatChart chart, int index) {
        TextView textNoKandidat = kandidatItem.findViewById(R.id.list_hasil_pengaduan_no_kandidat);
        TextView textNama = kandidatItem.findViewById(R.id.list_hasil_pengaduan_nama_text);
        TextView textNamaWakil = kandidatItem.findViewById(R.id.list_hasil_pengaduan_nama_wakil_text);
        TextView textDaerah1 = kandidatItem.findViewById(R.id.list_hasil_pengaduan_daerah_1);
        TextView textDaerah2 = kandidatItem.findViewById(R.id.list_hasil_pengaduan_daerah_2);
        TextView textTotal = kandidatItem.findViewById(R.id.list_hasil_pengaduan_total_text);
        TextView textStatus = kandidatItem.findViewById(R.id.list_hasil_pengaduan_status_text);

        textNoKandidat.setText("Kandidat " + index);
        textNama.setText(chart.getNama());
        textNamaWakil.setText(chart.getNamaWakil());
        textDaerah1.setText(chart.getProvinsi() + "/" + chart.getKota());
        textDaerah2.setText(chart.getProvinsi() + "/" + chart.getKota());
        textTotal.setText(String.valueOf(chart.getTotalAduan()) + " Pengaduan");
        textStatus.setText(chart.getStatus());
    }


}

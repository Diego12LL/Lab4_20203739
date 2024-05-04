package com.example.lab4_20203739;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ClimaViewHolder> {

    private List<ClimaInfo> climaList;

    public ClimaAdapter() {
        this.climaList = new ArrayList<>();
    }

    public void setClimaList(List<ClimaInfo> climaList) {
        this.climaList = climaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClimaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_clima, parent, false);
        return new ClimaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClimaViewHolder holder, int position) {
        ClimaInfo climaInfo = climaList.get(position);
        holder.bind(climaInfo);
    }

    @Override
    public int getItemCount() {
        return climaList.size();
    }

    static class ClimaViewHolder extends RecyclerView.ViewHolder {
        private TextView cityNameTextView;
        private TextView temperatureTextView;
        private TextView tempMinTextView;
        private TextView tempMaxTextView;
        private TextView vientoTextView;

        public ClimaViewHolder(@NonNull View itemView) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.cityNameTextView);
            temperatureTextView = itemView.findViewById(R.id.temperaturaTextView);
            tempMinTextView = itemView.findViewById(R.id.minTempTextView);
            tempMaxTextView = itemView.findViewById(R.id.maxTempTextView);
            vientoTextView = itemView.findViewById(R.id.vientoTextView);
        }

        public void bind(ClimaInfo climaInfo) {
            cityNameTextView.setText(climaInfo.getCityName());
            temperatureTextView.setText("Temperatura: " + climaInfo.getTemperature() + " °C");
            tempMinTextView.setText("Min: " + climaInfo.getTempMin() + " °C");
            tempMaxTextView.setText("Max: " + climaInfo.getTempMax() + " °C");
            vientoTextView.setText("Viento: " + climaInfo.getWindDirection());
        }
    }
}

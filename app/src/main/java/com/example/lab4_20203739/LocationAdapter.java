package com.example.lab4_20203739;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20203739.LocationInfo;
import com.example.lab4_20203739.R;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<LocationInfo> locationList;

    public void setLocationList(List<LocationInfo> locationList) {
        this.locationList = locationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_geolocalizaciones, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        if (locationList != null && position < locationList.size()) {
            LocationInfo locationInfo = locationList.get(position);
            holder.bind(locationInfo);
        }
    }

    @Override
    public int getItemCount() {
        return locationList != null ? locationList.size() : 0;
    }

    static class LocationViewHolder extends RecyclerView.ViewHolder {
        private TextView cityNameTextView;
        private TextView latTextView;
        private TextView lonTextView;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.cityNameTextView);
            latTextView = itemView.findViewById(R.id.latTextView);
            lonTextView = itemView.findViewById(R.id.lonTextView);
        }

        public void bind(LocationInfo locationInfo) {
            cityNameTextView.setText(locationInfo.getCityName());
            latTextView.setText("Latitud: " + locationInfo.getLatitude());
            lonTextView.setText("Longitud: " + locationInfo.getLongitude());
        }
    }
}


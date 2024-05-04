package com.example.lab4_20203739;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab4_20203739.ClimaAdapter;
import com.example.lab4_20203739.ClimaInfo;
import com.example.lab4_20203739.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClimaFragment extends Fragment {
    private Button geolocationButton;
    private Button climateButton;
    private EditText searchLatitud;
    private EditText searchLongitud;
    private RecyclerView recyclerView;
    private ClimaAdapter climaAdapter;
    private RequestQueue requestQueue;

    private static final String API_KEY = "792edf06f1f5ebcaf43632b55d8b03fe";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clima_fragment, container, false);

        requestQueue = Volley.newRequestQueue(requireContext());
        searchLatitud = view.findViewById(R.id.searchLatitud);
        searchLongitud = view.findViewById(R.id.searchLongitud);
        Button searchButton = view.findViewById(R.id.searchButton);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        climaAdapter = new ClimaAdapter();
        recyclerView.setAdapter(climaAdapter);

        geolocationButton = view.findViewById(R.id.geolocationButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitud = searchLatitud.getText().toString().trim();
                String longitud = searchLongitud.getText().toString().trim();
                if (!latitud.isEmpty() && !longitud.isEmpty()) {
                    fetchData(latitud, longitud);
                } else {
                    Toast.makeText(requireContext(), "Ingresa la latitud y longitud", Toast.LENGTH_SHORT).show();
                }
            }
        });


        geolocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GeoFragment geoFragment = new GeoFragment();


                getParentFragmentManager().beginTransaction()
                        .replace(R.id.climaFragment, geoFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void fetchData(String latitud, String longitud) {
        String url = API_URL + "?lat=" + latitud + "&lon=" + longitud + "&appid=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String cityName = response.has("name") ? response.getString("name") : "Desconocido";
                            double temperature = response.getJSONObject("main").getDouble("temp");
                            double tempMin = response.getJSONObject("main").getDouble("temp_min");
                            double tempMax = response.getJSONObject("main").getDouble("temp_max");
                            String windDegree = response.getString("wind");

                            ClimaInfo climaInfo = new ClimaInfo(cityName, temperature, tempMin, tempMax, windDegree);
                            List<ClimaInfo> climaList = new ArrayList<>();
                            climaList.add(climaInfo);

                            climaAdapter.setClimaList(climaList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(requireContext(), "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ClimaFragment", "Error en la solicitud: " + error.getMessage());
                        Toast.makeText(requireContext(), "Error en la solicitud", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(request);
    }
}


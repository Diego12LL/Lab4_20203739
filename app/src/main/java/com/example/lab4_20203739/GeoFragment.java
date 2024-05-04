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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab4_20203739.LocationInfo;
import com.example.lab4_20203739.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GeoFragment extends Fragment {

    private EditText searchEditText;
    private RecyclerView recyclerView;
    private LocationAdapter locationAdapter;
    private RequestQueue requestQueue;

    private static final String API_KEY = "8dd6fc3be19ceb8601c2c3e811c16cf1";
    private static final String API_URL = "https://api.openweathermap.org/geo/1.0/direct";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.geolocalizacion_fragment, container, false);

        requestQueue = Volley.newRequestQueue(requireContext());
        searchEditText = view.findViewById(R.id.searchEditText);
        Button searchButton = view.findViewById(R.id.searchButton);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        locationAdapter = new LocationAdapter();
        recyclerView.setAdapter(locationAdapter);

        // Botón "Buscar"
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = searchEditText.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    fetchData(cityName);
                } else {
                    Toast.makeText(requireContext(), "Por favor ingresa una ciudad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón "Geolocalización"
        Button geolocationButton = view.findViewById(R.id.geolocationButton);
        geolocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes realizar alguna acción si es necesario
                Toast.makeText(requireContext(), "Botón de Geolocalización presionado", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón "Clima"
        Button climateButton = view.findViewById(R.id.climateButton);
        climateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar al fragmento ClimaFragment
                navigateToClimaFragment();
            }
        });

        return view;
    }

    private void fetchData(String cityName) {
        String url = API_URL + "?q=" + cityName + "&limit=1&appid=" + API_KEY;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<LocationInfo> locationList = new ArrayList<>();

                        try {
                            if (response.length() > 0) {
                                JSONObject location = response.getJSONObject(0);
                                String name = location.getString("name");
                                double lat = location.getDouble("lat");
                                double lon = location.getDouble("lon");

                                // Crear objeto LocationInfo y agregarlo a la lista
                                LocationInfo locationInfo = new LocationInfo(name, lat, lon);
                                locationList.add(locationInfo);

                                // Actualizar el adaptador con la nueva lista de ubicaciones
                                locationAdapter.setLocationList(locationList);
                            } else {
                                Toast.makeText(requireContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(requireContext(), "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("GeoFragment", "Error en la solicitud: " + error.getMessage());
                        Toast.makeText(requireContext(), "Error en la solicitud", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(request);
    }

    private void navigateToClimaFragment() {
        // Crear instancia del ClimaFragment
        ClimaFragment climaFragment = new ClimaFragment();

        // Reemplazar el fragmento actual con ClimaFragment
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.geoFragment, climaFragment);
        transaction.addToBackStack(null); // Permite regresar al fragmento anterior
        transaction.commit();
    }
}

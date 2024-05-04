package com.example.lab4_20203739;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button enterButton = findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abrir el fragmento de geolocalización
                openGeoFragment();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Verificar la conexión a Internet y mostrar el diálogo si no hay conexión
        if (!isConnected()) {
            showNoInternetDialog();
        }
    }

    private void openGeoFragment() {
        // Abrir el fragmento GeoFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new GeoFragment())
                .addToBackStack(null)
                .commit();
    }

    private boolean isConnected() {
        // Verificar la conexión a Internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void showNoInternetDialog() {
        // Mostrar el diálogo de conexión a Internet
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No se pudo establecer la conexión con Internet")
                .setMessage("Verifique su conexión e inténtelo de nuevo.")
                .setPositiveButton("Configuración", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS));  // Abrir configuración
                    }
                })
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();   // Cerrar la aplicación
                    }
                })
                .setCancelable(false) // Impide que se cierre el diálogo hasta que se seleccione una opción
                .show();
    }
}

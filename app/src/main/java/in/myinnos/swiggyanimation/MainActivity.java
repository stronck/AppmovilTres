package in.myinnos.swiggyanimation;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Actividad principal de la aplicación.
 *
 * Obtiene los datos desde una fuente HTTP y los presenta
 * mediante un RecyclerView.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    RecyclerAdapter adpater;

    @SuppressLint({"SetTextI18n", "NewApi"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Carga el diseño principal de la actividad.
        setContentView(R.layout.activity_main);

        // Vincula las vistas declaradas con ButterKnife.
        ButterKnife.bind(this);

        // Configura el título de la aplicación en la barra superior.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }

        // Inicializa el adaptador mientras se obtiene la información remota.
        adpater = new RecyclerAdapter(
                getApplicationContext(),
                new ArrayList<DataModel>()
        );

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adpater);

        // Conserva la animación de los iconos al desplazar la lista.
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(
                    RecyclerView recyclerView,
                    int newState
            ) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(
                    RecyclerView recyclerView,
                    int dx,
                    int dy
            ) {
                super.onScrolled(recyclerView, dx, dy);
                adpater.recyclerViewScrolled(recyclerView, dy);
            }
        });

        // Consulta los datos del servidor en segundo plano.
        new LoadFoodTask().execute();
    }

    /**
     * Tarea que realiza el acceso HTTP sin bloquear la interfaz.
     */
    private class LoadFoodTask
            extends AsyncTask<Void, Void, List<DataModel>> {

        @Override
        protected List<DataModel> doInBackground(Void... voids) {
            try {
                return new FoodApiService().getFoods();
            } catch (Exception exception) {
                return new ArrayList<DataModel>();
            }
        }

        @Override
        protected void onPostExecute(List<DataModel> foods) {
            super.onPostExecute(foods);

            // Actualiza el adaptador con la información recibida.
            adpater = new RecyclerAdapter(
                    getApplicationContext(),
                    foods
            );

            recyclerView.setAdapter(adpater);
        }
    }
}

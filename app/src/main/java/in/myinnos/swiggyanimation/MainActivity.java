package in.myinnos.swiggyanimation;

import android.annotation.SuppressLint;
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
 * Muestra una lista de alimentos y conserva la animación
 * de los elementos mientras el usuario realiza scroll.
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

        // Crea el adaptador con la lista de alimentos de ejemplo.
        adpater = new RecyclerAdapter(
                getApplicationContext(),
                getSampleFoodList()
        );

        // Configura el RecyclerView para mostrar los elementos verticalmente.
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
    }

    /**
     * Genera los datos que se muestran en la lista.
     * Se conservan los 100 elementos originales de la aplicación.
     */
    private List getSampleFoodList() {
        ArrayList list = new ArrayList();

        int i = 1;

        for (int j = 100; i <= j; ++i) {
            list.add(
                    new DataModel(
                            "Food " + i,
                            "Category " + i,
                            "Offer " + i
                    )
            );
        }

        return list;
    }
}
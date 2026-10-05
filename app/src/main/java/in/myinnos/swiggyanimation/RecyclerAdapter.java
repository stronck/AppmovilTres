package in.myinnos.swiggyanimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adaptador encargado de conectar los datos con cada elemento
 * visual del RecyclerView.
 */
public class RecyclerAdapter
        extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<DataModel> foodList;
    private DataModel food;

    RecyclerAdapter(Context context, List<DataModel> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i
    ) {
        // Infla el diseño XML de cada elemento de la lista.
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int i
    ) {
        // Obtiene los datos correspondientes a la posición actual.
        food = foodList.get(i);

        // Muestra los datos en los controles de la interfaz.
        holder.txCategory.setText(food.getCategory());
        holder.tvTitle.setText(food.getTitle());
        holder.txOffer.setText(food.getOffer());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    /**
     * Mantiene las referencias de las vistas de cada elemento.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imOffer)
        ImageView imOffer;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.txCategory)
        TextView txCategory;

        @BindView(R.id.txOffer)
        TextView txOffer;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Vincula las vistas del elemento con ButterKnife.
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * Rota el icono de oferta de los elementos visibles
     * según el desplazamiento realizado por el usuario.
     */
    void recyclerViewScrolled(
            RecyclerView recyclerView,
            int scrollAmount
    ) {
        RecyclerAdapter.ViewHolder holder = null;

        LinearLayoutManager layoutManager =
                (LinearLayoutManager) recyclerView.getLayoutManager();

        int starPos = layoutManager.findFirstVisibleItemPosition();
        int lastPos = layoutManager.findLastVisibleItemPosition();

        if (starPos <= lastPos) {
            while (true) {
                holder = (RecyclerAdapter.ViewHolder)
                        recyclerView.findViewHolderForLayoutPosition(starPos);

                if (holder != null) {
                    holder.imOffer.setRotation(
                            holder.imOffer.getRotation()
                                    + (float) scrollAmount
                    );
                }

                if (starPos == lastPos) {
                    break;
                }

                ++starPos;
            }
        }
    }
}
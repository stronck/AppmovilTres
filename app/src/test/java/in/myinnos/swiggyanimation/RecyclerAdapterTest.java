package in.myinnos.swiggyanimation;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Pruebas unitarias básicas del adaptador de la lista.
 *
 * Se verifica la cantidad de elementos que el adaptador administra,
 * sin depender de una interfaz gráfica o de un dispositivo.
 */
public class RecyclerAdapterTest {

    @Test
    public void shouldReturnNumberOfFoodItems() {
        RecyclerAdapter adapter = new RecyclerAdapter(
                null,
                Arrays.asList(
                        new DataModel("Food 1", "Category 1", "Offer 1"),
                        new DataModel("Food 2", "Category 2", "Offer 2"),
                        new DataModel("Food 3", "Category 3", "Offer 3")
                )
        );

        assertEquals(3, adapter.getItemCount());
    }

    @Test
    public void shouldReturnZeroForEmptyList() {
        RecyclerAdapter adapter = new RecyclerAdapter(
                null,
                Arrays.<DataModel>asList()
        );

        assertEquals(0, adapter.getItemCount());
    }
}

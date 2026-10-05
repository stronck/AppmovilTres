package in.myinnos.swiggyanimation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Pruebas unitarias del modelo de datos de la aplicación.
 */
public class DataModelTest {

    @Test
    public void shouldReturnTitleCategoryAndOffer() {
        DataModel model = new DataModel(
                "Pizza",
                "Comida",
                "20% OFF"
        );

        assertEquals("Pizza", model.getTitle());
        assertEquals("Comida", model.getCategory());
        assertEquals("20% OFF", model.getOffer());
    }

    @Test
    public void shouldPreserveEmptyValues() {
        DataModel model = new DataModel("", "", "");

        assertEquals("", model.getTitle());
        assertEquals("", model.getCategory());
        assertEquals("", model.getOffer());
    }
}

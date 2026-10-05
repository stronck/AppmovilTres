package in.myinnos.swiggyanimation;

/**
 * Modelo de datos utilizado por cada elemento de la lista.
 *
 * Contiene la información básica que muestra el adaptador.
 */
class DataModel {

    private String title;
    private String category;
    private String offer;

    /**
     * Crea un elemento con título, categoría y oferta.
     */
    DataModel(String title, String category, String offer) {
        this.title = title;
        this.category = category;
        this.offer = offer;
    }

    // Devuelve el título del alimento.
    String getTitle() {
        return title;
    }

    // Devuelve la categoría del alimento.
    String getCategory() {
        return category;
    }

    // Devuelve el texto de la oferta.
    String getOffer() {
        return offer;
    }
}
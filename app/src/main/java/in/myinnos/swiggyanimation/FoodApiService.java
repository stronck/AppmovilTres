package in.myinnos.swiggyanimation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de consultar los datos de alimentos
 * publicados en una fuente HTTP externa.
 */
class FoodApiService {

    private static final String DATA_URL =
            "https://raw.githubusercontent.com/stronck/AppmovilTres/main/data/foods.json";

    /**
     * Consulta el servidor y transforma la respuesta JSON
     * en objetos DataModel.
     */
    List<DataModel> getFoods() throws Exception {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(DATA_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception(
                        "El servidor respondió con código "
                                + connection.getResponseCode()
                );
            }

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream)
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            inputStream.close();

            return parseFoods(response.toString());

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Convierte la respuesta JSON del servidor en una lista de modelos.
     */
    private List<DataModel> parseFoods(String json) throws Exception {
        ArrayList<DataModel> foods = new ArrayList<>();
        JSONArray array = new JSONArray(json);

        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.getJSONObject(i);

            foods.add(
                    new DataModel(
                            item.getString("title"),
                            item.getString("category"),
                            item.getString("offer")
                    )
            );
        }

        return foods;
    }
}

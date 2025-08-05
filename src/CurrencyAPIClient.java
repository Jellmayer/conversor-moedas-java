import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyAPIClient {
    private static final String API_KEY = "f015b5b91fea374b7b3568a1";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final HttpClient client = HttpClient.newHttpClient();

    public double getConversionRate(String fromCurrency, String toCurrency) throws IOException, InterruptedException {
        String endpoint = BASE_URL + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ExchangeRateRespose exchangeRate = new Gson().fromJson(response.body(), ExchangeRateRespose.class);
        return exchangeRate.conversion_rate();
    }

    public double convert(double value, String fromCurrency, String toCurrency) throws IOException, InterruptedException {
        double rate = getConversionRate(fromCurrency, toCurrency);
        return value * rate;
    }
}

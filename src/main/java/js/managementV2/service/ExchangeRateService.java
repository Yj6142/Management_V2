package js.managementV2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import js.managementV2.domain.ExchangeRateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class ExchangeRateService {

    private final String callBackUrl;
    private final String authKey;
    private final String data;

    public ExchangeRateService(@Value("${openApi.callBackUrl}") String callBackUrl,
                               @Value("${openApi.authKey}") String authKey,
                               @Value("${openApi.data}") String data){
        this.callBackUrl = callBackUrl;
        this.authKey = authKey;
        this.data = data;
    }

    public List<ExchangeRateInfo> callExchangeApi() {

        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        List<ExchangeRateInfo> result = new ArrayList<>();

        String urlStr = callBackUrl +
                "authkey=" + authKey +
                "&data=" + data;

        try{
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            stream = getNetworkConnection(urlConnection);
            result = readStreamToJson(stream);

            if(stream != null) stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return result;
    }

    public ExchangeRateInfo getExchangeRate(Currency currency) {
        List<ExchangeRateInfo> exchangeRateInfoList = callExchangeApi();
        return exchangeRateInfoList.stream()
                .filter(item -> item.getCurrencyCode().equals(currency.getCurrencyCode()))
                .findFirst()
                .orElseThrow();
    }


    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);

        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code :" + urlConnection.getResponseCode());
        }

        return urlConnection.getInputStream();
    }

    private List<ExchangeRateInfo> readStreamToJson(InputStream stream)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        StringBuilder jsonData = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            jsonData.append(line);
        }

        br.close();

        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(jsonData.toString(), ExchangeRateInfo[].class));
    }

}

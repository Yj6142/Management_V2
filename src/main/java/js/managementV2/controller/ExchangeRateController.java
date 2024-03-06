package js.managementV2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import js.managementV2.domain.ExchangeRateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ExchangeRateController {

    @Value("${openApi.callBackUrl}")
    private String callBackUrl;

    @Value("${openApi.authKey}")
    private String authKey;

    @Value("${openApi.data}")
    private String data;

    @GetMapping("/todayRate")
    public ResponseEntity<List<ExchangeRateInfo>> callExchangeApi() {

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

        return new ResponseEntity<>(result, HttpStatus.OK);
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

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExchangeRateInfo> result = new ArrayList<>();

        String readLine;

        while ((readLine = br.readLine()) != null) {
            result = Arrays.asList(objectMapper.readValue(readLine, ExchangeRateInfo[].class));
        }

        br.close();
        return result;
    }
}

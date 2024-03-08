package js.managementV2.controller;

import js.managementV2.domain.ExchangeRateInfo;
import js.managementV2.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/todayRate")
    public ResponseEntity<List<ExchangeRateInfo>> getTodayRate() {
        List<ExchangeRateInfo> exchangeRateInfoList = exchangeRateService.callExchangeApi();

        log.info("exchangeInfoList = {}", exchangeRateInfoList);
        return new ResponseEntity<>(exchangeRateInfoList, HttpStatus.OK);
    }
}

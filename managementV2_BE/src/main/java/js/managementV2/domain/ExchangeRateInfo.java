package js.managementV2.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties({"cur_nm", "bkpr", "yy_efee_r", "ten_dd_efee_r", "kftc_deal_bas_r", "kftc_bkpr"})
public class ExchangeRateInfo {

    //결과 (1: 성공)
    private int result;

    @JsonProperty("cur_unit")
    private String currencyCode;

    //송금 보낼 떄
    private String tts;

    //송금 받을 때
    private String ttb;

    //매매기준율
    @JsonProperty("deal_bas_r")
    private String exchangeRate;

    @JsonCreator
    public ExchangeRateInfo(@JsonProperty("result") int result,
                            @JsonProperty("cur_unit") String currencyCode,
                            @JsonProperty("tts") String tts,
                            @JsonProperty("ttb") String ttb,
                            @JsonProperty("deal_bas_r") String exchangeRate) {
        this.result = result;
        if (currencyCode.equals("CNH")) {
            this.currencyCode = "CNY";
        } else {
            this.currencyCode = currencyCode;
        }
        this.tts = tts;
        this.ttb = ttb;
        this.exchangeRate = exchangeRate;
    }
}

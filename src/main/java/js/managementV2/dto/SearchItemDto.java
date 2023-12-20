package js.managementV2.dto;

import lombok.Data;

@Data
public class SearchItemDto {
    private String articleNum;
    private String name;
    private float price;
    private String exPrice;

    public SearchItemDto(String articleNum, String name, float price, float exPrice, String symbol) {
        this.articleNum = articleNum;
        this.name = name;
        this.price = price;
        this.exPrice = exPrice + symbol;
    }
}

package js.managementV2.dto;

import lombok.Data;

@Data
public class SearchItemDto {
    private Long id;
    private String articleNum;
    private String name;
    private float price;
    private String exPrice;

    public SearchItemDto(Long id, String articleNum, String name, float price, float exPrice, String symbol) {
        this.id = id;
        this.articleNum = articleNum;
        this.name = name;
        this.price = price;
        this.exPrice = exPrice + symbol;
    }
}

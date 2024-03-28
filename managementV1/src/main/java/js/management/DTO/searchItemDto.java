package js.management.DTO;

import lombok.Data;

@Data
public class searchItemDto {

    private String articleNum;
    private String name;
    private float price;
    private String exPrice;

    public searchItemDto(String articleNum, String name, float price, float exPrice, String symbol) {
        this.articleNum = articleNum;
        this.name = name;
        this.price = price;
        this.exPrice = exPrice + symbol;
    }
}

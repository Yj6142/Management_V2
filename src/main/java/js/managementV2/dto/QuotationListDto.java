package js.managementV2.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QuotationListDto {
    private LocalDate requestDate;
    private String articleNum;
    private String name;
    private float exPrice;
    private int orderTime;

    public QuotationListDto(LocalDate requestDate, String articleNum, String name, float exPrice, int orderTime) {
        this.requestDate = requestDate;
        this.articleNum = articleNum;
        this.name = name;
        this.exPrice = exPrice;
        this.orderTime = orderTime;
    }
}

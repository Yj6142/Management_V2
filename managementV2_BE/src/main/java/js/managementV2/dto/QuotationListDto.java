package js.managementV2.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QuotationListDto {
    private Long id;
    private LocalDate requestDate;
    private String articleNum;
    private String name;
    private float exPrice;
    private String currencyCode;
    private float imPrice;
    private int orderTime;

    public QuotationListDto(Long id, LocalDate requestDate, String articleNum, String name, float exPrice, String currencyCode, float imPrice, int orderTime) {
        this.id = id;
        this.requestDate = requestDate;
        this.articleNum = articleNum;
        this.name = name;
        this.exPrice = exPrice;
        this.currencyCode = currencyCode;
        this.imPrice = imPrice;
        this.orderTime = orderTime;
    }
}

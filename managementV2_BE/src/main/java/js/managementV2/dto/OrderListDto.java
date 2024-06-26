package js.managementV2.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderListDto {

    private Long id;
    private LocalDate orderDate;
    private String articleNum;
    private String itemName;
    private int quantity;
    private float imPrice;
    private float exPrice;
    private float profit;
    private String companyName;
    private String currencyCode;

    public OrderListDto(Long id, LocalDate orderDate, String articleNum, String itemName, int quantity, float imPrice, float exPrice, float profit, String companyName, String currencyCode) {
        this.id = id;
        this.orderDate = orderDate;
        this.articleNum = articleNum;
        this.itemName = itemName;
        this.quantity = quantity;
        this.imPrice = imPrice;
        this.exPrice = exPrice;
        this.profit = profit;
        this.companyName = companyName;
        this.currencyCode = currencyCode;
    }
}

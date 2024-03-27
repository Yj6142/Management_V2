package js.managementV2.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodayProfitDto {
    private LocalDate orderDate;
    private String companyName;
    private Double totalProfit;

    public TodayProfitDto(LocalDate orderDate, String companyName, Double totalProfit) {
        this.orderDate = orderDate;
        this.companyName = companyName;
        this.totalProfit = totalProfit;
    }

    public TodayProfitDto(LocalDate orderDate, Double totalProfit) {
        this.orderDate = orderDate;
        this.totalProfit = totalProfit;
    }
}

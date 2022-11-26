package com.example.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private Integer countChickenShaurma;
    private Integer countPigShaurma;
    private Integer countXXLShaurma;
    private Long orderId;
}

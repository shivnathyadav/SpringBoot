package com.shop.supermarket.dto;


import lombok.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemsDTO {

    private int itemId;


    @NotNull(message = "only contains string")
    @Size(min=1)
    private String itemName;


    @NotNull(message = "only contains integer")
    private int cost;

    private String company;


}

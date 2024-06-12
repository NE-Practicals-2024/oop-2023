package com.mugishap.rca.springboot.v1.payload.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateProductDTO {

    @NotNull
    private String productName;

    @NotNull
    private String productType;

    @NotNull
    @DecimalMin(value = "0.1", message = "Price should be greater that 0.1")
    private double price;

    @NotNull
    @Min(1)
    private int quantity;

    private String image;

}

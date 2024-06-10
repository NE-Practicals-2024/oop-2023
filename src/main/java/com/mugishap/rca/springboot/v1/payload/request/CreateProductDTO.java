package com.mugishap.rca.springboot.v1.payload.request;

import com.mugishap.rca.springboot.v1.models.File;
import com.mugishap.rca.springboot.v1.models.Quantity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateProductDTO {

    private String productName;

    private String productType;

    private double price;

    private LocalDateTime inDate;

    private int quantity;

}

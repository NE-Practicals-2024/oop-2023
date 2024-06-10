package com.mugishap.rca.springboot.v1.controllers;

import com.mugishap.rca.springboot.v1.models.Cart;
import com.mugishap.rca.springboot.v1.payload.response.ApiResponse;
import com.mugishap.rca.springboot.v1.services.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final ICartService cartService;

    @PatchMapping("/add-item")
    private ResponseEntity<ApiResponse> addItemToCart(
            @RequestParam("product") UUID productId,
            @RequestParam(value = "quantity", defaultValue = "1") int quantity
    ) {
        Cart cart = this.cartService.addProductToCart(productId, quantity);
        return ResponseEntity.ok(ApiResponse.success("Item added successfully", cart));
    }

}

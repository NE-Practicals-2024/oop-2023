package com.mugishap.rca.springboot.v1.services;

import com.mugishap.rca.springboot.v1.models.Cart;

import java.util.UUID;

public interface ICartService {

    Cart save(Cart cart);

    Cart addProductToCart(UUID productId, int quantity);
    Cart emptyCart();
    Cart purchaseItems();

}

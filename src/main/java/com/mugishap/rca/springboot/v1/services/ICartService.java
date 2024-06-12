package com.mugishap.rca.springboot.v1.services;

import com.mugishap.rca.springboot.v1.models.Cart;
import com.mugishap.rca.springboot.v1.models.Purchased;

import java.util.UUID;

public interface ICartService {

    void save(Cart cart);

    Cart addProductToCart(UUID productId, int quantity);
    Cart emptyCart();
    Purchased purchaseItems();

}

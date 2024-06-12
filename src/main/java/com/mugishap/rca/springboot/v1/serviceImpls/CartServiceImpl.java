package com.mugishap.rca.springboot.v1.serviceImpls;

import com.mugishap.rca.springboot.v1.exceptions.BadRequestException;
import com.mugishap.rca.springboot.v1.models.*;
import com.mugishap.rca.springboot.v1.repositories.ICartItemRepository;
import com.mugishap.rca.springboot.v1.repositories.ICartRepository;
import com.mugishap.rca.springboot.v1.repositories.IPurchasedRepository;
import com.mugishap.rca.springboot.v1.services.ICartService;
import com.mugishap.rca.springboot.v1.services.IProductService;
import com.mugishap.rca.springboot.v1.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    private final ICartRepository cartRepository;
    private final IUserService userService;
    private final IProductService productService;
    private final ICartItemRepository cartItemRepository;
    private final IPurchasedRepository purchasedRepository;

    @Override
    public void save(Cart cart) {
        this.cartRepository.save(cart);
    }

    @Override
    public Cart addProductToCart(UUID productId, int quantity) {
        User user = this.userService.getLoggedInUser();
        Cart cart = this.cartRepository.findByUser_Id(user.getId()).orElseThrow(() -> new BadRequestException("Cart with user id [" + user.getId() + "] not found"));
        Product product = this.productService.findById(productId);
        Optional<CartItem> existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            this.cartItemRepository.save(cartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            newCartItem.setTotalForProduct(quantity * product.getPrice());
            this.cartItemRepository.save(newCartItem);
            cart.getItems().add(newCartItem);
        }
        return cart;
    }

    @Override
    public Cart emptyCart() {
        User user = this.userService.getLoggedInUser();
        Cart cart = this.cartRepository.findByUser_Id(user.getId()).orElseThrow(() -> new BadRequestException("Cart not found"));
        cart.setItems(new ArrayList<>());
        return this.cartRepository.save(cart);
    }

    @Override
    public Purchased purchaseItems() {
        User user = this.userService.getLoggedInUser();
        Cart cart = this.cartRepository.findByUser_Id(user.getId()).orElseThrow(() -> new BadRequestException("Cart not found"));
        if (cart.getItems().isEmpty()) {
            throw new BadRequestException("No items in cart");
        }
        Purchased purchased = new Purchased();
        double total = cart.getItems().stream()
                .mapToDouble(CartItem::getTotalForProduct)
                .sum();
        purchased.setPurchasedProducts(cart.getItems());
        purchased.setTotal(total);
        this.purchasedRepository.save(purchased);
        this.cartItemRepository.deleteAll(cart.getItems());
        return purchased;
    }
}

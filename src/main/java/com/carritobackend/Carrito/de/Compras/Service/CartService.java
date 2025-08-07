package com.carritobackend.Carrito.de.Compras.Service;


import com.carritobackend.Carrito.de.Compras.Model.Cart;
import com.carritobackend.Carrito.de.Compras.Model.CartItem;
import com.carritobackend.Carrito.de.Compras.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final List<Product> products = List.of(
            new Product(1, "Producto 1", 100),
            new Product(2, "Producto 2", 200)
    );

    private final Cart cart = new Cart();

    public List<Product> getAllProducts() {
        return products;
    }

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Integer productId) {
        Optional<Product> productOpt = products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            CartItem existingItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + 1);
            } else {
                cart.getItems().add(new CartItem(product, 1));
            }
        } else {
            throw new IllegalArgumentException("Producto no encontrado");
        }
    }

    public void removeFromCart(Integer productId) {
        boolean removed = cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        if (!removed) {
            throw new IllegalArgumentException("Producto no encontrado en el carrito con ID: " + productId);
        }
    }
}

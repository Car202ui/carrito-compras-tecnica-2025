package com.carritobackend.Carrito.de.Compras.Controller;


import com.carritobackend.Carrito.de.Compras.Model.Cart;
import com.carritobackend.Carrito.de.Compras.Model.Product;
import com.carritobackend.Carrito.de.Compras.Service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carrito de Compras", description = "Operaciones para productos y carrito de compras")
@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Obtener todos los productos", description = "Devuelve la lista de productos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)))
    })
    @GetMapping("/products")
    public List<Product> getProducts() {
        return cartService.getAllProducts();
    }

    @Operation(summary = "Agregar producto al carrito", description = "Agrega un producto al carrito por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID de producto inválido", content = @Content)
    })
    @PostMapping("/cart")
    public ResponseEntity<String> addToCart(
            @Parameter(description = "ID del producto a agregar", required = true)
            @RequestBody AddToCartRequest request) {
        try {
            cartService.addToCart(request.getProductId());
            return ResponseEntity.ok("Producto agregado al carrito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener el carrito", description = "Devuelve el carrito actual con los productos agregados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito actual",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cart.class)))
    })
    @GetMapping("/cart")
    public Cart getCart() {
        return cartService.getCart();
    }

    @Data
    static class AddToCartRequest {
        private Integer productId;
    }


    @DeleteMapping("/cart/{productId}")
    @Operation(
            summary = "Eliminar un producto del carrito",
            description = "Elimina el producto indicado por ID del carrito en memoria."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID de producto inválido"),
    })
    public ResponseEntity<String> removeFromCart(
            @Parameter(description = "ID del producto a eliminar", required = true, example = "1")
            @PathVariable Integer productId) {
        try {
            cartService.removeFromCart(productId);
            return ResponseEntity.ok("Producto eliminado del carrito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo eliminar el producto: " + e.getMessage());
        }
    }
}

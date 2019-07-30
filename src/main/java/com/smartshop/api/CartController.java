package com.smartshop.api;

import com.smartshop.model.Cart;
import com.smartshop.model.CartProduct;
import com.smartshop.model.Product;
import com.smartshop.service.CartService;
import com.smartshop.service.CurrentUserService;
import com.smartshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<Product> getCartProduct() {
        int cartId = currentUserService.get().getCart().getCartId();
        Cart cart = cartService.findById(cartId);
        List<CartProduct> cartProducts = cart.getCartProduct();
        List<Product> result = new ArrayList<Product>();
        for (CartProduct i : cartProducts) {
            Map<String, String> map = new HashMap<>();
            map.put("productId", i.getProductId());
            Product cache = productService.find(map).get(0);
            cache.setAmount(i.getAmount());
            result.add(cache);
        }
        return result;
    }

    @PutMapping
    public ResponseEntity updateCartProduct(@RequestBody List<Product> products) {
        try {
            int cartId = currentUserService.get().getCart().getCartId();
            cartService.update(products, cartId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity addToCart(@RequestBody Product product) {
        System.out.println("some thing");
        try {
            int cartId = currentUserService.get().getCart().getCartId();
            List<Product> products = getCartProduct();
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductId() == product.getProductId()) {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
            };
            cartService.addToCart(cartId, product.getProductId(), 1);

            System.out.println("data add roi nhe");

            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}

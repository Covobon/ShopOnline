package com.smartshop.api;

import com.smartshop.model.Cart;
import com.smartshop.model.CartProduct;
import com.smartshop.model.Orders;
import com.smartshop.model.Product;
import com.smartshop.service.CartService;
import com.smartshop.service.CurrentUserService;
import com.smartshop.service.OrderService;
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

    @Autowired
    private OrderService orderService;

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
        try {
            int cartId = currentUserService.get().getCart().getCartId();
            List<Product> products = getCartProduct();
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductId().equals(product.getProductId())) {
                    cartService.modifyAmount(cartId, product.getProductId(), products.get(i).getAmount() + 1);
                    return new ResponseEntity("Success", HttpStatus.OK);
                }
            };
            cartService.addToCart(cartId, product.getProductId(), 1);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/negative")
    public ResponseEntity addToCartNegative(@RequestBody Product product){
        try {
            int cartId = currentUserService.get().getCart().getCartId();
            cartService.modifyAmount(cartId, product.getProductId(), product.getAmount());
            return new ResponseEntity("Success!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity removeFormCart(@PathVariable String productId) {
        try {
            int cartId = currentUserService.get().getCart().getCartId();
            cartService.removeFormCart(cartId, productId);
            return new ResponseEntity("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pay")
    public ResponseEntity pay(){
        Cart cart = currentUserService.get().getCart();
        if(getCartProduct().size() > 0) {
            Orders orders = new Orders(currentUserService.get().getUserName(), "Processing", cart.getAddress());
            orderService.save(orders);
            for (Product product : getCartProduct()) {
                orderService.addProduct(orders.getOrderId(), product);
            }
            cartService.clean(cart.getCartId());
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}

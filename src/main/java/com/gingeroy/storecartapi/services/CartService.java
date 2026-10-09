package com.gingeroy.storecartapi.services;

import com.gingeroy.storecartapi.dtos.CartDto;
import com.gingeroy.storecartapi.dtos.CartItemDto;
import com.gingeroy.storecartapi.entities.Cart;
import com.gingeroy.storecartapi.entities.CartItem;
import com.gingeroy.storecartapi.entities.Product;
import com.gingeroy.storecartapi.exceptions.CartNotFoundException;
import com.gingeroy.storecartapi.exceptions.ProductNotFoundException;
import com.gingeroy.storecartapi.mappers.CartMapper;
import com.gingeroy.storecartapi.repositories.CartRepository;
import com.gingeroy.storecartapi.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private ProductRepository productRepository;

    public CartDto createCart(){
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toCartDto(cart);
    }

    public CartItemDto addToCart(UUID cartId, Long productId){
        // 购物车是否存在
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null){
            throw new CartNotFoundException();
        }

        // 请求的商品是否存在
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null){
            throw new ProductNotFoundException();
        }

        // 购物车中是否已有该商品 - 有->数量++，无->新增
        CartItem cartItem = cart.addCartItem(product);

        cartRepository.save(cart);
        return cartMapper.toCartItemDto(cartItem);
    }

    public CartDto getCart(UUID cartId){
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null){
            throw new CartNotFoundException();
        }

        return cartMapper.toCartDto(cart);
    }

    public CartItemDto updateCartItem(UUID cartId, Long productId, Integer quantity){
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null){
            throw new CartNotFoundException();
        }

        CartItem cartItem = cart.getCartItem(productId);
        if (cartItem == null){
            throw new ProductNotFoundException();
        }

        cartItem.setQuantity(quantity);
        cartRepository.save(cart);

        return cartMapper.toCartItemDto(cartItem);
    }

    public void removeCartItem(UUID cartId,Long productId){
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null){
            throw new CartNotFoundException();
        }

        CartItem cartItem = cart.getCartItem(productId);
        if (cartItem == null){
            throw new ProductNotFoundException();
        }

        cart.getCartItems().remove(cartItem);
        cartRepository.save(cart);
        cartItem.setCart(null);
    }

    public void clearCartItems(UUID cartId){
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null){
            throw new CartNotFoundException();
        }

        cart.clearCartItems();
        cartRepository.save(cart);
    }
}

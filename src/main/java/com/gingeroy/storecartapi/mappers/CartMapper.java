package com.gingeroy.storecartapi.mappers;

import com.gingeroy.storecartapi.dtos.CartDto;
import com.gingeroy.storecartapi.dtos.CartItemDto;
import com.gingeroy.storecartapi.dtos.CartProductDto;
import com.gingeroy.storecartapi.entities.Cart;
import com.gingeroy.storecartapi.entities.CartItem;
import com.gingeroy.storecartapi.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toCartDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toCartItemDto(CartItem cartItem);

    CartProductDto toCartProductDto(Product Product);
}

package com.gingeroy.storecartapi.repositories;

import com.gingeroy.storecartapi.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
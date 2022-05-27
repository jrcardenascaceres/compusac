package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.Category;

public interface ICategorysRepository extends CrudRepository<Category, Long> {

}

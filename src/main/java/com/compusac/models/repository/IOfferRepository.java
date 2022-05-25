package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.Offer;

public interface IOfferRepository extends CrudRepository<Offer, Long> {

}

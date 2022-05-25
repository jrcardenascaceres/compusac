package com.compusac.models.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.compusac.models.entity.Offer;

public interface IOfferService {

	public List<Offer> findAll();

	public Offer findById(Long id) throws NotFoundException;

	public Offer create(Offer offer);

	public Offer update(Offer offer, Long id);

	public void delete(Long id);
}

package com.compusac.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compusac.models.entity.Offer;
import com.compusac.models.repository.IOfferRepository;

@Service
public class OfferServiceImpl implements IOfferService {

	@Autowired
	IOfferRepository offerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Offer> findAll() {
		return (List<Offer>) offerRepository.findAll();
	}

	@Override
	public Offer findById(Long id) throws NotFoundException {
		return offerRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public Offer create(Offer Offer) {
		return offerRepository.save(Offer);
	}

	@Override
	public Offer update(Offer offer, Long id) {
		return offerRepository.save(offer);
	}

	@Override
	public void delete(Long id) {
		offerRepository.deleteById(id);
	}
}

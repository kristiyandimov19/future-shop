package com.example.futureshop.services.impl;

import com.example.futureshop.models.services.OfferServiceModel;
import com.example.futureshop.repositories.OfferRepository;
import com.example.futureshop.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl( OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOffer(OfferServiceModel offerServiceModel) throws IOException {

    }
}

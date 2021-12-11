package com.example.futureshop.services;

import com.example.futureshop.models.services.OfferServiceModel;

import java.io.IOException;

public interface OfferService {
    public void addOffer(OfferServiceModel offerServiceModel) throws IOException;
}

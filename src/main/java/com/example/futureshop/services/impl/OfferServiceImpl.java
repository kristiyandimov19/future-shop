package com.example.futureshop.services.impl;

import com.example.futureshop.models.entities.OfferEntity;
import com.example.futureshop.models.services.OfferServiceModel;
import com.example.futureshop.repositories.OfferRepository;
import com.example.futureshop.services.CloudinaryService;
import com.example.futureshop.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class OfferServiceImpl implements OfferService {

    private final CloudinaryService cloudinaryService;
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(CloudinaryService cloudinary, OfferRepository offerRepository, ModelMapper modelMapper) {
        this.cloudinaryService = cloudinary;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOffer(OfferServiceModel offerServiceModel) throws IOException {
        MultipartFile img = offerServiceModel.getImageUrl();
        String url = cloudinaryService.uploadImage(img);

        OfferEntity offerEntity = new OfferEntity().setImageUrl(url);
    }
}

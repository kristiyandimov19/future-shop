package com.example.futureshop.models.services;

import org.springframework.web.multipart.MultipartFile;

public class OfferServiceModel {

    private MultipartFile imageUrl;

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public OfferServiceModel setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}

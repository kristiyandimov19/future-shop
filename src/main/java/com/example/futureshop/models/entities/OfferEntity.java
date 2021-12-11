package com.example.futureshop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.Instant;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigInteger price;

//    @OneToMany(mappedBy = "offer")
//    private List<CommentEntity> comments;

    @ManyToOne
    private ModelEntity model;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Instant dateCreated;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    private UserEntity seller;

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public ModelEntity getModel() {
        return model;
    }

    public OfferEntity setModel(ModelEntity model) {
        this.model = model;
        return this;
    }

    public String getName() {
        return name;
    }

    public OfferEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigInteger getPrice() {
        return price;
    }

    public OfferEntity setPrice(BigInteger price) {
        this.price = price;
        return this;
    }
//
//    public List<CommentEntity> getComments() {
//        return comments;
//    }
//
//    public OfferEntity setComments(List<CommentEntity> comments) {
//        this.comments = comments;
//        return this;
//    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public OfferEntity setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }
}

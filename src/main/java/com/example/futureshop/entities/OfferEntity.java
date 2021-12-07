package com.example.futureshop.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    private String name;

    @Column(nullable = false)
    private BigInteger price;

    @OneToMany(mappedBy = "offer")
    private List<CommentEntity> comments;

    @ManyToOne
    private ModelEntity model;

    private String description;

    private Integer yearCreated;

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

    public List<CommentEntity> getComments() {
        return comments;
    }

    public OfferEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getYearCreated() {
        return yearCreated;
    }

    public OfferEntity setYearCreated(Integer yearCreated) {
        this.yearCreated = yearCreated;
        return this;
    }
}

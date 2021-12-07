package com.example.futureshop.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    @ManyToOne
    private UserEntity author;

    private String comment;

    @ManyToOne
    private OfferEntity offer;
}

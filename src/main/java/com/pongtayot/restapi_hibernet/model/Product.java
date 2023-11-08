package com.pongtayot.restapi_hibernet.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Product {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
    @Column(length = 150, nullable = false, updatable = false)
    private String name;
    private String image;
    private int price;
    private int stock;

    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_At", nullable = false)
    private Date createAt;
    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_At", nullable = false)
    private Date updateAt;
}


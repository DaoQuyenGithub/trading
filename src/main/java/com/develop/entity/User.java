package com.develop.entity;

import com.develop.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "crypto_users")
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;
}

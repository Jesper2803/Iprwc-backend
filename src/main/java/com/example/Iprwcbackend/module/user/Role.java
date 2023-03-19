package com.example.Iprwcbackend.module.user;

import jakarta.persistence.*;

import java.util.Collection;

import static jakarta.persistence.GenerationType.AUTO;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN
}

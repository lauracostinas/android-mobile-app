package com.example.laura.myfirstapplication;

import java.io.Serializable;

/**
 * Created by Laura on 16-Jan-18.
 */

public class User implements Serializable {
    private String email;
    private String id;
    private Role role;

    public User(String email, String id, Role role) {
        this.email = email;
        this.id = id;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    enum Role {
        ADMIN,
        NORMAL
    }
}

package com.example.javalab2.domain;

import com.example.javalab2.domain.enums.Role;

public class User extends Entity{
    private String email;
    private String username;
    private String password;
    private RoleEntity role;
    private Integer wallet_id;
    private Wallet wallet;

    public User() {
    }

    public User(Integer id, String email, String username, String password, RoleEntity role, Integer wallet_id, Wallet wallet) {
        super(id);
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.wallet_id = wallet_id;
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public Integer getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(Integer wallet_id) {
        this.wallet_id = wallet_id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}

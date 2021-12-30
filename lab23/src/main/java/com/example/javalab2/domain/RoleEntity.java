package com.example.javalab2.domain;

import com.example.javalab2.domain.enums.Role;

public class RoleEntity extends Entity{
    private Role role;

    public RoleEntity() {
    }

    public RoleEntity(Integer id, Role role) {
        super(id);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

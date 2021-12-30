package com.example.javalab2.domain;

import com.example.javalab2.domain.enums.Status;

public class StatusEntity extends Entity{
    private Status status;

    public StatusEntity() {
    }

    public StatusEntity(Integer id, Status status) {
        super(id);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

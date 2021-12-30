package com.example.javalab2.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandSimple {
    void execute(HttpServletRequest request, HttpServletResponse response);
}

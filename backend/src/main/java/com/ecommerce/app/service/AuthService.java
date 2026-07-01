package com.ecommerce.app.service;

import com.ecommerce.app.dto.JwtResponse;
import com.ecommerce.app.dto.LoginRequest;
import com.ecommerce.app.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    JwtResponse login(LoginRequest request);
}

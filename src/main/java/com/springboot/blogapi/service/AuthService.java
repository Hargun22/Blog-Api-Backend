package com.springboot.blogapi.service;

import com.springboot.blogapi.payload.LoginDto;
import com.springboot.blogapi.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}

package com.nacos.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.function.ServerRequest;


public interface PermissionService {
    boolean hasPermission(ServerRequest request, Authentication authentication);
}
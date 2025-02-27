package com.Service.Students.Client;

import com.Service.Students.Dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
@FeignClient(name = "Authentication")
public interface AuthClient {
    @GetMapping("/auth/validate")
    ResponseEntity<UserResponse> validateToken(String token);
}

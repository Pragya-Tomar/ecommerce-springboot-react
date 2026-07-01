package com.ecommerce.app.service;

import com.ecommerce.app.dto.RegisterRequest;
import com.ecommerce.app.entity.ERole;
import com.ecommerce.app.entity.Role;
import com.ecommerce.app.exception.DuplicateResourceException;
import com.ecommerce.app.repository.RoleRepository;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.security.JwtUtil;
import com.ecommerce.app.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthServiceImpl authService;

    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("pragya");
        registerRequest.setEmail("pragya@example.com");
        registerRequest.setPassword("securePass123");
    }

    @Test
    void register_throwsDuplicateResourceException_whenUsernameTaken() {
        when(userRepository.existsByUsername("pragya")).thenReturn(true);

        assertThatThrownBy(() -> authService.register(registerRequest))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("Username is already taken");

        verify(userRepository, never()).save(any());
    }

    @Test
    void register_throwsDuplicateResourceException_whenEmailTaken() {
        when(userRepository.existsByUsername("pragya")).thenReturn(false);
        when(userRepository.existsByEmail("pragya@example.com")).thenReturn(true);

        assertThatThrownBy(() -> authService.register(registerRequest))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("Email is already registered");

        verify(userRepository, never()).save(any());
    }

    @Test
    void register_savesUser_whenUsernameAndEmailAreUnique() {
        when(userRepository.existsByUsername("pragya")).thenReturn(false);
        when(userRepository.existsByEmail("pragya@example.com")).thenReturn(false);
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));
        when(passwordEncoder.encode("securePass123")).thenReturn("hashed-password");

        authService.register(registerRequest);

        verify(userRepository, times(1)).save(any());
    }
}

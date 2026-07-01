package com.ecommerce.app.repository;

import com.ecommerce.app.entity.ERole;
import com.ecommerce.app.entity.Role;
import com.ecommerce.app.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByUsername_returnsUser_whenExists() {
        Role role = roleRepository.save(new Role(ERole.ROLE_USER));

        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("hashed")
                .enabled(true)
                .roles(Set.of(role))
                .build();
        userRepository.save(user);

        var found = userRepository.findByUsername("testuser");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void existsByUsername_returnsFalse_whenNotPresent() {
        assertThat(userRepository.existsByUsername("nobody")).isFalse();
    }

    @Test
    void existsByEmail_returnsTrue_whenPresent() {
        Role role = roleRepository.save(new Role(ERole.ROLE_USER));
        User user = User.builder()
                .username("janedoe")
                .email("jane@example.com")
                .password("hashed")
                .enabled(true)
                .roles(Set.of(role))
                .build();
        userRepository.save(user);

        assertThat(userRepository.existsByEmail("jane@example.com")).isTrue();
    }
}

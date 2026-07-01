package com.ecommerce.app.config;

import com.ecommerce.app.entity.ERole;
import com.ecommerce.app.entity.Role;
import com.ecommerce.app.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        for (ERole role : ERole.values()) {
            roleRepository.findByName(role).orElseGet(() -> roleRepository.save(new Role(role)));
        }
    }
}

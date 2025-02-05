package com.erpoticastec.backenderp.infra.security.service;

import com.erpoticastec.backenderp.model.Role;
import com.erpoticastec.backenderp.model.User;
import com.erpoticastec.backenderp.repository.RoleRepository;
import com.erpoticastec.backenderp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private UserRepository userRepository;

    public User getUserWithRole(String login) {
        User user = userRepository.findByEmail(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
}

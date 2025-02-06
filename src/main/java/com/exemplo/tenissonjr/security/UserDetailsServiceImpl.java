package com.exemplo.tenissonjr.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.model.User;
import com.exemplo.tenissonjr.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt =userRepository.findByUserName(username);
        if (userOpt.isPresent()) {
            return new UserAuthenticated(userOpt.get());
        }
        
        throw new UsernameNotFoundException("User not found");

    }

}
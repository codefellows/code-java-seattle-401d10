package com.codefellows.dinosaurs.configs;

import com.codefellows.dinosaurs.DinosaursApplication;
import com.codefellows.dinosaurs.models.dinosaurUser.DinosaurUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    DinosaurUserRepository dinosaurUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dinosaurUserRepository.findByUsername(username);
    }
}

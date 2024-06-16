package com.sandeep.securityConfiguration;

import com.sandeep.entities.User;
import com.sandeep.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {
    private IUserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityCustomUserDetailService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // apne user ko lode karan hai bas
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found with email : " + username));
        System.out.println(user.getName());
        System.out.println("####################");
        System.out.println(user.getEmail());
        System.out.println("####################");
        System.out.println(user.getPassword() +"Password");
        return user;
    }
}

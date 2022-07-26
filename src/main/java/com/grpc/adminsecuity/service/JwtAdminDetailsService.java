package com.grpc.adminsecuity.service;


import com.grpc.adminsecuity.model.Admin;
import com.grpc.adminsecuity.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin adminDetail = adminRepository.findByAdminname(username);
        if (adminDetail == null) {
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(adminDetail.getAdminname(), adminDetail.getPassword(),
                new ArrayList<>());
    }

    public Admin save(Admin admin) {
        Admin newAdmin = new Admin();
        newAdmin.setAdminname(admin.getAdminname());
        newAdmin.setPassword(bcryptEncoder.encode(admin.getPassword()));
        return adminRepository.save(newAdmin);
    }

}

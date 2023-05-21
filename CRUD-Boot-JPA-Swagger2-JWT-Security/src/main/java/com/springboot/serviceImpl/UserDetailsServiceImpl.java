package com.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.pojo.Admin;
import com.springboot.repository.AdminRepo;
/**
 * @apiNote Spring Security Service
 * 
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> admin = adminRepo.findById(username);
		if(admin.isPresent()) {
			return new User(admin.get().getAdminName(), admin.get().getPassword(), new ArrayList<>());
		}else
			return null;
	}

}

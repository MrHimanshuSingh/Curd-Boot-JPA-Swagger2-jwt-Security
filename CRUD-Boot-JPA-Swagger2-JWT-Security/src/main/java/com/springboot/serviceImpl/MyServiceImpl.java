package com.springboot.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.pojo.Admin;
import com.springboot.pojo.Customer;
import com.springboot.repository.AdminRepo;
import com.springboot.repository.MyRepo;
import com.springboot.service.MyService;
import com.springboot.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @apiNote
 *
 */
@Slf4j
@Service
public class MyServiceImpl implements MyService, UserDetailsService {

	private static final String ERRORMESSAGE = "Something went wrong";

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private MyRepo jpaRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Transactional
	@Override
	public String add(Customer customer) {
		try {

			log.debug("MyServiceImpl >> add() >> processing");
			Optional<Customer> optCust = get(customer.getCustomerEmail());
			if (optCust.isEmpty()) {
				jpaRepository.save(customer);
				log.info("MyServiceImpl >> add() >> {} is saved", customer.getCustomerName());
				return "Inserted";
			} else {
				log.info("MyServiceImpl >> add() >> {} AlreadyExist", customer.getCustomerName());
				return "AlreadyExisted";
			}
		} catch (Exception e) {
			log.error("add >> {}", e.toString());
			return ERRORMESSAGE;
		}

	}

	@Transactional
	@Override
	public String update(Customer customer) {
		try {
			log.debug("MyServiceImpl >> update() >> processing");
			Optional<Customer> optCust = get(customer.getCustomerEmail());
			if (optCust.isPresent()) {
				jpaRepository.save(customer);
				log.info("MyServiceImpl >> update() >> {} updated", customer.getCustomerName());
				return "Updated";
			} else {
				log.info("MyServiceImpl >> update() >> {} NotExist", customer.getCustomerName());
				return "NotExist";
			}
		} catch (Exception e) {
			log.error("update >> {}", e.toString());
			return ERRORMESSAGE;

		}
	}

	@Override
	public Optional<Customer> get(String email) {
		try {
			log.info("MyServiceImpl >> get() >> processing");
			return jpaRepository.findById(email);
		} catch (Exception e) {
			log.error("get >> {}", e.toString());
			return Optional.empty();
		}
	}

	@Transactional
	@Override
	public String delete(String email) {
		try {
			log.debug("MyServiceImpl >> delete() >> processing");
			Optional<Customer> optCust = get(email);
			if (optCust.isPresent()) {
				jpaRepository.deleteById(email);
				log.info("MyServiceImpl >> delete() >> {} Deleted", email);
				return "deleted";
			} else {
				log.info("MyServiceImpl >> delete() >> {} NotExist", email);
				return "NotExist";
			}
		} catch (Exception e) {
			log.error("delete >> {}", e.toString());
			return ERRORMESSAGE;
		}
	}

	@Override
	public List<Customer> getAll() {
		try {
			log.info("MyServiceImpl >> getAll() >> processing");
			return jpaRepository.findAll();
		} catch (Exception e) {
			log.error("getAll >> {}", e.toString());
			return Collections.emptyList();
		}
	}

	public String signUp(String username, String password, String role) {
		try {
			Optional<Admin> userOpt = adminRepo.findById(username);
			if (userOpt.isPresent()) {
				log.info("MyServiceImpl >> signUp() >> {} AlreadyExist", username);
				return "AlreadyExisted";
			} else {
				log.info("MyServiceImpl >> signUp() >> inserting new user");
				Admin admin = new Admin();
				admin.setPassword(passwordEncoder.encode(password));
				admin.setUsername(username);
				admin.setPosition(role);
				String token = jwtUtil.tokenGenerator(username);
				log.info("MyServiceImpl >> signUp >> token :: {}", token);
				admin.setAuthToken(token);
				adminRepo.save(admin);
				log.info("MyServiceImpl >> signUp >> saved ::  {}", admin.toString());
				return "saved";
			}
		} catch (Exception e) {
			log.error("signUp >> {}", e.toString());
			return ERRORMESSAGE;
		}
	}

//	TODO: sigIn implementation
	public void signIn(String username, String password) {
		try {

		} catch (Exception e) {
			log.error("signIn >> {}", e.toString());
//            return ERRORMESSAGE;	
		}
	}

	/* UserdetailsService */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> adminOpt = adminRepo.findById(username);
		if (adminOpt.isPresent()) {
			Admin admin = adminOpt.get();
			log.info("MyServiceImpl >> loadUserByUsername() >> {}", admin.toString());
			return new User(username, admin.getPassword(),
					Stream.of(admin.getPosition()).map(SimpleGrantedAuthority::new).toList());
		} else {
			throw new UsernameNotFoundException("User Not Found");
		}

	}
}

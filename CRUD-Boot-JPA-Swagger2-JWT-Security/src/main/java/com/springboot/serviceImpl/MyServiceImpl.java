package com.springboot.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.pojo.Customer;
import com.springboot.repository.MyRepo;
import com.springboot.service.MyService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @apiNote
 *
 */
@Slf4j
@Service
public class MyServiceImpl implements MyService {
	
	private final String errorMessage = "Something went wrong";
	
	@Autowired
	private MyRepo jpaRepository;

	@Transactional
	@Override
	public String add(Customer customer) {
		try {

			log.debug("MyServiceImpl >> get() >> processing");
			Optional<Customer> optCust = get(customer.getCustomerEmail());
			if (optCust.isEmpty()) {
				jpaRepository.save(customer);
				log.info("MyServiceImpl >> update() >> {} is saved", customer.getCustomerName());
				return "Inserted";
			} else {
				log.info("MyServiceImpl >> update() >> {} AlreadyExist", customer.getCustomerName());
				return "AlreadyExisted";
			}
		} catch (Exception e) {
			log.error("add >> {}",e.toString());
			return errorMessage;
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
			log.error("update >> {}",e.toString());
			return errorMessage;

		}
	}

	@Override
	public Optional<Customer> get(String email) {
		try {
			log.info("MyServiceImpl >> get() >> processing");
			return jpaRepository.findById(email);
		} catch (Exception e) {
			log.error("get >> {}",e.toString());
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
			log.error("delete >> {}",e.toString());
			return errorMessage;
		}
	}

	@Override
	public List<Customer> getAll() {
		try {
			log.info("MyServiceImpl >> getAll() >> processing");
			return jpaRepository.findAll();
		} catch (Exception e) {
			log.error("getAll >> {}",e.toString());
			return Collections.emptyList();
		}
	}
}

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

	@Autowired
	private MyRepo jpaRepository;

	@Transactional
	@Override
	public String add(Customer customer) {
		try {

			log.debug("MyServiceImpl >> get() >> processing");
			Optional<Customer> optCust = get(customer.getCustomer_Email());
			if (optCust.isEmpty()) {
				String status = (jpaRepository.save(customer) != null) ? "Inserted" : "NotInserted";
				log.info("MyServiceImpl >> update() >> {} ", customer.getCustomer_Name() + " is " + status);
				return status;
			} else {
				log.info("MyServiceImpl >> update() >> {} AlreadyExist", customer.getCustomer_Name());
				return "AlreadyExisted";
			}
		} catch (Exception e) {
			log.error(e.toString());
			return "Something went wrong";
		}

	}

	@Transactional
	@Override
	public String update(Customer customer) {
		try {
			log.debug("MyServiceImpl >> update() >> processing");
			Optional<Customer> optCust = get(customer.getCustomer_Email());
			if (optCust.isPresent()) {
				log.info("MyServiceImpl >> update() >> {} update", customer.getCustomer_Name());
				return (jpaRepository.save(customer) != null) ? "Updated" : "NotUpdated";
			} else {
				log.info("MyServiceImpl >> update() >> {} NotExist", customer.getCustomer_Name());
				return "NotExist";
			}
		} catch (Exception e) {
			log.error(e.toString());
			return "Something went wrong";

		}
	}

	@Override
	public Optional<Customer> get(String email) {
		try {
			log.info("MyServiceImpl >> get() >> processing");
			return jpaRepository.findById(email);
		} catch (Exception e) {
			log.error(e.toString());
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
			log.error(e.toString());
			return "Something went wrong";
		}
	}

	@Override
	public List<Customer> getAll() {
		try {
			log.info("MyServiceImpl >> getAll() >> processing");
			return jpaRepository.findAll();
		} catch (Exception e) {
			log.error(e.toString());
			return Collections.emptyList();
		}
	}
}

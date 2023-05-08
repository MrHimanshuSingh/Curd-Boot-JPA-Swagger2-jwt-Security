package com.springboot.daoImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.dao.RedisDao;
import com.springboot.pojo.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class RedisDaoImpl implements RedisDao {

	@Autowired
	private RedisTemplate<String, Customer> template;

	private static final String KEY = "Customer";

	@Override
	public String add(Customer customer) {
		try {
			Optional<Customer> optCustomer = get(customer.getCustomer_Email());
			if (!optCustomer.isPresent()) {
				template.opsForHash().put(KEY, customer.getCustomer_Email(), customer);
				log.info("RedisDaoImpl>>add>>{} Saved Successfully", customer.toString());
				return "success";
			} else {
				log.error("RedisDaoImpl>>add>>{} Already existed", customer.toString());
				return "already existed";
			}
		} catch (Exception e) {
			log.error("RedisDaoImpl>>add>>{}", e.toString());
			return "exception";
		}
	}

	@Override
	public String update(Customer customer) {
		try {
			Optional<Customer> optCustomer = get(customer.getCustomer_Email());
			if (optCustomer.isPresent()) {
				template.opsForHash().put(KEY, customer.getCustomer_Email(), optCustomer.get());
				log.info("RedisDaoImpl>>update>>{} Updated Successfully", customer.toString());
				return "success";
			} else {
				log.error("RedisDaoImpl>>update>>{} not exist", customer.toString());
				return "not exist";
			}
		} catch (Exception e) {
			log.error("RedisDaoImpl>>update>>{}", e.toString());
			return "exception";
		}
	}

	@Override
	public String delete(String email) {
		try {
			Optional<Customer> optCustomer = get(email);
			if (optCustomer.isPresent()) {
				template.opsForHash().delete(KEY, email);
				log.info("RedisDaoImpl>>delete>>{} deleted Successfully", email);
				return "success";
			} else {
				log.error("RedisDaoImpl>>delete>>{} not exist", email);
				return "not Exist";
			}
		} catch (Exception e) {
			log.info("RedisDaoImpl>>delete>>{}", e.toString());
			return "exception";
		}
	}

	@Override
	public Optional<Customer> get(String email) {
		try {
			log.info("RedisDaoImpl>>get>>{}", email);
			return Optional.ofNullable((Customer) template.opsForHash().get(KEY, email));
		} catch (Exception e) {
			log.error("RedisDaoImpl>>get>>{}", e.toString());
			return Optional.empty();
		}
	}

	@Override
	public List<Customer> getAll() {
		try {
			log.info("RedisDaoImpl>>getAll");
			return Stream.of(template.opsForHash().values(KEY)).map(e -> (Customer) e).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("RedisDaoImpl>>getAll>>{}", e.toString());
			return Collections.emptyList();
		}
	}

}

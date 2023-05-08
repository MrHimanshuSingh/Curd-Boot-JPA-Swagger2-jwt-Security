package com.springboot.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.RedisDao;
import com.springboot.pojo.Customer;
import com.springboot.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisDao redisDao;

	@Override
	public String add(Customer customer) {
		return this.redisDao.add(customer);
	}

	@Override
	public String update(Customer customer) {
		return this.redisDao.update(customer);
	}

	@Override
	public String delete(String email) {
		return this.redisDao.delete(email);
	}

	@Override
	public Optional<Customer> get(String email) {
		return this.redisDao.get(email);
	}

	@Override
	public List<Customer> getAll() {
		return this.redisDao.getAll();
	}

}

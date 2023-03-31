package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.springboot.service.MyService;

@Controller
public class MyController {
	
	@Autowired
	private MyService service;
	
}

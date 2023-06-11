package com.findhub.finhubbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.service.filter.FilterService;

@RestController
@RequestMapping("/api/search")
public class FilterController {
	
	@Autowired
	private FilterService filterService;
	
    @GetMapping("/accounts/{id}")
    public Account filterAccountById(@PathVariable("id") int id) {
    	return filterService.findAccountById(id);
    }
}

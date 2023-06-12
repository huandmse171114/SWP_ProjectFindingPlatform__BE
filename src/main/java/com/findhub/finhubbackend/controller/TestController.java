package com.findhub.finhubbackend.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.service.account.AccountService;
import com.findhub.finhubbackend.service.application.ApplicationService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/test")
public class TestController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/opt1/{id}")
    public Application findApplicationById(@PathVariable("id") int id) {
        return applicationService.findById(id);
    }

    @GetMapping("/opt2/{id}")
    public List<Application> findAllApplicationsByIdLike(@PathVariable("id") int id) {
        return applicationService.findAllByIdStartingWith(id);
    }

    @GetMapping("/opt3/{id}")
    public List<Application> findAllByProjectId(@PathVariable("id") int id) {
        return applicationService.findAllByProjectId(id);
    }

    @GetMapping("/opt4/{id}")
    public List<Application> findAllApplicationsByProjectIdLike(@PathVariable("id") int id) {
        return applicationService.findAllByProjectIdStartingWith(id);
    }

    @GetMapping("/opt5/{id}")
    public List<Application> findAllApplicationsByTeamId(@PathVariable("id") int id) {
        return applicationService.findAllByTeamId(id);
    }

    @GetMapping("/opt6/{id}")
    public List<Application> findAllApplicationsByTeamIdLike(@PathVariable("id") int id) {
        return applicationService.findAllByTeamIdStartingWith(id);
    }

    @GetMapping("/opt7/{id}")
    public List<Application> findAllApplicationsByStatus(@PathVariable("id") int id) {
        return applicationService.findAllByStatus(id);
    }

    @GetMapping("/opt8/{date}")
    public List<Application> findAllApplicationsByDate(@PathVariable("date") Date date) {
        return applicationService.findAllByDate(date);
    }

    @GetMapping("/opt9/")
    public List<Application> findAllApplicationsByDateBetween(Date f, Date t) {
        return applicationService.findAllByDateBetween(f, t);
    }

    @GetMapping("/opt10/{id}")
    public List<Account> findAllByStatus(@PathVariable("id") int id) {
        return accountService.findAllByStatus(id);
    }

    @GetMapping("/opt11/{id}")
    public Account findById(@PathVariable("id") int id) {
        return accountService.findById(id);
    }

    @GetMapping("/opt12/{id}")
    public Account findByEmail(@PathVariable("id") String id) {
        return accountService.findByEmail(id);
    }

    @GetMapping("/opt13/{id}")
    public List<Account> findAllByIdLike(@PathVariable("id") int id) {
        return accountService.findAllByIdStartingWith(id);
    }

    @GetMapping("/opt14/{id}")
    public List<Account> findAllByEmailLike(@PathVariable("id") String id) {
        return accountService.findAllByEmailContaining(id);
    }

    @GetMapping("/opt15/{id}")
    public List<Account> findAllByRole(@PathVariable("id") int id) {
        return accountService.findAllByRole(id);
    }

}

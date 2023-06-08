package com.findhub.finhubbackend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.repository.AccountRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account userAccount = accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
		return new User(userAccount.getEmail(), userAccount.getPassword(), mapRolesToAuthorities(userAccount.getRole()));
	}
	
	private Collection<GrantedAuthority> mapRolesToAuthorities(int role) {
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		if (role == AccountRole.ADMIN.getValue()) {
			roles.add(new SimpleGrantedAuthority("ADMIN"));
		}else if (role == AccountRole.MEMBER.getValue()) {
			roles.add(new SimpleGrantedAuthority("MEMBER"));
		}else roles.add(new SimpleGrantedAuthority("PUBLISHER"));
		
		return roles.stream().collect(Collectors.toList());
	}

	public boolean existsByEmail(String email) {
		return accountRepository.existsByEmail(email);
	}

}

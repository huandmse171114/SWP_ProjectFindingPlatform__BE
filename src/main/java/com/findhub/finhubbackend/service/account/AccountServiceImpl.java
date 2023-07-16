package com.findhub.finhubbackend.service.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.entity.publisher.Publisher;
import com.findhub.finhubbackend.entity.publisher.PublisherStatus;
import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.model.response.AccountResponseModel;
import com.findhub.finhubbackend.model.update.AccountUpdateModel;
import com.findhub.finhubbackend.repository.AccountRepository;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.publisher.PublisherService;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class AccountServiceImpl extends ServiceImpl<Account, AccountRepository, AccountStatus>
		implements AccountService {
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public Account findByEmail(String email) {
		Optional<Account> account = repo.findByEmail(email);
		return account.isPresent() ? account.get() : null;
	}

	@Override
	public List<Account> findAllByEmailContaining(String email) {
		return repo.findAllByEmailContaining(email);
	}

	@Override
	public List<Account> findAllByRole(AccountRole role) {
		return findAllByRole(role.getValue());
	}

	@Override
	public List<Account> findAllByRole(int role) {
		return repo.findAllByRole(role);
	}

	@Override
	public AccountResponseModel getModel(int id) {
		Account a = get(id);
		
		AccountResponseModel result = AccountResponseModel.builder()
				.id(id)
				.email(a.getEmail())
				.password(a.getPassword())
				.role(new StatusModel(a.getRole(), AccountRole.nameOf(a.getRole())))
				.status(new StatusModel(a.getStatus(), AccountStatus.nameOf(a.getStatus())))
				.build();
		return result;
	}

	@Override
	public List<StatusModel> getAllRole() {
		return AccountRole.getAll();
	}

	@Override
	public boolean existByEmail(String email) {
		return repo.existsByEmail(email);
	}

	@Override
	@Transactional
	public boolean update(AccountUpdateModel a) {
		Account account = get(a.getId());
		int oldRole = account.getRole();
		int newRole = a.getRole();
		if (oldRole != newRole) {
			if ((oldRole == AccountRole.ADMIN.getValue() || oldRole == AccountRole.PUBLISHER.getValue()) 
					&& newRole == AccountRole.MEMBER.getValue()) {
				// old member
				Publisher publisher = publisherService.findByEmail(a.getEmail()).get();
				
				publisherService.updateStatus(publisher.getId(), newRole);
				Optional<Member> oldMemberOptional = memberService.findByEmail(a.getEmail());
				if (oldMemberOptional.isPresent()) {
					Member member =  Member.builder()
							.id(oldMemberOptional.get().getId())
							.email(publisher.getEmail())
							.phone(publisher.getPhone())
							.dob(publisher.getDob())
							.name(publisher.getName())
							.balance(publisher.getBalance())
							.build();
					memberService.save(member);
				}else {
					Member member =  Member.builder()
							.email(publisher.getEmail())
							.phone(publisher.getPhone())
							.dob(publisher.getDob())
							.name(publisher.getName())
							.balance(publisher.getBalance())
							.build();
					memberService.save(member);
				}
			}else if ((newRole == AccountRole.ADMIN.getValue() || newRole == AccountRole.PUBLISHER.getValue()) 
					&& oldRole == AccountRole.MEMBER.getValue()) {
				Member member = memberService.findByEmail(a.getEmail()).get();
				memberService.updateStatus(member.getId(), MemberStatus.DELETED.getValue());
				Optional<Publisher> oldPublisherOptional = publisherService.findByEmail(a.getEmail());
				
				if (oldPublisherOptional.isPresent()) {
					Publisher publisher = Publisher.builder()
							.id(oldPublisherOptional.get().getId())
							.email(member.getEmail())
							.phone(member.getPhone())
							.dob(member.getDob())
							.name(member.getName())
							.balance(member.getBalance())
							.build();
					publisherService.save(publisher);					
				}else {
					Publisher publisher = Publisher.builder()
							.email(member.getEmail())
							.phone(member.getPhone())
							.dob(member.getDob())
							.name(member.getName())
							.balance(member.getBalance())
							.build();
					publisherService.save(publisher);
					
				}
			}
		}
		if (a.getPassword().equals(account.getPassword())) {
			repo.update(a.getId(), a.getEmail(), a.getPassword(), newRole);			
		}else {
			repo.update(a.getId(), a.getEmail(), passwordEncoder.encode(a.getPassword()), newRole);	
		}
		return true;
	}
}

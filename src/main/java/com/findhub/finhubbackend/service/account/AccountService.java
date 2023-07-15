package com.findhub.finhubbackend.service.account;

import java.util.List;

import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.entity.account.AccountStatus;
import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.model.response.AccountResponseModel;
import com.findhub.finhubbackend.model.update.AccountUpdateModel;
import com.findhub.finhubbackend.service.service.Service;

public interface AccountService extends Service<Account, AccountStatus> {
	/**
	 * find account by email (exact Email)
	 */
	public Account findByEmail(String email);
	
	public boolean existByEmail(String email);

	/**
	 * find accounts by email (approximate Email)
	 */
	public List<Account> findAllByEmailContaining(String email);

	/**
	 * find accounts by role
	 */
	public List<Account> findAllByRole(int role);

	/**
	 * find accounts by role
	 */
	public List<Account> findAllByRole(AccountRole role);
	
	public AccountResponseModel getModel(int id);

	public List<StatusModel> getAllRole();

	public boolean update(AccountUpdateModel a);
}

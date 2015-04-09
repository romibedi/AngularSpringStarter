package com.spring.angular.rest.assembler;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.spring.angular.core.services.util.AccountList;
import com.spring.angular.rest.mvc.AccountController;
import com.spring.angular.rest.resource.AccountListResource;
import com.spring.angular.rest.resource.AccountResource;

/**
 * Created by Chris on 7/22/14.
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {


    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}

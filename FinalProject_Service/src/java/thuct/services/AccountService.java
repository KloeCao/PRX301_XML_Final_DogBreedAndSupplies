/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuct.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import thuct.daos.AccountDAO;
import thuct.dtos.Account;

/**
 *
 * @author kloecao
 */
@Path("/account")
public class AccountService {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Account checkLogin(@QueryParam("id") String id, @QueryParam("pwd") String pwd) {
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.login(id, pwd);
        return account;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public int register(@QueryParam("id") String id, @QueryParam("pwd") String pwd, @QueryParam("fullname") String fullname) {
        int register = 0;
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        account.setIdaccount(id);
        account.setName(fullname);
        account.setPwd(pwd);
        if (accountDAO.checkDuplicate(id) == true) {
            accountDAO.register(account);
            register = 1;
        }
        return register;
    }
}

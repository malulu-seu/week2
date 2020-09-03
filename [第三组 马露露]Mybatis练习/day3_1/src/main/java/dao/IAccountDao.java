package dao;

import domain.AccountUser;

import java.util.List;


public interface IAccountDao {

    List<AccountUser> findAll();

    List<AccountUser> findAllJoin();
}

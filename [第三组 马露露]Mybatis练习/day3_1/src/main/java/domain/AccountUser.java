package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 把User和Account以userId连接成的表
 * 持有userid, account对象，user对象
 */

public class AccountUser implements Serializable {
    private Integer id;
    private Account account;
    private User user;

    public AccountUser() {
        account = new Account();
        user = new User();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUid() {
        return account.getUid();
    }

    public void setUid(Integer uid) {
        account.setUid(uid);
    }

    public Double getMoney() {
        return account.getMoney();
    }

    public void setMoney(Double money) {
        account.setMoney(money);
    }

    public String getUserName() {
        return user.getUserName();
    }

    public void setUserName(String userName) {
        user.setUserName(userName);
    }

    public Date getUserBirthday() {
        return user.getUserBirthday();
    }

    public void setUserBirthday(Date userBirthday) {
        user.setUserBirthday(userBirthday);
    }

    public String getUserSex() {
        return user.getUserSex();
    }

    public void setUserSex(String userSex) {
        user.setUserSex(userSex);
    }

    public String getUserAddress() {
        return user.getUserAddress();
    }

    public void setUserAddress(String userAddress) {
        user.setUserAddress(userAddress);
    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "id=" + id +
                ", account=" + account +
                ", user=" + user +
                '}';
    }
}

package dao;

import domain.QueryVo;
import domain.QueryVolds;
import domain.User;

import java.util.List;

/**
 * dao包放接口类（含有对实体的各种操作方法）
 */

public interface IUserDao {

    //查询所有用户信息
    List<User> findAll();

    //根据用户ID查询对应用户信息
    User findById(Integer userId);

    //保存（新增）新的用户
    int saveUser(User user);

    //更新用户信息
    int updateUser(User user);

    //根据用户ID删除某个用户
    int deleteUser(Integer userId);

    //根据用户名找到所有对应用户（模糊查询）
    List<User> findByName(String username);

    List<User> findByNameNew(String username);

    //查询用户表条目数
    int count();

    //根据查询类找到所有用户
    List<User> fingByVo(QueryVo queryVo);

    List<User> findInIds(QueryVolds queryVolds);
}

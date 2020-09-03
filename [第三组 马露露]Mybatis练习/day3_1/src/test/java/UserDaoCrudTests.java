import dao.IUserDao;
import domain.QueryVo;
import domain.QueryVolds;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoCrudTests {

    private IUserDao userDao;
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;

    @Before
    public void SetUp() throws Exception {
        //读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        //创建SqlSession工厂对象
        factory = builder.build(in);

        //创建SqlSession对象
        session = factory.openSession(true);//支持自动提交，就不需要commit

        //创建Dao的代理对象
        userDao = session.getMapper(IUserDao.class);

    }



    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for(User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testFindOne() {

        //执行操作
        User user = userDao.findById(41);
        System.out.println(user);
        Assert.assertEquals("张三", user.getUserName());
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("华泰");
        user.setUserAddress("南京事建邺区");
        user.setUserSex("女");
        user.setUserBirthday(new Date());

        int effectNumber = userDao.saveUser(user);

        Assert.assertEquals(1,effectNumber);

    }

    @Test
    public void testUpdateUser() {
        int id = 46;
        User user = userDao.findById(id);
        user.setUserAddress("北京市顺义区");
        int res = userDao.updateUser(user);

        Assert.assertEquals("北京市顺义区",userDao.findById(id).getUserAddress());
    }

    @Test
    public void testDeleteUser() {
        int res = userDao.deleteUser(57);

        Assert.assertEquals(1,res);
    }

    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%王%");

        Assert.assertEquals(2, users.size());

        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByNameNew() {
        List<User> users = userDao.findByNameNew("王");

        Assert.assertEquals(2, users.size());

        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testCount() {
        int res = userDao.count();

        Assert.assertEquals(14, res);
    }

    @Test
    public void testFindByVo() {
        QueryVo queryVo = new QueryVo();
        queryVo.setname("%王%");
        queryVo.setAddress("%南京%");
        List<User> users = userDao.fingByVo(queryVo);

        Assert.assertEquals(1, users.size());
        for(User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testFindInIds() {
        QueryVolds volds = new QueryVolds();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(45);
        list.add(46);
        volds.setIds(list);

        List<User> users = userDao.findInIds(volds);

        Assert.assertEquals(4,users.size());
    }

    @After
    public void testDown() throws Exception {
        //session.commit();
        session.close();
        in.close();
    }
}

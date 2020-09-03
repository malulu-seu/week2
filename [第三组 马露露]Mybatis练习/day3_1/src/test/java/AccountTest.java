import dao.IAccountDao;
import dao.IUserDao;
import domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAccountDao accountDao;

    @Before
    public void SetUp() throws Exception {
        //读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        //创建SqlSession工厂对象
        factory = builder.build(in);

        //创建SqlSession对象
        session = factory.openSession(true);//支持自动提交，就不需要session.commit操作

        //创建Dao的代理对象
        accountDao = session.getMapper(IAccountDao.class);

    }

    @Test
    public void testFindAll(){
        List<AccountUser> accountUsers = accountDao.findAll();
    }

    @Test
    public void testFindAllJoin(){
        List<AccountUser> accountUsers = accountDao.findAllJoin();
    }

    @After
    public void testDown() throws Exception {
        //session.commit();
        session.close();
        in.close();
    }
}

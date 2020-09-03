import dao.IAccountDao;
import dao.IRoleDao;
import domain.AccountUser;
import domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class RoleTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IRoleDao roleDao;

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
        roleDao = session.getMapper(IRoleDao.class);

    }

    @Test
    public void testFindAll(){
        List<Role> roles = roleDao.findAll();

        for (Role role : roles) {
            System.out.println("——每个角色的信息——");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

    @After
    public void testDown() throws Exception {
        //session.commit();
        session.close();
        in.close();
    }
}

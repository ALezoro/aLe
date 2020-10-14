package cn.itcast.test;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    /**
     * 测试查询所有
     * @throws Exception
     */
    @Test
    public void run() throws Exception{
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSeesionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取代理对象
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        //查询所有数据
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        //关闭资源
        sqlSession.close();
        inputStream.close();
    }


    /**
     * 测试保存
     * @throws Exception
     */
    @Test
    public void run2() throws Exception{
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSeesionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取代理对象
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        //保存
        Account account = new Account();
        account.setMoney(300.00);
        account.setName("王五");
        accountDao.saveAccount(account);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
        inputStream.close();
    }
}

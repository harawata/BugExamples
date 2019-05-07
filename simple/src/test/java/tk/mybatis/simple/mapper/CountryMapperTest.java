package tk.mybatis.simple.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import tk.mybatis.simple.model.Country;

public class CountryMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            // 通过 Resources 工具类将 mybatis-config.xml 配置文件读入 Reader
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            System.out.println("Test1");
            // 再通过 SqlSessionFactoryBuilder 建造类使用 Reader 创建 SqlSessionFactory 工厂对象
            // 在创建 sqlSessionFactory 对象的过程中：
            // 1. 解析 mybatis-config.xml 配置文件
            // 2. 读取配置文件中的 mappers 配置后
            // 3. 读取全部 Mapper.xml 进行具体方法的解析
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            System.out.println("Test2");

            reader.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }

    @Test
    public void testSelectAll() {

        // 使用时通过 SqlSessionFactory 工厂对象获取一个 sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过SqlSession 的 selectList 方法查找到 CountryMapper.xml 中的 id="selectAll" 的方法，执行SQL查询
            // MyBatis 底层使用 JDBC 执行 SQL，获得查询结果集 ResultSet 后，
            // 根据 resultType 的配置将结果映射为 Country 类型的集合，返回查询结果
            // 这样就得到了查询结果 countryList
            List<Country> countryList = sqlSession.selectList("selectAll");

            // 将结果打印到控制台
            printCountryList(countryList);
        } finally {
            // 最后关闭 SqlSession，否则会因为连接没有关闭，二导致数据库连接数过多，造成系统崩溃
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
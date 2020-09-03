package JDBC;

import domain.User;

import java.sql.*;

/**
 * jdbc的DEMO
 * 代码里很多硬编码：
 *      比如数据库信息变动，SQL语句变动，向preparedStatement传参数，结果集检索（列名）都需要改代码
 */

public class jdbcDemo {
    public static void main(String[] args) {
        try {

            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //通过驱动管理类获得数据库连接（传入URL,userName,password）
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day3?"+
                            "useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC",
                    "test","Htsc@1234");

            //定义SQL语句，其中？表示占位符
            String sql = "select * from user where username = ?";

            //获得预处理statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //设置SQL语句中的参数，第一个参数是序号，第二个参数是值
            preparedStatement.setString(1,"李四");

            //向数据库中发出查询，得到查询结果集
            ResultSet rs = preparedStatement.executeQuery();

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setUserBirthday(rs.getDate("birthday"));
                user.setUserSex(rs.getString("sex"));
                user.setUserAddress(rs.getString("address"));

                // 输出数据
                System.out.print("ID: " + user.getUserId());
                System.out.print(", 姓名: " + user.getUserName());
                System.out.print(", 出生日期： " + user.getUserBirthday());
                System.out.print(", 性别： " + user.getUserSex());
                System.out.print(", 地址： " + user.getUserAddress());
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            preparedStatement.close();
            connection.close();


        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{

        }
    }
}

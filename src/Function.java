import java.sql.*;
import java.util.logging.Logger;

public class Function {
    public static Connection getConnecttion()//与数据库取得连接
    {
        Connection conn=null;
        String url="jdbc:mysql://localhost:3306/task";
        String user="root";
        String password="123456";
        Logger log=Logger.getGlobal();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(url,user,password);
            if(!conn.isClosed())
            {
                log.info("数据库连接成功");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.warning("数据库驱动没有安装");
        }catch (SQLException e){
            e.printStackTrace();
            log.warning("数据库连接失败");
        }
        return conn;
    }

    public static void add(Connection conn,String stu_name,String stu_gender,
                           String stu_teachername,int stu_grade,int stu_class,int final_score,int stu_teacherid)
    {
        String sql="insert into students(stu_name,stu_gender,stu_teachername,stu_grade,stu_class,final_score,stu_teacherid) values(?,?,?,?,?,?,?)";
        try(PreparedStatement ps=conn.prepareStatement(sql)) {
            ps.setObject(1,stu_name);
            ps.setObject(2,stu_gender);
            ps.setObject(3,stu_teachername);
            ps.setObject(4,stu_grade);
            ps.setObject(5,stu_class);
            ps.setObject(6,final_score);
            ps.setObject(7,stu_teacherid);
            int n=ps.executeUpdate();
            System.out.println("增加成功");
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("增加失败");
        }
    }

    public static void delete(Connection conn, int stu_id)
    {
        String sql="delete from students where stu_id=?";
        try(PreparedStatement ps= conn.prepareStatement(sql))
        {
            ps.setObject(1,stu_id);
            int n=ps.executeUpdate();
            System.out.println("删除成功");
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("删除失败");
        }
    }

    public static void update(Connection conn,int stu_id,String stu_name)
    {
        String sql="update students set stu_name= ? where stu_id=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)) {
            ps.setObject(1,stu_name);
            ps.setObject(2,stu_id);
            int n=ps.executeUpdate();
            System.out.println("更新成功");
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("更新失败");
        }
    }

    public static void retrieve(Connection conn,int stu_id,int t_id)
    {
        String sql="select * from students,teachers where stu_id=? AND t_id=?";
        try(PreparedStatement ps= conn.prepareStatement(sql))
        {
            ps.setObject(1,stu_id);
            ps.setObject(2,t_id);
            try(ResultSet rs=ps.executeQuery())
            {
                while(rs.next())
                {
                    int id= rs.getInt("t_id");
                    String name=rs.getString("t_name");
                    int age=rs.getInt("t_age");
                    System.out.println("t_id:"+id+"  t_name:"+name+"  t_age:"+age);
                }
                rs.close();
            }
            ps.close();
            System.out.println("查找成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查找失败");
        }
    }

    public static void work(Connection conn) throws SQLException {
        try{
            conn.setAutoCommit(false);
            Function.add(conn,"Tt","male","Tom",8,1,77,3);
            Function.update(conn,1,"Ww");
            Function.delete(conn,3);
            Function.retrieve(conn,1,1);
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            conn.setAutoCommit(true);
        }
    }

    public static void close(Connection conn){//关闭资源Connection
        Logger log=Logger.getGlobal();
        if(conn != null){
            try {
                conn.close();
                log.info("Connection关闭成功");
            } catch (SQLException e) {
                e.printStackTrace();
                log.warning("Connection关闭失败");
            }
        }
    }



}


import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {

        Connection conn=null;
        conn=Function.getConnecttion();
//        //1.增
//        Function.add(conn,"Kk","female","Alice",9,1,83,1);
//        //这里有一个小问题：尝试将以下这个学生的final_score设置为NULL，但是不行，Types.NULL=0
//        Function.add(conn,"Mm","male","Tom",8,2, Types.NULL,3);
//        //2.删
//        Function.delete(conn,9);
//        //3.改
//        Function.update(conn,10,"Qq");
//        //4.查
//        Function.retrieve(conn,6,3);
        //5.事务
        Function.work(conn);
        Function.close(conn);
    }


}

import com.smart.framework.DataSet;
import com.smart.framework.helper.DBHelper;
import demo.smart.entity.Task;
import demo.smart.entity.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-13
 * Time: 下午10:30
 * To change this template use File | Settings | File Templates.
 */
public class Test extends Thread  {
    private static int num=0;

    public static void main(String[] args) throws  Exception{
          // long time =System.currentTimeMillis();

            for(int i=0;i<20;i++){
                Test  t = new Test();

                t.start();
            }
        //    System.out.println(System.currentTimeMillis()-time);

    }

    public  void say(){
        System.out.println("hello");
    }

    @Override
    public void run(){

        DBHelper.getInstance().beginTransaction();
        Connection conn = DBHelper.getInstance().getConnectionFromThreadLocal();
        QueryRunner qr = new QueryRunner();
        List<Task> list  = null;
        try {
            list = (List)qr.query(conn,"select * from task",new BeanListHandler(Task.class));
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


            System.out.println(Thread.currentThread().getName()+":"+conn);

        DBHelper.getInstance().commitTransaction();

       ;


    }
}

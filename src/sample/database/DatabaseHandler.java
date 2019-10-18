package sample.database;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import sample.modal.Task;
import sample.modal.User;

import java.sql.*;

public class DatabaseHandler extends Configs {

    Connection conn;

    public Connection getConn() throws ClassNotFoundException, SQLException {
    String connString="jdbc:mysql://"+hostName+":"+port+"/"+dbName;
    Class.forName("com.mysql.jdbc.Driver");
    conn= DriverManager.getConnection(connString,dbUser, dbPass);
        return conn;
    }

//delete task from db
    public void deleteTask(int userId, int taskId) throws SQLException, ClassNotFoundException {
        String query="DELETE FROM "+Consts.TASKS_TABLE+" WHERE "+Consts.TASK_ID+" =? AND "+
                Consts.TASK_USERID+ " =?";
        PreparedStatement preparedStatement=getConn().prepareStatement(query);
        preparedStatement.setInt(1,taskId);
        preparedStatement.setInt(2,userId);
        preparedStatement.execute();
        preparedStatement.close();
    }

//write
public void signUpUser(User user){
        String insert="INSERT INTO "+ Consts.USERS_TABLE+"(" +Consts.USER_FIRSTNAME+","+Consts.USER_LASTNAME+","+Consts.USER_USERNAME+","+ Consts.USER_PASSWORD+","+ Consts.USER_LOCATION +","+ Consts.USER_GENDER +") VALUES(?,?,?,?,?,?)";
    PreparedStatement ps= null;
    try {
        ps = getConn().prepareStatement(insert);
        ps.setString(1,user.getFirstname());
        ps.setString(2, user.getLastname());
        ps.setString(3,user.getUsername());
        ps.setString(4, user.getPassword());
        ps.setString(5,user.getLocation());
        ps.setString(6,user.getGender());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

}
public ResultSet getResultByUserId(int userId) throws SQLException, ClassNotFoundException {
ResultSet resultTask=null;
    String query="SELECT * FROM "+ Consts.TASKS_TABLE +" WHERE " + Consts.TASK_USERID + " =? ";
PreparedStatement preparedStatement=getConn().prepareStatement(query);
    preparedStatement.setInt(1, userId);
     resultTask =  preparedStatement.executeQuery();
return resultTask;
}
public ResultSet isUserLoggedIn(User user){
    ResultSet rs=null;
if (!user.getUsername().equals("") || !user.getPassword().equals("")){

     String query="SELECT * FROM "+ Consts.USERS_TABLE+" WHERE "+
             Consts.USER_USERNAME+ "=?"+ " AND "+ Consts.USER_PASSWORD+" =? ";
    try {
        PreparedStatement pst = getConn().prepareStatement(query);
        pst.setString(1, user.getUsername());
        pst.setString(2,user.getPassword());
        rs=pst.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}else{
    System.out.println("please provide you credentials");
}

        return rs;
}

 public void insertTask(Task task){
     String insert="INSERT INTO "+ Consts.TASKS_TABLE+"(" +Consts.TASK_TASK+","+Consts.USER_ID+","+Consts.TASK_DATE+","+Consts.TASK_DESCRIPTION+") VALUES(?,?,?,?)";
     PreparedStatement ps= null;
     try {
         ps = getConn().prepareStatement(insert);
         ps.setString(1,task.getTask());
         ps.setInt(2,task.getUserId());
         ps.setTimestamp(3, task.getDatecreated());
         ps.setString(4,task.getDescription());
         ps.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }

 }

 public int countUserTasks(int userId) throws SQLException, ClassNotFoundException {

        String query="SELECT * FROM "+ Consts.TASKS_TABLE +" WHERE " + Consts.TASK_USERID + " =? ";
     PreparedStatement pst = null;
         pst = getConn().prepareStatement(query);
         pst.setInt(1, userId);
       ResultSet rs=  pst.executeQuery();
       int count=0;
       while (rs.next()) count++;
     return count;
 }
//update

//delete
}
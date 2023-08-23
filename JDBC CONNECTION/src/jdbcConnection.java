import java.lang.reflect.Type;
import java.sql.*;
import java.sql.DriverManager;

public class jdbcConnection {
    public static void main(String[] args) throws Exception {
//       readRecords();
//       insertRecord();
//        prsInsertRecord();
//        update();

        spINOut();
//        spINoUT();
    }
    public static void readRecords() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String password="2003";
        String user="root";

        String query="select * from employee";

        Connection con=DriverManager.getConnection(url,user,password);
        Statement st=con.createStatement();
       ResultSet rs= st.executeQuery(query);

       while(rs.next()){
           System.out.println(rs.getInt(1));
           System.out.println(rs.getString(2));
           System.out.println(rs.getInt(3));
       }
       con.close();
    }
    public static void insertRecord() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="2003";

       String query ="insert into employee values(8,'naveen',340000)";
       Connection con=DriverManager.getConnection(url,user,password);
       Statement st= con.createStatement();
       int rows=st.executeUpdate(query);
        System.out.println("now of row affectted:"+rows);
    }

    public static void prsInsertRecord() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="2003";

        int id=9;
        String name="Ana";
        int salary=900000;
        String query="insert into employee values(?,?,?)";


        Connection con= DriverManager.getConnection(url,user,password);
//        this is no need statement object for prepared statement
//        Statement st= con.createStatement();
//        int rows=st.executeUpdate(query);
        PreparedStatement prs=con.prepareStatement(query);
        prs.setInt(1,id);
        prs.setString(2,name);
        prs.setInt(3,salary);
        int rows=prs.executeUpdate();


        System.out.println("Number of record insert :"+ rows);
        con.close();
    }
    public static void update() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="2003";

        int id=4;
        String name="Raj";
        int salary=123456;
        String query="update employee set salary=? where emp_id=?";

        Connection con= DriverManager.getConnection(url,user,password);
        PreparedStatement prs= con.prepareStatement(query);
        prs.setInt(1,salary);
        prs.setInt(2,id);
        int rows=prs.executeUpdate();

        System.out.println("Number of record insert :"+ rows);
        con.close();
    }

    public static void sp() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="2003";



        Connection con= DriverManager.getConnection(url,user,password);
        CallableStatement cst= con.prepareCall("{call GetEmp()}");
        ResultSet rs=cst.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getInt(3));
        }

        con.close();
    }

    public static void spINOut() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="2003";

       int id=9;
        Connection con= DriverManager.getConnection(url,user,password);
        CallableStatement cst=con.prepareCall("{call getIdbyName(?,?)}");
        cst.setInt(1,id);
        cst.registerOutParameter(2,Types.VARCHAR);
        cst.executeUpdate();

            System.out.println(cst.getString(2));


        con.close();

    }
    public static void spINoUT() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="2003";

        int id=5;
        Connection con= DriverManager.getConnection(url,user,password);
        CallableStatement cst=con.prepareCall("{call GetIn0ut(?,?)}");
        cst.setInt(1,id);
        cst.registerOutParameter(2,Types.VARCHAR);
        cst.executeUpdate();
        System.out.println(cst.getString(2));
        con.close();

    }



}

import java.sql.*;
//jdbc functions:
//1.initialize connection √
//2.close connection √
//3.add new account √(without gender, address and age)
//5.check the account password for login
//6.check previous account id for register √

//this is java database connector, nothing to say

public class jdbc {
    public jdbc(){}

        Connection con;
        public void initConnection(){

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","179179179");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        public void closeConnection(){
            if(con!=null){
                try{
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void showAllData(){
            try{
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from form");
                while(rs.next()){
                    System.out.println("rol1:"+rs.getString("id"));
                    System.out.println("rol2:"+rs.getString("pw"));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    public boolean searchForIdPw(String id,String pw){
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from form");
            while(rs.next()){
                if(rs.getString("id").equals(id)&&rs.getString("pw").equals(pw)){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
        }
    public boolean searchForId(String id){
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from form");
            while(rs.next()){
                if(rs.getString("id").equals(id)){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

        public void add(String id,String pw,String name){
            try{
                String sql ="insert into form values(?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1,id);
                ps.setString(2,pw);
                ps.setString(3,name);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}

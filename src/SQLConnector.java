import java.sql.*;

public class SQLConnector {


    public Connection Connectington(){
        String url = "jdbc:mysql://localhost:3306/test";
        String dbuser = "root";
        String dbpass = "root";
        try{
            Connection con = DriverManager.getConnection(url,dbuser,dbpass);
            return con;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
    public void visTelefonBog(){
        try {
            Connection con = Connectington();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from telefonbog");
            while(rs.next()){
                System.out.println("ID: "+rs.getString(1)+" Telefonnummer: "+rs.getString(2)+" Navn: "+rs.getString(3));
                Telefonbog t = new Telefonbog(rs.getString(2), rs.getString(3));
            }
        }catch (Exception e){
           throw new RuntimeException(e);
        }

    }

    public int erIdTaget(){
        int naesteId = 1;
        try{
            Connection con = Connectington();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) as max_id from test.telefonbog");

            if(rs.next()){
                int nuId = rs.getInt("max_id");
                naesteId = nuId + 1;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return naesteId;
    }

    public void TilfoejTilTelefonbog(String nummer, String navn){
        String query = "INSERT INTO telefonbog (id, Telefonnummer, navn) VALUES (?, ?, ?)";
        try{
            Connection con = Connectington();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, erIdTaget());
            pstmt.setString(2, nummer);
            pstmt.setString(3, navn);
            Telefonbog T = new Telefonbog(nummer,navn);
            pstmt.executeUpdate();
            System.out.println("Tilfoejet til telefonbogen");
        } catch (Exception e) {
            System.out.println("Fejl");
            throw new RuntimeException(e);
        }


    }

    public void SletFraTelefonbogNavn(String navn){
        String queryId = "DELETE FROM `test`.`telefonbog`where navn = "+"'"+navn+"'";
        String querySelect = "SELECT navn from telefonbog;";
        String querySelectSlettet = "Select * from telefonbog where navn = "+"'"+navn+"'";

        try{
            Connection con = Connectington();
            ResultSet rs = con.createStatement().executeQuery(querySelect);
            ResultSet rs2 = con.createStatement().executeQuery(querySelectSlettet);
            while(rs.next()){
                if(navn.equals(rs.getString(1))){
                    if (rs2.next()) {
                        System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+" blev slettet fra telefonbogen");
                        con.createStatement().executeUpdate(queryId);
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void SletFraTelefonbogNr(String nummer){
        String queryId = "DELETE FROM `test`.`telefonbog`where telefonnummer = "+"'"+nummer+"'";
        String querySelect = "SELECT telefonnummer from telefonbog;";
        String querySelectSlettet = "Select * from telefonbog where telefonnummer = "+"'"+nummer+"'";

    try{
        Connection con = Connectington();
        ResultSet rs = con.createStatement().executeQuery(querySelect);
        ResultSet rs2 = con.createStatement().executeQuery(querySelectSlettet);
        while(rs.next()){
            if(nummer.equals(rs.getString(1))){
                if(rs2.next()){
                    System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+" blev slettet fra telefonbogen");
                    con.createStatement().executeUpdate(queryId);
                }
            }
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }

    public void SletFraTelefonbogId(int id){
        String queryId = "DELETE FROM `test`.`telefonbog`where id = "+id;
        String querySelect = "SELECT id from telefonbog;";
        String querySelectSlettet = "Select * from telefonbog where id = "+id;

        try{
            Connection con = Connectington();
            ResultSet rs = con.createStatement().executeQuery(querySelect);
            ResultSet rs2 = con.createStatement().executeQuery(querySelectSlettet);
            while(rs.next()){
                if(id == rs.getInt(1)){
                    if(rs2.next()){
                        System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+" blev slettet fra telefonbogen");
                        con.createStatement().executeUpdate(queryId);
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void SoegEfterPersonId(int id){
        try{
            Connection con = Connectington();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from telefonbog where id ="+id);
        while(rs.next()){
            System.out.println("ID: "+rs.getString(1)+" Telefonnummer: "+rs.getString(2)+" Navn: "+rs.getString(3));
        }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void SoegEfterPersonNummer(String nummer){
        try{
            Connection con = Connectington();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from telefonbog where telefonnummer ="+"'"+nummer+"'");
            while(rs.next()){
                System.out.println("ID: "+rs.getString(1)+" Telefonnummer: "+rs.getString(2)+" Navn: "+rs.getString(3));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void SoegEfterPersonNavn(String navn){
        try{
            Connection con = Connectington();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from telefonbog where navn ="+"'"+navn+"'");
            while(rs.next()){
                System.out.println("ID: "+rs.getString(1)+" Telefonnummer: "+rs.getString(2)+" Navn: "+rs.getString(3));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
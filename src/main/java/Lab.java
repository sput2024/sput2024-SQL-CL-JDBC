

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionUtil;

/**
 * JDBC stands for Java DataBase Connectivity.  It is utilized to connect our java code with a database.
 * JDBC will allow us to execute SQL statements from java and retrieve the result set of that query to be utilized in java
 *
 * JDBC datatypes to know:
 *  - Connection: Creates an active connection to the database.
 *  - Statement: An object that represents a SQL statement to be executed.
 *  - ResultSet: An object that represents the virtual table return from a query (Only needed for executing DQL statements)
 *
 * Background:
 * Assume we have the following table:
 *      songs table
 *      |   id  |      title        |        artist         |
 *      -----------------------------------------------------
 *      |1      |'Let it be'        |'Beatles'              |
 *      |2      |'Hotel California' |'Eagles'               |
 *      |3      |'Kashmir'          |'Led Zeppelin'         |
 *
 * Assignment: Write JDBC logic in the methods below to achieve the following
 *                  - create a new song in our songs database table
 *                  - retrieve all songs from our database table
 *
 * If this is your first time working with JDBC, I recommend reading through the JDBCWalkthrough file that displays how to use JDBC for a similar scenario.
 */
public class Lab {
    public static void main(String[] args){}
    public void createSong(Song song)  {
        //write jdbc code here
    
    try {
        //retrieve active connection to db
        Connection connection = ConnectionUtil.getConnection();

        //SQL statement we are going to execute
        String sql = "insert into songs (id, title, artist) values (4,'Joyful Joyful', 'U2');";

        //create Statement object
        Statement statement = connection.createStatement();

        //execute the statement to db
        statement.executeUpdate(sql);


    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();

        try {
            //retrieve active connection to db
            Connection connection = ConnectionUtil.getConnection();
 
            String sql = "select * from songs;";

            //create the Statement object
            Statement statement = connection.createStatement();

            //execute the statement and retrieve result set
            ResultSet rs = statement.executeQuery(sql);

            //loop through each record and add record to song list object
            while(rs.next()){
                songs.add(new Song(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            //NOTE: in the above statement, rs.getString(1) is retrieving column 1 as a string from a record. in our situation, that will get the firstname. Since lastname is in the second column in the table, we retrieve that value by using rs.getString(2);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        return songs;
    }


}

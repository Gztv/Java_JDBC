package org.solution.jdbc;

import org.solution.jdbc.models.Album;
import org.solution.jdbc.repository.AlbumRep;
import org.solution.jdbc.repository.Repository;
import org.solution.jdbc.util.ConexionDB;

import java.sql.*;
import java.util.Date;

public class JdbcTest {
    public static void main(String[] args) {

        try ( Connection  conn = ConexionDB.getInstance();)
        {
            Repository<Album> rep = new AlbumRep();

            //System.out.println(rep.findById(3L));

            Album album = new Album();
            album.setName("Octavarium");
            album.setAuthor("Dream Theater");

            album.setRegisterTime(new Date());
            album.setGenre_id(16);

            rep.save(album);

            //rep.delete(21L);
            rep.findall().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package org.solution.jdbc.repository;

import org.solution.jdbc.models.Album;
import org.solution.jdbc.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRep implements Repository<Album>{
    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }


    @Override
    public List<Album> findall() {
        List<Album> albums = new ArrayList<>();
        try ( Statement stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM albums");)
            {
                while(result.next()){
                    Album album = createAlbum(result);

                    albums.add(album);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    private Album createAlbum(ResultSet result) throws SQLException {
        Album album = new Album();
        album.setId(result.getLong(1));
        album.setName(result.getString(2));
        album.setAuthor(result.getString(3));
        album.setRegisterTime(result.getDate(4));
        album.setGenre_id(result.getLong(5));
        return album;
    }

    @Override
    public Album findById(Long id) {
        Album a = null;

        try(PreparedStatement pstmt = getConnection().
                prepareStatement("SELECT * FROM albums WHERE id = ?")){
            pstmt.setLong(1,id);

            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    a = createAlbum(result);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public void save(Album album) {
        String sql;

    if(album.getId() != null && album.getId()>0){
        sql = "UPDATE albums SET name=?, author=? WHERE id=?";
    }else{
        sql = "INSERT INTO albums(name, author, registerTime, genre_id) VALUES(?,?,?,?)";
    }


    try(PreparedStatement pstmt = getConnection().prepareStatement(sql)){
        pstmt.setString(1,album.getName());
        pstmt.setString(2, album.getAuthor());

        if(album.getId() != null && album.getId()>0){
            pstmt.setLong(3,album.getId());
        }else{
            pstmt.setDate(3,new Date(album.getRegisterTime().getTime()));
            pstmt.setLong(4,album.getGenre_id());
        }

        pstmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement pstmt = getConnection().prepareStatement("DELETE FROM albums WHERE id=?")){
        pstmt.setLong(1,id);

        pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

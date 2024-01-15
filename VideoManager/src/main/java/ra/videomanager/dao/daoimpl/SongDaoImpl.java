package ra.videomanager.dao.daoimpl;

import org.springframework.stereotype.Repository;
import ra.videomanager.dao.ISongDao;
import ra.videomanager.model.Song;
import ra.videomanager.ulti.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SongDaoImpl implements ISongDao {
    @Override
    public List<Song> showAll() {
        List<Song> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement call = conn.prepareCall("SELECT * from song where status = true");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setId(rs.getString("id"));
                s.setSongName(rs.getString("songName"));
                s.setAuthor(rs.getString("author"));
                s.setDescription(rs.getString("description"));
                s.setImageUrl(rs.getString("imageUrl"));
                s.setVideoUrl(rs.getString("videoUrl"));
                s.setDuration(rs.getInt("duration"));
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(Song song) {
        Connection conn = ConnectDB.openConnection();
        CallableStatement call;
        try {
            if (song.getId() == null) {
                call = conn.prepareCall("insert into song(songName,author,description,imageUrl,videoUrl,duration,status) values (?,?,?,?,?,?,?)");
                call.setString(1, song.getSongName());
                call.setString(2, song.getAuthor());
                call.setString(3,song.getDescription());
                call.setString(4,song.getImageUrl());
                call.setString(5,song.getVideoUrl());
                call.setInt(6,song.getDuration());
                call.setBoolean(7,true);
            }else {
                call = conn.prepareCall("update song set songName = ? ,author = ?,description = ?,imageUrl = ?,videoUrl = ?,duration = ?,status = ? where id = ?");
                call.setString(1,song.getSongName());
                call.setString(2,song.getAuthor());
                call.setString(3,song.getDescription());
                call.setString(4,song.getImageUrl());
                call.setString(5,song.getVideoUrl());
                call.setInt(6,song.getDuration());
                call.setBoolean(7,song.isStatus());
                call.setString(8,song.getId());
            }
            call.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(String id) {
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement call = conn.prepareCall("DELETE FROM song where id = ?");
            call.setString(1,id);
            call.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Song findSongById(String id) {
        Connection conn = ConnectDB.openConnection();
        Song s = new Song();
        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM song where id = ?");
            call.setString(1,id);
            ResultSet rs = call.executeQuery();
            if(rs.next()) {
                s.setId(id);
                s.setSongName(rs.getString("songName"));
                s.setAuthor(rs.getString("author"));
                s.setDescription(rs.getString("description"));
                s.setImageUrl(rs.getString("imageUrl"));
                s.setVideoUrl(rs.getString("videoUrl"));
                s.setDuration(rs.getInt("duration"));
                s.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return s;
    }

    @Override
    public List<Song> findSongByNameOrAuthor(String search) {
        List<Song> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        Song s = new Song();
        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM song where songName like ? or author like ?");
            call.setString(1,"%" + search + "%");
            call.setString(2,"%" + search + "%");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                s.setId(rs.getString("id"));
                s.setSongName(rs.getString("songName"));
                s.setAuthor(rs.getString("author"));
                s.setDescription(rs.getString("description"));
                s.setImageUrl(rs.getString("imageUrl"));
                s.setVideoUrl(rs.getString("videoUrl"));
                s.setDuration(rs.getInt("duration"));
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }
}

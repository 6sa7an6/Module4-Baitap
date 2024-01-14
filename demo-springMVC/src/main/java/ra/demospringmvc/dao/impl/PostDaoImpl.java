package ra.demospringmvc.dao.impl;

import org.springframework.stereotype.Repository;
import ra.demospringmvc.dao.IPostDao;
import ra.demospringmvc.model.Post;
import ra.demospringmvc.ulti.ConnectDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDaoImpl implements IPostDao {
    @Override
    public List<Post> findAllPost() {
        List<Post> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM post");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setAuthor(rs.getString("author"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                Timestamp timeStamp = rs.getTimestamp("createdAt");
                LocalDateTime timeCreated = timeStamp.toLocalDateTime();
                post.setCreatedAt(timeCreated);
                post.setImageUrl(rs.getString("imageUrl"));
                list.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<Post> findAllPostByTitleOrContent(String search) {
        List<Post> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM post where title like ? or content like ?");
            call.setString(1, "%" + search + "%");
            call.setString(2, "%" + search + "%");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setAuthor(rs.getString("author"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                Timestamp timeStamp = rs.getTimestamp("createdAt");
                LocalDateTime timeCreated = timeStamp.toLocalDateTime();
                post.setCreatedAt(timeCreated);
                post.setImageUrl(rs.getString("imageUrl"));
                list.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(Post post) {
        Connection conn = ConnectDB.openConnection();
        CallableStatement call;
        try {
            if (post.getId() == null) {
                call = conn.prepareCall("insert into post(author,title,content,createdAt,imageUrl) values (?,?,?,?,?)");
                call.setString(1, post.getAuthor());
                call.setString(2, post.getTitle());
                call.setString(3, post.getContent());
                call.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                call.setString(5, post.getImageUrl());
                call.executeUpdate();
            } else {
                call = conn.prepareCall("update post set author = ?,title =?,content=?,createdAt=?,imageUrl=? where id = ?");
                call.setString(1, post.getAuthor());
                call.setString(2, post.getTitle());
                call.setString(3, post.getContent());
                call.setTimestamp(4, Timestamp.valueOf(post.getCreatedAt()));
                call.setString(5, post.getImageUrl());
                call.setLong(6, post.getId());
                call.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection conn = ConnectDB.openConnection();
        CallableStatement call;
        try {
            call = conn.prepareCall("DELETE from post where id = ?");
            call.setInt(1,id);
            call.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Post findPostById(Integer id) {
        Post post = new Post();
        Connection conn = ConnectDB.openConnection();
        CallableStatement call;
        try {
            call = conn.prepareCall("SELECT * from post where id = ?");
            call.setInt(1,id);
            ResultSet rs = call.executeQuery();
            if(rs.next()){
                post.setId(id);
                post.setAuthor(rs.getString("author"));
                post.setContent(rs.getString("content"));
                post.setTitle(rs.getString("title"));
                Timestamp timeStamp = rs.getTimestamp("createdAt");
                LocalDateTime timeCreated = timeStamp.toLocalDateTime();
                post.setCreatedAt(timeCreated);
                post.setImageUrl(rs.getString("imageUrl"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return post;
    }
}

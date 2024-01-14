package ra.demospringmvc.dao;

import ra.demospringmvc.model.Post;

import java.util.List;

public interface IPostDao {
    List<Post> findAllPost();
    List<Post> findAllPostByTitleOrContent(String search);
    void save(Post post);
    void delete(Integer id);
    Post findPostById(Integer id);
}

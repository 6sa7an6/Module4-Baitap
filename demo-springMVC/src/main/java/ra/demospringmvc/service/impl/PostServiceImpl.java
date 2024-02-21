package ra.demospringmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.demospringmvc.dao.IPostDao;
import ra.demospringmvc.model.Post;
import ra.demospringmvc.service.IPostService;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private IPostDao postDao;
    @Override
    public List<Post> findAllPost() {
        return postDao.findAllPost();
    }

    @Override
    public List<Post> findAllPostByTitleOrContent(String search) {
        return postDao.findAllPostByTitleOrContent(search);
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }
    @Override
    public void delete(Integer id) {
        postDao.delete(id);
    }

    @Override
    public Post findPostById(Integer id) {
        return postDao.findPostById(id);
    }
}

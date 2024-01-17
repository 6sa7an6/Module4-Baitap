package ra.videomanager.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.videomanager.dao.ISongDao;
import ra.videomanager.model.Song;
import ra.videomanager.service.ISongService;

import java.util.List;

@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    private ISongDao songDao;
    @Override
    public List<Song> showAll() {
        return songDao.showAll();
    }

    @Override
    public void save(Song song) {
        songDao.save(song);
    }

    @Override
    public void delete(String id) {
        songDao.delete(id);
    }

    @Override
    public Song findSongById(String id) {
        return songDao.findSongById(id);
    }

    @Override
    public List<Song> findSongByNameOrAuthor(String search) {
        return songDao.findSongByNameOrAuthor(search);
    }
}

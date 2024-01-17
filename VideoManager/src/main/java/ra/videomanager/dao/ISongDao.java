package ra.videomanager.dao;

import ra.videomanager.model.Song;

import java.util.List;

public interface ISongDao {
    List<Song> showAll();
    void save(Song song);
    void delete(String id);
    Song findSongById(String id);
    List<Song> findSongByNameOrAuthor(String search);
}

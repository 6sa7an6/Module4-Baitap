package ra.videomanager.service;

import ra.videomanager.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> showAll();
    void save(Song song);
    void delete(String id);
    Song findSongById(String id);
    List<Song> findSongByNameOrAuthor(String search);

}

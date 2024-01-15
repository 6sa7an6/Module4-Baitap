package ra.videomanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Song {
    String id , songName,author,description,imageUrl,videoUrl;
    int duration;
    boolean status;
}

package ra.demospringmvc.model;

import lombok.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {
    private Integer id;
    private String author;
    private String title;
    private String content;
/*
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
*/
    private LocalDateTime createdAt;
    private String imageUrl;
}


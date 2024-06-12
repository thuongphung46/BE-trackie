package vn.kma.hrmactvn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kma.hrmactvn.controller.post.dto.PostCreateRequest;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tk_post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "synopsis", columnDefinition = "TEXT")
    private String synopsis;

    @Column(name = "image")
    private String image;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;

    @Column(name = "modified_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp modifiedDate;

    public static Post from(PostCreateRequest request){
        return Post.builder()
                .name(request.getName())
                .description(request.getDescription())
                .synopsis(request.getSynopsis())
                .image(request.getImage())
                .build();
    }

    public void update(PostCreateRequest request){
        this.name = request.getName() != null ? request.getName() : this.name;
        this.description = request.getDescription() != null ? request.getDescription() : this.description;
        this.synopsis = request.getSynopsis() != null ? request.getSynopsis() : this.synopsis;
        this.image = request.getImage() != null ? request.getImage() : this.image;
    }
}


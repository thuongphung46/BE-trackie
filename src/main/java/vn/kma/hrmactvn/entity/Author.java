package vn.kma.hrmactvn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kma.hrmactvn.controller.author.AuthorCreateRequest;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tk_author")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "place")
    private String place;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

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

    @Transient
    private List<Post> posts;

    public static Author from(AuthorCreateRequest request) {
        LocalDate birthday = request.getBirthday() != null ? LocalDate.parse(request.getBirthday()) : null;
        return Author.builder()
                .name(request.getName())
                .birthday(birthday)
                .place(request.getPlace())
                .bio(request.getBio())
                .image(request.getImage())
                .build();
    }

    public void update(AuthorCreateRequest request) {
        LocalDate birthday = request.getBirthday() != null ? LocalDate.parse(request.getBirthday()) : null;
        this.name = request.getName() != null ? request.getName() : this.name;
        this.birthday = birthday != null ? birthday : this.birthday;
        this.place = request.getPlace() != null ? request.getPlace() : this.place;
        this.bio = request.getBio() != null ? request.getBio() : this.bio;
        this.image = request.getImage() != null ? request.getImage() : this.image;

    }

}

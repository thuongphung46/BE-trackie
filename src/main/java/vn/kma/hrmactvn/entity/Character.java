package vn.kma.hrmactvn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kma.hrmactvn.controller.character.CharacterCreateRequest;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tk_character")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "sign")
    private String sign;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "shoe_size")
    private Integer shoeSize;

    @Column(name = "favorite_food")
    private String favoriteFood;

    @Column(name = "dislikes")
    private String dislikes;

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

    public static Character from(CharacterCreateRequest request) {
        LocalDate birthday  =  request.getBirthday() != null ?  LocalDate.parse(request.getBirthday()) : LocalDate.now();
        return Character.builder()
                .name(request.getName())
                .birthday(birthday)
                .sign(request.getSign())
                .height(request.getHeight())
                .weight(request.getWeight())
                .bloodType(request.getBloodType())
                .shoeSize(request.getShoeSize())
                .favoriteFood(request.getFavoriteFood())
                .dislikes(request.getDislikes())
                .bio(request.getBio())
                .image(request.getImage())
                .build();
    }

    public void update(CharacterCreateRequest request) {
        LocalDate birthday  =  request.getBirthday() != null ?  LocalDate.parse(request.getBirthday()) : LocalDate.now();
        this.name = request.getName() != null ? request.getName() : this.name;
        this.birthday = request.getBirthday() != null ? birthday : this.birthday;
        this.sign = request.getSign() != null ? request.getSign() : this.sign;
        this.height = request.getHeight() != null ? request.getHeight() : this.height;
        this.weight = request.getWeight() != null ? request.getWeight() : this.weight;
        this.bloodType = request.getBloodType() != null ? request.getBloodType() : this.bloodType;
        this.shoeSize = request.getShoeSize() != null ? request.getShoeSize() : this.shoeSize;
        this.favoriteFood = request.getFavoriteFood() != null ? request.getFavoriteFood() : this.favoriteFood;
        this.dislikes = request.getDislikes() != null ? request.getDislikes() : this.dislikes;
        this.bio = request.getBio() != null ? request.getBio() : this.bio;
        this.image = request.getImage() != null ? request.getImage() : this.image;
    }
}

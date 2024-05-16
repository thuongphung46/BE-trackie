package vn.kma.hrmactvn.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "hrm_staff_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "staff_id")
  private Long staffId;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "gender", nullable = false)
  private int gender;

  @Column(name = "date_of_birth")
  private Timestamp dateOfBirth;

  @Column(name = "rank")
  private String rank;

  @Column(name = "identity_birth_place")
  private String identityBirthPlace;

  @Column(name = "country")
  private String country;

  @Column(name = "current_place")
  private String currentPlace;

  @Column(name = "identity_place")
  private String identityPlace;

  @Column(name = "identity_code")
  private String identityCode;

  @Column(name = "identity_date")
  private Timestamp identityDate;

  @Column(name = "place_of_issue")
  private String placeOfIssue;

  @Column(name = "favorite")
  private String favorite;

  @Column(name = "created_date", nullable = false, updatable = false)
  private Timestamp createdDate;

  @Column(name = "modified_date", nullable = false)
  private Timestamp modifiedDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "modified_by")
  private String modifiedBy;
}
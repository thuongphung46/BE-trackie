package vn.kma.hrmactvn.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "hrm_staff")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false)
  private String username;

  @JsonIgnore
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "level", nullable = false)
  private String level;

  @Column(name = "active", nullable = false)
  private Boolean active;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "personal_email")
  private String personalEmail;

  @Column(name = "work_email")
  private String workEmail;

  @Column(name = "created_date", nullable = false, updatable = false)
  private Timestamp createdDate;

  @Column(name = "modified_date", nullable = false)
  private Timestamp modifiedDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "modified_by")
  private String modifiedBy;
}
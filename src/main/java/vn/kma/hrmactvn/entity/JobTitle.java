package vn.kma.hrmactvn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import lombok.Data;

@Data
@Entity
@Table(name = "hrm_job_title")
public class JobTitle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "created_date", nullable = false, updatable = false)
  private Timestamp createdDate;

  @Column(name = "modified_date", nullable = false)
  private Timestamp modifiedDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "modified_by")
  private String modifiedBy;
}

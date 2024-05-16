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
@Table(name = "hrm_staff_working_history")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffWorkingHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "staff_id")
  private Long staffId;

  @Column(name = "date")
  private Timestamp date;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "bonus")
  private String bonus;

  @Column(name = "discipline")
  private String discipline;

  @Column(name = "created_date", nullable = false, updatable = false)
  private Timestamp createdDate;

  @Column(name = "modified_date", nullable = false)
  private Timestamp modifiedDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "modified_by")
  private String modifiedBy;
}

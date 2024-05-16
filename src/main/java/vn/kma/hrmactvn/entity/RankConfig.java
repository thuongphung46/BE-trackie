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
@Table(name = "hrm_rank_config")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankConfig {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "rank_name")
  private String rankName;

  @Column(name = "ratio")
  private Float ratio;

  @Column(name = "year")
  private Integer year;

  @Column(name = "created_date", nullable = false, updatable = false)
  private Timestamp createdDate;

  @Column(name = "modified_date", nullable = false)
  private Timestamp modifiedDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "modified_by")
  private String modifiedBy;
}
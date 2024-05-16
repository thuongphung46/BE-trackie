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
@Table(name = "hrm_staff_contract")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffContract {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "staff_id")
  private Long staffId;

  @Column(name = "contract_id")
  private Long contractId;

  @Column(name = "from_date")
  private Timestamp fromDate;

  @Column(name = "to_date")
  private Timestamp toDate;

  @Column(name = "status")
  private Integer status;

  @Column(name = "created_date", nullable = false, updatable = false)
  private Timestamp createdDate;

  @Column(name = "modified_date", nullable = false)
  private Timestamp modifiedDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "modified_by")
  private String modifiedBy;
}

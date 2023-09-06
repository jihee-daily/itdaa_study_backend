package net.itdaa.jbnu.study.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JPA 에서 Entity 객체는 사용하는 DB 의 Table 과 구조가 같아야 합니다.
 */
@Entity
@Data
@Table(name="ROAD_ADDR_INTG")
public class RoadAddrIntg {

    @Id
    @Column(name = "MGR_NO")
    private String mgrNO;                 // 관리번호 (PK)

    @Column(name = "SIDO_NAME")
    private String sidoName;              // 시도명

    @Column(name = "SIGUNGU_NAME")
    private String sigunguName;           // 시군구명

    @Column(name = "ROAD_NAME")
    private String roadName;              // 도로명

    @Column(name = "BLDG_MAIN_NO")
    private Integer bldgMainNo;           // 빌딩본번

    @Column(name = "BLDG_SUB_NO")
    private Integer bldgSubNo;            // 빌딩부번

    @Column(name = "CONST_BLDG_NAME")
    private String constBldgName;         // 건축물대장의 빌딩명

    @Column(name = "POST_CODE")
    private String postCode;              // 우편번호

    @Column(name = "FULL_ROAD_ADDR")
    private String fullRoadAddr;          // 전체 도로명 주소
}

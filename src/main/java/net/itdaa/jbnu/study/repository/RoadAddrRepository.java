package net.itdaa.jbnu.study.repository;

import net.itdaa.jbnu.study.entity.RoadAddrIntg;
import net.itdaa.jbnu.study.entity.RoadAddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DB의 Table (RoadAddrIntg Entity) 와 연결하여 Table 처리를 위한 JPA Repository 객체
 */

@Repository
public interface RoadAddrRepository extends JpaRepository<RoadAddress, String> {

    // 도로명주소를 도로명 LIKE 로 조회 ( LIKE '검색어%' )
    List<RoadAddress> findByRoadNameStartingWith(String roadName);

    // 도로명주소를 도로명 LIKE 와 빌딩본번 = 로 조회
    List<RoadAddress> findByRoadNameStartingWithAndBldgMainNo(String roadName, Integer bldgMainNo);

    // 도로명주소를 도로명,빌딩본번,빌딩부번 모두 = 로 조회
    List<RoadAddress> findByRoadNameAndBldgMainNoAndBldgSubNo(String roadName, Integer bldgMainNo, Integer bldgSubNo);

}

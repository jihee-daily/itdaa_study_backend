package net.itdaa.jbnu.study.entity;

/**
 * 실제 RestAPI 에 응답으로 전달되는 도로명 주소
 * - Entity 객체 그대로 사용하면 필요없는 정보가 응답정보로 전달되므로
 *   필요한 데이터만 응답정보로 전달하기 위해 interface 로 처리
 */
public interface RoadAddress {

    String getFullRoadAddr();
    String getPostCode();
}

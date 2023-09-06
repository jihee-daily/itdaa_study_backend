package net.itdaa.jbnu.study.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import net.itdaa.jbnu.study.entity.RoadAddress;
import net.itdaa.jbnu.study.repository.RoadAddrRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")  // API 를 호출하기 위한 주소 값입니다. 예: http://localhost:8080/api)
public class RoadAddrApiController {

    static final String resMsg       = "resMsg";
    static final String resRoadAddr  = "roadAddr";
    static final String resCnt       = "roadAddrCnt";

    ResponseEntity<?> entity = null;

    @Autowired
    RoadAddrRepository roadAddrRepository;

    @ApiOperation(value="조회할 도로명 주소(전체 or 일부)", notes="(도로명 주소의 일부 정보 or 정확한 주소)로 해당하는 도로명주소를 조회합니다.")
    @GetMapping(value="/roadAddr")  // API 를 호출하기 위한 주소 값이며 상위 주소의 하위주소값입니다. 예: http://localhost:8080/api/roadAddr)
    @ApiImplicitParams({
           @ApiImplicitParam(name = "searchRoadAddr", value = "검색할 도로명", required = true, dataType = "String", defaultValue = ""),
           @ApiImplicitParam(name = "searchRoadAddrBldgNumber", value = "검색할 빌딩본번-빌딩부번", required = false, dataType = "String", defaultValue = "")
    })
    public ResponseEntity<?> getRoadAddr(@RequestParam(value = "searchRoadAddr") String searchRoadAddress
                                        ,@RequestParam(value = "searchRoadAddrBldgNumber", required = false)  String searchBldgNumber) {


        Integer buildingMainNumber = 0;      // DB에 조회하기 위한 도로명주소 건물본번
        Integer buildingSubNumber = 0;       // DB에 조회하기 위한 도로명주소 건물부번

        // HTTP Status Code 란?  https://hongong.hanbit.co.kr/http-%EC%83%81%ED%83%9C-%EC%BD%94%EB%93%9C-%ED%91%9C-1xx-5xx-%EC%A0%84%EC%B2%B4-%EC%9A%94%EC%95%BD-%EC%A0%95%EB%A6%AC/
        HttpStatus resultStatus = HttpStatus.OK;   // 기본적으로 정상적으로 조회가 된다는 가정하에 반환하는 HTTP Status 값은 200 (OK) 입니다.

        List<RoadAddress> searchResultList = new ArrayList<>();  // DB 조회 후 데이터 값이 있을 경우 결과가 저장될 List 객체 입니다.
        Map<String,Object> returnMap = new HashMap<>();          // 실제 API Return 되는 값이 들어가는 Map 객체 입니다.

        int searchResultListSize = 0; // 최종적으로 DB에서 도로명 주소를 찾은 결과의 갯수 초기화 값은 0.

        // 실행중 예외발생을 탐지하기 위하여
        try {
            /**
             1. 입력된 searchRoadAddress 는 필수값이므로 무조건 입력되어 들어오게 됩니다.
             2. 입력된 searchBldgNumber 는 필수값이 아니므로 값이 있을수도 없을수도 있습니다.
             2-1. 만약 searchBldgNumber 가 입력되지 않을 경우 도로명을 Like 조회 해야 합니다.
             2-2. 만약 searchBldgNumber 가 입력되고, 그 값에 '-' 이 입력되지 않을 경우 건물 본번만 있는 형태입니다.
             2-3. 만약 searchBldgNumber 가 입력되고, 그 값에 '-' 이 포함되면 '건물 본번 - 건물 부번' 인 형태입니다.
             */
            // searchBldgNumber null 이 아니면 건물번호가 입력된 것 입니다.
            if (searchBldgNumber ) {

                // 건물번호가 본번 형태인지 부번 형태인지 '-' 을 기준으로 확인해야 합니다.
                String[] bldgNumberArray =

                // 건물번호가 본번만 입력된 형태라면 (예 : 흑석로 84)
                if (bldgNumberArray.length == ) {

                    // 건물번호가 문자로 되어 있으므로 숫자로 바꿔야 합니다. (DB는 숫자컬럼으로 되어 있음)
                    buildingMainNumber = Integer.parseInt();

                    // 도로명 검색어를 Like 로 하여 건물번호가 일치하는 도로명 주소를 찾습니다.
                    searchResultList = roadAddrRepository.;

                }
                // 건물번호가 본번,부번 모두 입력된 형태라면 (예 : 흑석로 84-116)
                else if (bldgNumberArray.length == ) {

                    // 건물번호(본번/부번)이 문자로 되어 있으므로 숫자로 바꿔야 합니다. (DB는 숫자컬럼으로 되어 있음)
                    buildingMainNumber = bldgNumberArray[0];
                    buildingSubNumber = bldgNumberArray[1];

                    // 도로명 검색어를 = 로 하여 건물본번, 건물부번 모두가 일치하는 도로명 주소를 찾습니다.
                    searchResultList = roadAddrRepository.;
                }
            }
            // searchBldgNumber null 이면 도로명 검색어만 입력된 것입니다.
            else {

                // 도로명 검색어를 Like 로 하여 도로명 주소를 찾습니다.
                searchResultList = roadAddrRepository.findByRoadNameStartingWith(searchRoadAddress);

            }

            searchResultListSize = searchResultList; // 최종적으로 DB에서 도로명 주소를 찾은 결과의 갯수

            // 도로명 주소가 검색된 결과가 없다면.
            if (searchResultListSize == 0) {
                resultStatus = HttpStatus.NOT_FOUND; // HTTP Status 코드는 NOT_FOUND 로 합니다. (404)
            }

            returnMap.put(resMsg, "정상처리되었습니다.");    // return 메세지는 "정상" 으로 하고
            returnMap.put(resRoadAddr, );  // return 주소정보는 조회 결과를 넣습니다.
            returnMap.put(resCnt, ); // return 건수정보는 조회 결과의 건수를 넣습니다.

        }
        // 실행중 예외가 발생할 경우
        catch (Exception e) {

            log.error(e.getMessage()); // 오류 내용을 로그로 남깁니다.
            e.printStackTrace();

            resultStatus = HttpStatus.SERVICE_UNAVAILABLE;    // HTTP Status 코드는 SERVICE_UNAVAILABLE 로 합니다. (503)
            returnMap.put(resMsg, "오류가 발생하였습니다.");      // return 메세지는 "오류발생" 으로 하고
            returnMap.put(resRoadAddr, "");                   // return 주소정보는 빈 값을 넣습니다.
            returnMap.put(resCnt, 0);                         // return 건수정보는 0 건으로 넣습니다.
        }
        // 예외여부 상관없이 최종적으로 결과를 반환합니다.
        finally {
            entity = new ResponseEntity<>(returnMap, resultStatus);  // 최종적으로 API 결과 ResponseEntity 객체를 생성합니다.

            return entity;  // API 결과 반환.
        }
    }
}

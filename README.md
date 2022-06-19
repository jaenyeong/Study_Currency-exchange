# Currency Exchange
* 환율 계산기
  * 환율 API를 활용하여 환율 계산 과제

## 미션 요구사항
* `송금 국가`는 `미국`으로 고정
* `수취 국가`는 `한국(KRW)`, `일본(JPY)`, `필리핀(PHP)` 세 군데 중 한 곳을 `select box`로 선택할 수 있을 것
* 수취 국가 선택 시 환율이 변경될 것
  * 환율은 `1 USD`에 대응하는 선택한 통화의 금액
* 송금액을 `USD`로 입력, `Submit`을 눌러 제출하면 수취 금액이 선택한 통화로 계산될 것
* 환율과 수취 금액은 소수점 2번째 자리까지, 3자리 이상인 경우 `,`를 가운데 찍어 출력
  * 예를 들어, 값이 `1234`라면 `1,234.00`으로 출력될 것

## 프로그래밍 요구사항
* 스프링 부트 등과 같은 프레임워크를 자유롭게 사용할 것
  * 다만 가급적이면 최신 버전을 사용할 것
* `HTML`, `JavaScript`, `SSR` 템플릿 엔진 등만 사용할 것
* DB는 `H2`, `Docker` 기반 `MySQL` 등을 자유롭게 사용할 것
  * 단, 특정 로컬 환경에 의존적이지 않게 구성할 것
* 환율 구현
  * [환율 정보 참조](https://currencylayer.com/)
    * 실시간으로 가져올 것
    * 웹 서버가 시작될 때 또는 매 호출할 때마다 가져와도 됨
  * 새로 무료 게정을 등록해 API 키를 받아 사용
  * 반환되는 `JSON` 타입의 환율 데이터를 활용
    ~~~json
    {
        "success":true,
        "terms":"https:\/\/currencylayer.com\/terms",
        "privacy":"https:\/\/currencylayer.com\/privacy",
        "timestamp":1545881647,
        "source":"USD",
        "quotes":{
            "USDAED":3.673197,
            "USDAFN":76.088502,
            "USDALL":108.014949,
            "USDAMD":484.684999,
            "USDANG":1.78935,
            "USDAOA":308.428019,
            "USDARS":38.025498,
            "USDAUD":1.41645,
            "USDAWG":1.8005,
            "USDAZN":1.704992,
            "USDBAM":1.720195,
            "USDBBD":2.0065,
            "USDBDT":84.095497,
            "USDBGN":1.720202,
            "USDBHD":0.377845,
            "USDBIF":1796.55,
            "USDBMD":1,
            "USDBND":1.375902,
            "USDBOB":6.926599,
            "USDBRL":3.921797,
            "USDBSD":1.002425,
            "USDBTC":0.000264,
            "USDBTN":70.202655,
            "USDBWP":10.813986,
            "USDBYN":2.143987,
            "USDBYR":19600,
            "USDBZD":2.02065,
            "USDCAD":1.359085,
            "USDCDF":1631.000242,
            "USDCHF":0.992601,
            "USDCLF":0.025048,
            "USDCLP":693.601955,
            "USDCNY":6.891597,
            "USDCOP":3302.1,
            "USDCRC":600.159866,
            "USDCUC":1,
            "USDCUP":26.5,
            "USDCVE":96.98802,
            "USDCZK":22.743597,
            "USDDJF":177.720369,
            "USDDKK":6.55895,
            "USDDOP":50.863502,
            "USDDZD":118.89004,
            "USDEGP":17.935975,
            "USDERN":15.000356,
            "USDETB":28.186971,
            "USDEUR":0.878695,
            "USDFJD":2.134978,
            "USDFKP":0.790015,
            "USDGBP":0.790055,
            "USDGEL":2.664971,
            "USDGGP":0.790081,
            "USDGHS":4.92205,
            "USDGIP":0.790015,
            "USDGMD":49.349825,
            "USDGNF":9119.750355,
            "USDGTQ":7.753973,
            "USDGYD":209.405028,
            "USDHKD":7.83205,
            "USDHNL":24.452983,
            "USDHRK":6.528297,
            "USDHTG":77.535503,
            "USDHUF":282.139656,
            "USDIDR":14562.5,
            "USDILS":3.77795,
            "USDIMP":0.790081,
            "USDINR":70.255029,
            "USDIQD":1196.15,
            "USDIRR":42105.000352,
            "USDISK":117.039989,
            "USDJEP":0.790081,
            "USDJMD":128.584974,
            "USDJOD":0.71021,
            "USDJPY":110.959498,
            "USDKES":102.009392,
            "USDKGS":69.850079,
            "USDKHR":4034.850439,
            "USDKMF":433.624965,
            "USDKPW":900.056691,
            "USDKRW":1121.419945,
            "USDKWD":0.30395,
            "USDKYD":0.835385,
            "USDKZT":371.980321,
            "USDLAK":8577.350421,
            "USDLBP":1511.700215,
            "USDLKR":181.995016,
            "USDLRD":157.49779,
            "USDLSL":14.580079,
            "USDLTL":2.95274,
            "USDLVL":0.60489,
            "USDLYD":1.398302,
            "USDMAD":9.57215,
            "USDMDL":17.241499,
            "USDMGA":3546.103567,
            "USDMKD":54.068501,
            "USDMMK":1570.350024,
            "USDMNT":2637.254916,
            "USDMOP":8.087899,
            "USDMRO":356.999895,
            "USDMUR":34.397439,
            "USDMVR":15.449742,
            "USDMWK":730.9697,
            "USDMXN":19.894202,
            "USDMYR":4.176501,
            "USDMZN":61.490214,
            "USDNAD":14.579558,
            "USDNGN":365.14006,
            "USDNIO":32.539886,
            "USDNOK":8.74611,
            "USDNPR":112.505037,
            "USDNZD":1.48712,
            "USDOMR":0.38613,
            "USDPAB":1.002415,
            "USDPEN":3.3677,
            "USDPGK":3.37735,
            "USDPHP":52.72027,
            "USDPKR":140.215019,
            "USDPLN":3.76165,
            "USDPYG":5935.90292,
            "USDQAR":3.641105,
            "USDRON":4.073703,
            "USDRSD":103.902084,
            "USDRUB":68.953405,
            "USDRWF":895.105,
            "USDSAR":3.760902,
            "USDSBD":8.20555,
            "USDSCR":13.683499,
            "USDSDG":47.736004,
            "USDSEK":9.066405,
            "USDSGD":1.37275,
            "USDSHP":1.320899,
            "USDSLL":8599.9997,
            "USDSOS":580.000195,
            "USDSRD":7.457965,
            "USDSTD":21050.59961,
            "USDSVC":8.770703,
            "USDSYP":514.999392,
            "USDSZL":14.583503,
            "USDTHB":32.607967,
            "USDTJS":9.44805,
            "USDTMT":3.5,
            "USDTND":3.00425,
            "USDTOP":2.25965,
            "USDTRY":5.277175,
            "USDTTD":6.794603,
            "USDTWD":30.796503,
            "USDTZS":2299.901063,
            "USDUAH":27.443502,
            "USDUGX":3716.096617,
            "USDUSD":1,
            "USDUYU":32.344501,
            "USDUZS":8350.549968,
            "USDVEF":9.987501,
            "USDVND":23336.2,
            "USDVUV":113.783789,
            "USDWST":2.626846,
            "USDXAF":576.94015,
            "USDXAG":0.06652,
            "USDXAU":0.000787,
            "USDXCD":2.70255,
            "USDXDR":0.721204,
            "USDXOF":576.940096,
            "USDXPF":104.903214,
            "USDYER":250.349931,
            "USDZAR":14.523899,
            "USDZMK":9001.202279,
            "USDZMW":11.953972,
            "USDZWL":322.355011
        }
    }
    ~~~
  * 아래 어떤 방법으로 구현하든 무관
    * 환율을 미리 계산해 `HTML/JavaScript(프론트)`에서 캐싱하여 통화(국가)를 변경할 때마다 출력 가능
    * 통화(국가)를 변경할 때마다 서버에 요청하여 정보를 받는 것도 가능
  * 다음과 같은 경우 `송금액이 바르지 않습니다`라는 에러 문구 출력 (팝업, 하단에 빨간 텍스트로 표기)
    * 송금액을 아예 입력하지 않은 경우
    * `0`보다 작은 음수값을 입력하는 경우
    * 올바른 숫자가 아닌 다른 값을 입력하는 경우

## 구현 중 특이 사항

### 스프링 부트 서버 실행 경고
* `OpenJDK 64-Bit Server VM warning: Options -Xverify:none and -noverify were deprecated in JDK 13 and will likely be removed in a future release.`
  * `-Xverify:none` and `-noverify` 옵션은 향후 JDK 13 버전 이후에 지원되지 않을 수 있음

## `Currencylayer` API

### `API Response Properties`
* `success`
  * 쿼리 성공 여부
* `terms`
  * `Currencylayer` 이용 약관에 대한 링크
* `privacy`
  *  `Currencylayer` 개인 정보 보호 정책에 대한 링크
* `timestamp`
  * 환율이 수집된 정확한 날짜-시간(`UNIX`) 반환
* `source`
  * 기준이 되는 통화 (기본값: `USD`)
* `quotes`
  * 통화, 변환율 쌍으로 구성된 모든 환율 값 목록

## 구현 기술

### `RestTemplate` 사용
`HTTP` 요청 시 사용하는 클라이언트 모듈
* `RestTemplate`
  * 기존 `Sync` 방식의 요청 클라이언트 모듈
  * 현재는 스프링 진영에서 유지만 하고 있음
  * [문서](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/web/client/RestTemplateBuilder.html)

#### `WebClient`와 비교
`WebClient`는 `Async` 방식의 요청 모듈
* `Reactor`, `Netty` 등과 같은 라이브러를 통해 API를 제공
* [WebClient, RestTemplate 비교](https://www.baeldung.com/spring-webclient-resttemplate)
* 최종적으로 현재 요구사항으로는 `WebClient`까지 사용하지 않아도 된다고 판단

### `DecimalFormat`
* 소수점 2번째 자리까지의 출력 포맷을 위해 사용
  * 주어진 조건이 `BigDecimal`을 사용할 만큼 크지 않아 `double`로 구현
* [변경 방법 참고](https://baeldung-cn.com/java-double-to-string)

package kr.maxerve.admin.utils;

public final class CommonCodeUtil {
	public static String COMMON_CODE_EMPTY = "000";

	/**
	 * 차량종류
	 * @author khs
	 *
	 */
	public enum _001 {
		ROOT("001"),
		_001("001001"),   // 승용/SUV
		_002("001002"),   // 미니버스
		_003("001003"),   // 버스/트럭
		_004("001004");   // 기타

		private final String value;

		private _001(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 예약장소
	 * @author khs
	 *
	 */
	public enum _002 {
		ROOT("002"),
		_001("002001"),   // 미래청(회의/세미나)
		_002("002002"),   // 야외공간
		_003("002003"),   // 메이커스페이스
		_004("002004"),   // 우드파크
		_005("002005"),   // 모두모임방
		_006("002006"),   // 미래청(녹음/운동)
		_007("002007"),   // 파크투어
		_008("002008"),   // 극장동
		_009("002009"),   // 맛동
		_010("002010");   // 참여동

		private final String value;

		private _002(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 파일종류
	 * @author khs
	 *
	 */
	public enum _003 {
		ROOT("003"),
		_001("003001"),   // 이미지
		_002("003002"),   // 동영상
		_003("003003"),   // 문서
		_004("003004"),   // ZIP 압축
		_005("003005");   // 바이너리

		private final String value;

		private _003(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 자료유형
	 * @author khs
	 *
	 */
	public enum _004 {
		ROOT("004"),
		_001("004001"),   // 일반/문서
		_002("004001"),   // 이미지
		_003("004001");   // 동영상

		private final String value;

		private _004(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 경영지원 문의유형
	 * @author khs
	 *
	 */
	public enum _005 {
		ROOT("005"),
		_001("005001"),    // 경영
		_002("005002"),    // 회계
		_003("005003"),    // 노무
		_004("005004");    // 기타


		private final String value;

		private _005(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 소셜종류
	 * @author khs
	 *
	 */
	public enum _006 {
		ROOT("006"),
		_001("006001"),  // 페이스북
		_002("006002"),  // 트위터
		_003("006003"),  // 구글
		_004("006004"),  // 네이버
		_005("006005"),  // 다음
		_006("006006"),  // 카카오
		_007("006007");  // 인스타그램

		private final String value;

		private _006(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 공감토크 종류
	 * @author khs
	 *
	 */
	public enum _007 {
		ROOT("007"),
		_001("007001"),   // 찬반
		_002("007002"),   // 설문
		_003("007003");   // 토론

		private final String value;

		private _007(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 문의유형
	 * @author khs
	 *
	 */
	public enum _008 {
		ROOT("008"),
		_001("008001"),    // 입주
		_002("008002"),    // 시설사용
		_003("008003"),    // 행사
		_004("008004");    // 기타

		private final String value;

		private _008(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 카테고리 종류
	 * @author khs
	 *
	 */
	public enum _009 {
		ROOT("009"),
		_001("009001"),   // 혁신멤버
		_002("009002"),   // 프로젝트
		_003("009003"),   // 이벤트/행사
		_004("009004");   // 자료실

		private final String value;

		private _009(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 관계소재지
	 * @author khs
	 *
	 */
	public enum _010 {
		ROOT("010"),
		_001("010001"),   // 혁신멤버
		_002("010002"),   // 제안
		_003("010003"),   // 공감토크
		_004("010004"),   // 프로젝트
		_005("010005"),   // 이벤트/행사 카테고리
		_006("010006"),   // 시설
		_007("010007"),   // 자료실
		_008("010008"),   // 구인
		_009("010009"),   // 경영지원
		_010("010010"),   // 입주신청
		_011("010011"),	  // 메인
		_012("010012"),	  // 공지사항
		_013("010013"),	  // 댓글
		_018("010018");	  // 시설예약

		private final String value;

		private _010(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 프로젝트 분류
	 * @author khs
	 *
	 */
	public enum _011  {
		ROOT("011"),
		_001("011001");	// 공모전

		private final String value;

		private _011(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 교육/행사 카테고리
	 * @author khs
	 *
	 */
	public enum _012 {
		ROOT("012"),
		_001("012001");	// 교육

		private final String value;

		private _012(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 설문종류
	 * @author khs
	 *
	 */
	public enum _013 {
		ROOT("013"),
		_001("013001"),  // 객관식
		_002("013002");  // 주관식

		private final String value;

		private _013(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}



	/**
	 * 단체형태
	 * @author khs
	 *
	 */
	public enum _014 {
		ROOT("014"),
		_001("014001"),   // 일반 주식회사
		_002("014002"),   // 사회적기업
		_003("014003"),   // 비영리민간단체
		_004("014004"),   // 일반 협동조합
		_005("014005"),   // 사회적 협동조합
		_006("014006"),   // 개인 또는 임의단체
		_007("014007");   // 기타

		private final String value;

		private _014(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 회원유형
	 * @author khs
	 *
	 */
	public enum _015 {
		ROOT("015"),
		_001("015001"),  // 입주업체
		_002("015002"),  // 비입주업체
		_003("015003");  // 혁신가

		private final String value;

		private _015(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 위치정보종류
	 * @author khs
	 *
	 */
	public enum _016 {
		ROOT("016"),
		_001("016001"),   // 구글맵설정
		_002("016002");   // 이미지등록

		private final String value;

		private _016(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 채널구분
	 * @author khs
	 *
	 */
	public enum _017 {
		ROOT("017"),
		_001("017001"),   // 홈페이지
		_002("017002"),   // 페이스북
		_003("017003"),   // 트위터
		_004("017004"),   // 카카오스토리
		_005("017005"),   // 인스타그램
		_006("017006"),   // 유투브
		_007("017007"),   // 블로그
		_008("017008");   // 직접입력

		private final String value;

		private _017(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 투어방문목적
	 * @author khs
	 *
	 */
	public enum _018 {
		ROOT("018"),
		_001("018001"),   // 관람
		_002("018002"),   // 답사
		_003("018003"),   // 벤치마킹
		_004("018004"),   // 연구
		_005("018005");   // 기타

		private final String value;

		private _018(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 자료실 카테고리
	 * @author khs
	 *
	 */
	public enum _019 {
		ROOT("019");

		private final String value;

		private _019(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 예약진행상태
	 * @author khs
	 *
	 */
	public enum _020 {
		ROOT("020"),
		_001("020001"),    // 검토중
		_002("020002"),    // 승인완료
		_003("020003"),    // 예약완료
		_004("020004"),    // 예약보류
		_005("020005");    // 예약취소

		private final String value;

		private _020(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 로그위치
	 * @author khs
	 *
	 */
	public enum _021 {
		ROOT("021"),
		_001("021001"),    // 메인
		_002("021002"),    // 혁신멤버 목록
		_003("021003"),    // 혁신멤버 상세
		_004("021004"),    // 후원신청
		_005("021005"),    // 제안목록
		_006("021006"),    // 제안상세
		_007("021007"),    // 제안등록
		_008("021008"),    // 공감토크 상세
		_009("021009"),    // 종료된 공감토크 목록
		_010("021010"),    // 프로젝트 목록
		_011("021011"),    // 프로젝트 상세
		_012("021012"),    // 프로젝트 등록
		_013("021013"),    // 이벤트/행사 목록
		_014("021014"),    // 이벤트/행사 상세
		_015("021015"),    // 이벤트/행사 등록
		_016("021016"),    // 파크투어 안내
		_017("021017"),    // 파크투어 신청
		_018("021018"),    // 파크투어 신청완료
		_019("021019"),    // 시설예약 현황
		_020("021020"),    // 시설예약 현황
		_021("021021"),    // 우드파크 월회원 신청
		_022("021022"),    // 자료실 목록
		_023("021023"),    // 자료실 상세
		_024("021024"),    // 자료실 등록
		_025("021025"),    // 리서치랩 자료실 목록
		_026("021026"),    // 구인정보 목록
		_027("021027"),    // 구인정보 상세
		_028("021028"),    // 구인정보 등록
		_029("021029"),    // 경영지원 목록
		_030("021030"),    // 경영지원 상세
		_031("021031"),    // 경영지원 등록
		_032("021032"),    // 결제하기
		_033("021033"),    // 결제완료
		_034("021034"),    // 시설안내
		_035("021035"),    // 입주안내
		_036("021036"),    // 입주신청
		_037("021037"),    // 뉴스레터 목록
		_038("021038"),    // 뉴스레터 상세
		_039("021039"),    // 센터공지 목록
		_040("021040"),    // 센터공지 상세
		_041("021041"),    // FAQ
		_042("021042"),    // Q&A
		_043("021043"),    // 통합검색
		_044("021044"),    // 메뉴 통합검색
		_045("021045"),    // 혁신멤버 통합검색
		_046("021046"),    // 혁신활동 통합검색
		_047("021047"),    // 자료실 통합검색
		_048("021048"),    // FAQ통합검색
		_049("021049"),    // 센터공지 통합검색
		_050("021050"),    // 혁신멤버 가입
		_051("021051"),    // 로그인
		_052("021052"),    // 아이디 찾기
		_053("021053"),    // 비밀번호 찾기
		_054("021054"),    // 비밀번호 재설정
		_055("021055"),    // 멤버정보 수정
		_056("021056"),    // 내가 쓴 글
		_057("021057"),    // 내가 쓴 댓글
		_058("021058"),    // 문의내역
		_059("021059"),    // 문의내역 상세
		_060("021060"),    // 예약신청 내역
		_061("021061"),    // 예약신청 내역상세
		_062("021062"),    // 혁신파크소개
		_063("021063"),    // 개인정보취급방침
		_064("021064"),    // 오시는 길
		_065("021065"),    // 유관사이트
		_066("021066"),    // 사이트맵
		_067("021067"),    // 블로그 메인
		_068("021068"),    // 블로그 검색
		_069("021069"),    // 블로그 혁신활동 목록
		_070("021070"),    // 센터 알리미 목록
		_071("021071"),    // 업체소식 목록
		_072("021072"),    // 커뮤니티 채널 카테고리 설정
		_073("021073"),    // 블로그 게시물 목록
		_074("021074"),    // 블로그 게시물 상세
		_075("021075"),    // 블로그 게시물 등록
		_076("021076");    // 블로그 설정

		private final String value;

		private _021(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 입주신청상태
	 * @author khs
	 *
	 */
	public enum _022 {
		ROOT("022"),
		_001("022001"),  // 심사중
		_002("022002"),  // 입주보류
		_003("022003");  // 입주확정

		private final String value;

		private _022(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 관리자메뉴
	 * @author khs
	 *
	 */
	public enum _023 {
		ROOT("023"),
		_001("023001"),	// 기본관리
		_002("023002"),	// 메인관리
		_003("023003"),	// 회원관리
		_004("023004"),	// 혁신활동관리
		_005("023005"), // 시설/투어 관리
		_006("023006"),	// 정산관리
		_007("023007");	// 혁신파크 관리

		private final String value;

		private _023(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 관리자메뉴 > 기본관리
	 * @author khs
	 *
	 */
	public enum _023001 {
		ROOT("023001"),
		_001("023001001"),   // 관리자계정관리
		_002("023001002"),   // 관리자 권한관리
		_003("023001003"),   // 카테고리 관리
		_004("023001004"),   // 태그관리
		_005("023001005"),   // SMS관리
		_006("023001006");   // 방문자수

		private final String value;

		private _023001(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 관리자메뉴 > 메인관리
	 * @author khs
	 *
	 */
	public enum _023002 {
		ROOT("023002");

		private final String value;

		private _023002(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 관리자메뉴 > 회원관리
	 * @author khs
	 *
	 */
	public enum _023003 {
		ROOT("023003"),
		_001("023003001"),    // 입주단체관리
		_002("023003002"),    // 신규입주신청
		_003("023003003");    // 온라인멤버

		private final String value;

		private _023003(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 관리자메뉴 > 혁신활동관리
	 * @author khs
	 *
	 */
	public enum _023004 {
		ROOT("023004"),
		_001("023004001"),    // 제안
		_002("023004002"),    // 프로젝트
		_003("023004003"),    // 이벤트/행사
		_004("023004004"),    // 자료실
		_005("023004005");    // 설문결과

		private final String value;

		private _023004(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 관리자메뉴 > 시설/투어관리
	 * @author khs
	 *
	 */
	public enum _023005 {
		ROOT("023005"),
		_001("023005001"),    // 시설예약현황
		_002("023005002"),    // 시설일정관리
		_003("023005003"),    // 시설정보
		_004("023005004"),    // 파크투어 신청현황
		_005("023005005");    // 파크투어 일정관리

		private final String value;

		private _023005(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 관리자메뉴 > 정산관리
	 * @author khs
	 *
	 */
	public enum _023006 {
		ROOT("023006"),
		_001("023006001"),    // 결제
		_002("023006002"),    // 업체별 정산
		_003("023006003");    // 후원금

		private final String value;

		private _023006(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 관리자메뉴 > 혁신파크관리
	 * @author khs
	 *
	 */
	public enum _023007 {
		ROOT("023007"),
		_001("023007001"),    // 센터공지 목록
		_002("023007002"),    // 뉴스레터 목록
		_003("023007003");    // 구인관리

		private final String value;

		private _023007(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 단순리소스 종류
	 * @author khs
	 *
	 */
	public enum _024 {
		ROOT("024"),
		_001("024001"),   // 인기검색어
		_002("024002"),   // SMS 시설예약
		_003("024003"),   // SMS 투어예약
		_004("024004");   // 자료실인기태그

		private final String value;

		private _024(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 입주그룹
	 * @author khs
	 *
	 */
	public enum _025 {
		ROOT("025"),
		_001("025001"),     // 혁신센터
		_002("025002"),     // 청년허브
		_003("025003"),     // 사회적경제지원센터
		_004("025004"),     // 서북50플러스캠퍼스
		_005("025005");     // 마을공동체종합지원센터

		private final String value;

		private _025(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 성별
	 * @author khs
	 *
	 */
	public enum _026 {
		ROOT("026"),
		_001("026001"),    // 남성
		_002("026002");    // 여성

		private final String value;

		private _026(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 플랫폼
	 * @author khs
	 *
	 */
	public enum _027 {
		ROOT("027"),
		_001("027001"),   // PC
		_002("027002");   // 모바일

		private final String value;

		private _027(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 우드파크 클래스 사용료 구분
	 * @author khs
	 *
	 */
	public enum _028 {
		ROOT("028"),
		_001("028001"),   // 클래스
		_002("028002");   // 월회원제

		private final String value;

		private _028(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 시설구분
	 * @author khs
	 *
	 */
	public enum _031 {
		ROOT("031"),
		_001("031001"),   // 입주단체
		_002("031003"),   // 회의/세미나
		_003("031003");   // 기타시설

		private final String value;

		private _031(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 신청경로
	 * @author khs
	 *
	 */
	public enum _032 {
		ROOT("032"),
		_001("032001"),   // 국문
		_002("032002");   // 영문

		private final String value;

		private _032(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
}

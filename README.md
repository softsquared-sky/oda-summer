# summerfactory
 

노트 
8.25 -> 자동로그인 체크박스 부분 이미지 구현해야함

8.26 ->	라디오 버튼들 라디오 그룹화 해야함(java로) 라디오 버튼 이미지 변환도 (java) 에서 처리하기
	activity_sign_up.xml -> 가입하기 버튼! enable 및 입력처리 추후 java와 작업

8.27 -> splash UI OK , login UI Ok , join UI Ok(중복 조회 와 가입하기 enable 처리 java에서 해야함)

8.27 Search 프래그먼트 UI 제작중 리스트 더미 작업필요


Search 페이지에 최근검색어는 프래그먼트에 리스트뷰를 추가하여 관리하고 인기검색어도 마찬가지로 리스트뷰를 추가하여 관리한다.

하지만 검색어를 입력후 엔터키 버튼 입력시 -> 쿼리를 날린다->json을 받아온다->list형태로 뿌린다.

8.29 14시 28분 :  Search UI - 최근검색어 100%(리스트 역순 구현,add 시 0번부터 넣는 로직)

Popular은 미구현

8.29 - 22:26분 : 메인 화면 구축60%(리사이클러뷰 헤더 및 footer 추가하기) 바텀바 적용 OK
코디네이터레이아웃 미적용, 리사이클러뷰 스크롤에 따라 visible 구현하기

8.30 - SignUpActivity 기능 90%(중복확인API 미구현)
버그 - 통신시에 콜백이 onFailure()로 바로 가는데 서버에서는 정상적으로 회원가입 API 성공

8.31일 SignUpActivity setting 100%(서버 측  중복확인 API 미구현)
통신문제 버그 해결
 

9.1 Main UI 80% StickyHeader,맨위로 스크롤,FAB 미구현
-header와 footer 추가

9.2 -detail productDetail UI 90% , Viewpager 동적 연동ok
ViewPager 크기에 따라서 자동으로 맞춰주는 메소드
https://wimir-dev.tistory.com/56 

9-3 상품 후기 프래그먼트 내에 리스트뷰 구현시 스크롤뷰 내에 리스트뷰가 되어있어 스크롤이 먹히지 않는 현상

viewpager내에 프래그먼트 내에 리스트뷰라 구조가 복잡하여 해결X(질문해야함)

detail UI 100%(기능미구현및 서비스미구현) - 16:45

미완성 기능
Main
-Main에서 전체선택시 전체선택하는 메소드 어댑터 내에 allCheck 미구현*
-아이템 체크시 갯수 증가
-아이템 클릭시 API 요청 후 detail로 넘어가기 

Search
 recent 
  -최근검색시 중복 제거 및 위로 올라오는 로직 미구현
  -detail 액티비티 및 API 미연동


Detail-Review 에서 스크롤뷰 안에 리스트뷰

 mLvProductReview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mLvProductReview.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


를 통해 해결, 하지만 UI적으론 미흡


9.4 
Splash jwt 토큰으로 서버통신

9.5



로그인 액티비티 OK
회원가입 액티비티 OK 
스플래쉬 OK
Main UI OK - 전체선택및 바로주문,체크박스와텍스트연동X
detail UI 90% - 리뷰부분 서비스 미연결 (서버 데이터 부족 및 API 변수 다름)
Response에 있는 것을 Item으로 옮겨서 작업해야함

장바구니 UI 100%(custom footer 미구현)
 -전체선택활성화 및 색상변경미적용(상품이있을때보여주고 없을땐 회색처리미구현)
	즉 초기상태 미구현

	전체선택 체크박스연동 X (메인과 동일함)
	체크박스누를시 주문요청하기 버튼 enable및색상변경
	체크박스 누를시 아이템 price 합계를 오다가에 더해주는 로직 미구현


전체적인 icon 크기 조절 

9.6 장바구니 frame UI 구성 기능 미구현

9.6 19:08 주문확인영수증 UI OK


인기검색어

기술미구현
최근검색어 자료구조set
장바구니 프레임
장바구니 전체체크
메인액티비티 전체체크 및 바로주문 , 스크롤 과 fab 연동



서비스 연동해야할것들
detail - review 
장바구니 담기 API
바로주문
마이페이지
후기등록

UI - 해야할것들
detail 하단 수량
후기등록 UI
검색시 UI


9.7 마이페이지 UI 100% ,설정 UI 100%
총갯수,서비스연결,

바로주문 API OK 
메인 OK
장바구니 OK
설정OK
마이페이지(API미연동)
주문OK(서비스미연동)
영수증OK
로그인OK
회원가입OK
상세정보OK
후기(서비스미연동)

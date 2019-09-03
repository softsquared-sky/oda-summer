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





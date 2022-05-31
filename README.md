# airbnb
> 12조

## 팀원

|iOS|BE|
|---|---|
|[sally](https://github.com/sally4405)|[루이](https://github.com/Louie-03)|
|[bibi](https://github.com/bibi6666667)|[동기](https://github.com/donggi-lee-bit)|

---

## 작업 내용 
- 프로젝트 셋팅 : SPM 설정 (RxSwift, Alamofire)
- 탭 바 컨트롤러에 3개의 VC 추가 
- 검색 탭의 NavigationBar에 SearchBar 추가 
- Mapkit 사용하여 지역 검색 기능 추가
- CalendarView 추가 
  - 날짜 선택 기능은 미완성 

- 검색 뷰의 '근처 인기 여행지'화면을 뷰컨트롤러가 아닌 뷰(`PopularLocationView`)로 변경 
  - LocationResultView에서 searchBar.text에 따라 show/hide하도록 변경 
- 검색 화면의 위치, 날짜, 요금, 인원을 표시하는 테이블뷰와 지우기/다음 버튼 추가 

---

## 결과물

### iOS
|근처의 인기 여행지 화면과 지역 입력 화면|지역 검색 결과가 포함된 날짜 입력 화면|
|---|---|
|<img width="320" src="https://user-images.githubusercontent.com/45891045/171108401-e1671d38-e4a8-4c4e-ba0d-748eafea96b9.gif">|<img width="320" src="https://user-images.githubusercontent.com/45891045/171108407-c35b1d20-bd6a-44c3-8f57-9454f017d784.gif">|

---

## 고민과 해결

### iOS
- 검색 홈 화면에서 UINavigationController를 UITabBarController의 탭에 Embed할 때, 그냥 넣는 게 아닌  rootViewController로 설정해야 함
  - [참고 자료](https://stackoverflow.com/questions/43961766/uinavigationcontroller-and-tabbarcontroller-programmatically-no-storyboards)
- 뷰를 클로저로 생성할 때 self.에 접근하기 위해 lazy로 몇몇 프로퍼티를 선언

---

## Document
- [Ground Rule](https://github.com/sally4405/airbnb/wiki/Ground-Rule)
- [기획서](https://www.figma.com/proto/inTClwuq2Hr7E33JPIMKza/%EB%AA%A8%EB%B0%94%EC%9D%BC_%EC%88%99%EC%86%8C%EC%98%88%EC%95%BD%EC%84%9C%EB%B9%84%EC%8A%A4?page-id=56%3A1424&node-id=56%3A1972&viewport=25%2C336%2C0.03&scaling=contain)
- [디자인 가이드](https://www.figma.com/file/inTClwuq2Hr7E33JPIMKza/%EB%AA%A8%EB%B0%94%EC%9D%BC_%EC%88%99%EC%86%8C%EC%98%88%EC%95%BD%EC%84%9C%EB%B9%84%EC%8A%A4?node-id=56%3A2206)


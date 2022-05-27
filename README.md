# airbnb
> 12조

## 팀원

|iOS|BE|
|---|---|
|[sally](https://github.com/sally4405)|[루이](https://github.com/Louie-03)|
|[bibi](https://github.com/bibi6666667)|[동기](https://github.com/donggi-lee-bit)|

---

## 작업 내용

- 프로젝트 셋팅 
- SwiftPackageManager로 RxSwift, Alamofire 추가 
- 검색 탭바에 대한 SearchViewController 만들기 시작 
- 검색 탭 첫 화면 구성 
- 검색 탭바의 위치 입력 화면 구성(진행 중) 고민과 해결 
- UIImageView.image에 넣을 원본 이미지를 화면 크기에 맞게 적절히 비율 조정하기 
- UISearchController와 UINavigationController 활용해 위치 검색 화면 구성

---

## 결과물

### iOS
|메인 화면과 검색창 클릭시 화면 이동|
|---|
|<img width="320" src="https://user-images.githubusercontent.com/67407678/170634454-f3055aca-2e6c-4c8f-bdff-fbebccb1cd43.gif">|

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


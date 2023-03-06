# 🚀sMarket 기술 공유 플랫폼
- 판매자가 자신의 기술을 공유할 수 있는 플랫폼
- 기술 공유를 위해서 1대1 채팅, 음성 채팅, 화상 채팅 기능을 제공
## ✨목표
- 최종 시연과 투표를 통하여 사용자의 입장에서 이용하기 편리한 서비스인지 평가 받을 수 있다
- 정해진 일정 안에서 축소 없이 개인의 목표를 달성할 수 있다.

## ✨기술 스택
- Android 스택
  - Kotlin
  - Android Studio
  - Figma
  - STOMP
  - POSTMAN


## ✨구현기능
   - 로그인/회원가입
   - 홈 (당근마켓과 유사) → 게시글 리스트
   - 검색바
   - 카테고리 별 검색
   - 판매화면(글 제목, 가격 입력, 게시글 내용 작성)
   - 내 정보
    - 판매 내역
    - 비밀번호, 닉네임 변경
    - 로그아웃
   - 토론방 (그룹 채팅)


## ✨구현 내용
- 회원가입
  - 아이디 중복확인
  - 아이디, 비밀번호 정해진 양식에 맞는지 확인
  - 비밀번호 일치 확인


  ![KakaoTalk_20230222_140110410_01](https://user-images.githubusercontent.com/59364681/220527024-7a1020d3-cc2d-40e6-bb80-77165e90bc54.jpg)

- 로그인
  - 일반 로그인
  - JWT 로그인


  ![KakaoTalk_20230222_140110410](https://user-images.githubusercontent.com/59364681/220527004-755a963e-08c7-44bf-b467-bf07e2f7c197.jpg)


- 홈화면
  - 게시글 리스트 제공
  - 검색 기능
  
  ![KakaoTalk_20230222_140110410_02](https://user-images.githubusercontent.com/59364681/220527073-cf76ef38-a9b8-4475-be31-01fee323adac.jpg)
  ![KakaoTalk_20230222_140110410_05](https://user-images.githubusercontent.com/59364681/220527106-efbd0f7d-dde2-4926-a140-5ba7c61d2dbf.jpg)
  ![KakaoTalk_20230222_140110410_06](https://user-images.githubusercontent.com/59364681/220527130-a5391e54-bcce-4a09-813c-f4b8777961d7.jpg)


- 카테고리 검색
  - 카테고리 별로 게시글을 볼 수 있음
  
  ![KakaoTalk_20230222_140110410_07](https://user-images.githubusercontent.com/59364681/220527155-f900e9a6-7d71-4926-9592-fb2897420ac5.jpg)
  ![KakaoTalk_20230222_140110410_08](https://user-images.githubusercontent.com/59364681/220527217-6e7d6814-3938-40dc-af1c-2f39c81a9b4e.jpg)

- 게시글 작성


 ![KakaoTalk_20230222_140110410_04](https://user-images.githubusercontent.com/59364681/220527242-7a0193e2-7460-4da4-8913-25557e221ff7.jpg)


- 게시글 보기
  - 게시글 내용 자세히 보기
  - 작성자라면 수정 및 삭제 가능
  - 실시간 토론방으로 입장 가능
  
  ![KakaoTalk_20230222_140110410_03](https://user-images.githubusercontent.com/59364681/220527325-130828a7-05ab-40a0-8047-fcab4a72b331.jpg)


- 실시간 토론방
  - 게시글을 통해서 들어갈 수 있음
  - 다대다 실시간 그룹채팅 가능

  ![KakaoTalk_20230222_142231096](https://user-images.githubusercontent.com/59364681/220530314-dba8ba64-0764-46f5-b069-ce71bc4417fd.jpg)



- 내 정보
  - 구매내역, 판매내역 열람
  - 비밀번호, 닉네임 변경 가능
  - 로그아웃
 
![KakaoTalk_20230222_140110410_10](https://user-images.githubusercontent.com/59364681/220527409-c75f16fa-dbd3-4ac3-824a-f97b9f09d370.jpg)
![KakaoTalk_20230222_140110410_11](https://user-images.githubusercontent.com/59364681/220527417-6f53a181-f279-4678-b0a9-c2d21d6fbe01.jpg)
![KakaoTalk_20230222_140110410_12](https://user-images.githubusercontent.com/59364681/220527423-bab26ea2-133b-45d0-85fc-fef84608a37a.jpg)
![KakaoTalk_20230222_140110410_13](https://user-images.githubusercontent.com/59364681/220527436-bc7086cf-e35d-4875-87b3-1af18286a68c.jpg)

  

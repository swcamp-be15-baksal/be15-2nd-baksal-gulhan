# 계속 수정 예정 렉 걸려서 한번 올려요
<br>

![걸한 1800X300 (1)](https://github.com/user-attachments/assets/3df0757b-d859-4703-8145-2814e6a24228)
<br><br>
## 💥 박살 팀원 소개
<div align="center">
<table>
  <tr>
    <td align="center"><img src="https://github.com/user-attachments/assets/e3a393d6-4769-4549-9a0a-e357ac3aeb5a" width="1300" /></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/18944db2-9365-4755-a3e4-a1df705703ac" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/95b87238-502e-4530-adb1-5094821c443d" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/af5b61de-ac42-45a5-8831-3f1e491706a8" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/9334b47b-f650-4a83-97c4-42892d6ed4bd" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/74ea7d1e-12a4-4610-928e-13860ac3a2b2" width="1300"/></td>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/hwan1023">김태환</a></td>
    <td align="center"><a href="https://github.com/chaeyeong1219">윤채영</a></td>
    <td align="center"><a href="https://github.com/kkkwid">이승재</a></td>
    <td align="center"><a href="https://github.com/Cho-Hyun-Seung">조현승</a></td>
    <td align="center"><a href="https://github.com/ckaudgh">차명호</a></td>
    <td align="center"><a href="https://github.com/dxmlk">한성경</a></td>
  </tr>
</table>

</div>
<br>


## 📍 목차

 <a href="#1">1. 프로젝트 기획</a>
  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#1-1">1-1. 프로젝트 소개</a>

  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#1-2">1-2. 주요 기능</a>

  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#1-3">1-3. WBS(Work Breakdown Structure)</a>

  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#1-4">1-4. 요구사항 명세서 </a>

 <a href="#2">2. DB 모델링</a>

 <a href="#3">3. API 명세서 </a>

 <a href="#4">4. 배포 및 운영 </a>

 <a href="#5">5. 기술 스택 </a>

 <a href="#6">6. 테스트코드 </a>
 
<a href="#6">7. 트러블슈팅 </a>

<br>


## <p id="1"> 📑 1. 프로젝트 기획</p>

### <p id="1-1">1-1. 프로젝트 소개</p>
![speech](https://capsule-render.vercel.app/api?type=speech&height=200&fontSize=45&color=gradient&text=걸어서-nl-한국속으로&animation=blinking&fontAlign=30,60&fontAlignY=35,55)

**걸한**은 박물관, 유적지, 민속촌에 대한 정보를 한눈에 볼 수 있는 **역사·문화 여행 통합 플랫폼**으로 시설 운영 정보, 기념품 구매, 패키지 예약 등 다양한 서비스를 하나의 플랫폼에서 편리하게 이용할 수 있습니다. 

<br><br>

### <p id="1-2">1-2. 주요 기능</p>
|서비스 도메인|기능 설명|
|:--:|:--:|
|회원 기능|자체/소셜 회원가입<br>기념품 구매 시 적립금 획득<br>회원 등급제 (노비, 평민, 중인, 양반, 왕)<br>회원 정보 수정, 탈퇴|
|역사·문화 장소|박물관/유적지/민속촌 목록 및 상세 조회<br>조건별(지역, 이름) 필터링<br>관심 등록 및 해제|
|패키지·체험|패키지 목록 및 상세 조회<br>조건별(지역, 일자) 필터링<br>관심 등록 및 해제<br>장바구니 및 결제<br>적립금 사용<br>구매내역 및 배송 조회<br>리뷰 작성·수정·삭제|
|기념품|기념품 목록 및 상세 조회<br>조건별(종류) 필터링<br>장바구니 및 결제<br>적립금 사용<br>구매내역 및 배송 조회<br>리뷰 작성·수정·삭제|
|마이페이지|관심 장소/기념품/패키지 관리<br>회원 등급 조회<br>내가 쓴 동행글/댓글 조회<br>구매내역 조회 및 결제 취소<br>리뷰 관리<br>동행 모집글/댓글 확인|
|관리자 기능|대시보드(판매량·판매수익 조회)<br>기념품/패키지 등록·수정·삭제<br>운송장 정보 관리<br>공지사항 등록·수정·삭제<br>리뷰 관리<br>사용자 적립금 관리|
<br><br>

### <p id="1-3">1-3. WBS(Work Breakdown Structure)</p>
<details>
<summary>WBS 임시사진</summary>
<div>
  <img src="https://github.com/user-attachments/assets/e8bf8bb2-f56b-4f55-a2f8-59b08c67c634"/>  
</div>
</details>
<br><br>

### <p id="1-4">1-4. 요구사항 명세서</p>
<details>
<summary>요구사항 명세서 임시사진</summary>
<div >
  <img src="https://github.com/user-attachments/assets/e8bf8bb2-f56b-4f55-a2f8-59b08c67c634"/>  
</div>
</details>
<br><br>

##  <p id="2"> 🛢️ 2. DB모델링</p>
erdcloud 넣어야지
<br><br>


##  <p id="3"> 🗃️ 3. API 명세서 </p>
Swagger 넣어야지
<br><br>


## <p id="4"> 🤖 4. 배포 및 운영 </p>
MSA 넣어야지
<br><br>

## <p id="5"> 🔧 5. 기술 스택 </p>
<div align="center">
<div dir="auto">
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"/>
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"/>
</div>
<div dir="auto">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/ERDCLOUD-339AF0?style=for-the-badge&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">
</div>
</div>

<br><br>

##  <p id="6"> 🐞 6. 트러블슈팅</p>

### 6-1. 어떤 문제 
#### 1️⃣ 에러 발생 코드

``` 

```


#### 3️⃣ 발생 원인 
``` 

```

#### 4️⃣ 해결 방법 
``` 

```

<br>

##  <p id="6"> 🧑‍💻 6. 테스트코드? </p>
##  <p id="7"> 🧑‍💻 7. 흠... </p>

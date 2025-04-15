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

 <a href="#2">2. DDD 설계 </a>

 <a href="#3">3. DB 모델링</a>

 <a href="#4">4. API 명세서 </a>

 <a href="#5">5. 배포 및 운영 </a>

 <a href="#6">6. 기술 스택 </a>

 <a href="#7">7. 팀원 회고 </a>

<br>

---

## <p id="1"> 📑 1. 프로젝트 기획</p>

### <p id="1-1">1-1. 프로젝트 소개</p>
> #### 프로젝트 개요
![speech](https://capsule-render.vercel.app/api?type=speech&height=200&fontSize=45&color=gradient&text=걸어서-nl-한국속으로&animation=blinking&fontAlign=30,60&fontAlignY=35,55)

**걸한**은 박물관, 유적지, 민속촌에 대한 정보를 한눈에 볼 수 있는 **역사·문화 여행 통합 플랫폼**으로 시설 운영 정보, 기념품 구매, 패키지 예약 등 다양한 서비스를 하나의 플랫폼에서 편리하게 이용할 수 있습니다. 
<br> <br>

> #### 기획 배경

국립 및 사립 박물관·유적지의 국내·외 **관람객 수 지속적으로 증가**하고 있다. 2023년 국립중앙박물관의 관람객 수는 418만 명으로 세계 6위 수준이라고 한다. 특히 외국인 관광객이 전년 대비 2배 이상 증가하였으며, '전시+체험+기념품' 패키지형 프로그램 등 **문화 체험 수요도 증가**하고 있다. 그러나 이러한 관광 및 체험 수요 증가에도 불구하고 정보는 굉장히 **파편화**되어 있는데, 관련 정보를 얻기 위해서는 기관 개별 사이트를 통해 확인해야 하며, 예약 시스템도 각기 달라 **비교 및 접근이 불편**하다.

<br>

> #### 필요성

✅ 전국 박물관·민속촌·유적지 정보를 통합하여 정보 접근성의 한계 극복 <br>
✅ 패키지 예약 기능을 제공하여 관광객들의 편의성 증대 <br>
✅ 역사·문화 체험 예약을 모바일 기반 플랫폼의 디지털로 전환하여 사용자의 편의성 증대

<br>

> #### 차별점

✅ 기존의 분산화된 정보 대신 하나의 플랫폼에서 모든 정보 제공 <br>
✅ 단일 장소 체험 대신 여러 장소를 가이드의 해설과 체험할 수 있는 복합형 패키지 <br>
✅ 적립금 및 관심 기능으로 사용자의 편의성 및 지속적인 이용 유도

<br>

> #### 서비스 대상

🧍전시·체험을 즐기는  **20~50대 내·외국인 개별 여행객** <br>
👨‍👩‍👧‍👦 자녀와 함께 문화체험을 원하는 **가족 단위** 사용자 <br>
🧑‍🎓 **학교 및 단체** 프로그램 참여자 <br>
🧑‍🚀 한국의 전통문화에 관심 있는 **외국인 관광객** 

<br>

> #### 기대 효과

걸한은 복잡한 검색 없이도 사용자들에게 통합된 정보를 제공함으로써 **문화 접근성이 향상**될 것이다. 또한 맞춤형 패키지를 통해 **관광 유입을 유도하고 지역 경제를 활성화**하며, 회원 등급제/적립금 리워드 시스템/관심 등록 등으로 **사용자 재방문율 및 충성도 향상**에 기여한다. 이뿐만 아니라, 사용자 행동 데이터를 기반으로 구매 패턴을 분석하고 관심도를 추적하여 **타겟 마케팅 및 상품 최적화 전략 수립**도 가능할 것이라고 기대한다.

<br>

> #### 추후 방향성

 추후에 **다국어 기능**을 추가하여 외국 관광객의 편의성을 제고하고, 모바일 앱을 출시하여 접근성을 강화할 예정이다. 국내에서도 **박물관 및 지자체와의 전략적 제휴 및 문화유산 교육 콘텐츠와의 연계**를 통해서 지역 연계형 체험 프로그램을 개발하는 등 협력 모델을 구축할 것이다.    


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
[![WBS](https://img.shields.io/badge/WBS-바로가기-red?style=for-the-badge)]([https://docs.google.com/spreadsheets/d/1wghof-f87BU-JfmXJzU21RelVI2vohon8qLOsyW2D_k/edit?gid=0#gid=0])
<details>
<summary>미리보기(아직 안 넣음)</summary>
<div>
  <img src="https://github.com/user-attachments/assets/e8bf8bb2-f56b-4f55-a2f8-59b08c67c634"/>  
</div>
</details>
<br><br>

### <p id="1-4">1-4. 요구사항 명세서</p>
[![요구사항명세서](https://img.shields.io/badge/요구사항명세서-바로가기-yellow?style=for-the-badge)]([https://docs.google.com/spreadsheets/d/1wghof-f87BU-JfmXJzU21RelVI2vohon8qLOsyW2D_k/edit?gid=619006843#gid=619006843])
<details>
<summary>미리보기</summary>
<div >
  <img src="https://github.com/user-attachments/assets/751fc07a-cd8d-4dee-9043-257ea60704fd"/>  
</div>
</details>
<br><br>



##  <p id="2"> 📜 2. DDD 설계</p>
[![DDD](https://img.shields.io/badge/DDD-바로가기-blue?style=for-the-badge)]([https://miro.com/app/board/uXjVINvacm4=/])

<br><br>


##  <p id="3"> 🛢️ 3. DB모델링</p>
[![ERDCLOUD](https://img.shields.io/badge/ERDCloud-바로가기-green?style=for-the-badge)]([https://www.erdcloud.com/d/99TuoiE7CtAbEm8F7])
<details>
<summary>미리보기</summary>
<div >
  <img src="https://github.com/user-attachments/assets/3c8cac48-f8ef-4aca-9d08-62d6a3772a8e"/>  
</div>
</details>
<br><br>



##  <p id="4"> 🗃️ 4. API 명세서 </p>
<details>
<summary>미리보기</summary>
<div >
  <img src="https://github.com/user-attachments/assets/15dc1101-6471-45cd-b839-598d7ab947bb"/>  
</div>
</details>
<br><br>



## <p id="5"> 🤖 5. 배포 및 운영 </p>
<details>
<summary>MSA 서버 구조도</summary>
<div >
  <img src="https://github.com/user-attachments/assets/32c6c2ba-73f6-45a8-a782-01ae05303d77"/>  
</div>
</details>

<br><br>

## <p id="6"> 🔧 6. 기술 스택 </p>
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

##  <p id="7"> 👨‍👩‍👧‍👦 7. 팀원 회고 </p>

|이름|회고|
|:--:|:--:|
|김태환|김태환 회고|
|윤채영|윤채영 회고|
|이승재|이승재 회고|
|조현승|조현승 회고|
|차명호|차명호 회고|
|한성경|한성경 회고|



<br>

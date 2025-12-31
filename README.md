# 🐷 Future Pig - 커뮤니티 게시판 프로젝트

> Java Spring Boot + React 기반의 간단한 커뮤니티 웹 애플리케이션

---

## 📌 프로젝트 소개

Future Pig는 Spring Boot로 백엔드를, React로 프론트엔드를 구축한 커뮤니티 웹 애플리케이션입니다.  
사용자는 게시글을 작성하고, 댓글을 달고, 좋아요를 눌러 상호작용할 수 있습니다.

---

## 🛠️ 사용 기술 스택

### 📦 Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 / MySQL (선택)
- Lombok
- Maven

### 💻 Frontend
- React.js (CRA 또는 Vite)
- Axios
- React Router
- CSS Modules

### ☁️ DevOps
- GitHub
- IntelliJ / VSCode
- Postman / Thunder Client
- RESTful API 설계

---

## 🗂️ 프로젝트 구조

future_pig/
├── src/
│ ├── main/
│ │ ├── java/com/project/community/
│ │ │ ├── controller/
│ │ │ ├── domain/
│ │ │ ├── dto/
│ │ │ ├── repository/
│ │ │ └── service/
│ │ └── resources/
│ │ ├── application.properties
│ │ └── templates/
│ └── test/java/com/project/community/
│
├── react-app/
│ ├── src/
│ │ ├── posts/
│ │ ├── css/
│ │ ├── Home.js
│ │ └── axiosInstance.js
│ └── public/
│ └── index.html
│
├── pom.xml
└── README.md

yaml
코드 복사

---

## 📸 주요 기능

| 기능명        | 설명                             |
|---------------|----------------------------------|
| 🔐 회원 기능   | 로그인, 로그아웃, 회원가입 기능  |
| 📝 게시판 기능 | 글 작성, 조회, 수정, 삭제        |
| 💬 댓글 기능   | 댓글 등록, 삭제                  |
| ❤️ 좋아요     | 게시글 좋아요 토글 기능         |
| 🔍 목록 필터   | 게시글 목록 정렬 및 필터링 기능 |

---

## 🚀 실행 방법

### 1️⃣ 백엔드 서버 실행
```bash
cd future_pig
./mvnw spring-boot:run
2️⃣ 프론트엔드 실행
bash
코드 복사
cd react-app
npm install
npm start
💡 API 연동
.env 파일에 백엔드 주소 설정 필요 (ex: REACT_APP_API=http://localhost:8080)

📌 향후 계획
✅ JWT 로그인 연동

✅ 게시글 좋아요 구현

🔄 프론트엔드 디자인 개선

🔄 배포 환경 구성 (EC2 or Vercel)

📫 연락처
개발자: 이나흠

GitHub: skgma1019


---

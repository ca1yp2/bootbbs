# Spring Boot 게시판 프로젝트

Spring Boot를 기반으로 구현한 간단한 **게시판(Board)** 웹 애플리케이션입니다.  
JPA, Thymeleaf, MySQL을 이용한 CRUD 구현과 파일 업로드 기능을 포함하고 있으며, HTML 이스케이프 처리를 위해 Jsoup 라이브러리를 사용합니다.

---

## 📌 주요 기능

- 게시글 목록 조회, 등록, 수정, 삭제
- 게시글 상세 페이지
- 파일 업로드 및 다운로드 ( 현재 오류 발생으로 추후 개선 예정 )
- MySQL 연동 및 JPA를 통한 데이터 저장
- Thymeleaf 기반 템플릿 엔진 적용
- HTML 태그 이스케이프 처리 (`jsoup` 사용)
- Spring Devtools를 통한 빠른 개발(Java 17로 변경 후 사용 가능)

---

## 🛠 기술 스택

- **Java 11**
- **Spring Boot 3.4.4**
- **Spring Web / JPA / Thymeleaf**
- **MySQL**
- **Lombok**
- **Jsoup** – HTML 이스케이프 처리
- **Gradle**

---

## ⚙️ 프로젝트 실행 방법

1. **MySQL DB 생성**
   ```sql
   CREATE DATABASE bootbbs DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
   ```

2. **.env** 설정
   ```.env
   DB_URL=DB의 URL
   DB_USERNAME=DB 유저네임
   DB_PASSWORD=DB 비밀번호
   ```

3. BootbbsApplication.java 클릭 후 실행
   
---

## 📁 프로젝트 구조

```
bootbbs/
├── src/
│    ├── main
│    │    ├── java.net.ca1yps.bootbbs
│    │    ├── config
│    │    │   └── WebConfig.java
│    │    ├── controller
│    │    │   ├── BoardController.java
│    │    │   └── FileController.java
│    │    ├── entity
│    │    │   ├── Board.java
│    │    │   └── FileEntity.java
│    │    ├── repository
│    │    │   ├── BoardRepository.java
│    │    │   └── FileRepository.java
│    │    ├── service
│    │    │   ├── BoardService.java
│    │    │   └── FileService.java
│    │    ├── util
│    │    │   └── FileUploadUtil.java
│    │    └── BootbbsApplication.java
│    ├── resources
│    │       ├── static
│    │       │     ├── css
│    │       │     ├── images
│    │       │     ├── js
│    │       │     └── upload
│    │       └── templates
│    │               ├── inc
│    │               │    ├── footer.html
│    │               │    └── header.html
│    │               ├── edit.html
│    │               ├── list.html
│    │               ├── view.html
│    │               └── write.html
│    └── application.yml
├── .gitattributes
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── HELP.md
├── README.md
└── settings.gradle
```

---

## ✅ 사용된 주요 라이브러리

```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-devtools'
implementation 'org.jsoup:jsoup:1.19.1'
runtimeOnly 'com.mysql:mysql-connector-j'
```
---

## 📌 TODO (향후 예정 기능)
- 게시글 댓글 기능 추가
- 파일 업로드 기능 에러 수정
- 로그인/회원가입 기능 추가
- 관리자 페이지 구현


---

## 📄 라이선스

MIT 라이선스 (MIT License)

저작권 (c) 2025 ca1yp2

본 프로젝트는 학습 및 개인 포트폴리오 용도로 제작되었습니다. 
본 소프트웨어 및 관련 문서 파일(이하 "소프트웨어")을 무상으로 획득한 모든 사람에게
소프트웨어를 제한 없이 사용, 복사, 수정, 병합, 출판, 배포, 서브라이선스 및 판매할 권리를
허가합니다.

단, 위 저작권 표시와 이 허가 표시를 소프트웨어의 모든 복사본 또는 중요한 부분에 포함시켜야 합니다.

본 소프트웨어는 상품성, 특정 목적 적합성 및 비침해에 대한 보증 없이 "있는 그대로" 제공됩니다.
저작권자 또는 저작권 보유자는 소프트웨어 사용 또는 기타 거래와 관련하여 발생하는
어떠한 청구, 손해 또는 기타 책임에 대해서도 책임을 지지 않습니다.

---

MIT License

Copyright (c) 2025 ca1yp2

This project was created for learning and personal portfolio purposes.
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
## 요구사항  
1. 요구사항에 맞게 Entity를 설계하고 ERD 만들기  
2. 회원가입 API  
	1. Username,password를 전달받음  
	2. username은 최소 4자 이상, 10자 이하, 알파벳 소문자, 숫자로 구성  
	3. password는 최소 8자 이상, 15자 이하, 알파벳 대소문자, 숫자로 구성  
3. 로그인 API  
	1. Username,password를 전달받음   
	2. DB에서 username 유무 확인하고 password비교  
	3. 로그인 성공 시 JWT토큰 발급  
	4. 헤더에 성공 메시지, 상태코드와 함께 반환하기   

## 요구 수정사항  
1. 전체 게시글 목록 조회 API
	1. 제목, 작성자명, 작성 내용, 작성 날짜 조회하기  
	2. 작성 날짜 기준으로 내림차순 정렬  
2. 게시글 작성 API  
	1. 토큰 검사 후 유효하면 작성 가능  
	2. 제목, 작성자명, 작성 내용 저장  
	3. 저장된 게시글 반환  
3. 선택 게시글 조회 API  
	1. 제목, 작성자명, 작성 날짜, 작성 내용을 조회  
4. 선택 게시글 수정 API  
	1. 토큰 검증 후 유효한 토큰이면 수정 가능  
	2. 제목, 작성 내용을 수정하고 반환  
5. 선택 게시글 삭제 API  
	1. 토큰 검증 후 유효한 토큰이면 수정 가능  
	2. 삭제하고 성공했다는 메시지, 상태코드 반환  

## 요구사항 API 명세서  
| 기능     | URL | Method | Request Header | Request            | Response |  Response Header   | 
| -------- | --- | ------ | -------------- | --------------------------------- | --------- | --- |
| 회원가입 |  /api/auth/signup   |    POST    |                | { </br>"username": "bin1234",</br> "password": "Bin@12345",</br> "admin": "true"</br> } |       { </br>"msg": "회원가입 성공", </br>"statusCode": 200 </br>}   |     |
| 로그인   |  /api/auth/login   |     POST   |                |   {</br> "username": "bin1234", </br>"password": "Bin@12345" </br>}                 |     {</br> "msg": "로그인 성공",</br> "statusCode": 200 </br>}     |   Authorization: Bearer eyJhbGciOiJIUz</br>I1NiJ9.eyJzdWIiOiJiaW4xMjM0IiwiZXhwIjoxNjY5OD</br>cwNDUyLCJpYXQiOjE2Njk4NjY4NTJ9.mm</br>8wgaV8M70hidhPX4Ut6UONZGaxjA1KnOJT1mO59Xc  |

## 요구사항 체크리스트
- [x] 요구사항에 맞게 Entity를 설계하고 ERD 만들기
- [x] 회원가입 API
	- [x] username은 최소 4자 이상, 10자 이하, 알파벳 소문자, 숫자로 구성
	- [x] password는 최소 8자 이상, 15자 이하, 알파벳 대소문자, 숫자로 구성  
	- [x] 헤더에 성공 메시지, 상태코드와 함께 반환하기
- [x] 로그인 API
	- [x] DB에서 username 유무 확인하고 password비교
	- [x] 로그인 성공 시 JWT토큰 발급
	- [x] 헤더에 성공 메시지, 상태코드와 함께 반환하기  

## lv.3 요구사항 추가  
- [ ] 회원 권한 부여하기(ADMIN,USER) - ADMIN회원은 모든 게시글, 댓글 수정/삭제 가능  
	- [x] 회원가입시 어드민 / 유저 멤버에 입력  
	- [x] 댓글 수정 / 삭제  
	- [ ] 게시글 수정 / 삭제  
		- [ ] 멤버에서 userRole 가져오기  
		- [ ] Admin이면 AllPass  

## 요구 수정사항 API 명세서  
| 기능             | URL | Method | Request Header | Request | Response | Response Header |
| ---------------- | --- | ------ | -------------- | ------- | -------- | --------------- |
| 게시글 작성 |  /api/post   |  POST   |  Bearer eyJhbGciOiJIU</br>zI1NiJ9.eyJzdWIiOiJiaW4</br>xMjM0IiwiZXhwIjoxNjc2MT</br>gzMzQwLCJpYXQiOjE2NzYxN</br>zk3NDB9.LVPP_5QIi5defwn1</br>QX10mmtwA6RlnsrHJVy_z6cBKIg | { </br>"title": "게시글5", </br>"content": "내용5" </br>} | { </br>"id": 5,</br> "title": "게시글5",</br> "content": "내용5", </br>"username": "bin1234", </br>"createdAt": "2022-12-01T12:56:36.821474",</br>"modifiedAt": "2022-12-01T12:56:36.821474" </br>}  |                 |
| 게시글 목록 조회 |  /api/post   |  GET  |    |         |     {</br>{ </br>"id": 5, </br>"title": "게시글5", </br>"content": "내용5",</br> "username": "bin1234", </br>"createdAt": "2022-12-01T12:56:36.821474", </br>"modifiedAt": "2022-12-01T12:56:36.821474" </br>} </br>,{ </br>"id": 5, </br>"title": "게시글5", </br>"content": "내용5", </br>"username": "bin1234", </br>"createdAt": "2022-12-01T12:56:36.821474", </br>"modifiedAt": "2022-12-01T12:56:36.821474" </br>}</br> }     |                 |
| 게시글 상세 조회 |   /api/post/{id}  |  GET  |   |         |    { </br>"id": 5, </br>"title": "게시글5",</br> "content": "내용5",</br> "username": "bin1234",</br> "createdAt": "2022-12-01T12:56:36.821474",</br> "modifiedAt": "2022-12-01T12:56:36.821474" </br>}       |                 |
| 게시글 수정 |  /api/post/{id}   |  PUT  | Bearer eyJhbGciOiJIU</br>zI1NiJ9.eyJzdWIiOiJiaW4</br>xMjM0IiwiZXhwIjoxNjc2MT</br>gzMzQwLCJpYXQiOjE2NzYxN</br>zk3NDB9.LVPP_5QIi5defwn1</br>QX10mmtwA6RlnsrHJVy_z6cBKIg |   {</br> "title": "게시글5", </br>"content": "내용5" </br>}     |    {</br> "id": 5, </br>"title": "게시글5", </br>"content": "내용5", </br>"username": "bin1234", </br>"createdAt": "2022-12-01T12:56:36.821474", </br>"modifiedAt": "2022-12-01T12:56:36.821474" </br>}       |                 |
| 게시글 삭제 |  /api/post/{id}   |  DELETE  | Bearer eyJhbGciOiJIU</br>zI1NiJ9.eyJzdWIiOiJiaW4</br>xMjM0IiwiZXhwIjoxNjc2MT</br>gzMzQwLCJpYXQiOjE2NzYxN</br>zk3NDB9.LVPP_5QIi5defwn1</br>QX10mmtwA6RlnsrHJVy_z6cBKIg |         |  {</br> "msg": "게시글 삭제 성공",</br> "statusCode": 200 </br>}  |                 |

## ERD  
![ERD.png](./ERD.png)  

## 요구 수정사항 체크리스트  
- [x]  전체 게시글 목록 조회 API 
	- [x]  제목, 작성자명, 작성 내용, 작성 날짜 조회하기 
	- [x]  작성 날짜 기준으로 내림차순 정렬 
- [x] 게시글 작성 API 
	- [x] 토큰 검사 후 유효하면 작성 가능 
	- [x] 제목, 작성자명, 작성 내용 저장 
	- [x] 저장된 게시글 반환 
- [x] 선택 게시글 조회 API  
	- [x] 제목, 작성자명, 작성 날짜, 작성 내용을 조회 
- [x] 선택 게시글 수정 API 
	- [x] 토큰 검증 후 유효한 토큰이면 수정 가능 
	- [x] 제목, 작성 내용을 수정하고 반환 
- [x] 선택 게시글 삭제 API 
	- [x] 토큰 검증 후 유효한 토큰이면 수정 가능 
	- [x] 삭제하고 성공했다는 메시지, 상태코드 반환 


## 트러블 슈팅  
1. @RequestBody 없으면 null 값 저장

## 미해결  
- [x] findByUsername ?  
- [ ] @Slf4j  
- [ ] 토큰 검사 시 else 후에 예외처리가 필요한가?  
- [x] 아이디 중복 확인  
- [ ] post 리턴하면 왜 스택오버플로우?? 
- [x] findAllByOrderByModifiedAtDesc()   


## lv.3 요구사항  
- [x] 댓글 작성 API  
	- [x] 토큰을 검사하여 유효한 토큰일 경우 댓글 작성 가능  
	- [x] 게시글이 DB에 있는지 유무 확인  
	- [x] 게시글이 있다면 댓글을 등록하고 댓글 반환  
- [x] 댓글 수정 API  
	- [x] 토큰을 검사하여 유효한 토큰일 경우 댓글 작성 가능  
	- [x] 댓글이 DB에 저장 유무 확인  
	- [x] 댓글이 있다면 댓글을 등록하고 등록된 댓글 반환  
- [x] 댓글 삭제 API 
	- [x] 토큰을 검사하여 유효한 토큰일 경우, 해당 사용자일 경우 삭제 가능  
	- [x] 댓글의 DB 저장 유무  
	- [x] 댓글이 있다면 삭제하고 성공 메시지, 상태코드 반환  
- [ ] 예외처리  
	- [ ] 토큰 x 또는 정상 토큰이 아닌 경우 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400  
	- [ ] 토큰 o, 유효하지만 해당 사용자의 글이 아닌경우 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400  
	- [ ] 이미 존재하는 username으로 회원가입 하는 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400  
	- [ ] 로그인시 username과 password가 맞지 않다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client  


## lv.3 요구사항 API 명세서  
| 기능      | URL                         | Method | Request Header                                                                                                                                                        | Request                                                    | Response                                                                             | Response Header |
| --------- | --------------------------- | ------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------- | ------------------------------------------------------------------------------------ | --------------- |
| 댓글 작성 | /api/comment/{id}           | POST   | Bearer eyJhbGciOiJIU</br>zI1NiJ9.eyJzdWIiOiJiaW4</br>xMjM0IiwiZXhwIjoxNjc2MT</br>gzMzQwLCJpYXQiOjE2NzYxN</br>zk3NDB9.LVPP_5QIi5defwn1</br>QX10mmtwA6RlnsrHJVy_z6cBKIg | {</br>"author" : "author",</br> "content", "content"</br>} | {</br>"comment", "comment",</br>"author" : "author",</br> "content", "content"</br>} |                 |
| 댓글 수정 | /api/comment/{comment} | PUT    | Bearer eyJhbGciOiJIU</br>zI1NiJ9.eyJzdWIiOiJiaW4</br>xMjM0IiwiZXhwIjoxNjc2MT</br>gzMzQwLCJpYXQiOjE2NzYxN</br>zk3NDB9.LVPP_5QIi5defwn1</br>QX10mmtwA6RlnsrHJVy_z6cBKIg | {</br>"author" : "author",</br> "content", "content"</br>} | {</br>"comment", "comment",</br>"author" : "author",</br> "content", "content"</br>}                           |                 |
| 댓글 삭제 | /api/comment/{comment} | DELETE | Bearer eyJhbGciOiJIU</br>zI1NiJ9.eyJzdWIiOiJiaW4</br>xMjM0IiwiZXhwIjoxNjc2MT</br>gzMzQwLCJpYXQiOjE2NzYxN</br>zk3NDB9.LVPP_5QIi5defwn1</br>QX10mmtwA6RlnsrHJVy_z6cBKIg |                                                            | {</br> "msg": "게시글 삭제 성공",</br> "statusCode": 200 </br>}                      |                 |


## 미해결  
comment와 member와의 관계  




bin14   
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaW4xNCIsImV4cCI6MTY3NjM1NDk2NywiaWF0IjoxNjc2MzUxMzY3fQ.ki6lwVZ0gcoRD7LnZmxMOEV_IFM79EyU44sK9L8tO_I

bin33  
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaW4zMyIsImV4cCI6MTY3NjM1NDk4OCwiaWF0IjoxNjc2MzUxMzg4fQ.L-7lNomss4rbAps_yDbt7nZOtvei7xM_kch7-4MwWWQ  

admin  
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NjM1NTExOSwiaWF0IjoxNjc2MzUxNTE5fQ.5WukhZ8C1W_8lGFvRTKMNKXb7Xb1Zp28dvZRUnEEbj4
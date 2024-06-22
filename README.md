# Swagger UI + Spring Rest docs = restdocs-api-spec
Swagger UI 장점인 클라이언트 직접 테스트 가능
Spring Rest Docs 장점인 테스트 코드를 통한 API 문서화
두 장점을 합쳐서 사용할 수 있는 restdocs-api-spec 라이브러리 사용 방법

## 순서
1. build.gradle 파일에 restdocs-api-spec 라이브러리 추가
2. CORS 설정을 위해 SecurityConfig 작성
3. 테스트 코드 작성
4. build.gradle 등록한 openapi3 명령을 실행
5. restdocs-api-spec 라이브러리를 통한 openapi3.json 파일 생성
6. docker 통한 Swagger UI 실행

### restdocs-api-spec 문서화를 위한 테스트 코드 실행 및 문서 생성
```shell
./gradlew openapi3
```
> 해당 과정을 거치면 build/api-spec 디렉토리에 openapi3.json 파일이 생성

### docker 직접 실행 명령
```shell title:"Swagger UI 실행"
docker run -d -p 80:8080 --name swagger -e SWAGGER_JSON=/tmp/openapi3.json -v {openapi13.json 파일이 위치한 디렉토리 경로}:/tmp swaggerapi/swagger-ui
```

### docker-compose 실행
```shell title:"docker-compose.yml"
version: '3'
services:
  swagger-ui:
    image: swaggerapi/swagger-ui
    container_name: swagger
    ports:
      - "80:8080"
    volumes:
      - {openapi13.json 파일이 위치한 디렉토리 경로}:/tmp
    environment:
      SWAGGER_JSON: /tmp/openapi3.json
```
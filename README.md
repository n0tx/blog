# Springboot Restful Blog API Application
> Springboot, H2DB, Spring Security, JWT, JPA, Rest API, List Pagination and Unit Test


## How to setup and run
1. [Using Docker](#using-docker)

2. [Using the cloned github source](#using-the-cloned-github-source)


## Using Docker
- [pull docker image](#pull-docker-image)
- [check downloaded docker images](#check-downloaded-docker-images)
- [run the docker image](#run-the-docker-image)
- [access h2db via browser](#access-h2db-via-browser)
- [access swagger openapi](#access-swagger-openapi)
- [test list api endpoints that do not have authorization checks](#test-list-api-endpoints-that-do-not-have-authorization-checks)

---

- #### ***pull docker image***
 
```text
docker pull rcandra612/blog-api:latest
```

![image](https://github.com/n0tx/blog/assets/44139279/7b82d4c2-4078-4a76-88db-03cc8ed587b8)


![image](https://github.com/n0tx/blog/assets/44139279/e18424cc-2ac7-4f45-bcf9-fb1881cfa940)
   

- #### ***check downloaded docker images***
   
```text
docker images
```

![image](https://github.com/n0tx/blog/assets/44139279/3202eda8-1777-4bb2-a4c3-9484f9e06846)


- #### ***run the docker image***

```text
docker run -p 8080:8080 c2680123e9c3
```

![image](https://github.com/n0tx/blog/assets/44139279/fba33799-b5ab-481e-94ac-8e73e76f43ef)


- #### ***access h2db via browser***

> url address
```text
http://localhost:8080/h2-ui
```

> jdbc url
```text
jdbc:h2:mem:blog
```

![image](https://github.com/n0tx/blog/assets/44139279/8516fee6-6c8a-4b75-8791-14727eace546)


![image](https://github.com/n0tx/blog/assets/44139279/82b7cad8-696d-4297-8cc4-b3cf9e5b348c)


- #### ***access swagger openapi***

```text
http://localhost:8080/swagger-ui/index.html
```

![image](https://github.com/n0tx/blog/assets/44139279/423cfea7-487f-44aa-bb72-53f194226599)


- #### ***test list api endpoints that do not have authorization checks***

![image](https://github.com/n0tx/blog/assets/44139279/23a1ce87-94ac-468d-9221-0851ace0afa7)

- #### ***try running on api endpoing list***

![image](https://github.com/n0tx/blog/assets/44139279/c9e21edb-fd7e-4247-9ba3-ba3929f90d86)


- #### ***get results from get request list api endpoint***

![image](https://github.com/n0tx/blog/assets/44139279/ebe665d9-b742-4e53-8825-6b08e2b75e8a)

- #### ***test endpoints that use authorization***

![image](https://github.com/n0tx/blog/assets/44139279/89501b9d-afce-4595-9408-a070ab248230)

- #### ***must login first to get token***

![image](https://github.com/n0tx/blog/assets/44139279/711493f6-1f87-4cf5-8ff2-d990bc81004c)

- #### ***existing users:***
`admin`, `riki`

> `admin`, full granted for add and change data (create, update, delete)

- #### ***the endpoint requires authorization***

![image](https://github.com/n0tx/blog/assets/44139279/9481b96c-7cee-4c70-ae2a-fb1d4de40523)
 
![image](https://github.com/n0tx/blog/assets/44139279/a6522a11-a97a-41e7-9ca5-0a8ceff8e890)

![image](https://github.com/n0tx/blog/assets/44139279/9930187c-823d-4846-922c-203a3a5954c7)

- #### ***using user and password admin for login authentication to get the token***

![image](https://github.com/n0tx/blog/assets/44139279/6636e119-53c0-41f5-9df5-a452583b9d8c)

- #### ***input user admin, password admin at login post request***

```json
{
   "usernameOrEmail": "admin",
   "password": "admin"
}
```

![image](https://github.com/n0tx/blog/assets/44139279/5fced688-0dd1-4293-9a06-2e0e08e00b69)

- #### ***success login and get the token***

![image](https://github.com/n0tx/blog/assets/44139279/6816bfef-63d0-45cc-a77c-1d954c60a118)

- #### ***try the endpoint which need the token authorization***

![image](https://github.com/n0tx/blog/assets/44139279/ebde202c-05da-493c-9d50-279a74e24968)

- #### ***input the token***

```text
token example
```

```text
   eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJyaWtpQG1haWwuY29tIiwiaWF0IjoxNzAwNjgxODgzLCJleHAiOjE3MDEyODY2ODN9.R29gAfz4xPror1utBX3AE8_XH-FVMISeoUP4I4zW1Ryufvorey49NvOKSP5GFX-Z
```

![image](https://github.com/n0tx/blog/assets/44139279/9c30de23-d007-4eb9-9528-198cdea9e8bf)

- #### ***try to send post data request to createBlog***

```json
{ 
   "id": 0,
   "title": "title test post",
   "body": "body test post",
   "userId": "1"
}
```

![image](https://github.com/n0tx/blog/assets/44139279/4abbc433-b9df-4ef2-98ef-e4d9c34ac216)

- #### ***get `created or 201` HTTP status response***

![image](https://github.com/n0tx/blog/assets/44139279/d63859d0-caab-4da3-af4e-0138e04a142c)

- #### ***check the database at posts table***

```text
http://localhost:8080/h2-ui
```

![image](https://github.com/n0tx/blog/assets/44139279/310c1c5b-830d-4719-80ae-186c28c1e4ca)


## Using the cloned github source

- #### ***git clone souce from github***

```text
git clone https://github.com/n0tx/blog.git
```

![image](https://github.com/n0tx/blog/assets/44139279/a5059df5-4e1d-4b7f-8c18-87710f1c02d2)


- #### ***run springboot using maven cli***

```text
ls
```

![image](https://github.com/n0tx/blog/assets/44139279/bbccbf0a-58ff-4994-ad14-e71ba6204346)

- #### ***as optional we can do test first before run the application***

```text
./mvnw test
```
![image](https://github.com/n0tx/blog/assets/44139279/fbab2fc5-e8cc-4926-bc3a-bc7e58148aa3)

![image](https://github.com/n0tx/blog/assets/44139279/1c36bcfa-4829-4a1d-9b4a-9007e6047bdf)


```text
./mvnw spring-boot:run
```
![image](https://github.com/n0tx/blog/assets/44139279/b2b51e1a-3c3d-4487-9aac-167c246631a5)


- #### ***check h2 database***
```text
http://localhost:8080/h2-ui
```

![image](https://github.com/n0tx/blog/assets/44139279/b06e2324-0346-4d95-9182-9f7730ace333)

- #### ***check swagger openapi***
```text
http://localhost:8080/swagger-ui/index.html
```
![image](https://github.com/n0tx/blog/assets/44139279/33111d87-b04f-4879-aa88-ce9020745960)


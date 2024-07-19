# Api-Generator-cv

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- Java 17
- Gradle 8.8

### Login User
POST https://api-generator-cv.onrender.com/api/auth/login
Content-Type: application/json

{
  "username" : "feals",
  "password" : "Jhjdfhjdf"
}

### Logout
DELETE https://api-generator-cv.onrender.com/api/auth/logout
Content-Type: application/json
X-API-TOKEN: d1ff397d-a8b3-4beb-b341-a8c8596fb353

{}

### Register User
POST https://api-generator-cv.onrender.com/api/users
Content-Type: application/json

{
  "username" : "feals",
  "password" : "Jhjdfhjdf",
  "email" : "example@gmail.com"
}

### Get User
GET https://api-generator-cv.onrender.com/api/users/current
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{}

### Create detail user
POST https://api-generator-cv.onrender.com/api/user/detail
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
  "firstName" : "Febyk",
  "lastName" : "Alek Satria",
  "city" : "Jakarta",
  "country" : "Indonesia",
  "phone" : "086473538367",
  "about" : "saya programmer"
}

### Get detail user
https://api-generator-cv.onrender.com/api/user/detail
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
}

### Update detail user
PUT https://api-generator-cv.onrender.com/api/user/detail
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
  "firstName" : "Febyk",
  "lastName" : "Alek Satria",
  "city" : "Jakarta",
  "country" : "Indonesia",
  "phone" : "086473538367",
  "about" : "programmer"
}

### Get social media
GET https://api-generator-cv.onrender.com/api/socials
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
}

### Create social media
POST https://api-generator-cv.onrender.com/api/socials
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "platform" : "instagram",
    "username" : "febyk.as"
  }
]

###Update social media
PUT https://api-generator-cv.onrender.com/api/socials
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
{
  "id" : "bd1ab19c-0bb0-4a50-8c69-70c8bd3ea48a",
  "platform" : "instagram",
  "username" : "febyk.as",
  "url" : "https://www.instagram.com/febyk.as/"
}
]

###Delete social media
DELETE https://api-generator-cv.onrender.com/api/socials/c44913c8-9daa-4a72-8e23-ad50231346df
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{

}

### Get skill
GET https://api-generator-cv.onrender.com/api/skills
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
}

### Create skill
POST https://api-generator-cv.onrender.com/api/skills
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "skill" : "React/Next Js",
    "level" : "advanced"
  },
  {
    "skill" : "Node Js",
    "level" : "advanced"
  },
  {
    "skill" : "Laravel",
    "level" : "advanced"
  },
  {
    "skill" : "Flutter",
    "level" : "advanced"
  },
  {
    "skill" : "Springboot",
    "level" : "advanced"
  }
]

### Update skill
PUT https://api-generator-cv.onrender.com/api/skills
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "id" : "f3b5df55-cc63-42ae-9e5a-27360bef62f6",
    "skill" : "game",
    "level" : "menengah"
  },
  {
    "id" : "325cfb6c-a96b-45bb-b757-425ee997d004",
    "skill" : "programmer",
    "level" : "jago"
  }
]

### Delete skill
DELETE https://api-generator-cv.onrender.com/api/skills/f3b5df55-cc63-42ae-9e5a-27360bef62f6
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521



### Get achieve
GET https://api-generator-cv.onrender.com/api/achieves
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
}

### Create achieve
POST https://api-generator-cv.onrender.com/api/achieves
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "achieve" : "balap karung",
    "year" : "1999"
  },
  {
    "achieve" : "makan kerupuk",
    "year" : "2022"
  }
]

### Update achieve
PUT https://api-generator-cv.onrender.com/api/achieves
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "id" : "e65d669e-ef24-4bc1-8e07-1af127cc1d14",
    "achieve" : "balap sepeda",
    "year" : "2004"
  }
]

### Delete achieve
DELETE https://api-generator-cv.onrender.com/api/achieves/e65d669e-ef24-4bc1-8e07-1af127cc1d14
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521



### Get language
GET https://api-generator-cv.onrender.com/api/languages
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
}

### Create language
POST https://api-generator-cv.onrender.com/api/languages
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "language" : "Indonesia",
    "level" : "active"
  },
  {
    "language" : "makan kerupuk",
    "level" : "passive"
  }
]

### Update language
PUT https://api-generator-cv.onrender.com/api/languages
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "id" : "98dc8f5a-24d2-4bb5-a8e0-07f6f8b63ea3",
    "language" : "English",
    "level" : "passive"
  }
]

### Delete language
DELETE https://api-generator-cv.onrender.com/api/languages/21759f89-c7f4-4dd4-9cb2-d43c2f89e17f
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521



### Get education
GET https://api-generator-cv.onrender.com/api/educations
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
}

### Create education
POST https://api-generator-cv.onrender.com/api/educations
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "school" : "Sriwijaya University",
    "startDate" : "2018-08-01",
    "endDate" : "2022-07-01",
    "major" : "Teknik Informatika",
    "gpa" : 3.62,
    "desc" : "Kuliah"
  },
  {
    "school" : "SMA N 1",
    "startDate" : "2015-07-01",
    "endDate" : "2018-04-01",
    "major" : "MIPA",
    "gpa" : 90,
    "desc" : "Sekolah"
  }
]

### Update education
PUT https://api-generator-cv.onrender.com/api/educations
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "id" : "e15018b3-e3ba-4f04-b4a5-37e065441b5d",
    "school" : "SMA N 1",
    "startDate" : "2015-04-11",
    "endDate" : "2018-04-11",
    "major" : "MIPA",
    "gpa" : 92,
    "desc" : "Sekolah"
  }
]

### Delete education
DELETE https://api-generator-cv.onrender.com/api/educations/e15018b3-e3ba-4f04-b4a5-37e065441b5d
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521




### Get organization
GET https://api-generator-cv.onrender.com/api/organizations
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
}

### Create organization
POST https://api-generator-cv.onrender.com/api/organizations
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "organization" : "Uread",
    "startDate" : "2018-04-11",
    "endDate" : "2022-04-11",
    "title" : "Sekretaris manajer",
    "desc" : "organisasi"
  },
  {
    "organization" : "HMIF",
    "startDate" : "2015-04-11",
    "endDate" : "2018-04-11",
    "title" : "Deputy Division Head Kajian Strategi",
    "desc" : "himpunan"
  }
]

### Update organization
PUT https://api-generator-cv.onrender.com/api/organizations
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "id" : "1bd49438-2ab5-4d62-973b-7b0f5151e57e",
    "organization" : "HMIF",
    "startDate" : "2015-04-11",
    "endDate" : "2018-04-11",
    "title" : "WAKADIV Kastrad",
    "desc" : "himpunan"
  }
]

### Delete organization
DELETE https://api-generator-cv.onrender.com/api/organizations/1bd49438-2ab5-4d62-973b-7b0f5151e57e
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521



### Get experience
GET https://api-generator-cv.onrender.com/api/experiences
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

{
}

### Create experience
POST https://api-generator-cv.onrender.com/api/experiences
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "company" : "KISEL Group",
    "startDate" : "2018-04-11",
    "endDate" : "2022-04-11",
    "title" : "STAFF",
    "desc" : "Staff Developer"
  },
  {
    "company" : "HANA Bank",
    "startDate" : "2015-04-11",
    "endDate" : "2018-04-11",
    "title" : "Asisten Manager Service Interface",
    "desc" : "ASMEN Interface"
  }
]

### Update experience
PUT https://api-generator-cv.onrender.com/api/experiences
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521

[
  {
    "id" : "7e6b5b54-b1af-450e-bb79-91e41dd993ec",
    "company" : "KISEL Group",
    "startDate" : "2018-04-11",
    "endDate" : "2022-04-11",
    "title" : "STAFF",
    "desc" : "Staff Developer"
  }
]

### Delete experience
DELETE https://api-generator-cv.onrender.com/api/experiences/7e6b5b54-b1af-450e-bb79-91e41dd993ec
Content-Type: application/json
X-API-TOKEN: 994867da-40b8-452d-97bd-012c8ea82521












## QueryDSL 사용
1. 의존성 추가 - gradle
```groovy
// QueryDSL Version
def queryDSLVersion = '4.2.2'
// QueryDSL PATH
def generated = "src/main/generated/java"

// QueryDSL - DSL(Domain Specific Language)
compile("com.querydsl:querydsl-core:${queryDSLVersion}")
compile("com.querydsl:querydsl-jpa:${queryDSLVersion}")
compile("com.querydsl:querydsl-apt:${queryDSLVersion}")
annotationProcessor(
"com.querydsl:querydsl-apt:${queryDSLVersion}:jpa",
)
```



### 참조
1. [https://seodaeya.tistory.com/122](https://seodaeya.tistory.com/122)
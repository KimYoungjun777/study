# Tomcat 9 공식 이미지 사용
FROM tomcat:9.0

# 빌드된 WAR 파일을 Tomcat webapps 폴더에 ROOT.war로 복사
COPY build/libs/study-1.0.war /usr/local/tomcat/webapps/ROOT.war

# 8080 포트 개방
EXPOSE 9094

# Tomcat 실행
CMD ["catalina.sh", "run"]
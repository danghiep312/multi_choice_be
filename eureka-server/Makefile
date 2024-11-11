default:
	cat ./Makefile

build:
	./mvnw clean package -DskipTests

run:
	java -jar target/*.jar --spring.profiles.active=postgresql
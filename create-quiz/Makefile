default:
	cat ./Makefile

build:
	./mvnw clean package -DskipTests

build-image:
	docker build -t create-quiz-service .

save-image:
	docker save -o create-quiz-service.tar create-quiz-service

scp-image:
	scp *.tar vunaproptit@35.197.148.25:/home/vunaproptit

scp-docker-compose:
	scp docker-compose.yaml vunaproptit@35.197.148.25:/home/vunaproptit

scp-env:
	scp .env vunaproptit@35.197.148.25:/home/vunaproptit

run:
	java -jar target/*.jar --spring.profiles.active=postgresql

deploy:
	make build
	make build-image
	make save-image
	make scp-image
	make scp-docker-compose
	make scp-env
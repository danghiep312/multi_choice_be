default:
	cat ./Makefile

build:
	./mvnw clean package -DskipTests

build-image:
	docker build -t join-quiz-service .

save-image:
	docker save -o join-quiz-service.tar join-quiz-service

scp-image:
	scp *.tar vunaproptit@34.143.193.6:/home/vunaproptit

scp-docker-compose:
	scp docker-compose.yaml vunaproptit@34.143.193.6:/home/vunaproptit

scp-env:
	scp .env vunaproptit@34.143.193.6:/home/vunaproptit

run:
	java -jar target/*.jar --spring.profiles.active=postgresql

deploy:
	make build
	make build-image
	make save-image
	make scp-image
	make scp-docker-compose
	make scp-env
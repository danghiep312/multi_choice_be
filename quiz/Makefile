default:
	cat ./Makefile

build:
	./mvnw clean package -DskipTests

build-image:
	docker build -t quiz-service .

save-image:
	docker save -o quiz-service.tar quiz-service

scp-image:
	scp *.tar vunaproptit@35.198.240.178:/home/vunaproptit

scp-docker-compose:
	scp docker-compose.yml vunaproptit@35.198.240.178:/home/vunaproptit

scp-env:
	scp .env vunaproptit@35.198.240.178:/home/vunaproptit

run:
	java -jar target/*.jar --spring.profiles.active=postgresql

deploy:
	make build
	make build-image
	make save-image
	make scp-image
	make scp-docker-compose
	make scp-env
default:
	cat ./Makefile

build:
	./mvnw clean package -DskipTests

build-image:
	docker build -t notification-service .

save-image:
	docker save -o notification-service.tar notification-service

scp-image:
	scp *.tar vunaproptit@35.247.183.159:/home/vunaproptit

scp-docker-compose:
	scp docker-compose.yaml vunaproptit@35.247.183.159:/home/vunaproptit

scp-env:
	scp .env vunaproptit@35.247.183.159:/home/vunaproptit

run:
	java -jar target/*.jar --spring.profiles.active=postgresql

deploy:
	make build
	make build-image
	make save-image
	make scp-image
	make scp-docker-compose
	make scp-env
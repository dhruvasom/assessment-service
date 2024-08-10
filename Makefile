# Define variables
IMAGE_NAME=assessment_service
TAG=latest

# Default target: build and run the Docker containers
.PHONY: all
all: build up

# Target to build the Docker image
.PHONY: build
build:
	./gradlew clean build && docker build -t $(IMAGE_NAME):$(TAG) .

# Target to start the services using Docker Compose
.PHONY: up
up:
	docker-compose up -d

# Target to stop and remove containers
.PHONY: down
down:
	docker-compose down

# Target to clean up unused Docker images and containers
.PHONY: clean
clean:
	docker system prune -f

# Target to clean build and start
.PHONY: cbr
cbr:
	./gradlew clean build && docker build -t $(IMAGE_NAME):$(TAG) . && docker-compose up -d

.PHONY: qr
qr:
	docker-compose down && ./gradlew clean build -x test && docker build -t $(IMAGE_NAME):$(TAG) . && docker-compose up -d
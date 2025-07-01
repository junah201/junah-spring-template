.PHONY: build
build:
	gradle build  --stacktrace --info

.PHONY: clean
clean:
	gradle clean build --stacktrace --info

.PHONY: jar
jar:
	gradle bootJar

.PHONY: run
run:
	gradle bootRun

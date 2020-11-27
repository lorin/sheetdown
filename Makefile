.PHONY: run runjar

run:
	lein run

jar:
	lein uberjar

runjar:
	java -jar target/uberjar/sheetdown-1.0.0-standalone.jar

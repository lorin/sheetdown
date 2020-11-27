.PHONY: run runjar

runjar:
	java -jar target/uberjar/sheetdown-1.0.0-standalone.jar

run:
	lein run

jar:
	lein uberjar
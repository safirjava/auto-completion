Word Auto Completer
==============================
Base on the prefix this service will return suggested word from the dictionary. Using <b>Trie data structure</b> to store the dictionary word for faster search.<br/>

In order to run the application do the following: <br/>
1. Install Maven 3
2. Clone the repo and run maven command:
```bash
mvn spring-boot:run
```
3. Open a new terminal tab, navigate to clone repo and load the city list using command
```bash
./import-city.sh
```

<br/>Following are sample request for getting suggestion in city list.
```bash
curl http://localhost:8085/suggest_cities?start=k&atmost=5
```
```bash
curl http://localhost:8085/suggest_cities?start=kol&atmost=5
```
```bash
curl http://localhost:8085/suggest_cities?start=kolka&atmost=10
```
```bash
curl http://localhost:8085/suggest_cities?start=hy&atmost=10
```

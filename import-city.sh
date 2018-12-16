OLDIFS=$IFS
IFS=","
while read f1
do
    #echo "City $f8"
    `curl -d "$f1" -H "Content-Type: text/plain" -X POST http://localhost:8085/add_word`
done < Cities.txt

IFS=$OLDIFS
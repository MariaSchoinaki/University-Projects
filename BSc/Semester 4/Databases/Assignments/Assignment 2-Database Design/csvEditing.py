import csv
import ast

noduplicant1 = []
noduplicant2 = []

fr = open("keywords.csv", 'r', encoding="utf8")
csvreader = csv.reader(fr)
header = next(csvreader)

fw1 = open("hasKeyword.csv", 'w', newline='', encoding="utf8")
fw2 = open("Keyword.csv", 'w', newline='', encoding="utf8")
writer1 = csv.writer(fw1)
row = ["movie_id", "key_id"]
writer1.writerow(row)

writer2 = csv.writer(fw2)
row = ["id", "name"]
writer2.writerow(row)

for i in csvreader:
    JS = i[1]
    data = ast.literal_eval(JS)
    for j in data:
        row = [i[0], j['id']]
        if row not in noduplicant1:
            noduplicant1.append(row)
            
        row = [j['id'], j['name']]
        if row not in noduplicant2:
            noduplicant2.append(row)
writer1.writerows(noduplicant1)
writer2.writerows(noduplicant2)
fw1.close()
fw2.close()
fr.close()

import csv
import requests
from bs4 import BeautifulSoup

with open("output.csv", "w+") as f:

	writer = csv.writer(f)
	writer.writerow(["Name", "Capital", "Population", "Area"])

	url = "https://scrapethissite.com/pages/simple/"
	response = requests.get(url)

	# print "We got a {} response code from {}".format(response.status_code, url)
	# print response.text

	soup = BeautifulSoup(response.text, "html.parser")

	# searching for title tag in source code
	# print soup.find("title")

	# searching for all div tags having a class named country
	countries = soup.find_all("div", "country")
	print "We found {} countries on this page".format(len(countries))
	print countries
	
	for country in countries:
		#print country.find("h3").text.strip()
		name = country.find("h3").text.strip().encode("utf-8")
		capital = country.find("span", "country-capital").text.strip().encode("utf-8")
		population = country.find("span", "country-population").text.strip()
		area = country.find("span", "country-area").text.strip()
		print name + "{\n\tcapital : " + capital +"\n\tpopulation : " + population + "\n}"
		writer.writerow([name, capital, population, area])

import csv
import requests
from bs4 import BeautifulSoup


url = "http://www.4coffshore.com/windfarms/"
response = requests.get(url)

soup = BeautifulSoup(response.text, "html.parser")

# searching for all div tags having a class named country
countries = soup.find_all("h4", "nomargin")
print "We found {} countries on this page".format(len(countries))

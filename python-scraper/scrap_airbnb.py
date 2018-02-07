import csv
import requests
from bs4 import BeautifulSoup

url = "https://scrapethissite.com/pages/simple/"
response = requests.get(url)

print response.text


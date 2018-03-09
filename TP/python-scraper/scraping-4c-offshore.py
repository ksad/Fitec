import csv
import requests
from bs4 import BeautifulSoup
import time

from random import choice
import requests

desktop_agents = ['Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36',
                 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36',
                 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36',
                 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/602.2.14 (KHTML, like Gecko) Version/10.0.1 Safari/602.2.14',
                 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36',
                 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.98 Safari/537.36',
                 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.98 Safari/537.36',
                 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36',
                 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36',
                 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0']

def random_headers():
    return {'User-Agent': choice(desktop_agents),'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8'}

url = "http://www.4coffshore.com/windfarms/"
#url = "https://fr.finance.yahoo.com/quote/FB?p=FB"

# Handle time
for term in ["web scraping", "web crawling", "scrape this site"]:
	t0 = time.time()
	r = requests.get(url, headers=random_headers(), params=dict(query=term))

	# get the difference between before and after the request, in seconds
	response_delay = time.time() - t0
	# wait 10x longer than it took them to respond
	time.sleep(5 * response_delay)

print "We got a {} response code from {}".format(r.status_code, url)
print r.text

soup = BeautifulSoup(r.text, "html.parser")

# searching for all div tags having a class named country
title = soup.find("title")
print title

countries = soup.find_all("div", "nomargin")
print "We found {} countries on this page".format(len(countries))

print countries

for country in countries:
    name = country.find("h4").text.strip().encode("utf-8")
    print name
#capital = soup.find("span", "Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(ib)").text.strip().encode("utf-8")
#capital = soup.find("span", "col-sm-12 col-sm-6 col-md-3 nomargin").text.strip().encode("utf-8")
#print capital

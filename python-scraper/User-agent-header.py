import requests

# Handle Headers
headers = {
"Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,\
*/*;q=0.8",
"Accept-Encoding": "gzip, deflate, sdch, br",
"Accept-Language": "en-US,en;q=0.8",
"Cache-Control": "no-cache",
"dnt": "1",
"Pragma": "no-cache",
"Upgrade-Insecure-Requests": "1",
"User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/5\
37.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"
} 

# Handle sessions
r = requests.get("http://www.example.com", headers=headers)

session = requests.Session()
# make a login POST request, using the session
session.post("http://example.com/login", data=dict(
email="me@domain.com",
password="secret_value"
))
# subsequent requests that use the session will automatically handle cookies
r = session.get("http://example.com/protected_page")

# Handle time
import time
for term in ["web scraping", "web crawling", "scrape this site"]:
t0 = time.time()
r = requests.get("http://example.com/search", params=dict(
query=term
))
# get the difference between before and after the request, in seconds
response_delay = time.time() - t0
# wait 10x longer than it took them to respond
time.sleep(10 * response_delay)
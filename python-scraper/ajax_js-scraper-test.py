import requests
import json

#query = requests.get("https://content.api.nytimes.com/svc/weather/v2/current-and-seven-day-forecast.json")
query = json.loads("test-example.json")

# convert the response JSON into a structured python `dict()`

data = query.json()

for item in data["items"]:
	print item["name"]	
	print item["price"]

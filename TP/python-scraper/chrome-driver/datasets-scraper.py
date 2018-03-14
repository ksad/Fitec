from selenium import webdriver
from selenium.webdriver.common.by import By
import selenium.webdriver.support.ui as ui
import selenium.webdriver.support.expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException
import os
import time
from unidecode import unidecode

class datasetsdownload():
	def __init__(self):
		options = webdriver.ChromeOptions()
		options.add_argument('ignore-certificate-errors')
		options.add_argument('ignore-ssl-errors')
		self.driver = webdriver.Chrome(chrome_options=options, executable_path= os.path.dirname(os.path.realpath(__file__))+"/chromedriver")
		self.tmpDriver = webdriver.Chrome(chrome_options=options, executable_path= os.path.dirname(os.path.realpath(__file__))+"/chromedriver")

	def timerPractice(self):
		time.sleep(10)

	def gotoinsee(self):
		self.driver.get("https://www.insee.fr/fr/statistiques?taille=100&debut=0&theme=1&categorie=5&geo=FRANCE-1")
		ui.WebDriverWait(self.driver, 15).until(EC.visibility_of_element_located((By.ID, "documents")))
		time.sleep(1)
		
		uriList = self.driver.find_element_by_id("documents")
		folder = ""
		subFolder = ""
		fileName = ""
		uri = ""
		description = ""
		
		for selectAll in uriList.find_elements_by_xpath("//*"):
			if selectAll.get_attribute("class") == 'echo-lien':
				uri = selectAll.get_attribute("href")
				print uri
				'''
				self.tmpDriver.get(uri)
				ui.WebDriverWait(self.driver, 15).until(EC.visibility_of_element_located((By.CLASS, "sommaire-droite")))
				category = self.tmpDriver.find_element_by_class_name("categorie").text.lower().replace(" ", "_")
				#if not self.tmpDriver.find_elements_by_xpath("//h1[2]"):
				if not self.tmpDriver.find_element_by_css_selector("h1.titre-sommaire"):
					print uri+" : "+"can't download files"
					
				else:
					print uri+" : "+"OK"
					#print title.text #[1].text.lower().replace(" ", "_")
					#print uri+" : "+"can't download files"

				if not os.path.exists(category):
					print "i'm creating a dir ...."
					os.makedirs(category)
				'''

				
#	def teardown(self):
#		self.driver.close()

if __name__ == "__main__":
	obj = datasetsdownload()
	obj.gotoinsee()
		

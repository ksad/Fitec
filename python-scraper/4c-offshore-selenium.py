from selenium import webdriver
from selenium.webdriver.common.by import By
import selenium.webdriver.support.ui as ui
import selenium.webdriver.support.expected_conditions as EC
import time

#chrome_path = r"chromedriver"
options = webdriver.ChromeOptions()
options.add_argument('--ignore-certificate-errors')
options.add_argument('--ignore-ssl-errors')

driver = webdriver.Chrome(chrome_options=options)

time.sleep(10)

driver.get("http://www.4coffshore.com/")
ui.WebDriverWait(driver, 15).until(EC.visibility_of_element_located((By.ID, "player")))
driver.find_element_by_xpath('//a[@title="Offshore wind intelligence"]').click()
time.sleep(5)

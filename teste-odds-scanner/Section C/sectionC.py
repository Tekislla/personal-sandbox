from xml.etree.ElementPath import xpath_tokenizer
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import csv

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
driver.get('https://finance.yahoo.com/quote/BTC-EUR/history/')

date1 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[1]/td[1]')
close1 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[1]/td[5]')

date2 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[2]/td[1]')
close2 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[2]/td[5]')

date3 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[3]/td[1]')
close3 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[3]/td[5]')

date4 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[4]/td[1]')
close4 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[4]/td[5]')

date5 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[5]/td[1]')
close5 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[5]/td[5]')

date6 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[6]/td[1]')
close6 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[6]/td[5]')

date7 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[7]/td[1]')
close7 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[7]/td[5]')

date8 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[8]/td[1]')
close8 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[8]/td[5]')

date9 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[9]/td[1]')
close9 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[9]/td[5]')

date10 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[10]/td[1]')
close10 = driver.find_element(By.XPATH, '//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[10]/td[5]')

f = open('eur_btc_rates.csv', 'a', newline="")
headers = ('Date', 'BTC Closing Value')
r1 = (date1.text, close1.text)
r2 = (date2.text, close2.text)
r3 = (date3.text, close3.text)
r4 = (date4.text, close4.text)
r5 = (date5.text, close5.text)
r6 = (date6.text, close6.text)
r7 = (date7.text, close7.text)
r8 = (date8.text, close8.text)
r9 = (date9.text, close9.text)
r10 = (date10.text, close10.text)

writer = csv.writer(f)
writer.writerow(headers)
writer.writerow(r1)
writer.writerow(r2)
writer.writerow(r3)
writer.writerow(r4)
writer.writerow(r5)
writer.writerow(r6)
writer.writerow(r7)
writer.writerow(r8)
writer.writerow(r9)
writer.writerow(r10)

f.close()
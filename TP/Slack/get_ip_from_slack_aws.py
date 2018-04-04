# -*- coding: utf-8 -*-

from slackclient import SlackClient
import os
import json
import datetime
import calendar
import re

class bcolors:
    HEADER = '\033[95m'
    BLUE = '\033[94m'
    GREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'

now = datetime.datetime.now()
now_date = now.strftime("%Y-%m-%d %H:%M:%S")
'''
start_today_date = now.strftime("%Y-%m-%d 00:00:00")
today_date = datetime.datetime.strptime(start_today_date, "%Y-%m-%d %H:%M:%S")
today_timestamp = calendar.timegm(today_date.utctimetuple())
'''

slack_token = os.environ["SLACK_API_TOKEN"]
sc = SlackClient(slack_token)

def read_slack_message(channel):
  return sc.api_call(
	"channels.history",
	 channel=channel,
	 count=100,
	 inclusive=1,
	 oldest = "1520467200",
	 pretty=1
 )

response = read_slack_message("C9AQ91CUB")
#response = read_slack_message("C9N1NFT29")

user1 = "AZIZ"
user2 = "RUBEN"
user3 = "ALI"
user4 = "KARIM"
user5 = "FEL"
if response["ok"]:
	results = response["messages"]
	#for res in results:
		#print res['text']
	if results:
		for res in results:
			if (res.get('type') == 'message' and res.get('user') == 'U92AAH5C6' ): # user : mehdi
			#if (res.get('type') == 'message' and res.get('user') == 'U90SDQ2NL' ): # # user : karim
				ip = re.findall( r'[\s]+[0-9]+(?:\.[0-9]+){3}', res.get('text'))
				if ip:
					last_update_date = datetime.datetime.fromtimestamp(float(str(res['ts']))).strftime('%Y-%m-%d %H:%M:%S')
					title = "Today's IP : (Last update : "+last_update_date+")"
					tablen = len(title) + 1
					print '-' * tablen
					print bcolors.BOLD + bcolors.BLUE + title + bcolors.ENDC
					print '-' * tablen
					for i in res.get('text').split('\n'):
						check_line = re.findall( r'[0-9]+(?:\.[0-9]+){3}', i)
						if check_line:
							if user1 in str(i) :
								print bcolors.WARNING + bcolors.BOLD + str(i) + bcolors.ENDC + ' ' * ((tablen - len(i)))
								ip_user1 = re.search(r'\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}', str(i)).group()
								#os.system('sed -i "12s/.*/\\tHostname "'+ip_user1+'"/" ~/.ssh/config && sed -i "4s/.*/"'+ip_karim+'"\\tnode-karim\\tip-172-31-18-9.eu-west-1.compute.internal/" /etc/hosts')	
								os.system('sed -i "5s/.*/"'+ip_user1+'"\\tambari-server\\tip-172-31-20-126.eu-west-1.compute.internal #Azziz/" /etc/hosts')
							elif user2 in str(i):
								print bcolors.WARNING + bcolors.BOLD + str(i) + bcolors.ENDC + ' ' * ((tablen - len(i)))
								ip_user2 = re.search(r'\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}', str(i)).group()
								os.system('sed -i "6s/.*/"'+ip_user2+'"\\truben_aws\\tip-172-31-20-240.eu-west-1.compute.internal #Ruben/" /etc/hosts')
							elif user3 in str(i):
								print bcolors.WARNING + bcolors.BOLD + str(i) + bcolors.ENDC + ' ' * ((tablen - len(i)))
								ip_user3 = re.search(r'\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}', str(i)).group()
								os.system('sed -i "7s/.*/"'+ip_user3+'"\\tali_aws\\tip-172-31-29-164.eu-west-1.compute.internal #Ali/" /etc/hosts')
							elif user4 in str(i) :
								print bcolors.WARNING + bcolors.BOLD + str(i) + bcolors.ENDC + ' ' * ((tablen - len(i)))
								ip_user4 = re.search(r'\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}', str(i)).group()
								os.system('sed -i "8s/.*/"'+ip_user4+'"\\tkarim_aws\\tip-172-31-18-9.eu-west-1.compute.internal #Karim/" /etc/hosts')
							elif user5 in str(i) :
								print bcolors.WARNING + bcolors.BOLD + str(i) + bcolors.ENDC + ' ' * ((tablen - len(i)))
								ip_user5 = re.search(r'\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}', str(i)).group()
								os.system('sed -i "9s/.*/"'+ip_user5+'"\\tfelix_aws\\tip-172-31-31-238.eu-west-1.compute.internal #Felix/" /etc/hosts')
							else:
								print str(i)
					print '-' * tablen
					break;
	else:
		#last_update_date = datetime.datetime.fromtimestamp(now_date).strftime('%Y-%m-%d %H:%M:%S')
		title = " Today's IP : (Check time : "+now_date+") "
		tablen = len(title) + 1
		print '-' * tablen
		print '|' + bcolors.BOLD + bcolors.BLUE + title + bcolors.ENDC + "|"
		print '-' * tablen
		error_msg = ' No IP available !'
		sep = (((tablen - len(error_msg)))/2)
		print '|' + ' ' * sep + bcolors.BOLD + bcolors.FAIL + error_msg + bcolors.ENDC + ' ' * (((tablen - len(error_msg) - 1))/2) + '|'
		print '-' * tablen

#!/usr/bin/python
# -*- coding: utf-8 -*-
import random
import datetime
import time
import sys, getopt

U_ANTENNA = './data/antennas.txt'
U_IDS = './data/uuids.txt'


def main(argv):
	sleepsecs = 300
	try:
		opts, args = getopt.getopt(argv,"s:",[])
	except getopt.GetoptError:
		print 'test.py -s XXX'
	for opt, arg in opts:
		if opt == '-s':
			sleepsecs = int(arg)

	print "Reading Antennas file"
	antennas = []
	with open(U_ANTENNA, 'r') as myfile:
		for line in myfile:
			antennas.append(int(line))

	print "Reading UIDS file"
	uuids = []
	with open(U_IDS, 'r') as myfile:
		i=0
		for line in myfile:
			uuids.append([])
			uuids[i].append(line.strip('\n'))
			uuids[i].append(antennas[random.randint(0,len(antennas)-1)])
			i = i+1

	# initialize previous datetimes
	pr_dt = {}
	i = 0
	while i < len(uuids):
		pr_dt[uuids[i][0]]=datetime.datetime.now()
		i += 1
	current_datetime = datetime.datetime.now()
	while True:
		i = 0
		current_datetime = datetime.datetime.now()
		while i < len(uuids):
			# Change antenna
			if random.randint(0,100) > 90 :
				uuids[i][1] = antennas[random.randint(0,len(antennas)-1)]
			new_dt = current_datetime + datetime.timedelta(seconds=random.randint(10,sleepsecs-10))
			while new_dt <= pr_dt[uuids[i][0]] :
				new_dt = current_datetime + datetime.timedelta(seconds=random.randint(10,sleepsecs-1))
			# Lost event
			if random.randint(0,100) >= 20 :
				print "<"+str(uuids[i][0])+","+str(uuids[i][1])+","+str(new_dt.strftime('%H:%M:%S.%f'))[:-3]+">"
				if random.randint(0,100) >= 95 :
					new_dt_1 = new_dt
					rounds = random.randint(1,4)
					round = 1
					rand_ant = antennas[random.randint(0,len(antennas)-1)]
					while round <=rounds:
						new_dt_1 = new_dt_1 + datetime.timedelta(seconds=1)
						new_dt_2  = new_dt_1 + datetime.timedelta(seconds=2)
						new_dt_3  = new_dt_2 + datetime.timedelta(seconds=2)
						print "<"+str(uuids[i][0])+","+str(rand_ant)+","+str(new_dt_1.strftime('%H:%M:%S.%f'))[:-3]+">"
						print "<"+str(uuids[i][0])+","+str(uuids[i][1])+","+str(new_dt_2.strftime('%H:%M:%S.%f'))[:-3]+">"
						print "<"+str(uuids[i][0])+","+str(rand_ant)+","+str(new_dt_3.strftime('%H:%M:%S.%f'))[:-3]+">"
						round += 1
			i += 1
		time.sleep(sleepsecs)



if __name__ == "__main__":
    main(sys.argv[1:])

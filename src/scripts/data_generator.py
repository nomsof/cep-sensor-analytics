#!/usr/bin/python
# -*- coding: utf-8 -*-
import random
import datetime
import time
import sys, getopt

U_ANTENNA = '/home/snomikou/master/cep-sensor-analytics/src/scripts/data/antennas.txt'
U_IDS = '/home/snomikou/master/cep-sensor-analytics/src/scripts/data/uuids.txt'


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
			if random.randint(0,100) > 90 :
				uuids[i][1] = antennas[random.randint(0,len(antennas)-1)]
			new_dt = current_datetime + datetime.timedelta(seconds=random.randint(10,sleepsecs-10))
			while new_dt <= pr_dt[uuids[i][0]] :
				new_dt = current_datetime + datetime.timedelta(seconds=random.randint(10,sleepsecs-1))
			print "<"+str(uuids[i][0])+","+str(uuids[i][1])+","+str(new_dt.strftime('%H:%M:%S.%f'))[:-3]+">"
			i += 1
		time.sleep(sleepsecs)



if __name__ == "__main__":
    main(sys.argv[1:])

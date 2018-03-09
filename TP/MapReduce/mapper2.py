#! /usr/bin/python
#-*- coding: utf-8 -*-

import sys
import re

# input comes from STDIN (standard input)
for line in sys.stdin:
	# remove leading and trailing whitespace
	line = line.strip()
	# split the line into words
	words = line.split()
	words = map(lambda x: x.split('--'), words)
	# Flatten list
	words = [word for sublist in words for word in sublist]
	
	# increase counters
	for word in words:
		# Clean the words
		word = re.sub('(^[\.\(\)\[\],\?!;:_\-—£\$\/\\\+\*%@&\"]+|[\.\(\)\[\],\?!;:_\-—£\$\/\\\+\*%@&\"]+$)', '', word)
		word = word.lower().strip()
		# write the results to STDOUT (standard output);
		# what we output here will be the input for the
		# Reduce step, i.e. the input for reducer.py
		#
		# tab-delimited; the trivial word count is 1
		if word != "":
			print '%s\t%s' % (word, 1)
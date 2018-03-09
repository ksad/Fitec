command to run mapper/reducer :

hadoop jar /usr/hdp/2.6.3.0-235/hadoop-mapreduce/hadoop-streaming-2.7.3.2.6.3.0-235.jar -files mapper.py,reducer.py -mapper mapper.py -reducer reducer.py -input /user/MapReduce/* -output /user/MapReduce/gutenberg-output


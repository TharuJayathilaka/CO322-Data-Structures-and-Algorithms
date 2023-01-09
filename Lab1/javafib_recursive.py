import matplotlib.pyplot as plt
import numpy as np

n1=np.arange(0,41,1) 
# running times of Fib.java file
y1=np.array([0
,1000
,100
,300
,300
,300
,300
,500
,800
,1100
,1800
,2901
,4601
,7500
,15399
,19900
,33000
,53000
,53300
,10299
,16599
,26600
,42900
,73200
,112300
,181400
,293700
,477900
,768501
,1253700
,2062300
,3273500
,5296300
,8647501
,14006200
,22590800
,36638300
,59729201
,95408600
,154393000
,260004700])

plt.plot(n1,y1/1000000000) #time taken to recursive method    The time is in ns, we should convert it to seconds by dividing by 10^9
plt.xlabel('Problem Size')	#label the x axis
plt.ylabel('Run Time / (seconds)') # label the y axis
plt.title('Using Recursion method to find Fibonacci number--(Java)') # graph title
plt.show() # show the graph

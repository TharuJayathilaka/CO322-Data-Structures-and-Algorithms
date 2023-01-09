import matplotlib.pyplot as plt
import numpy as np

n1=np.arange(0,41,1) 

# running times of Fib.java file
y2=np.array([0
,8999
,101
,200
,200
,200
,200
,200
,200
,101
,101
,200
,99
,200
,200
,200
,200
,200
,200
,200
,299
,301
,200
,300
,300
,300
,300
,300
,300
,300
,300
,300
,400
,400
,300
,400
,300
,400
,400
,400
,400])

plt.plot(n1,y2/1000000) #time taken to iteration method   The time is in ns, we should convert it to seconds by dividing by 10^9
plt.xlabel('Problem Size')	#label the x axis
plt.ylabel('Run Time / (ms)') # label the y axis
plt.title('Using Iteration method to find Fibonacci number--(Java)') # graph title
plt.show() # show the graph

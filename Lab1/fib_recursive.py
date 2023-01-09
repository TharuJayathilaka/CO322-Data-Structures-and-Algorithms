import matplotlib.pyplot as plt
import numpy as np
import timeit

#!/usr/bin/python

def fib_r(x) :
    if (x <= 2):
        return 1
    return fib_r(x-1) + fib_r(x-2)

#x = 40
#print ("Fib of " + str(x) + " = " + str(fib_r(x)))
#print ("Fib of " + str(x) + " = " + str(fib_i(x)))

n1=np.array([0]) # initialize the first element of the n1 array
y1=np.array([0]) # initialize the first element of the y1 array
for x in range(1,41): # x goes from 1 to 40
	start=timeit.default_timer() # get the start time
	print ("Fib of " + str(x) + " = " + str(fib_r(x))) # run the recursive method for that relevant method
	stop=timeit.default_timer() # get the time now as stop
	Time= stop-start # calculate the time taken to run the code
	#print(x)
	n1=np.append(n1,x) # add x to the array
	y1=np.append(y1,Time) # add run time to the array
	
	
plt.plot(n1,y1)   #time taken to recursive method
plt.xlabel('Problem Size')	#label the x axis
plt.ylabel('Run Time / (seconds)')  #label the y axis
plt.title('Using Recursion method to find Fibonacci number--(Python)') # graph title
plt.show() # show the graph













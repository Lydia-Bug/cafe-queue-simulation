## Overview of code:
The purpose is to simulate the queue at my high school cafeteria. It often gets very long, which is seen as an issue to many people.
The code will run through the code for every second in an hour (the length of lunchtime). In uses a linked list to keep track of the queuers, it will remove the head of the queue after its been served, and it will be adding people to the queue throughout the hour. There are different rules that change different aspects of the queues, and various important outcomes are recorded, and displayed at the end of the simulation

## Input:

### Amount of people who join the queue each minute
You can either simulate the people joining the queue, or read in the people arriving from a table. If reading in from a table the table should have 1 to 3600 (seconds in an hour) in one column, and the amount of people arriving at that second in the other. If you simulate it, with each second it'll add people with some probability determined by the 'queuers constant' which you chose. The amount of queuers entering the queue will decrease with time to reflect how the cafeteria actually works. 

### Teachers
At the highschool teachers are allowed to cut the line, so they need to be treated differently then the other student queuers. You can chose the amount of students per one teacher. And you can choose if teachers are allowed to cut or not, so you can see if some teachers cutting really makes a difference in student wait times. 

### Maximum queue length
This is the maximum queue length, before students will decide that the queue is too long, and will chose not to enter the queue

### Serving time
This is the time is takes to serve someone, once a queuer is being served, they will stay being served for this amount of time, then the next person will start being served

## Output:

### Hungry students and teachers
This is the amount of queuers that would have joined the queue if not for the fact, it would have exceeded the maximum queue length. And queuers that are still in the queue at the end of lunch. This will only apply to teachers if they aren't allowed to cut in line.

### Served students and teachers
This is the amount of all the students and teachers that were served


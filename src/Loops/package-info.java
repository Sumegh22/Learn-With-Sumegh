package Loops;
/*** 
* 

#Syntax of for loop

for(initialization;terminal condition;increment/decrement) {
	
do something;
}

eg.
for(int i=0; i<11;i++) {
	Sysout(i+" ");
}


-------------------------------------------------------------------- 
#Syntax of while loop
 
var declaration;

while(condition) {
	do something;
	increament
}

eg.

int i=0;
while(i<11) {
	sysout(i+" ");
 i++;	
}

-------------------------------------------------------------------- 
#Syntax of do while loop

do {
	do something; }
while(condition);


eg.
do {Sysout(i+", "); i++;}
while(i<11);

note: in do while, the action is performed even before testing the condition,
so if the conitn is not true evenin that case the action will be performed at least once.
However, once you get into the loop, then no extra attempts will be made after the condition is failed.

as in the above example if i is passed as 9 -> then you see result [9, 10, ]
no further result after check fails

but if we pass 12 as an input then it will print only [12, ]

*/

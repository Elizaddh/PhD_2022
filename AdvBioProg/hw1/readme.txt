
(1) Write code to print to the console 1,000 randomly generated 
	DNA 3 mers (e.g. “ACA”, “TCG” )
	where the frequency of A,C,G and T is 25% and is uniformly sampled.

Have your code track how often it prints out the 3 mer (“AAA”) 
How often would you expect to see this 3mer by chance?  Is Java’s number
close to the number that you would expect?

(3) Modify your code so that the frequency of A,C,G and T is

		p(A) = 0.12
		p(C) = 0.38
		p(G) = 0.39
		p(T) = 0.11

What is the expected frequency now of “AAA”?  Does Java produce “AAA” 
at close to the expected frequency?

Hint the code: 
	Random random = new Random();
	float f = random.nextFloat();
produces a random number uniformly between 0 and 1.
For those with a stats background and/or a lot of time on your hands…

	Rerun the above simulation for 10,000 sequences 10,000 times.  
	Use the chi-square test to show that the p-values for the null hypothesis
	that the actual distribution does not match the expected distribution are 
	uniformly distributed.  

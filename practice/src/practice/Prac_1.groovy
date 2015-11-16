package practice

import java.io.File.TempDirectory;

public class Prac_1 {
	
	public static void main(def args){
		 int p1 = 0, p2 = 1, res = 0, res2 = 0, fin = 0
	
		 while (true) { 
			res2 = p2
			res = p1 + p2
			//print res + ", "
			p2 = res
			p1 = res2
			if (res % 2 == 0) {
				fin = fin + res
			} else if (res > 4000000) {
				break;
			}
		 }
		 println fin + "\n---------"
		 
		 Double i = 2, sum = 1, x= 600851475143, test =0, temp = 0
		 LinkedList fct = new LinkedList()
		 temp = x
			while(true){
				if (x % i == 0) {
					test = i
					fct.add(i)
					x = x / test
					sum = sum * i
				}
				if (sum == temp) {
					break
				}
				i++
			}
			
		 	println "prob3: " + fct.getLast().intValue()
			 println fin + "\n---------"
			 println 999 * 999
			 
			long p4_s = 0, p4_i = 0, p4_sum = 0
			for (int z = 100; z <= 999; z++) {
				sum = z 
			}
	}
	
}
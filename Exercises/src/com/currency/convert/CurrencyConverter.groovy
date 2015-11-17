package com.currency.convert

import java.lang.invoke.SwitchPoint;

class CurrencyConverter {

	static void main(args) {
		Converter cn = new Converter()
		Scanner sc = new Scanner(System.in)
		String ch

		while(true) {

			println "\nSelect Your Choice"
			println "\n[a] Convert a Value" +
					"\n[b] Get Conversion Rate" +
					"\n[c] Show All Country Currency Codes" +
					"\n[d] Exit"
			print "> Enter your Choice: "
			ch = sc.nextLine()
		
			switch(ch){
				case "a":
					println "Please follow the format: <value> <code> (ex. 5 PHP)"
					print	"Convert From: "
					String frm = sc.nextLine()
					def fr = frm.trim().split(" ")

					print "To: "
					String to = sc.nextLine()
					
					if(fr.size() > 1) cn.conv(fr[0], fr[1].toUpperCase(), to.toUpperCase())
					else println "Please follow the correct Format or Enter a Valid Currency Code"

					break
				case "b":
					print	"\n\nConvert From: "
					String frm = sc.nextLine()
					
					print "To: "
					String to = sc.nextLine()
	
					if(frm) cn.getRate(frm.toUpperCase(), to.toUpperCase())
			
					break
				case "c":
					cn.getCurrencyCode()
					break
				case "d":
					return
					break
				default:
					println "Invalid Choice"
					break
			}
			print "Press any key to continue."
			sc.nextLine()
		}
	}
}

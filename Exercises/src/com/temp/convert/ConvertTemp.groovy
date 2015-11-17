package com.temp.convert


import org.codehaus.groovy.classgen.ReturnAdder;

import wslite.*

class ConvertTemp {
	static void main(args) {
		Convert c = new Convert()
		Scanner sc = new Scanner(System.in)

		int cel, far

		while(true) {

			print "\n\nSelect your Choice" +
					"\n[a] Convert Celcius to Farenheit" +
					"\n[b] Convert Farenheit to Celcius" +
					"\n[c] Exit\n" +
					"\n> Enter Your Choice: "
			def ch = sc.nextLine()

			switch(ch) {
				case "a":
					try{
						print "\nEnter a Value to Convert: "
						cel = sc.nextInt()
						c.celToFar(cel)
					} catch(InputMismatchException e) {
						println "Please enter a valid value"
					}
					break
				case "b":
					try{
						print "\nEnter a Value to Convert: "
						far = sc.nextInt()
						c.farToCel(far)
					} catch(InputMismatchException e) {
						println "Please enter a valid value"
					}
					break
				case "c":
					return
					break
				default:
					println "Invalid Choice"
					break
			}
			sc.nextLine()
		}
	}
}

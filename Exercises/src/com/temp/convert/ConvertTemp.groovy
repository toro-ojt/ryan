package com.temp.convert


import java.awt.Frame
import java.awt.Panel
import java.awt.TextArea;

import groovy.swing.SwingBuilder
import static javax.swing.JFrame.EXIT_ON_CLOSE
import java.awt.BorderLayout
import wslite.*

class ConvertTemp {
	static void main(args) {
		Convert c = new Convert()
		//		Scanner sc = new Scanner(System.in)
		//
		//		int cel, far
		//
		//		while(true) {
		//
		//			print "\n\nSelect your Choice" +
		//					"\n[a] Convert Celcius to Farenheit" +
		//					"\n[b] Convert Farenheit to Celcius" +
		//					"\n[c] Exit\n" +
		//					"\n> Enter Your Choice: "
		//			def ch = sc.nextLine()
		//
		//			switch(ch) {
		//				case "a":
		//					try{
		//						print "\nEnter a Value to Convert: "
		//						cel = sc.nextInt()
		//						c.celToFar(cel)
		//					} catch(InputMismatchException e) {
		//						println "Please enter a valid value"
		//					}
		//					break
		//				case "b":
		//					try{
		//						print "\nEnter a Value to Convert: "
		//						far = sc.nextInt()
		//						c.farToCel(far)
		//					} catch(InputMismatchException e) {
		//						println "Please enter a valid value"
		//					}
		//					break
		//				case "c":
		//					return
		//					break
		//				default:
		//					println "Invalid Choice"
		//					break
		//			}
		//			sc.nextLine()
		//		}
		def fr
		def sB = new SwingBuilder()
		//String opt = ["Celcius", "Temperature"]
		sB.edt {
			lookAndFeel 'nimbus'
			fr = frame(title: 'Convert Temperature',
			show: true,
			locationRelativeTo: null,
			resizable: false,
			defaultCloseOperation: EXIT_ON_CLOSE,
			pack: true ) {
				borderLayout(vgap: 5)

				panel(constraints: BorderLayout.NORTH, border: compoundBorder([
					emptyBorder(0),
					titledBorder('Enter Temperature')
				])) {
					tableLayout {
						tr {
							td { label 'Value: '  }
							td {
								textField id: 'val', columns: 8
							}
							td {
								comboBox id: 'opt', items: [
									'Celcius to Fahrenheit',
									'Fahrenheit to Celcius'
								]
							}
						}
						tr {
							td { label 'Result: ' }
							td(colspan: 2) {
								textArea id: 'res', columns: 22, enabled: false
							}
						}
					}
				}

				panel(constraints: BorderLayout.CENTER, border: compoundBorder([emptyBorder(0)])) {
					button id: 'conv', text: 'Convert Temperature', actionPerformed: {
						if (val.text != "" && (val.text.isNumber())) {
							if (opt.selectedItem == "Celcius to Fahrenheit") {
								String fah = c.celToFar(val.text.toDouble())
								res.text = "$val.text Celcius is: $fah in Fahrenheit"
								val.text = ''
							} else if (opt.selectedItem == "Fahrenheit to Celcius") {
								String cel = c.farToCel(val.text.toDouble())
								res.text = "$val.text Fahrenheit is: $cel in Celcius"
								val.text = ''
							}
							fr.pack()
						} else {
							res.text = 'Please enter a valid value to Convert'
							val.text = ''
						}
					}
				}
			}
		}
	}
}

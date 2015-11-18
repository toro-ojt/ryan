package com.currency.convert

import groovy.swing.SwingBuilder
import static javax.swing.JFrame.EXIT_ON_CLOSE
import java.awt.BorderLayout
import javax.swing.BorderFactory
import java.awt.Panel
import javax.swing.JScrollPane

class CurrencyConverter {

	static void main(args) {
		Converter cn = new Converter()
		//		Scanner sc = new Scanner(System.in)
		//		String ch
		//
		//		while(true) {
		//
		//			println "\nSelect Your Choice"
		//			println "\n[a] Convert a Value" +
		//					"\n[b] Get Conversion Rate" +
		//					"\n[c] Show All Country Currency Codes" +
		//					"\n[d] Exit"
		//			print "> Enter your Choice: "
		//			ch = sc.nextLine()
		//
		//			switch(ch){
		//				case "a":
		//					println "Please follow the format: <value> <code> (ex. 5 PHP)"
		//					print	"Convert From: "
		//					String frm = sc.nextLine()
		//					def fr = frm.trim().split(" ")
		//
		//					print "To: "
		//					String to = sc.nextLine()
		//
		//					if(fr.size() > 1) cn.conv(fr[0], fr[1].toUpperCase(), to.toUpperCase())
		//					else println "Please follow the correct Format or Enter a Valid Currency Code"
		//
		//					break
		//				case "b":
		//					print	"\n\nConvert From: "
		//					String frm = sc.nextLine()
		//
		//					print "To: "
		//					String to = sc.nextLine()
		//
		//					if(frm) cn.getRate(frm.toUpperCase(), to.toUpperCase())
		//
		//					break
		//				case "c":
		//					cn.getCurrencyCode()
		//					break
		//				case "d":
		//					return
		//					break
		//				default:
		//					println "Invalid Choice"
		//					break
		//			}
		//			print "Press any key to continue."
		//			sc.nextLine()
		//		}
		def fr
		def sB = new SwingBuilder()
		sB.edt {
			lookAndFeel 'nimbus'
			fr = frame(title: 'Currency Converter',
			show: true,
			locationRelativeTo: null,
			resizable: false,
			defaultCloseOperation: EXIT_ON_CLOSE,
			pack: true) {
				borderLayout(vgap: 10)
				hbox{
					vbox {
						hbox{
							panel(constraints: BorderLayout.CENTER) {
								tableLayout {
									tr {
										td (colspan: 4) {
											panel( border: BorderFactory.createEmptyBorder(0,0,8,0)) { label 'Please follow the format: <value> <code> (ex. 5 PHP)' }
										}
									}

									tr {
										td { label 'Convert From: ' }

										td {
											textField id: 'convFrom', columns: 10
										}

										td { label 'To: ' }

										td {
											textField id: 'convTo', columns: 5
										}
									}

									tr {
										td { label '-->' }
										td(colspan: 3) {
											panel( border: BorderFactory.createEmptyBorder(8,0,0,0)) {
												textField id: 'res', enabled: false, columns: 19
											}
										}

									}

									tr{
										td(colspan: 4) {
											panel(border: BorderFactory.createEmptyBorder(10,0,5,0)) {
												button 'Convert', preferredSize: [330, 50], actionPerformed: {
													def frm = convFrom.text.trim().split(" ")
													if(frm.size() > 1) res.text = cn.conv(frm[0], frm[1].toUpperCase(), convTo.text.toUpperCase())
													else if (frm.size() == 1) res.text = cn.getRate(frm[0].toUpperCase(), convTo.text.toUpperCase())
													else res.text = "Please follow the correct Format\nor Enter a Valid Currency Code"
												}
											}
										}

									}
								}
							}
						}
					}

					vbox {
						panel(constraints: BorderLayout.CENTER) {
							scrollPane(verticalScrollBarPolicy:JScrollPane.VERTICAL_SCROLLBAR_ALWAYS) {
								textArea id:'cc', 'List of Country Codes:\n\n' + cn.getCurrencyCode(), columns: 19, rows: 10, caretPosition: 0
							}
						}
					}
				}
			}
		}
	}
}

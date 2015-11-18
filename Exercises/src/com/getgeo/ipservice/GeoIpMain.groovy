package com.getgeo.ipservice

import groovy.swing.SwingBuilder
import groovy.swing.factory.CompoundBorderFactory;

import static javax.swing.JFrame.EXIT_ON_CLOSE
import java.awt.BorderLayout
import java.awt.Frame;

@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*

class GeoIpMain {

	static GetGeo g, gc

	static void main(args) {
//		println "GetGeoIP"
//		def read = new Scanner(System.in)
//		def flag = true
//
//		while(flag){
//			print "\nList of Commands" +
//					"\n[a] GetGeoByIP" +
//					"\n[b] GetGeoByIPContext" +
//					"\n[c] Exit\n"
//
//			print "> Enter your Choice: "
//			def ch = read.nextLine()
//
//			switch(ch){
//				case "a":
//
//					print "> Enter an IP Address: "
//					def ip = read.nextLine()
//
//					getGeoByIP(ip)
//					printGeoByIP()
//					break
//				case "b":
//					getGeoByIPContext()
//					printGeoByIPContext()
//					break
//				case "c":
//					flag = false
//					break
//				default:
//					println "Invalid Choice."
//					break
//			}
//		}
		def myFrame
		def sB = new SwingBuilder()
		sB.edt {
			lookAndFeel 'nimbus',
			myFrame = frame(title: 'Get Location By IP Address',
				  show: true,
				  locationRelativeTo: null,
				  resizable: false,
				  defaultCloseOperation: EXIT_ON_CLOSE,
				  pack: true ) {

				  panel(constraints: BorderLayout.PAGE_START,
					    border: compoundBorder([
								emptyBorder(0),
								titledBorder('Get Location By IP')])) {
							
							tableLayout {
								tr {
									td {
										label 'IP Address: '
									}
									td {
										textField id: 'ip', columns: 15
									}
								}
								
								tr {
									td(colspan: 2) {
										button 'Get Location', preferredSize: [260, 30], actionPerformed: {
											getGeoByIP(ip.text)
											res.text = printGeoByIP()
											ip.text = ''
											myFrame.pack()
										}
									}
								}
							}
					  }
					
					panel(constraints: BorderLayout.CENTER,
						border: compoundBorder([
								emptyBorder(0),
								titledBorder('Get Location By IP Context')])) {
										
							tableLayout {

								tr {
									td {
										button 'Get Location', preferredSize: [260, 30], actionPerformed: {
											getGeoByIPContext()
											res.text = printGeoByIPContext()
											myFrame.pack()
										}
									}
								}
							}
						}
				  
					panel(constraints: BorderLayout.PAGE_END,
						border: compoundBorder([
								emptyBorder(0),
								titledBorder('Results')])) {
													
							tableLayout {
			
								tr {
									td {
										textArea id: 'res', columns: 20, rows: 4, enabled: false
									}
								}
							}
						}
				  }
		}
	}

	static def getGeoByIP(ip){
		g = new GetGeo()
		def web, p
		def s_clnt = new SOAPClient('http://www.webservicex.net/geoipservice.asmx')
		def res = s_clnt.send(SOAPAction: 'http://www.webservicex.net/GetGeoIP') {
			body {
				GetGeoIP(xmlns:web='http://www.webservicex.net/') { IPAddress(ip) }
			}
		}
		p = res.GetGeoIPResponse.GetGeoIPResult
		if (p.IP)	{
			g.code = p.ReturnCode
			g.ip = p.IP
			g.details = p.ReturnCodeDetails
			g.country_n = p.CountryName
			g.country_c = p.CountryCode
		}
	}

	static def printGeoByIP() {
		String res
		if(g.code != 0) {
			g.code.size().times {
				res = "IP: ${g.ip[it].toString()}" +
					  "\nCountry Name: ${g.country_n[it].toString()}" +
					  "\nCountry Code: ${g.country_c[it].toString()}"
			}
		} else {
			res = "No Data Found."
		}
		return res
	}


	static def getGeoByIPContext() {
		gc = new GetGeo()
		def web, p
		def s_clnt = new SOAPClient('http://www.webservicex.net/geoipservice.asmx')
		def res = s_clnt.send(SOAPAction: 'http://www.webservicex.net/GetGeoIPContext') {
			body {
				GetGeoIPContext(xmlns:web='http://www.webservicex.net/')
			}
		}
		
		p = res.GetGeoIPContextResponse.GetGeoIPContextResult
		if (p.IP)	{
			gc.code = p.ReturnCode
			gc.ip = p.IP
			gc.details = p.ReturnCodeDetails
			gc.country_n = p.CountryName
			gc.country_c = p.CountryCode
		}
	}

	static def printGeoByIPContext() {
		String res
		if(gc.code) {
			gc.code.size().times {
				res = "IP: ${gc.ip[it].toString()}" +
					  "\nCountry Name: ${gc.country_n[it].toString()}" +
					  "\nCountry Code: ${gc.country_c[it].toString()}"
			}
		} else {
			res = "No Data Found."
		}
		return res
	}
}

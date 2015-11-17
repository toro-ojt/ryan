package com.getgeo.ipservice

import groovy.lang.Grab

@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*

class GeoIpMain {

	static GetGeo g, gc

	static void main(args) {
		println "GetGeoIP"
		def read = new Scanner(System.in)
		def flag = true

		while(flag){
			print "\nList of Commands" +
					"\n[a] GetGeoByIP" +
					"\n[b] GetGeoByIPContext" +
					"\n[c] Exit\n"

			print "> Enter your Choice: "
			def ch = read.nextLine()

			switch(ch){
				case "a":

					print "> Enter an IP Address: "
					def ip = read.nextLine()

					getGeoByIP(ip)
					printGeoByIP()
					break
				case "b":
					getGeoByIPContext()
					printGeoByIPContext()
					break
				case "c":
					flag = false
					break
				default:
					println "Invalid Choice."
					break
			}
		}
	}

	static def getGeoByIP(ip){
		g = new GetGeo()
		def web
		def s_clnt = new SOAPClient('http://www.webservicex.net/geoipservice.asmx')
		def res = s_clnt.send(SOAPAction: 'http://www.webservicex.net/GetGeoIP') {
			body {
				GetGeoIP(xmlns:web='http://www.webservicex.net/') { IPAddress(ip) }
			}
		}

		if (res.GetGeoIPResponse.GetGeoIPResult.IP)	{
			g.code = res.GetGeoIPResponse.GetGeoIPResult.ReturnCode
			g.ip = res.GetGeoIPResponse.GetGeoIPResult.IP
			g.details = res.GetGeoIPResponse.GetGeoIPResult.ReturnCodeDetails
			g.country_n = res.GetGeoIPResponse.GetGeoIPResult.CountryName
			g.country_c = res.GetGeoIPResponse.GetGeoIPResult.CountryCode
		}
	}

	static def printGeoByIP() {

		if(g.code != 0) {
			g.code.size().times {
				print "\nReturn Code: ${g.code[it].toString()}" +
						"\nIP: ${g.ip[it].toString()}" +
						"\nReturn Code Details: ${g.details[it].toString()}" +
						"\nCountry Name: ${g.country_n[it].toString()}" +
						"\nCountry Code: ${g.country_c[it].toString()}" +
						"\n -------------------------- \n"
			}
		} else {
			println "No Data Found."
		}
	}


	static def getGeoByIPContext() {
		gc = new GetGeo()
		def web
		def s_clnt = new SOAPClient('http://www.webservicex.net/geoipservice.asmx')
		def res = s_clnt.send(SOAPAction: 'http://www.webservicex.net/GetGeoIPContext') {
			body {
				GetGeoIPContext(xmlns:web='http://www.webservicex.net/')
			}
		}

		if (res.GetGeoIPContextResponse.GetGeoIPContextResult.IP)	{
			gc.code = res.GetGeoIPContextResponse.GetGeoIPContextResult.ReturnCode
			gc.ip = res.GetGeoIPContextResponse.GetGeoIPContextResult.IP
			gc.details = res.GetGeoIPContextResponse.GetGeoIPContextResult.ReturnCodeDetails
			gc.country_n = res.GetGeoIPContextResponse.GetGeoIPContextResult.CountryName
			gc.country_c = res.GetGeoIPContextResponse.GetGeoIPContextResult.CountryCode
		}
	}

	static def printGeoByIPContext() {

		if(gc.code) {
			gc.code.size().times {
				print "\nReturn Code: ${gc.code[it].toString()}" +
						"\nIP: ${gc.ip[it].toString()}" +
						"\nReturn Code Details: ${gc.details[it].toString()}" +
						"\nCountry Name: ${gc.country_n[it].toString()}" +
						"\nCountry Code: ${gc.country_c[it].toString()}" +
						"\n -------------------------- \n"
			}
		} else {
			println "No Data Found."
		}
	}
}

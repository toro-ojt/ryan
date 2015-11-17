package com.temp.convert

import wslite.soap.SOAPClient

class Convert {
	def fahrenheit, celcius

	def celToFar(cel) {
		String web
		def s_clnt = new SOAPClient('http://www.w3schools.com/webservices/tempconvert.asmx')
		def res = s_clnt.send(SOAPAction: 'http://www.w3schools.com/webservices/CelsiusToFahrenheit') {
			body {
				CelsiusToFahrenheit(xmlns:web='http://www.w3schools.com/webservices/') { Celsius(cel) }
			}
		}
		def p = res.CelsiusToFahrenheitResponse.CelsiusToFahrenheitResult
		if (p)	{
			fahrenheit = p
			println "\n${cel} Celcius is: ${fahrenheit} in Fahrenheit\n"
		}
	}

	def farToCel(far) {
		String web, path = "FahrenheitToCelsiusResponse\\.CelsiusToFahrenheitResult"
		def s_clnt = new SOAPClient('http://www.w3schools.com/webservices/tempconvert.asmx')
		def res = s_clnt.send(SOAPAction: 'http://www.w3schools.com/webservices/FahrenheitToCelsius') {
			body {
				FahrenheitToCelsius(xmlns:web='http://www.w3schools.com/webservices/') { Fahrenheit(far) }
			}
		}
		def p = res.FahrenheitToCelsiusResponse.CelsiusToFahrenheitResult
		if (p)	{
			celcius = p
			println "\n${far} Fahrenheit is: ${celcius} in Celcius\n"
		}
	}
}

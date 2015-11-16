package com.prac_2.h

class getGeo {
	
	def void getGeoLoc(country, city = "", province = "") {
		
		def response = new XmlSlurper().parse("http://maps.google.com/maps/api/geocode/xml?address=${country.trim().replaceAll(' ', '+')}?${city.trim().replaceAll(' ', '+')}?${province.trim().replaceAll(' ', '+')}")
		
		def formatted_addr = response.result.formatted_address
		def longtitude = response.result.geometry.location.lng
		def latitude = response.result.geometry.location.lat
		
		formatted_addr.size().times{ println "${formatted_addr[it].toString()}  Longtidude: ${longtitude[it].toString()} | Latitude: ${latitude[it].toString()}" }
		
	}
}

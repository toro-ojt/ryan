package com.rest.prac

@Grab(
	group='org.codehaus.groovy.modules.http-builder',
	module='http-builder',
	version='0.6'
  )
//import static groovyx.net.http.ContentType.XML
import groovyx.net.http.RESTClient

class GetGeoLoc {
	
	static void main(args) {
		
		getFormattedAdd('Philippines', 'San Fernando', 'pampanga')
	}
	
	static def getFormattedAdd(country, city, province) {
		String URL = 'http://maps.googleapis.com'
		String BASE_PATH = '/maps/api/geocode/xml'
		
		def r_client = new RESTClient(URL)
		def response = r_client.get(path: "${BASE_PATH}", query: [sensor: "${city}", address: "${country}",  city: "${city}", province: "${province}"])
			 println "Location: $response.data.result.address_component.long_name" +
			 		 "\nLatitude: $response.data.result.geometry.location.lat" +
					 "\nLongtitude: $response.data.result.geometry.location.lng"
  }
}


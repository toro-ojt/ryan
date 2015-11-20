package googlemapapi

import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

class GoogleClient {
	//https://roads.googleapis.com= 'https://maps.googleapis.com'
	String url = 'https://maps.googleapis.com'
	String service
	String output
	String md
	def params
	
	def processReq() {
//		println params
		String path
		if (service == 'place') {
			path = "/maps/api/$service/$md/$output"
		} else if (service == 'geolocate') {
			path = "/geolocation/v1/$service"
		} else if (service == 'roads') {
			path = "/v1/$md"
		} else {
			path = "/maps/api/$service/$output"
		}
//		println "$url ---- $service ---- $output ---- $md ---- $path"
		try {
			def g_client = new RESTClient(url)
			def res = g_client.get(path: path, query: params, contentType: TEXT)

			assert res.status == 200

			return res.data.text
			
		} catch (groovyx.net.http.HttpResponseException e) { return e } //'Error: Page Not Found'}
	}
	
}

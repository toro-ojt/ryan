package com.competeAPI

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

class Compete {
	final static String URL = 'https://apps.compete.com'
	String site
	LinkedHashMap params
	def a
	def compete(){
		String path = "/sites/$site/trended/search/"
		try {
			def c_client = new RESTClient(URL)
			def res = c_client.get(path: path, query: params)
	
			assert res.status == 200
			
			res.data.data.trends.rank.date.size().times { println res.data.data.trends.rank.date[it] + " == " + res.data.data.trends.rank.value[it] }
		} catch (groovyx.net.http.HttpResponseException e) { return e }
	}
	
	
}

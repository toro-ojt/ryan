package com.rest.prac

class PracticeRest {
	
	static void main(args) {
	def url = new URL('http://groovy.codehaus.org/')
	def connection = url.openConnection()
	connection.requestMethod = 'GET'
	if (connection.responseCode == 200) {
		println connection.content.text
		println connection.contentType
		println connection.lastModified
		connection.headerFields.each {
			println "> ${it}"
		}
	} else {
		println 'An error occurred: ' +
		connection.responseCode + ' ' +
		connection.responseMessage
	}
}
}
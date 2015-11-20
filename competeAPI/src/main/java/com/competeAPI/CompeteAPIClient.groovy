package com.competeAPI

class CompeteAPIClient {
	
	static void main(args) {
		def p = ['e', 'ewqe', 'e']
		Compete c = new Compete(site: 'facebook.com', params: [apikey: '2737eb0c713e1dd36d0b3a39bb7bb0c2', metrics: 'rank, uv'])
		c.compete()
		p.size().times { println p[it]}
	}
	
}

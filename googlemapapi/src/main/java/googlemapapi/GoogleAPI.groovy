package googlemapapi

import groovy.swing.SwingBuilder
import static javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.JScrollPane
import java.awt.BorderLayout as BL

class GoogleAPI {

	static void main(args){
		//		def ea = [a: 1, b:2, c: 3]
		//		List a = []
		//		println ea
		//		ea.each { k, v -> a << "$k=$v"  }
		//		def b = a.join('&')
		//		println b

		GoogleClient gc
		//= new GoogleClient(service: 'geocode', output: 'xml', params: [address: 'Philippines', city: 'San Fernando', province: 'pampanga'])
		//gc.processReq()
		Scanner sc = new Scanner(System.in)
		String p_name, p_value, serv, out, d
		boolean trz = true
		//		AIzaSyBWVUoT14vkn4k6ZrJpIx2E-brhoKVMOZ8
		//		Mode: driving
		//		Origin: 75 9th Ave, New York, NY
		//		Destination: MetLife Stadium Dr East Rutherford, NJ 07073

		//		println 'Google MAP API'
		//
		//		print '\nService: '
		//		serv = sc.nextLine()
		//		print 'Output: '
		//		out = sc.nextLine()
		def param = [:]
		//		println 'Input Parameters' +
		//		'\n Type: \"done\" to Finish'
		//		while(trz) {
		//			print 'Parameter Name : '
		//			p_name = sc.nextLine()
		//			print 'Parameter Value: '
		//			p_value = sc.nextLine()
		//			print 'Press \"Enter\" if you want to add more or Type: \"done\" to Finish\n> '
		//			d = sc.nextLine()
		//			println '-------------------------\n'
		//			if (d.trim().toLowerCase() == 'done')  trz = false
		//
		//			if (p_name.trim() != '' && p_value != '')  param.put(p_name, p_value)
		//		}
		//
		//		//println "$param"
		//		gc = new GoogleClient(service: serv, output: out, params: param)
		//		gc.processReq()
		//		param.each { k, v -> println "$k: $v" }
		String frmt, slct, url, p_o
		def sB = new SwingBuilder()
		def fr
		sB.edt{
			lookAndFeel 'nimbus'
			fr = frame(title: 'Google API Client',
			show: true,
			locationRelativeTo: null,
			resizable: false,
			defaultCloseOperation: EXIT_ON_CLOSE,
			pack: true){

				hbox{
					vbox{
						panel(constraints: BL.NORTH){
							tableLayout {
								tr {
									td (colspan: 2){
										panel(constraints: BL.CENTER){
											label 'Service: '
											comboBox id: 'service', items: [
												'Directions',
												'Distance Matrix',
												'Elevation',
												'Geocoding',
												'Geolocation',
												'Roads',
												'Time Zone',
												'Places'
											], preferredSize: [230, 30], actionPerformed: {
												url = 'https://maps.googleapis.com'
												if (service.selectedItem == 'Places') {
													road_opt.visible = false
													other_opt.visible = true
												} else if (service.selectedItem == 'Roads') {
													url = 'https://roads.googleapis.com'
													road_opt.visible = true
													other_opt.visible = false
												} else {
													url = 'https://maps.googleapis.com'
													other_opt.visible = false
													road_opt.visible = false
												}
												fr.pack()
											}
										}
									}
								}
								tr {
									td (colfill: true){
										panel(constraints: BL.CENTER, visible: false, id: 'other_opt'){
											label 'Options: '
											comboBox id: 'p_opt', items: [
												'Place Search',
												'Place Details',
												'Auto-Complete'
											]
										}
									}
								}
								
								tr {
									td (colfill: true){
										panel(constraints: BL.CENTER, visible: false, id: 'road_opt'){
											label 'Options: '
											comboBox id: 'r_option', items: [
												'Snap to Roads',
												'Speed Limits'
											]
										}
									}
								}
								tr {
									td (colspan: 2){
										panel(constraints: BL.CENTER){
											label 'Output: '
											comboBox id: 'format', items: ['XML Format', 'JSON Format'], preferredSize: [230, 30]
										}
									}
								}
								tr {
									td {
										panel(constraints: BL.CENTER){ label 'Enter Paramaters: ' }
									}
								}
								tr {
									td {
										panel(constraints: BL.CENTER){
											label 'Name:  '
											textField id: 'p_n', columns: 13
										}
									}
									td (rowspan: 2){
										button 'Add', preferredSize: [60, 60], actionPerformed: {
											if(p_n.text.trim() != '' && p_v.text.trim() != '') {
												param.put(p_n.text, p_v.text)
												l_p.text = ''
												param.each { k, v -> l_p.text = l_p.text + "$k\t|   $v\n" }
												p_n.text = ''
												p_v.text = ''
												btn_p.enabled = true
											}
										}
									}
								}
								tr {
									td {
										panel(constraints: BL.CENTER){
											label 'Value:  '
											textField id: 'p_v', columns: 13
										}
									}
								}
								tr {
									td(colspan: 2) {
										panel(constraints: BL.CENTER){
											tableLayout {
												tr {
													td { label '    Name' }
													td { label '|' }
													td { label 'Value' }
												}
												tr {
													td(colspan: 3){
														scrollPane (verticalScrollBarPolicy:JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, id: "s_p") {
															textArea id: 'l_p', columns: 23, rows: 8
														}
													}
												}
												tr {
													td(colspan: 3){
														button id: 'btn_p', 'Process Request', preferredSize: [290, 30], enabled: false, actionPerformed: {
															if(service.selectedIndex > -1 && format.selectedIndex > -1 && param.size() != 0) {
																//param.put('key', 'AIzaSyBWVUoT14vkn4k6ZrJpIx2E-brhoKVMOZ8')
																frmt = (format.selectedItem == 'XML Format') ? 'xml': 'json'
																if (service.selectedItem == 'Directions') { slct = 'directions' }
																else if (service.selectedItem == 'Distance Matrix') { slct = 'distancematrix' }
																else if (service.selectedItem == 'Elevation') { slct = 'elevation' }
																else if (service.selectedItem == 'Geocoding') { slct = 'geocode' }
																else if (service.selectedItem == 'Geolocation') { slct = 'geolocate' }
																else if (service.selectedItem == 'Roads') { slct = 'roads' }
																else if (service.selectedItem == 'Time Zone') { slct = 'timezone' }
																else if (service.selectedItem == 'Places') { slct = 'place' }
																
																
																if (p_opt.selectedItem == 'Place Search' && other_opt.visible == true) { p_o = 'nearbysearch' }
																else if (p_opt.selectedItem == 'Place Details' && other_opt.visible == true) { p_o = 'details' }
																else if (p_opt.selectedItem == 'Auto-Complete' && other_opt.visible == true) { p_o = 'autocomplete' }
																else if (r_option.selectedItem == 'Snap to Roads' && road_opt.visible == true) { p_o = 'snapToRoads' }
																else if (r_option.selectedItem == 'Speed Limits' && road_opt.visible == true) { p_o = 'speedLimits' }
																
																gc = new GoogleClient(url: url, service: slct, output: frmt, params: param, md: p_o)
																res.text = gc.processReq()
																l_p.text = ''
																param = [:]
																format.selectedIndex = 0
																service.selectedIndex = 0
																p_opt.selectedIndex = 0
																btn_p.enabled = false
															}
														}
													}
												}
											}

										}
									}
								}
							}
						}
					}
					vbox{
						panel(constraints: BL.CENTER){
							scrollPane(verticalScrollBarPolicy: JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, id: "s_r") {
								textArea id: 'res', columns: 30, rows: 23
							}
						}
					}
				}
			}
		}
	}
}

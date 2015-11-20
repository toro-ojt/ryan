package com.calculator

@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*
import static javax.swing.JFrame.EXIT_ON_CLOSE
import groovy.swing.SwingBuilder
import groovy.swing.factory.TDFactory;

import java.awt.BorderLayout
import java.awt.Panel;

class Calculator {

	static void main(args) {

		def fr, tb1
		def sB = new SwingBuilder()
		sB.edt {
			lookAndFeel 'nimbus'
			fr = frame (title: 'Calculator',
			show: true,
			locationRelativeTo: null,
			resizable: false,
			defaultCloseOperation: EXIT_ON_CLOSE,
			pack: true){
				hbox{
					panel(constraints: BorderLayout.CENTER){
						tableLayout {
							tr{
								td {
									panel{
										label 'Value 1: '
										textField id: 'num1', columns: 18
									}
								}
								td(rowspan: 3) {
									panel {
										tableLayout {
											tr {
												td{
													button 'Add', preferredSize: [100, 25], actionPerformed: {
														if ( (num1.text.trim() != '' && num1.text.isNumber() && !(num1.text.contains('.')) ) && ( num2.text.trim() != '' && num2.text.isNumber() && !(num1.text.contains('.')) ) ) {
															res.text = 'Results: ' + calc(num1.text.toInteger(), num2.text.toInteger(), 'Add')
														} else {
															res.text = 'Please enter a whole number'
														}
													}
												}
											}
											tr {
												td{
													button 'Subtract', preferredSize: [100, 25], actionPerformed: {
														if ( (num1.text.trim() != '' && num1.text.isNumber() && !(num1.text.contains('.')) ) && ( num2.text.trim() != '' && num2.text.isNumber() && !(num1.text.contains('.')) ) ) {
															res.text = 'Results: ' + calc(num1.text.toInteger(), num2.text.toInteger(), 'Subtract')
														} else {
															res.text = 'Please enter a whole number'
														}
													}
												}
											}
											tr {
												td{
													button 'Multiply', preferredSize: [100, 25], actionPerformed: {
														if ( (num1.text.trim() != '' && num1.text.isNumber() && !(num1.text.contains('.')) ) && ( num2.text.trim() != '' && num2.text.isNumber() && !(num1.text.contains('.')) ) ) {
															res.text = 'Results: ' + calc(num1.text.toInteger(), num2.text.toInteger(), 'Multiply')
														} else {
															res.text = 'Please enter a whole number'
														}
													}
												}
											}
											tr {
												td{
													button 'Divide', preferredSize: [100, 25], actionPerformed: {
														if ( (num1.text.trim() != '' && num1.text.isNumber() && !(num1.text.contains('.')) ) && ( num2.text.trim() != '' && num2.text.isNumber() && !(num1.text.contains('.')) ) ) {
															res.text = 'Results: ' + calc(num1.text.toInteger(), num2.text.toInteger(), 'Divide')
														} else {
															res.text = 'Please enter a whole number'
														}
													}
												}
											}
										}
									}
								}
							}

							tr{
								td {
									panel{
										label 'Value 2: '
										textField id: 'num2', columns: 18
									}
								}
							}

							tr {
								td(colspan: 3) {
									panel{
										label '              >>'
										textField id:'res', columns: 13, enabled: false
										label '<<'
									}
								}
							}
						}
					}
				}
			}
		}

		//		println add(2, 5)
		//		println subtract(2, 5)
		//		println mult(2, 5)
		//		println div(2, 9)
	}

	static String calc(num1, num2, algo) {
		def tem
		def s_clnt = new SOAPClient('http://www.dneonline.com/calculator.asmx')
		def res = s_clnt.send(SOAPAction: "http://tempuri.org/$algo") {
			body {
				"$algo"(xmlns:tem='http://tempuri.org/') {
					intA(num1)
					intB(num2)
				}
			}
		}
		return res."${algo}Response"."${algo}Result"
	}
}

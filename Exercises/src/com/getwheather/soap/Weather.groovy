package com.getwheather.soap

import groovy.lang.Grab
import java.awt.BorderLayout as BL
import java.sql.ResultSet

import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.BorderFactory

@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*

class Weather {

	static WeatherInfo wi = new WeatherInfo()
	static ForeCast fc = new ForeCast()
	static cw = new CityWeather()

	static main(args) {

		def bld = new groovy.swing.SwingBuilder()

		def frme = bld.frame (
				pack: true,
				locationRelativeTo: null,
				visible: true,
				title: "Weather",
				layout: new BL(),
				defaultCloseOperation: JFrame.EXIT_ON_CLOSE,
				) {
					vbox(constraints: BL.CENTER){
						panel(layout: new BL()){
							vbox(constraints: BL.NORTH){
								hbox{
									label(text: "Weather Information")
									button(text: "Show Information", actionPerformed: {

										getInfo()
										if (wi.id) {
											result.setText "Weather Information: \nWeather ID\t\t|\tDescription\n"
											wi.id.size().times { result.setText result.text + "${wi.id[it].toString()}\t\t|\t${wi.desc[it].toString()}\n" }
											result.setCaretPosition(0)
										} else {
											result.setText "No Data."
										}
									} )
								}
								hbox{
									def txtGetForeCast = textField(columns: 15)
									def btnGetForeCast = button(text: "Get Forecast", actionPerformed: {

										getForecast(txtGetForeCast.text)
										if (fc.id) {
											result.setText "Forecast Information: \nCity: ${fc.city}\t\tState: ${fc.state}\nWeather Station: ${fc.station}\n\n"
											fc.id.size().times {
												result.setText result.text + """Date: ${fc.date[it].toString()}
Weather ID: ${fc.id[it].toString()}\tDescription: ${fc.desc[it].toString()}
Temperatures:
    Morning: ${fc.temp_low[it].toString()}
    Daytime: ${fc.temp_high[it].toString()}
Probability Of Precipiation:
    Night Time: ${fc.night_time[it].toString()}
    Day Time: ${fc.day_time[it].toString()}""" + "\n-----------------\n\n"
											}
											result.setCaretPosition(0)
										} else {
											result.setText "No Data Found"
											txtGetForeCast.setText "Please Enter a Zip Code"
										}
									})
								}
								hbox{
									def txtGetWeather = textField(columns: 15)
									def btnGetWeather = button(text: "Get Weather Info", actionPerformed: {

										getWeather(txtGetWeather.text)
										if (cw.id) {

											result.setText "Weather Information\n"
											cw.id.size().times {
												result.setText result.text + """City: ${cw.city[it].toString()}          State: ${cw.state[it].toString()}
Weather ID: ${cw.id[it].toString()}          Description: ${cw.desc[it].toString()}
Temperature: ${cw.temp[it].toString()}
Relative Humidity: ${cw.rel_humid[it].toString()}
Wind: ${cw.wind[it].toString()}
Pressure: ${cw.pressure[it].toString()}
""" + "\n-----------------\n\n"
											}
											result.setCaretPosition(0)
										} else {
											result.setText "No Data Found"
											txtGetWeather.setText "Please Enter a Zip Code"
										}
									})
								}
							}
							panel(constraints: BL.CENTER, border: BorderFactory.createEmptyBorder(80, 10, 80, 10)){
							}
						}
					}
					vbox(constraints: BL.EAST){
						scrollPane( verticalScrollBarPolicy:JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, id: "scroll" ) {
							textArea(text: "Information Here", id: "result",columns: 35)
						}
					}
				}
	}

	def static getForecast(zip) {
		fc = new ForeCast()
		def weat, p
		def s_clnt = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
		def res = s_clnt.send(SOAPAction: 'http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP') {
			body {
				GetCityForecastByZIP(xmlns:weat= 'http://ws.cdyne.com/WeatherWS/') { ZIP(zip) }
			}
		}
		p = res.GetCityForecastByZIPResponse.GetCityForecastByZIPResult
		if (p.Success == "true") {
			fc.state = p.State
			fc.city = p.City
			fc.station = p.WeatherStationCity
			fc.id = p.ForecastResult.Forecast.WeatherID
			fc.date = p.ForecastResult.Forecast.Date
			fc.desc = p.ForecastResult.Forecast.Desciption
			fc.temp_high = p.ForecastResult.Forecast.Temperatures.DaytimeHigh
			fc.temp_low = p.ForecastResult.Forecast.Temperatures.MorningLow
			fc.night_time = p.ForecastResult.Forecast.ProbabilityOfPrecipiation.Nighttime
			fc.day_time = p.ForecastResult.Forecast.ProbabilityOfPrecipiation.Daytime
		}
	}

	def static getWeather(zip) {
		cw = new CityWeather()
		def weat, p
		def s_clnt = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
		def res = s_clnt.send(SOAPAction: 'http://ws.cdyne.com/WeatherWS/GetCityWeatherByZIP') {
			body {
				GetCityWeatherByZIP(xmlns:weat= 'http://ws.cdyne.com/WeatherWS/') { ZIP(zip) }
			}
		}
		p = res.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult
		if (p.Success == "true") {

			cw.id = p.WeatherID
			cw.state = p.State
			cw.city = p.City
			cw.station = p.WeatherStationCity
			cw.desc = p.Description
			cw.temp = p.Temperature
			cw.rel_humid = p.RelativeHumidity
			cw.wind = p.Wind
			cw.pressure = p.Pressure
		}
	}

	def static getInfo() {
		def weat, p
		def s_clnt = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
		def res = s_clnt.send(SOAPAction: 'http://ws.cdyne.com/WeatherWS/GetWeatherInformation') {
			body {
				GetWeatherInformation(xmlns:weat= 'http://ws.cdyne.com/WeatherWS/')
			}
		}
		p = res.GetWeatherInformationResponse.GetWeatherInformationResult.WeatherDescription
		if (p.WeatherID) {
			wi.id = p.WeatherID
			wi.desc = p.Description
			wi.pic = p.PictureURL
		}
	}
}

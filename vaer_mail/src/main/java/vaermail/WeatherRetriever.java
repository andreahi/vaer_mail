package vaermail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WeatherRetriever {
	private final static String  PRECIPITATION ="precipitation";
	private final static String  WINDDIRECTION ="windDirection";
	private final static String  WINDSPEED ="windSpeed";
	private final static String  TEMPERATURE ="temperature";

	// create a formated forecast message
	private static String createMessage(String cloud, float precipitation, int temperature,  String windDirection, String windSpeed){
		String weatherMsg = "";
		weatherMsg+=cloud+"\n";
		weatherMsg+="Nedbør: " + precipitation + "mm\n";
		weatherMsg+="Temperatur: " + temperature + " celsius\n";
		weatherMsg+="Vind: " + windDirection + ", " + windSpeed +"\n";
		
		return weatherMsg;
	}
	
	//get weather forecase from the url
	public static String get_vaer(String url) throws MalformedURLException, SAXException, IOException, ParserConfigurationException{

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document document = factory.newDocumentBuilder().parse(new URL(url).openStream());
		NodeList nodeList = document.getElementsByTagName("tabular");//get element where the weather data reside

		Node node = nodeList.item(0);
		Element weatherElement  = (Element)node.getChildNodes().item(1);
		NodeList weatherdata = weatherElement.getChildNodes();
		int temperature  = -1;
		String windDirection  = "NaN";
		String windSpeed = "NaN";
		float precipitation = -1;
		String cloud = "NaN";
		for(int j = 0; j < weatherdata.getLength(); j++){ //loop trough the weather data looking for element node
			if(weatherdata.item(j).getNodeType() == Node.ELEMENT_NODE){
				Element e = (Element)weatherdata.item(j);
				if(e.getLocalName().equals("symbol"))
					cloud = e.getAttribute("name").trim();

				else if(e.getLocalName().equals(PRECIPITATION))
					precipitation = Float.parseFloat(e.getAttribute("value").trim());

				else if(e.getLocalName().equals(WINDDIRECTION))
					windDirection = e.getAttribute("name").trim();

				else if(e.getLocalName().equals(WINDSPEED))
					windSpeed = e.getAttribute("name").trim();

				else if(e.getLocalName().equals(TEMPERATURE))
					temperature = Integer.parseInt(e.getAttribute("value").trim());
			}
		}
		return createMessage(cloud, precipitation, temperature, windDirection, windSpeed);
	}
}

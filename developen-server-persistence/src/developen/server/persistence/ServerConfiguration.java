package developen.server.persistence;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class ServerConfiguration {

	
	private String url;
	
	private String driver;
	
	private String userName;
	
	private String password;

	private XPath xPath;
	
	private InputStream input;
	
	private Document document;
	

	public ServerConfiguration configure(){

		return this.configure("connection.xml");

	}

	
	public ServerConfiguration configure(String file) {

		
		try {

			input = Thread.currentThread().
					
					getContextClassLoader().
					
					getResourceAsStream(file);
			
			document = DocumentBuilderFactory.
					
					newInstance().
					
					newDocumentBuilder().
					
					parse(new InputSource(input));
			
			xPath = XPathFactory.newInstance().
					
					newXPath();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return this;

		
	}

	
	public String getUrl() {

		
		if (url == null || url.isEmpty())

			try {

				url = (String) xPath.
						
						compile("//configuration//jdbc//url").
						
						evaluate(document, XPathConstants.STRING);

			} catch (XPathExpressionException e) {

				e.printStackTrace();

			}

		return url;

		
	}

	
	public void setUrl(String url) {

		this.url = url;

	}

	
	public String getDriver() {

		
		if (driver == null || driver.isEmpty())

			try {

				driver = (String) xPath.
						
						compile("//configuration//jdbc//driver").
						
						evaluate(document, XPathConstants.STRING);

			} catch (XPathExpressionException e) {

				e.printStackTrace();

			}

		return driver;

		
	}

	
	public void setDriver(String driver) {

		this.driver = driver;

	}

	
	public String getUserName() {

		
		if (userName == null || userName.isEmpty())

			try {

				userName = (String) xPath.
						
						compile("//configuration//jdbc//username").
						
						evaluate(document, XPathConstants.STRING);

			} catch (XPathExpressionException e) {

				e.printStackTrace();

			}

		return userName;

		
	}

	
	public void setUserName(String userName) {

		this.userName = userName;

	}

	
	public String getPassword() {

		
		if (password == null || password.isEmpty())

			try {

				password = (String) xPath.
						
						compile("//configuration//jdbc//password").
						
						evaluate(document, XPathConstants.STRING);

			} catch (XPathExpressionException e) {

				e.printStackTrace();  

			}

		return password;

		
	}

	
	public void setPassword(String password) {

		this.password = password;

	}

	
	public ServerSessionFactory buildServerSessionFactory(){
		
		return new ServerSessionFactory(this);
				
	}

	
}
package developen.server.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import developen.common.persistence.session.Session;
import developen.common.persistence.session.SessionFactory;


public class ServerSessionFactory implements SessionFactory{


	private ServerConfiguration configuration;


	public ServerSessionFactory(ServerConfiguration configuration){

		this.configuration = configuration;

	}


	public ServerConfiguration getConfiguration() {

		return configuration;

	}


	public void setConfiguration(ServerConfiguration configuration) {

		this.configuration = configuration;

	}


	public Connection createConnection() 

			throws

			SQLException,

			SAXException,

			IOException,

			ParserConfigurationException,

			XPathExpressionException,

			InstantiationException,

			IllegalAccessException,

			ClassNotFoundException{


		Class.forName(getConfiguration().getDriver());

		return DriverManager.getConnection(

				getConfiguration().getUrl(), 

				getConfiguration().getUserName(), 

				getConfiguration().getPassword());


	}


	public Session createSession()

			throws 

			XPathExpressionException, 

			InstantiationException, 

			IllegalAccessException, 

			ClassNotFoundException, 

			SQLException, 

			SAXException, 

			IOException, 

			ParserConfigurationException{


		return new ServerSession(createConnection());


	}


}
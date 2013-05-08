package developen.common.persistence.query;

import java.io.Serializable;

public class Column implements Serializable {


	private static final long serialVersionUID = -8216602146208140006L;

	private String name = "";

	private String alias = "";

	private Class<?> from;


	public Column(String name){

		setName(name);

	}


	public Column(String name, Class<?> from){


		setName(name);

		setFrom(from);


	}


	public Column(String name, Class<?> from, String alias){


		setName(name);

		setFrom(from);

		setAlias(alias);


	}


	public String getName() {

		return name;

	}


	public void setName(String name) {

		this.name = name;

	}


	public String getAlias() {

		return alias;

	}


	public void setAlias(String alias) {

		this.alias = alias;

	}


	public Class<?> getFrom() {

		return from;

	}


	public void setFrom(Class<?> from) {

		this.from = from;

	}


	public String toString(){

		
		return (getFrom() == null ? "" : '"' + getFrom().getSimpleName() + '"' + ".") 

				+ '"' + getName() + '"' 

				+ (getAlias().isEmpty() ? "" : " AS " + '"' + getAlias() + '"');

		
	}


}
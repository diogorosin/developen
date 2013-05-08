package developen.common.persistence.query;

import java.io.Serializable;

public class Limit implements Serializable {


	private static final long serialVersionUID = -3815300502205025046L;

	private Param low;

	private Param high;

	private Query query;


	public Limit(Object low, Object high) {

		setLow(new Param(low));

		setHigh(new Param(high));

	}


	public String toString(){

		return " LIMIT " + getLow().getValue() + " OFFSET " + getHigh().getValue();

	}


	public Query getQuery() {

		return query;

	}


	public void setQuery(Query query) {

		this.query = query;

	}


	public Param getLow() {

		return low;

	}


	public void setLow(Param low) {

		this.low = low;

	}


	public Param getHigh() {

		return high;

	}


	public void setHigh(Param high) {

		this.high = high;

	}


}

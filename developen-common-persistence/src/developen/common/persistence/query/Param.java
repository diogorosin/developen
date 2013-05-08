package developen.common.persistence.query;

import java.io.Serializable;

public class Param implements Serializable {

	
	private static final long serialVersionUID = 4721578820442873650L;
	
	private Object value;
	
	private int index;
	
	
	public Param(Object value){
		
		this.setValue(value);
		
	}

	
	public int getIndex() {
		
		return index;
		
	}


	public void setIndex(int index) {
		
		this.index = index;
		
	}
	
	
	public Object getValue() {
		
		return value;
		
	}

	
	public void setValue(Object value) {
		
		this.value = value;
		
	}


}

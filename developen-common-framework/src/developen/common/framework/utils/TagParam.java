package developen.common.framework.utils;


public class TagParam {

	
	public static final String FIELD = "{$field}";
	
	public static final String MAXLENGHT = "{$maxLength}";
	
	public static final String MINLENGHT = "{$minLength}";
	
	public static final String FIRST_VALUE = "{$first_value}";
	
	public static final String SECOND_VALUE = "{$second_value}";
	
	private String name;
	
	private Object value;


	public TagParam(String name, Object value){
		
		
		setName(name);
		
		setValue(value);
		
		
	}
	
	
	public String getName() {
		
		return name;
		
	}
	
	
	public void setName(String name) {
		
		this.name = name;
		
	}

	
	public void setValue(Object value) {
		
		this.value = value;
		
	}

	
	public Object getValue() {
		
		return value;
		
	}

	
}
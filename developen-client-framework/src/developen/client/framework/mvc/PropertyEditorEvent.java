package developen.client.framework.mvc;



public class PropertyEditorEvent {
	
	
	private Object property;

	
	public PropertyEditorEvent(Object property){
		
		this.setProperty(property);
		
	}

	
	public Object getProperty() {
		
		return property;
		
	}
	

	public void setProperty(Object property) {
		
		this.property = property;
		
	}
	
	
}
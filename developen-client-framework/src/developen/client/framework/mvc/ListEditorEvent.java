package developen.client.framework.mvc;




public class ListEditorEvent {
	
	
	private Object object;

	
	public ListEditorEvent(Object object){
		
		this.setObject(object);
		
	}

	
	public Object getObject() {
		
		return object;
		
	}
	

	public void setObject(Object object) {
		
		this.object = object;
		
	}
	
	
}
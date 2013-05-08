package developen.common.framework.messenger;

import developen.common.framework.utils.Tag;

public abstract class Message {

	
	private Tag text;

	
	public Message(Tag text){
		
		this.setText(text);
		
	}
	
	
	public void setText(Tag text) {
		
		this.text = text;
		
	}
	

	public Tag getText() {
		
		return text;
		
	}
	

}
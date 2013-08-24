package developen.client.framework.widget;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import developen.common.framework.mvc.View;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Action;

public abstract class DBAction extends Action implements View {

	
	private static final long serialVersionUID = 717574648430504776L;

	protected Condition condition;
	
	
	public DBAction(Tag caption) {

		super(caption);

	}
	

	public abstract void actionPerformed(ActionEvent arg0);
	

	public void modelPropertyChanged(PropertyChangeEvent event) {

		setEnabled(getCondition().analyse(event, this));

	}

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new AllwaysEnabledCondition();
		
		return condition;

		
	}

	
	public void setCondition(Condition condition){

		this.condition = condition;

	}


}
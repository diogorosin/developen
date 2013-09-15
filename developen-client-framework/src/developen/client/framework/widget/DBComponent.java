package developen.client.framework.widget;

import developen.common.framework.mvc.View;

public interface DBComponent extends View {
	
	
	public void setPropertyName(String propertyName);

	public String getPropertyName();
	
	public void setCondition(Condition condition);

	public Condition getCondition();

	public void setFixedValue(boolean fixedValue);

	public boolean isFixedValue();

	
}
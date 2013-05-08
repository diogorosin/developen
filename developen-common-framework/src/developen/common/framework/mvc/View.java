package developen.common.framework.mvc;


import java.beans.PropertyChangeEvent;

public interface View {

	public abstract void modelPropertyChanged(PropertyChangeEvent evt);

}
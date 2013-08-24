package developen.client.framework.widget;

import developen.common.framework.mvc.Model;
import developen.common.framework.widget.Action;

public interface MimeTypeProvider {


	public Action getEntryActionOfMimeType(Class<? extends Model> model);
	

	public void setEntryActionOfMimeType(Class<? extends Model> model, Action action);

	
}
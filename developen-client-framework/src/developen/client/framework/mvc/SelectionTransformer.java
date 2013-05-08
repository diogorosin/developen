package developen.client.framework.mvc;

import java.util.List;

public interface SelectionTransformer {
	
	public List<Object> transform(List<Object> selection) throws Exception; 

}
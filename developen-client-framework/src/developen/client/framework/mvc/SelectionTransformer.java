package developen.client.framework.mvc;

import java.util.List;

public interface SelectionTransformer<E> {
	
	public List<E> transform(List<E> selection) throws Exception; 

}
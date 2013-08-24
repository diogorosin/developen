package developen.client.osm.factory;


import developen.client.framework.mvc.EntryController;
import developen.client.osm.widget.MVCBean;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.View;

public class MVCBeanFactory {


	public static MVCBean create(Class<? extends Model> model, 

			Class<? extends View> view, 

			Class<? extends Controller> controller) 

					throws Exception{

		Model m = model.newInstance();

		Controller c = controller.newInstance();

		c.setModel(m);

		View v = view.getConstructor(c.getClass()).newInstance(c);

		c.addView(v);

		if (c instanceof EntryController)

			((EntryController) c).clear();

		MVCBean bean = new MVCBean();

		bean.setModel(m);

		bean.setView(v);

		bean.setController(c);

		return bean;


	}


}
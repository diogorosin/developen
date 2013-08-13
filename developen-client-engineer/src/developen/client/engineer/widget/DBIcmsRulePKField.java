package developen.client.engineer.widget;

import java.awt.BorderLayout;

import javax.swing.JComponent;

import developen.client.engineer.mvc.IcmsRulePKController;
import developen.client.engineer.mvc.IcmsRulePKView;
import developen.common.engineer.mvc.IcmsRulePK;


public class DBIcmsRulePKField extends JComponent{


	private static final long serialVersionUID = -6009120201640056143L;

	private IcmsRulePKView view;

	private IcmsRulePKController controller;


	public DBIcmsRulePKField(IcmsRulePK model){


		controller = new IcmsRulePKController();

		view = new IcmsRulePKView(controller);

		controller.addView(view);

		controller.setModel(model);

		setLayout(new BorderLayout());

		add(view, BorderLayout.CENTER);


	}


	public IcmsRulePKView getView() {

		return view;

	}


	public IcmsRulePKController getController() {

		return controller;

	}


	public void requestFocus(){


		if (getView().getIcmsField().isEnabled())

			getView().getIcmsField().requestFocus();

		else


			if (getView().getFromField().isEnabled())

				getView().getFromField().requestFocus();

			else

				if (getView().getToField().isEnabled())

					getView().getToField().requestFocus();


	}


}
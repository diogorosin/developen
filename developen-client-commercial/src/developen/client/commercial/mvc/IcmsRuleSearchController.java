package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.mvc.Icms;
import developen.common.commercial.mvc.IcmsRule;
import developen.common.commercial.mvc.Rule;
import developen.common.commercial.mvc.State;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;


public class IcmsRuleSearchController extends SearchController {


	public static final String ICMS_PROPERTY = "Icms";
	
	public static final String FROM_PROPERTY = "From";
	
	public static final String TO_PROPERTY = "To";
	
	public static final String RULE_PROPERTY = "Rule";


	public IcmsRuleSearchModel getModel(){

		return (IcmsRuleSearchModel) super.getModel();

	}


	public void changeIcmsProperty(Icms newValue){

		setModelProperty(IcmsRuleSearchController.ICMS_PROPERTY, newValue);

	}


	public void changeFromProperty(State newValue){

		setModelProperty(IcmsRuleSearchController.FROM_PROPERTY, newValue);

	}


	public void changeToProperty(State newValue){

		setModelProperty(IcmsRuleSearchController.TO_PROPERTY, newValue);

	}
	
	
	public void changeRuleProperty(Rule newValue){

		setModelProperty(IcmsRuleSearchController.RULE_PROPERTY, newValue);

	}

	
	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(IcmsRule.class);
		
		if (getModel().getIcms()!=null)
			
			query.add(new Equal(new Column("icms", IcmsRule.class), getModel().getIcms()));
		
		if (getModel().getFrom()!=null)
			
			query.add(new Equal(new Column("from", IcmsRule.class), getModel().getFrom()));
		
		if (getModel().getTo()!=null)
			
			query.add(new Equal(new Column("to", IcmsRule.class), getModel().getTo()));
		
		if (getModel().getRule()!=null)
			
			query.add(new Equal(new Column("rule", IcmsRule.class), getModel().getRule()));
		
		return query;

	
	}


}
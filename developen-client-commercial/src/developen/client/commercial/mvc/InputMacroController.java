package developen.client.commercial.mvc;

import developen.common.commercial.mvc.InputMacro;
import developen.common.fiscal.mvc.InputCfop;

public class InputMacroController extends MacroController {


	public static final String CFOP_STATE_1_PROPERTY = "CfopState1";

	public static final String CFOP_INTERSTATE_1_PROPERTY = "CfopInterstate1";

	public static final String CFOP_EXTERIOR_1_PROPERTY = "CfopExterior1";

	public static final String CFOP_STATE_2_PROPERTY = "CfopState2";

	public static final String CFOP_INTERSTATE_2_PROPERTY = "CfopInterstate2";

	public static final String CFOP_EXTERIOR_2_PROPERTY = "CfopExterior2";

	public static final String CFOP_STATE_3_PROPERTY = "CfopState3";

	public static final String CFOP_INTERSTATE_3_PROPERTY = "CfopInterstate3";

	public static final String CFOP_EXTERIOR_3_PROPERTY = "CfopExterior3";
	
	public static final String CFOP_STATE_4_PROPERTY = "CfopState4";

	public static final String CFOP_INTERSTATE_4_PROPERTY = "CfopInterstate4";

	public static final String CFOP_EXTERIOR_4_PROPERTY = "CfopExterior4";

	
	public InputMacro getModel(){

		return (InputMacro) super.getModel();

	}


	public void changeCfopState1Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_STATE_1_PROPERTY, newValue);

	}


	public void changeCfopInterstate1Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_INTERSTATE_1_PROPERTY, newValue);

	}
	
	
	public void changeCfopExterior1Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_EXTERIOR_1_PROPERTY, newValue);

	}
	
	
	public void changeCfopState2Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_STATE_2_PROPERTY, newValue);

	}


	public void changeCfopInterstate2Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_INTERSTATE_2_PROPERTY, newValue);

	}
	
	
	public void changeCfopExterior2Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_EXTERIOR_2_PROPERTY, newValue);

	}
	
	
	public void changeCfopState3Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_STATE_3_PROPERTY, newValue);

	}


	public void changeCfopInterstate3Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_INTERSTATE_3_PROPERTY, newValue);

	}
	
	
	public void changeCfopExterior3Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_EXTERIOR_3_PROPERTY, newValue);

	}
	
	
	public void changeCfopState4Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_STATE_4_PROPERTY, newValue);

	}


	public void changeCfopInterstate4Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_INTERSTATE_4_PROPERTY, newValue);

	}
	
	
	public void changeCfopExterior4Property(InputCfop newValue) {

		setModelProperty(InputMacroController.CFOP_EXTERIOR_4_PROPERTY, newValue);

	}
	
	
	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(InputMacroController.CFOP_STATE_1_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_INTERSTATE_1_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_EXTERIOR_1_PROPERTY, null);		
		
		setModelProperty(InputMacroController.CFOP_STATE_2_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_INTERSTATE_2_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_EXTERIOR_2_PROPERTY, null);
		
		setModelProperty(InputMacroController.CFOP_STATE_3_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_INTERSTATE_3_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_EXTERIOR_3_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_STATE_4_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_INTERSTATE_4_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_EXTERIOR_4_PROPERTY, null);
		

	}


	public void onInclude() throws Exception{


		super.onInclude();
		
		setModelProperty(InputMacroController.CFOP_STATE_1_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_INTERSTATE_1_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_EXTERIOR_1_PROPERTY, null);		
		
		setModelProperty(InputMacroController.CFOP_STATE_2_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_INTERSTATE_2_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_EXTERIOR_2_PROPERTY, null);
		
		setModelProperty(InputMacroController.CFOP_STATE_3_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_INTERSTATE_3_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_EXTERIOR_3_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_STATE_4_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_INTERSTATE_4_PROPERTY, null);

		setModelProperty(InputMacroController.CFOP_EXTERIOR_4_PROPERTY, null);


	}


}
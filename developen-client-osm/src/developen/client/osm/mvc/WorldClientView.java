package developen.client.osm.mvc;

import developen.client.application.action.ApplicationAction;
import developen.client.application.i18n.AdministratorTag;
import developen.client.application.i18n.DevelOpenCloudTag;
import developen.client.application.i18n.ModulesTag;
import developen.client.application.i18n.ParameterizationTag;
import developen.client.application.i18n.SecurityTag;
import developen.client.application.mvc.ClientController;
import developen.client.application.mvc.ClientView;
import developen.client.osm.action.ChangePasswordAction;
import developen.client.osm.action.CompanyEntryAction;
import developen.client.osm.action.ExitAction;
import developen.client.osm.action.HelpAboutAction;
import developen.client.osm.action.HelpAction;
import developen.client.osm.action.IcmsEntryAction;
import developen.client.osm.action.InputCfopEntryAction;
import developen.client.osm.action.InputMacroEntryAction;
import developen.client.osm.action.InputOrderEntryAction;
import developen.client.osm.action.LogoutAction;
import developen.client.osm.action.OutputCfopEntryAction;
import developen.client.osm.action.OutputMacroEntryAction;
import developen.client.osm.action.OutputOrderEntryAction;
import developen.client.osm.action.PaymentConditionEntryAction;
import developen.client.osm.action.PersonEntryAction;
import developen.client.osm.action.ProductEntryAction;
import developen.client.osm.action.PurchaseOrderEntryAction;
import developen.client.osm.action.ReceiptConditionEntryAction;
import developen.client.osm.action.RuleEntryAction;
import developen.client.osm.action.SaleOrderEntryAction;
import developen.client.osm.action.SystemCompanyEntryAction;
import developen.client.osm.action.SystemPersonEntryAction;
import developen.client.osm.action.UnitMeasureEntryAction;
import developen.client.osm.i18n.CommercialModuleTag;
import developen.client.osm.i18n.EngineerModuleTag;
import developen.client.osm.i18n.EntriesTag;
import developen.client.osm.i18n.FinanceModuleTag;
import developen.client.osm.i18n.FiscalModuleTag;
import developen.client.osm.i18n.MovementsTag;
import developen.client.osm.i18n.SubjectModuleTag;
import developen.common.engineer.i18n.TributesTag;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.EnglishUSARadioButtonMenuItem;
import developen.common.framework.widget.Menu;
import developen.common.framework.widget.PortugueseBrazilRadioButtonMenuItem;


public class WorldClientView extends ClientView{

	
	private static final long serialVersionUID = 2176404851166695386L;

	private Menu modulesCommercialMenu;
	
	private Menu modulesCommercialEntriesMenu;
	
	private Menu modulesCommercialMovementsMenu;
	
	private Menu modulesFinanceMenu;
	
	private Menu modulesFinanceEntriesMenu;

	private Menu modulesFiscalMenu;

	private Menu modulesFiscalEntriesMenu;

	private Menu modulesEngineerMenu;
	
	private Menu modulesEngineerEntriesMenu;
	
	private Menu modulesEngineerEntriesTributesMenu;

	private Menu modulesSubjectMenu;
	
	private Menu modulesSubjectEntriesMenu;

	protected ExitAction exitAction;
	
	protected LogoutAction logoutAction;
	
	protected HelpAction helpAction;
	
	protected HelpAboutAction helpAboutAction;

	protected SystemCompanyEntryAction systemCompanyEntryAction;
	
	protected PortugueseBrazilRadioButtonMenuItem portugueseBrazilRadioButtonMenuItem;
	
	protected EnglishUSARadioButtonMenuItem englishUSARadioButtonMenuItem;
	
	protected ApplicationAction changePasswordAction;

	protected SystemPersonEntryAction systemPersonEntryAction;

	protected CompanyEntryAction companyEntryAction;
	
	protected PersonEntryAction personEntryAction;
	
	protected ProductEntryAction productEntryAction;
	
	protected UnitMeasureEntryAction unitMeasureEntryAction;
	
	protected IcmsEntryAction icmsEntryAction;
	
	protected RuleEntryAction ruleEntryAction;
	
	protected OutputMacroEntryAction outputMacroEntryAction;
	
	protected InputMacroEntryAction inputMacroEntryAction;

	protected SaleOrderEntryAction saleOrderEntryAction;

	protected PurchaseOrderEntryAction purchaseOrderEntryAction;

	protected OutputOrderEntryAction outputOrderEntryAction;

	protected InputOrderEntryAction inputOrderEntryAction;

	protected ReceiptConditionEntryAction receiptConditionEntryAction;
	
	protected PaymentConditionEntryAction paymentConditionEntryAction;
	
	protected InputCfopEntryAction inputCfopEntryAction;
	
	protected OutputCfopEntryAction outputCfopEntryAction;
	
	
	public WorldClientView(ClientController controller) {

		super(controller);

	}

	
	public Object[] getMenuHierarchy() {

		
		if (menuHierarchy == null){

			menuHierarchy = new Object[]{
					new DevelOpenCloudTag(),

					new Object[]{
						new ModulesTag(),

						new Object[]{
							new CommercialModuleTag(),	

							new Object[]{
								new EntriesTag(),

								getInputMacroEntryAction(),

								getOutputMacroEntryAction()

							},

							new Object[]{
								new MovementsTag(),

								getInputOrderEntryAction(),
								
								getOutputOrderEntryAction()

							}

						},

						new Object[]{
							new FinanceModuleTag(),	

							new Object[]{
								new EntriesTag(),

								getReceiptConditionEntryAction(),
								
								getPaymentConditionEntryAction()

							}

						},

						new Object[]{
							new FiscalModuleTag(),	

							new Object[]{
								new EntriesTag(),

								getInputCfopEntryAction(),
								
								getOutputCfopEntryAction()

							}

						},

						new Object[]{
							new EngineerModuleTag(),	

							new Object[]{
								new EntriesTag(),

								getProductEntryAction(),
								
								getUnitMeasureEntryAction(),
								
								new Object[]{
									new TributesTag(),

									getIcmsEntryAction(),
									
									getRuleEntryAction()

								}

							}

						},

						new Object[]{
							new SubjectModuleTag(),	

							new Object[]{
								new EntriesTag(),

								getPersonEntryAction(),
								
								getCompanyEntryAction()

							}

						}

					},

					new Object[]{
						new AdministratorTag(),

						new Object[]{
							new SecurityTag(),

							getSystemPersonEntryAction()

						},

						new Object[]{
							new ParameterizationTag(),

							getSystemCompanyEntryAction()

						}

					}

			};

		}
		
		return menuHierarchy;

		
	}

	
	public Tag getCaption(){
		
		return new DevelOpenCloudTag();
		
	}


	protected ApplicationAction getSystemPersonEntryAction() {

		
		if (systemPersonEntryAction == null){
			
			systemPersonEntryAction = new SystemPersonEntryAction(getDesktop());
			
			getController().addView(systemPersonEntryAction);
			
		}
		
		return systemPersonEntryAction;

		
	}

	
	protected Menu getModulesMenu(){

		
		if (modulesMenu==null){

			modulesMenu = new Menu(new ModulesTag());
			
			modulesMenu.add(getModulesCommercialMenu());
			
			modulesMenu.add(getModulesFinanceMenu());

			modulesMenu.add(getModulesFiscalMenu());

			modulesMenu.add(getModulesEngineerMenu());
			
			modulesMenu.add(getModulesSubjectMenu());
			
			modulesMenu.addSeparator();
			
			modulesMenu.add(getLogoutAction());
			
			modulesMenu.add(getExitAction());

		}
		
		return modulesMenu;
		

	}

	
	private Menu getModulesCommercialMenu() {
	
		
		if (modulesCommercialMenu==null){
			
			modulesCommercialMenu = new Menu(new CommercialModuleTag());
			
			modulesCommercialMenu.add(getModulesCommercialEntriesMenu());
			
			modulesCommercialMenu.add(getModulesCommercialMovementsMenu());
			
		}
		
		return modulesCommercialMenu;
		
		
	}
	
	
	private Menu getModulesCommercialEntriesMenu() {

		
		if (modulesCommercialEntriesMenu==null){
			
			modulesCommercialEntriesMenu = new Menu(new EntriesTag());
			
			modulesCommercialEntriesMenu.add(getInputMacroEntryAction());
			
			modulesCommercialEntriesMenu.add(getOutputMacroEntryAction());
			
		}
		
		return modulesCommercialEntriesMenu;
		
		
	}

	
	private Menu getModulesCommercialMovementsMenu() {

		
		if (modulesCommercialMovementsMenu==null){
			
			modulesCommercialMovementsMenu = new Menu(new MovementsTag());
			
			modulesCommercialMovementsMenu.add(getInputOrderEntryAction());
			
			modulesCommercialMovementsMenu.add(getOutputOrderEntryAction());
			
		}
		
		return modulesCommercialMovementsMenu;
		
		
	}

	
	private Menu getModulesFinanceMenu() {
	
		
		if (modulesFinanceMenu==null){
			
			modulesFinanceMenu = new Menu(new FinanceModuleTag());
			
			modulesFinanceMenu.add(getModulesFinanceEntriesMenu());
			
		}
		
		return modulesFinanceMenu;
		
		
	}

	
	private Menu getModulesFinanceEntriesMenu() {

	
		if (modulesFinanceEntriesMenu==null){
			
			modulesFinanceEntriesMenu = new Menu(new EntriesTag());
			
			modulesFinanceEntriesMenu.add(getReceiptConditionEntryAction());
			
			modulesFinanceEntriesMenu.add(getPaymentConditionEntryAction());
			
		}
		
		return modulesFinanceEntriesMenu;
		
		
	}

	
	private Menu getModulesFiscalMenu() {
	
		
		if (modulesFiscalMenu==null){
			
			modulesFiscalMenu = new Menu(new FiscalModuleTag());
			
			modulesFiscalMenu.add(getModulesFiscalEntriesMenu());
			
		}
		
		return modulesFiscalMenu;
		
		
	}

	
	private Menu getModulesFiscalEntriesMenu() {

	
		if (modulesFiscalEntriesMenu==null){
			
			modulesFiscalEntriesMenu = new Menu(new EntriesTag());
			
			modulesFiscalEntriesMenu.add(getInputCfopEntryAction());
			
			modulesFiscalEntriesMenu.add(getOutputCfopEntryAction());
			
		}
		
		return modulesFiscalEntriesMenu;
		
		
	}

	
	private Menu getModulesEngineerMenu() {
	
		
		if (modulesEngineerMenu==null){
			
			modulesEngineerMenu = new Menu(new EngineerModuleTag());
			
			modulesEngineerMenu.add(getModulesEngineerEntriesMenu());
			
		}
		
		return modulesEngineerMenu;
		
		
	}

	
	private Menu getModulesEngineerEntriesMenu() {

	
		if (modulesEngineerEntriesMenu==null){
			
			modulesEngineerEntriesMenu = new Menu(new EntriesTag());
			
			modulesEngineerEntriesMenu.add(getProductEntryAction());
			
			modulesEngineerEntriesMenu.add(getUnitMeasureEntryAction());
			
			modulesEngineerEntriesMenu.addSeparator();
			
			modulesEngineerEntriesMenu.add(getModulesEngineerEntriesTributesMenu());
			
		}
		
		return modulesEngineerEntriesMenu;
		
		
	}


	private Menu getModulesEngineerEntriesTributesMenu() {

		
		if (modulesEngineerEntriesTributesMenu==null){
			
			modulesEngineerEntriesTributesMenu = new Menu(new TributesTag());
			
			modulesEngineerEntriesTributesMenu.add(getIcmsEntryAction());
			
			modulesEngineerEntriesTributesMenu.addSeparator();
			
			modulesEngineerEntriesTributesMenu.add(getRuleEntryAction());
			
		}
		
		return modulesEngineerEntriesTributesMenu;
		
		
	}

	
	private Menu getModulesSubjectMenu(){

		
		if (modulesSubjectMenu==null){

			modulesSubjectMenu = new Menu(new SubjectModuleTag());
			
			modulesSubjectMenu.add(getModulesSubjectEntriesMenu());

		}
		
		return modulesSubjectMenu;
		

	}

	
	private Menu getModulesSubjectEntriesMenu(){

		
		if (modulesSubjectEntriesMenu==null){

			modulesSubjectEntriesMenu = new Menu(new EntriesTag());
			
			modulesSubjectEntriesMenu.add(getPersonEntryAction());
			
			modulesSubjectEntriesMenu.add(getCompanyEntryAction());

		}
		
		return modulesSubjectEntriesMenu;
		

	}

	
	protected ExitAction getExitAction() {

		
		if (exitAction == null)
			
			exitAction = new ExitAction(getController());
		
		return exitAction;
		

	}
	

	protected LogoutAction getLogoutAction() {

		
		if (logoutAction == null){
			
			logoutAction = new LogoutAction(getController());
			
			getController().addView(logoutAction);
			
		}
		
		return logoutAction;
		

	}

	
	protected ApplicationAction getChangePasswordAction() {

		
		if (changePasswordAction == null){
			
			changePasswordAction = new ChangePasswordAction(getDesktop(), getController());
			
			getController().addView(changePasswordAction);
			
		}
		
		return changePasswordAction;
		

	}

	
	protected PortugueseBrazilRadioButtonMenuItem getPortugueseBrazilRadioButtonMenuItem() {

		
		if (portugueseBrazilRadioButtonMenuItem == null)
			
			portugueseBrazilRadioButtonMenuItem = new PortugueseBrazilRadioButtonMenuItem();
		
		return portugueseBrazilRadioButtonMenuItem;
		

	}

	
	protected EnglishUSARadioButtonMenuItem getEnglishUSARadioButtonMenuItem() {

		
		if (englishUSARadioButtonMenuItem == null)
			
			englishUSARadioButtonMenuItem = new EnglishUSARadioButtonMenuItem();
		
		return englishUSARadioButtonMenuItem;
		

	}

	
	protected HelpAboutAction getHelpAboutAction() {

		
		if (helpAboutAction == null)
			
			helpAboutAction = new HelpAboutAction(getDesktop());
		
		return helpAboutAction;
		

	}

	
	protected HelpAction getHelpAction() {

		
		if (helpAction == null)
			
			helpAction = new HelpAction(getController());
		
		return helpAction;
		

	}

	
	protected ApplicationAction getSystemCompanyEntryAction() {

		
		if (systemCompanyEntryAction == null){
			
			systemCompanyEntryAction = new SystemCompanyEntryAction(getDesktop());
			
			getController().addView(systemCompanyEntryAction);
			
		}
		
		return systemCompanyEntryAction;

		
	}

	
	protected ApplicationAction getPersonEntryAction() {

		
		if (personEntryAction == null){
			
			personEntryAction = new PersonEntryAction(getDesktop());
			
			getController().addView(personEntryAction);
			
		}
		
		return personEntryAction;
		

	}

	
	protected ApplicationAction getCompanyEntryAction() {

		
		if (companyEntryAction == null){
			
			companyEntryAction = new CompanyEntryAction(getDesktop());
			
			getController().addView(companyEntryAction);
			
		}
		
		return companyEntryAction;

		
	}

	
	protected ApplicationAction getProductEntryAction() {

		
		if (productEntryAction==null){
			
			productEntryAction = new ProductEntryAction(getDesktop());
			
			getController().addView(productEntryAction);
			
		}	
		
		return productEntryAction;
		
		
	}


	protected ApplicationAction getUnitMeasureEntryAction() {

		
		if (unitMeasureEntryAction==null){
			
			unitMeasureEntryAction = new UnitMeasureEntryAction(getDesktop());
			
			getController().addView(unitMeasureEntryAction);
			
		}	
		
		return unitMeasureEntryAction;
		
		
	}

	
	protected ApplicationAction getIcmsEntryAction() {

		
		if (icmsEntryAction==null){
			
			icmsEntryAction = new IcmsEntryAction(getDesktop());
			
			getController().addView(icmsEntryAction);
			
		}	
		
		return icmsEntryAction;
		
		
	}

	
	protected ApplicationAction getRuleEntryAction() {

		
		if (ruleEntryAction==null){
			
			ruleEntryAction = new RuleEntryAction(getDesktop());
			
			getController().addView(ruleEntryAction);
			
		}	
		
		return ruleEntryAction;
		
		
	}

	
	protected ApplicationAction getOutputMacroEntryAction() {

		
		if (outputMacroEntryAction==null){
			
			outputMacroEntryAction = new OutputMacroEntryAction(getDesktop());
			
			getController().addView(outputMacroEntryAction);
			
		}	
		
		return outputMacroEntryAction;
		
		
	}


	protected ApplicationAction getInputMacroEntryAction() {

		
		if (inputMacroEntryAction==null){
			
			inputMacroEntryAction = new InputMacroEntryAction(getDesktop());
			
			getController().addView(inputMacroEntryAction);
			
		}	
		
		return inputMacroEntryAction;
		
		
	}

	
	protected ApplicationAction getSaleOrderEntryAction() {

		
		if (saleOrderEntryAction==null){
			
			saleOrderEntryAction = new SaleOrderEntryAction(getDesktop());
			
			getController().addView(saleOrderEntryAction);
			
		}	
		
		return saleOrderEntryAction;
		
		
	}
	
	
	protected ApplicationAction getPurchaseOrderEntryAction() {

		
		if (purchaseOrderEntryAction==null){
			
			purchaseOrderEntryAction = new PurchaseOrderEntryAction(getDesktop());
			
			getController().addView(purchaseOrderEntryAction);
			
		}	
		
		return purchaseOrderEntryAction;
		
		
	}


	protected ApplicationAction getOutputOrderEntryAction() {

		
		if (outputOrderEntryAction==null){
			
			outputOrderEntryAction = new OutputOrderEntryAction(getDesktop());
			
			getController().addView(outputOrderEntryAction);
			
		}	
		
		return outputOrderEntryAction;
		
		
	}
	
	
	protected ApplicationAction getInputOrderEntryAction() {

		
		if (inputOrderEntryAction==null){
			
			inputOrderEntryAction = new InputOrderEntryAction(getDesktop());
			
			getController().addView(inputOrderEntryAction);
			
		}	
		
		return inputOrderEntryAction;
		
		
	}

	
	protected ApplicationAction getReceiptConditionEntryAction() {

		
		if (receiptConditionEntryAction==null){
			
			receiptConditionEntryAction = new ReceiptConditionEntryAction(getDesktop());
			
			getController().addView(receiptConditionEntryAction);
			
		}	
		
		return receiptConditionEntryAction;
		
		
	}

	
	protected ApplicationAction getPaymentConditionEntryAction() {

		
		if (paymentConditionEntryAction==null){
			
			paymentConditionEntryAction = new PaymentConditionEntryAction(getDesktop());
			
			getController().addView(paymentConditionEntryAction);
			
		}	
		
		return paymentConditionEntryAction;
		
		
	}

	
	protected ApplicationAction getInputCfopEntryAction() {

		
		if (inputCfopEntryAction==null){
			
			inputCfopEntryAction = new InputCfopEntryAction(getDesktop());
			
			getController().addView(inputCfopEntryAction);
			
		}	
		
		return inputCfopEntryAction;
		
		
	}

	
	protected ApplicationAction getOutputCfopEntryAction() {

		
		if (outputCfopEntryAction==null){
			
			outputCfopEntryAction = new OutputCfopEntryAction(getDesktop());
			
			getController().addView(outputCfopEntryAction);
			
		}	
		
		return outputCfopEntryAction;
		
		
	}

	
}
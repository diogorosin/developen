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
import developen.client.osm.action.LogoutAction;
import developen.client.osm.action.PersonEntryAction;
import developen.client.osm.action.ProductEntryAction;
import developen.client.osm.action.PurchaseOrderEntryAction;
import developen.client.osm.action.SaleOrderEntryAction;
import developen.client.osm.action.SystemCompanyEntryAction;
import developen.client.osm.action.SystemPersonEntryAction;
import developen.client.osm.action.UnitMeasureEntryAction;
import developen.client.osm.i18n.CommercialModuleTag;
import developen.client.osm.i18n.EngineerModuleTag;
import developen.client.osm.i18n.EntriesTag;
import developen.client.osm.i18n.PurchasesTag;
import developen.client.osm.i18n.SalesTag;
import developen.client.osm.i18n.SubjectModuleTag;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.EnglishUSARadioButtonMenuItem;
import developen.common.framework.widget.Menu;
import developen.common.framework.widget.PortugueseBrazilRadioButtonMenuItem;


public class WorldClientView extends ClientView{

	
	private static final long serialVersionUID = 2176404851166695386L;

	private Menu modulesCommercialMenu;
	
	private Menu modulesCommercialSalesMenu;
	
	private Menu modulesCommercialPurchasesMenu;
	
	private Menu modulesEngineerMenu;
	
	private Menu modulesEngineerEntriesMenu;

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
	
	protected SaleOrderEntryAction saleOrderEntryAction;

	protected PurchaseOrderEntryAction purchaseOrderEntryAction;
	

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
								new SalesTag(),

								getSaleOrderEntryAction()

							},
							
							new Object[]{
								new PurchasesTag(),

								getPurchaseOrderEntryAction()

							}

						},

						new Object[]{
							new EngineerModuleTag(),	

							new Object[]{
								new EntriesTag(),

								getProductEntryAction(),
								
								getUnitMeasureEntryAction()

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
			
			modulesCommercialMenu.add(getModulesCommercialSalesMenu());
			
			modulesCommercialMenu.add(getModulesCommercialPurchasesMenu());
			
		}
		
		return modulesCommercialMenu;
		
		
	}
	
	
	private Menu getModulesCommercialSalesMenu() {

		
		if (modulesCommercialSalesMenu==null){
			
			modulesCommercialSalesMenu = new Menu(new SalesTag());
			
			modulesCommercialSalesMenu.add(getSaleOrderEntryAction());
			
		}
		
		return modulesCommercialSalesMenu;
		
		
	}

	
	private Menu getModulesCommercialPurchasesMenu() {

		
		if (modulesCommercialPurchasesMenu==null){
			
			modulesCommercialPurchasesMenu = new Menu(new PurchasesTag());
			
			modulesCommercialPurchasesMenu.add(getPurchaseOrderEntryAction());
			
		}
		
		return modulesCommercialPurchasesMenu;
		
		
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
			
		}
		
		return modulesEngineerEntriesMenu;
		
		
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


}
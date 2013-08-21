package developen.client.osm.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

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
import developen.client.osm.action.InputMacroEntryAction;
import developen.client.osm.action.InputOrderEntryAction;
import developen.client.osm.action.IpiEntryAction;
import developen.client.osm.action.IssqnEntryAction;
import developen.client.osm.action.LogoutAction;
import developen.client.osm.action.OutputMacroEntryAction;
import developen.client.osm.action.OutputOrderEntryAction;
import developen.client.osm.action.PaymentConditionEntryAction;
import developen.client.osm.action.PersonEntryAction;
import developen.client.osm.action.PisCofinsEntryAction;
import developen.client.osm.action.ProductEntryAction;
import developen.client.osm.action.PurchaseOrderEntryAction;
import developen.client.osm.action.ReceiptConditionEntryAction;
import developen.client.osm.action.RuleEntryAction;
import developen.client.osm.action.SaleOrderEntryAction;
import developen.client.osm.action.ServiceEntryAction;
import developen.client.osm.action.SystemCompanyEntryAction;
import developen.client.osm.action.SystemPersonEntryAction;
import developen.client.osm.action.UnitMeasureEntryAction;
import developen.client.osm.i18n.CommercialModuleTag;
import developen.client.osm.i18n.EntriesTag;
import developen.client.osm.i18n.FinanceModuleTag;
import developen.client.osm.i18n.MovementsTag;
import developen.client.osm.i18n.ProgeniesTag;
import developen.client.osm.i18n.SubjectsTag;
import developen.common.commercial.i18n.MacrosTag;
import developen.common.commercial.i18n.OrdersTag;
import developen.common.commercial.i18n.ProductTag;
import developen.common.commercial.i18n.TributesTag;
import developen.common.finance.i18n.ConditionsTag;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.EnglishUSARadioButtonMenuItem;
import developen.common.framework.widget.Menu;
import developen.common.framework.widget.PortugueseBrazilRadioButtonMenuItem;


public class WorldClientView extends ClientView{


	private static final long serialVersionUID = 2176404851166695386L;

	private Menu modulesCommercialMenu;

	private Menu modulesCommercialEntriesMenu;

	private Menu modulesCommercialEntriesMacrosMenu;

	private Menu modulesCommercialEntriesSubjectsMenu;

	private Menu modulesCommercialEntriesProgeniesMenu;

	private Menu modulesCommercialEntriesProgeniesProductMenu;	

	private Menu modulesCommercialEntriesProgeniesTributesMenu;

	private Menu modulesCommercialMovementsMenu;

	private Menu modulesCommercialMovementsOrdersMenu;	

	private Menu modulesFinanceMenu;

	private Menu modulesFinanceEntriesMenu;

	private Menu modulesFinanceEntriesConditionsMenu;

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
	
	protected ServiceEntryAction serviceEntryAction;

	protected UnitMeasureEntryAction unitMeasureEntryAction;

	protected IcmsEntryAction icmsEntryAction;
	
	protected PisCofinsEntryAction pisCofinsEntryAction;
	
	protected IpiEntryAction ipiEntryAction;
	
	protected IssqnEntryAction issqnEntryAction;

	protected RuleEntryAction ruleEntryAction;

	protected OutputMacroEntryAction outputMacroEntryAction;

	protected InputMacroEntryAction inputMacroEntryAction;

	protected SaleOrderEntryAction saleOrderEntryAction;

	protected PurchaseOrderEntryAction purchaseOrderEntryAction;

	protected OutputOrderEntryAction outputOrderEntryAction;

	protected InputOrderEntryAction inputOrderEntryAction;

	protected ReceiptConditionEntryAction receiptConditionEntryAction;

	protected PaymentConditionEntryAction paymentConditionEntryAction;


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

								new Object[]{
									new MacrosTag(),

									getInputMacroEntryAction(),

									getOutputMacroEntryAction()

								},

								new Object[]{
									new SubjectsTag(),

									getCompanyEntryAction(),

									getPersonEntryAction()

								},

								new Object[]{
									new ProgeniesTag(),

									new Object[]{
										new ProductTag(),

										getProductEntryAction()

									},

									getServiceEntryAction(),
									
									getUnitMeasureEntryAction(),
									
									new Object[]{
										new TributesTag(),

										getIcmsEntryAction(),
										
										getIpiEntryAction(),
										
										getPisCofinsEntryAction(),
										
										getIssqnEntryAction(),

										getRuleEntryAction()

									}

								}

							},

							new Object[]{
								new MovementsTag(),

								new Object[]{
									new OrdersTag(),

									getInputOrderEntryAction(),

									getOutputOrderEntryAction()

								}

							}

						},

						new Object[]{
							new FinanceModuleTag(),	

							new Object[]{
								new EntriesTag(),

								new Object[]{
									new ConditionsTag(),

									getReceiptConditionEntryAction(),

									getPaymentConditionEntryAction()

								}
								
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

			modulesCommercialEntriesMenu.add(getModulesCommercialEntriesMacrosMenu());

			modulesCommercialEntriesMenu.add(getModulesCommercialEntriesSubjectsMenu());

			modulesCommercialEntriesMenu.add(getModulesCommercialEntriesProgeniesMenu());

		}

		return modulesCommercialEntriesMenu;


	}


	private Menu getModulesCommercialEntriesMacrosMenu() {


		if (modulesCommercialEntriesMacrosMenu==null){

			modulesCommercialEntriesMacrosMenu = new Menu(new MacrosTag());

			modulesCommercialEntriesMacrosMenu.add(getInputMacroEntryAction());

			modulesCommercialEntriesMacrosMenu.add(getOutputMacroEntryAction());

		}

		return modulesCommercialEntriesMacrosMenu;


	}


	private Menu getModulesCommercialEntriesSubjectsMenu() {


		if (modulesCommercialEntriesSubjectsMenu==null){

			modulesCommercialEntriesSubjectsMenu = new Menu(new SubjectsTag());
			
			JMenuItem m1 = new JMenuItem(getCompanyEntryAction());
			
			m1.setAccelerator(KeyStroke.getKeyStroke(
					
			        KeyEvent.VK_J, ActionEvent.CTRL_MASK));

			modulesCommercialEntriesSubjectsMenu.add(m1);

			JMenuItem m2 = new JMenuItem(getPersonEntryAction());
			
			m2.setAccelerator(KeyStroke.getKeyStroke(
					
			        KeyEvent.VK_F, ActionEvent.CTRL_MASK));

			modulesCommercialEntriesSubjectsMenu.add(m2);

			
		}

		return modulesCommercialEntriesSubjectsMenu;


	}


	private Menu getModulesCommercialEntriesProgeniesMenu() {


		if (modulesCommercialEntriesProgeniesMenu==null){

			modulesCommercialEntriesProgeniesMenu = new Menu(new ProgeniesTag());

			modulesCommercialEntriesProgeniesMenu.add(getModulesCommercialEntriesProgeniesProductMenu());

			JMenuItem m1 = new JMenuItem(getServiceEntryAction());
			
			m1.setAccelerator(KeyStroke.getKeyStroke(
					
			        KeyEvent.VK_S, ActionEvent.CTRL_MASK));

			modulesCommercialEntriesProgeniesMenu.add(m1);
			
			modulesCommercialEntriesProgeniesMenu.addSeparator();

			modulesCommercialEntriesProgeniesMenu.add(getUnitMeasureEntryAction());
			
			modulesCommercialEntriesProgeniesMenu.addSeparator();
			
			modulesCommercialEntriesProgeniesMenu.add(getModulesCommercialEntriesProgeniesTributesMenu());

		}

		return modulesCommercialEntriesProgeniesMenu;


	}


	private Menu getModulesCommercialEntriesProgeniesProductMenu() {


		if (modulesCommercialEntriesProgeniesProductMenu==null){

			modulesCommercialEntriesProgeniesProductMenu = new Menu(new ProductTag());

			JMenuItem m1 = new JMenuItem(getProductEntryAction());
			
			m1.setAccelerator(KeyStroke.getKeyStroke(
					
			        KeyEvent.VK_P, ActionEvent.CTRL_MASK));
			
			modulesCommercialEntriesProgeniesProductMenu.add(m1);

		}

		return modulesCommercialEntriesProgeniesProductMenu;


	}


	private Menu getModulesCommercialEntriesProgeniesTributesMenu() {


		if (modulesCommercialEntriesProgeniesTributesMenu==null){

			modulesCommercialEntriesProgeniesTributesMenu = new Menu(new TributesTag());

			modulesCommercialEntriesProgeniesTributesMenu.add(getIcmsEntryAction());
			
			modulesCommercialEntriesProgeniesTributesMenu.add(getIpiEntryAction());
			
			modulesCommercialEntriesProgeniesTributesMenu.add(getPisCofinsEntryAction());
			
			modulesCommercialEntriesProgeniesTributesMenu.add(getIssqnEntryAction());

			modulesCommercialEntriesProgeniesTributesMenu.addSeparator();

			modulesCommercialEntriesProgeniesTributesMenu.add(getRuleEntryAction());

		}

		return modulesCommercialEntriesProgeniesTributesMenu;


	}


	private Menu getModulesCommercialMovementsMenu() {


		if (modulesCommercialMovementsMenu==null){

			modulesCommercialMovementsMenu = new Menu(new MovementsTag());

			modulesCommercialMovementsMenu.add(getModulesCommercialMovementsOrdersMenu());

		}

		return modulesCommercialMovementsMenu;


	}


	private Menu getModulesCommercialMovementsOrdersMenu() {


		if (modulesCommercialMovementsOrdersMenu==null){

			modulesCommercialMovementsOrdersMenu = new Menu(new OrdersTag());

			modulesCommercialMovementsOrdersMenu.add(getInputOrderEntryAction());

			modulesCommercialMovementsOrdersMenu.add(getOutputOrderEntryAction());

		}

		return modulesCommercialMovementsOrdersMenu;


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

			modulesFinanceEntriesMenu.add(getModulesFinanceEntriesConditionsMenu());

		}

		return modulesFinanceEntriesMenu;


	}


	private Menu getModulesFinanceEntriesConditionsMenu() {


		if (modulesFinanceEntriesConditionsMenu==null){

			modulesFinanceEntriesConditionsMenu = new Menu(new ConditionsTag());

			modulesFinanceEntriesConditionsMenu.add(getReceiptConditionEntryAction());

			modulesFinanceEntriesConditionsMenu.add(getPaymentConditionEntryAction());

		}

		return modulesFinanceEntriesConditionsMenu;


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


	protected ApplicationAction getServiceEntryAction() {


		if (serviceEntryAction==null){

			serviceEntryAction = new ServiceEntryAction(getDesktop());

			getController().addView(serviceEntryAction);

		}	

		return serviceEntryAction;


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


	protected ApplicationAction getPisCofinsEntryAction() {


		if (pisCofinsEntryAction==null){

			pisCofinsEntryAction = new PisCofinsEntryAction(getDesktop());

			getController().addView(pisCofinsEntryAction);

		}	

		return pisCofinsEntryAction;


	}


	protected ApplicationAction getIpiEntryAction() {


		if (ipiEntryAction==null){

			ipiEntryAction = new IpiEntryAction(getDesktop());

			getController().addView(ipiEntryAction);

		}	

		return ipiEntryAction;


	}


	protected ApplicationAction getIssqnEntryAction() {


		if (issqnEntryAction==null){

			issqnEntryAction = new IssqnEntryAction(getDesktop());

			getController().addView(issqnEntryAction);

		}	

		return issqnEntryAction;


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


}
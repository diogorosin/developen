package developen.client.osm.mvc;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import developen.client.application.action.EntryAction;
import developen.client.application.i18n.AdministratorTag;
import developen.client.application.i18n.DevelOpenCloudTag;
import developen.client.application.i18n.ModulesTag;
import developen.client.application.i18n.ParameterizationTag;
import developen.client.application.i18n.SecurityTag;
import developen.client.application.mvc.ChangePasswordController;
import developen.client.application.mvc.ChangePasswordModel;
import developen.client.application.mvc.ChangePasswordView;
import developen.client.application.mvc.ClientController;
import developen.client.application.mvc.ClientView;
import developen.client.application.mvc.HelpAboutController;
import developen.client.application.mvc.HelpAboutModel;
import developen.client.application.mvc.HelpAboutView;
import developen.client.application.mvc.SystemCompanyController;
import developen.client.application.mvc.SystemCompanyView;
import developen.client.commercial.mvc.CompanyController;
import developen.client.commercial.mvc.CompanyView;
import developen.client.commercial.mvc.IcmsController;
import developen.client.commercial.mvc.IcmsView;
import developen.client.commercial.mvc.InputMacroController;
import developen.client.commercial.mvc.InputMacroView;
import developen.client.commercial.mvc.IpiController;
import developen.client.commercial.mvc.IpiView;
import developen.client.commercial.mvc.MeasureUnitController;
import developen.client.commercial.mvc.MeasureUnitView;
import developen.client.commercial.mvc.OutputMacroController;
import developen.client.commercial.mvc.OutputMacroView;
import developen.client.commercial.mvc.PersonController;
import developen.client.commercial.mvc.PersonView;
import developen.client.commercial.mvc.PisCofinsController;
import developen.client.commercial.mvc.PisCofinsView;
import developen.client.commercial.mvc.ProductController;
import developen.client.commercial.mvc.ProductDetailController;
import developen.client.commercial.mvc.ProductDetailView;
import developen.client.commercial.mvc.ProductFinishController;
import developen.client.commercial.mvc.ProductFinishView;
import developen.client.commercial.mvc.ProductGroupController;
import developen.client.commercial.mvc.ProductGroupView;
import developen.client.commercial.mvc.ProductLineController;
import developen.client.commercial.mvc.ProductLineView;
import developen.client.commercial.mvc.ProductMarkController;
import developen.client.commercial.mvc.ProductMarkView;
import developen.client.commercial.mvc.ProductModelController;
import developen.client.commercial.mvc.ProductModelView;
import developen.client.commercial.mvc.ProductView;
import developen.client.commercial.mvc.RuleController;
import developen.client.commercial.mvc.RuleView;
import developen.client.finance.mvc.PaymentConditionController;
import developen.client.finance.mvc.PaymentConditionView;
import developen.client.finance.mvc.ReceiptConditionController;
import developen.client.finance.mvc.ReceiptConditionView;
import developen.client.framework.util.DesktopPaneChangedEvent;
import developen.client.osm.action.ChangePasswordAction;
import developen.client.osm.action.CloseAction;
import developen.client.osm.action.CompanyEntryAction;
import developen.client.osm.action.HelpAboutAction;
import developen.client.osm.action.HelpAction;
import developen.client.osm.action.IcmsEntryAction;
import developen.client.osm.action.InputMacroEntryAction;
import developen.client.osm.action.InputOrderEntryAction;
import developen.client.osm.action.IpiEntryAction;
import developen.client.osm.action.IssqnEntryAction;
import developen.client.osm.action.LogoutAction;
import developen.client.osm.action.MeasureUnitEntryAction;
import developen.client.osm.action.OpenEntryAction;
import developen.client.osm.action.OpenSearchAction;
import developen.client.osm.action.OutputMacroEntryAction;
import developen.client.osm.action.OutputOrderEntryAction;
import developen.client.osm.action.PaymentConditionEntryAction;
import developen.client.osm.action.PersonEntryAction;
import developen.client.osm.action.PisCofinsEntryAction;
import developen.client.osm.action.ProductDetailEntryAction;
import developen.client.osm.action.ProductEntryAction;
import developen.client.osm.action.ProductFinishEntryAction;
import developen.client.osm.action.ProductGroupEntryAction;
import developen.client.osm.action.ProductLineEntryAction;
import developen.client.osm.action.ProductMarkEntryAction;
import developen.client.osm.action.ProductModelEntryAction;
import developen.client.osm.action.PurchaseOrderEntryAction;
import developen.client.osm.action.ReceiptConditionEntryAction;
import developen.client.osm.action.RuleEntryAction;
import developen.client.osm.action.SaleOrderEntryAction;
import developen.client.osm.action.ServiceEntryAction;
import developen.client.osm.action.SystemCompanyEntryAction;
import developen.client.osm.action.SystemPersonEntryAction;
import developen.client.osm.factory.MVCBeanFactory;
import developen.client.osm.i18n.CommercialModuleTag;
import developen.client.osm.i18n.EntriesTag;
import developen.client.osm.i18n.FinanceModuleTag;
import developen.client.osm.i18n.MovementsTag;
import developen.client.osm.i18n.ProgeniesTag;
import developen.client.osm.i18n.SubjectsTag;
import developen.client.osm.widget.MVCBean;
import developen.common.commercial.i18n.MacrosTag;
import developen.common.commercial.i18n.OrdersTag;
import developen.common.commercial.i18n.ProductTag;
import developen.common.commercial.i18n.TributesTag;
import developen.common.commercial.mvc.Company;
import developen.common.commercial.mvc.Icms;
import developen.common.commercial.mvc.InputMacro;
import developen.common.commercial.mvc.Ipi;
import developen.common.commercial.mvc.MeasureUnit;
import developen.common.commercial.mvc.OutputMacro;
import developen.common.commercial.mvc.OutputOrder;
import developen.common.commercial.mvc.Person;
import developen.common.commercial.mvc.PisCofins;
import developen.common.commercial.mvc.Product;
import developen.common.commercial.mvc.ProductDetail;
import developen.common.commercial.mvc.ProductFinish;
import developen.common.commercial.mvc.ProductGroup;
import developen.common.commercial.mvc.ProductLine;
import developen.common.commercial.mvc.ProductMark;
import developen.common.commercial.mvc.ProductModel;
import developen.common.commercial.mvc.Rule;
import developen.common.commercial.mvc.SaleOrder;
import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.finance.i18n.ConditionsTag;
import developen.common.finance.mvc.PaymentCondition;
import developen.common.finance.mvc.ReceiptCondition;
import developen.common.framework.exception.NotYetImplementedException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.EnglishUSARadioButtonMenuItem;
import developen.common.framework.widget.InternalFrame;
import developen.common.framework.widget.InternalFramePosition;
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

	protected CloseAction closeAction;

	protected OpenEntryAction openEntryAction;

	protected OpenSearchAction openSearchAction;

	protected LogoutAction logoutAction;

	protected HelpAction helpAction;

	protected HelpAboutAction helpAboutAction;

	protected SystemCompanyEntryAction systemCompanyEntryAction;

	protected PortugueseBrazilRadioButtonMenuItem portugueseBrazilRadioButtonMenuItem;

	protected EnglishUSARadioButtonMenuItem englishUSARadioButtonMenuItem;

	protected EntryAction changePasswordAction;

	protected SystemPersonEntryAction systemPersonEntryAction;

	protected CompanyEntryAction companyEntryAction;

	protected PersonEntryAction personEntryAction;

	protected ProductEntryAction productEntryAction;

	protected ProductGroupEntryAction productGroupEntryAction;

	protected ProductMarkEntryAction productMarkEntryAction;

	protected ProductLineEntryAction productLineEntryAction;

	protected ProductModelEntryAction productModelEntryAction;

	protected ProductDetailEntryAction productDetailEntryAction;

	protected ProductFinishEntryAction productFinishEntryAction;

	protected ServiceEntryAction serviceEntryAction;

	protected MeasureUnitEntryAction measureUnitEntryAction;

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


//	protected HashMap<Class<? extends Model>, Action> getMimeTypes() {
//
//
//		HashMap<Class<? extends Model>, Action> mimes = super.getMimeTypes();
//
//		mimes.put(OutputMacro.class, getOutputMacroEntryAction());
//
//		mimes.put(InputMacro.class, getInputMacroEntryAction());
//
//		mimes.put(Product.class, getProductEntryAction());
//
//		mimes.put(ProductGroup.class, getProductGroupEntryAction());
//
//		mimes.put(ProductMark.class, getProductMarkEntryAction());
//
//		mimes.put(ProductLine.class, getProductLineEntryAction());
//
//		mimes.put(ProductModel.class, getProductModelEntryAction());
//
//		mimes.put(ProductDetail.class, getProductDetailEntryAction());
//
//		mimes.put(ProductFinish.class, getProductFinishEntryAction());
//
//		mimes.put(MeasureUnit.class, getMeasureUnitEntryAction());
//
//		mimes.put(Icms.class, getIcmsEntryAction());
//
//		mimes.put(PisCofins.class, getPisCofinsEntryAction());
//
//		mimes.put(Ipi.class, getIpiEntryAction());
//
//		mimes.put(Rule.class, getRuleEntryAction());
//
//		mimes.put(Person.class, getPersonEntryAction());
//
//		mimes.put(Company.class, getCompanyEntryAction());
//
//		mimes.put(SystemPerson.class, getSystemPersonEntryAction());
//
//		mimes.put(SystemCompany.class, getSystemCompanyEntryAction());
//
//		mimes.put(PaymentCondition.class, getPaymentConditionEntryAction());
//
//		mimes.put(ReceiptCondition.class, getReceiptConditionEntryAction());
//
//		return mimes;
//
//
//	}


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

										getProductEntryAction(),

										getProductGroupEntryAction(),

										getProductMarkEntryAction(),

										getProductLineEntryAction(),

										getProductModelEntryAction(),

										getProductDetailEntryAction(),

										getProductFinishEntryAction()

									},

									getServiceEntryAction(),

									getMeasureUnitEntryAction(),

									new Object[]{
										new TributesTag(),

										getIcmsEntryAction(),

										getPisCofinsEntryAction(),

										getIpiEntryAction(),

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


	protected EntryAction getSystemPersonEntryAction() {


		if (systemPersonEntryAction == null){

			systemPersonEntryAction = new SystemPersonEntryAction();

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

			modulesMenu.add(getCloseAction());

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

			modulesCommercialEntriesProgeniesMenu.add(getMeasureUnitEntryAction());

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

			modulesCommercialEntriesProgeniesProductMenu.addSeparator();

			modulesCommercialEntriesProgeniesProductMenu.add(getProductGroupEntryAction());

			modulesCommercialEntriesProgeniesProductMenu.add(getProductMarkEntryAction());

			modulesCommercialEntriesProgeniesProductMenu.add(getProductLineEntryAction());

			modulesCommercialEntriesProgeniesProductMenu.add(getProductModelEntryAction());

			modulesCommercialEntriesProgeniesProductMenu.add(getProductDetailEntryAction());

			modulesCommercialEntriesProgeniesProductMenu.add(getProductFinishEntryAction());


		}

		return modulesCommercialEntriesProgeniesProductMenu;


	}


	private Menu getModulesCommercialEntriesProgeniesTributesMenu() {


		if (modulesCommercialEntriesProgeniesTributesMenu==null){

			modulesCommercialEntriesProgeniesTributesMenu = new Menu(new TributesTag());

			modulesCommercialEntriesProgeniesTributesMenu.add(getIcmsEntryAction());

			modulesCommercialEntriesProgeniesTributesMenu.add(getPisCofinsEntryAction());

			modulesCommercialEntriesProgeniesTributesMenu.add(getIpiEntryAction());

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


	protected CloseAction getCloseAction() {


		if (closeAction == null)

			closeAction = new CloseAction(getController());

		return closeAction;


	}


	protected OpenEntryAction getOpenEntryAction() {


		if (openEntryAction == null){

			openEntryAction = new OpenEntryAction();

			getController().addView(openEntryAction);

		}

		return openEntryAction;


	}


	protected OpenSearchAction getOpenSearchAction() {


		if (openSearchAction == null){

			openSearchAction = new OpenSearchAction();

			getController().addView(openSearchAction);

		}

		return openSearchAction;


	}


	protected LogoutAction getLogoutAction() {


		if (logoutAction == null){

			logoutAction = new LogoutAction();

			getController().addView(logoutAction);

		}

		return logoutAction;


	}


	protected EntryAction getChangePasswordAction() {


		if (changePasswordAction == null){

			changePasswordAction = new ChangePasswordAction();

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

			helpAction = new HelpAction();

		return helpAction;


	}


	protected EntryAction getSystemCompanyEntryAction() {


		if (systemCompanyEntryAction == null){

			systemCompanyEntryAction = new SystemCompanyEntryAction(getDesktop());

			getController().addView(systemCompanyEntryAction);

		}

		return systemCompanyEntryAction;


	}


	protected EntryAction getPersonEntryAction() {


		if (personEntryAction == null){

			personEntryAction = new PersonEntryAction();

			getController().addView(personEntryAction);

		}

		return personEntryAction;


	}


	protected EntryAction getCompanyEntryAction() {


		if (companyEntryAction == null){

			companyEntryAction = new CompanyEntryAction();

			getController().addView(companyEntryAction);

		}

		return companyEntryAction;


	}


	protected EntryAction getProductEntryAction() {


		if (productEntryAction==null){

			productEntryAction = new ProductEntryAction();

			getController().addView(productEntryAction);

		}	

		return productEntryAction;


	}


	protected EntryAction getProductGroupEntryAction() {


		if (productGroupEntryAction==null){

			productGroupEntryAction = new ProductGroupEntryAction();

			getController().addView(productGroupEntryAction);

		}	

		return productGroupEntryAction;


	}


	protected EntryAction getProductModelEntryAction() {


		if (productModelEntryAction==null){

			productModelEntryAction = new ProductModelEntryAction();

			getController().addView(productModelEntryAction);

		}	

		return productModelEntryAction;


	}


	protected EntryAction getProductMarkEntryAction() {


		if (productMarkEntryAction==null){

			productMarkEntryAction = new ProductMarkEntryAction();

			getController().addView(productMarkEntryAction);

		}	

		return productMarkEntryAction;


	}


	protected EntryAction getProductLineEntryAction() {


		if (productLineEntryAction==null){

			productLineEntryAction = new ProductLineEntryAction();

			getController().addView(productLineEntryAction);

		}	

		return productLineEntryAction;


	}


	protected EntryAction getProductDetailEntryAction() {


		if (productDetailEntryAction==null){

			productDetailEntryAction = new ProductDetailEntryAction();

			getController().addView(productDetailEntryAction);

		}	

		return productDetailEntryAction;


	}


	protected EntryAction getProductFinishEntryAction() {


		if (productFinishEntryAction==null){

			productFinishEntryAction = new ProductFinishEntryAction();

			getController().addView(productFinishEntryAction);

		}	

		return productFinishEntryAction;


	}


	protected EntryAction getServiceEntryAction() {


		if (serviceEntryAction==null){

			serviceEntryAction = new ServiceEntryAction();

			getController().addView(serviceEntryAction);

		}	

		return serviceEntryAction;


	}


	protected EntryAction getMeasureUnitEntryAction() {


		if (measureUnitEntryAction==null){

			measureUnitEntryAction = new MeasureUnitEntryAction();

			getController().addView(measureUnitEntryAction);

		}	

		return measureUnitEntryAction;


	}


	protected EntryAction getIcmsEntryAction() {


		if (icmsEntryAction==null){

			icmsEntryAction = new IcmsEntryAction();

			getController().addView(icmsEntryAction);

		}	

		return icmsEntryAction;


	}


	protected EntryAction getPisCofinsEntryAction() {


		if (pisCofinsEntryAction==null){

			pisCofinsEntryAction = new PisCofinsEntryAction();

			getController().addView(pisCofinsEntryAction);

		}	

		return pisCofinsEntryAction;


	}


	protected EntryAction getIpiEntryAction() {


		if (ipiEntryAction==null){

			ipiEntryAction = new IpiEntryAction();

			getController().addView(ipiEntryAction);

		}	

		return ipiEntryAction;


	}


	protected EntryAction getIssqnEntryAction() {


		if (issqnEntryAction==null){

			issqnEntryAction = new IssqnEntryAction();

			getController().addView(issqnEntryAction);

		}	

		return issqnEntryAction;


	}


	protected EntryAction getRuleEntryAction() {


		if (ruleEntryAction==null){

			ruleEntryAction = new RuleEntryAction();

			getController().addView(ruleEntryAction);

		}	

		return ruleEntryAction;


	}


	protected EntryAction getOutputMacroEntryAction() {


		if (outputMacroEntryAction==null){

			outputMacroEntryAction = new OutputMacroEntryAction();

			getController().addView(outputMacroEntryAction);

		}	

		return outputMacroEntryAction;


	}


	protected EntryAction getInputMacroEntryAction() {


		if (inputMacroEntryAction==null){

			inputMacroEntryAction = new InputMacroEntryAction();

			getController().addView(inputMacroEntryAction);

		}	

		return inputMacroEntryAction;


	}


	protected EntryAction getSaleOrderEntryAction() {


		if (saleOrderEntryAction==null){

			saleOrderEntryAction = new SaleOrderEntryAction();

			getController().addView(saleOrderEntryAction);

		}	

		return saleOrderEntryAction;


	}


	protected EntryAction getPurchaseOrderEntryAction() {


		if (purchaseOrderEntryAction==null){

			purchaseOrderEntryAction = new PurchaseOrderEntryAction();

			getController().addView(purchaseOrderEntryAction);

		}	

		return purchaseOrderEntryAction;


	}


	protected EntryAction getOutputOrderEntryAction() {


		if (outputOrderEntryAction==null){

			outputOrderEntryAction = new OutputOrderEntryAction();

			getController().addView(outputOrderEntryAction);

		}	

		return outputOrderEntryAction;


	}


	protected EntryAction getInputOrderEntryAction() {


		if (inputOrderEntryAction==null){

			inputOrderEntryAction = new InputOrderEntryAction();

			getController().addView(inputOrderEntryAction);

		}	

		return inputOrderEntryAction;


	}


	protected EntryAction getReceiptConditionEntryAction() {


		if (receiptConditionEntryAction==null){

			receiptConditionEntryAction = new ReceiptConditionEntryAction();

			getController().addView(receiptConditionEntryAction);

		}	

		return receiptConditionEntryAction;


	}


	protected EntryAction getPaymentConditionEntryAction() {


		if (paymentConditionEntryAction==null){

			paymentConditionEntryAction = new PaymentConditionEntryAction();

			getController().addView(paymentConditionEntryAction);

		}	

		return paymentConditionEntryAction;


	}


	public void executeSystemPersonEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				SystemPerson.class, 

				WorldSystemPersonView.class, 

				WorldSystemPersonController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeSystemCompanyEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				SystemCompany.class, 

				SystemCompanyView.class, 

				SystemCompanyController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeServiceEntry() throws Exception{


		try {

			throw new NotYetImplementedException();

		} catch (NotYetImplementedException e) {

			Messenger.show(e);

		}


	}


	public void executeSaleOrderEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				SaleOrder.class, 

				WorldSaleOrderView.class, 

				WorldSaleOrderController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeRuleEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				Rule.class, 

				RuleView.class, 

				RuleController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeReceiptConditionEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				ReceiptCondition.class, 

				ReceiptConditionView.class, 

				ReceiptConditionController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executePaymentConditionEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				PaymentCondition.class, 

				PaymentConditionView.class, 

				PaymentConditionController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executePurchaseOrderEntry() throws Exception{


		try {

			throw new NotYetImplementedException();

		} catch (NotYetImplementedException e) {

			Messenger.show(e);

		}


	}


	public void executeProductModelEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				ProductModel.class, 

				ProductModelView.class, 

				ProductModelController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeProductMarkEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				ProductMark.class, 

				ProductMarkView.class, 

				ProductMarkController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeProductLineEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				ProductLine.class, 

				ProductLineView.class, 

				ProductLineController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeProductGroupEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				ProductGroup.class, 

				ProductGroupView.class, 

				ProductGroupController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeProductFinishEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				ProductFinish.class, 

				ProductFinishView.class, 

				ProductFinishController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeProductEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				Product.class, 

				ProductView.class, 

				ProductController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeProductDetailEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				ProductDetail.class, 

				ProductDetailView.class, 

				ProductDetailController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executePisCofinsEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				PisCofins.class, 

				PisCofinsView.class, 

				PisCofinsController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executePersonEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				Person.class, 

				PersonView.class, 

				PersonController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeOutputOrderEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				OutputOrder.class, 

				WorldOutputOrderView.class, 

				WorldOutputOrderController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeOutputMacroEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				OutputMacro.class, 

				OutputMacroView.class, 

				OutputMacroController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeMeasureUnitEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				MeasureUnit.class, 

				MeasureUnitView.class, 

				MeasureUnitController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeIssqnEntry() throws Exception{


		try {

			throw new NotYetImplementedException();

		} catch (NotYetImplementedException e) {

			Messenger.show(e);

		}


	}


	public void executeIpiEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				Ipi.class, 

				IpiView.class, 

				IpiController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeInputOrderEntry() throws Exception{


		try {

			throw new NotYetImplementedException();

		} catch (NotYetImplementedException e) {

			Messenger.show(e);

		}


	}


	public void executeInputMacroEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				InputMacro.class, 

				InputMacroView.class, 

				InputMacroController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeIcmsEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				Icms.class, 

				IcmsView.class, 

				IcmsController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeCompanyEntry() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				Company.class, 

				CompanyView.class, 

				CompanyController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeHelpAbout() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				HelpAboutModel.class, 

				HelpAboutView.class, 

				HelpAboutController.class);

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void executeChangePassword() throws Exception{


		MVCBean bean = MVCBeanFactory.create(

				ChangePasswordModel.class, 

				ChangePasswordView.class, 

				ChangePasswordController.class);

		((ChangePasswordController)bean.getController()).

		changeSystemPersonProperty(getController().getModel().getSystemPerson());

		getDesktop().add((InternalFrame)bean.getView());

		((InternalFrame)bean.getView()).setVisible(true);

		((InternalFrame)bean.getView()).setLocation(InternalFramePosition.CENTER);


	}


	public void internalFrameDeactived(DesktopPaneChangedEvent event) {


		boolean finded = getDesktop().getComponentCount() > 0;

		if (finded)

			for (Component comp : getDesktop().getComponents()) {

				if (comp instanceof JInternalFrame){

					getDesktop().setSelectedFrame((JInternalFrame)comp);

					try {

						((JInternalFrame)comp).setSelected(true);

					} catch (PropertyVetoException e) {

						e.printStackTrace();

					}

					break;

				}

			}

		else

			getController().changeActiveFrameProperty(null);


	}


	public void internalFrameActived(DesktopPaneChangedEvent event) {


		JInternalFrame f = (JInternalFrame) event.getSource();

		if (f!=null)

			getController().changeActiveFrameProperty(f);


	}

}
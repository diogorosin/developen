package developen.client.application.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import developen.client.application.action.NewPasswordAction;
import developen.client.application.i18n.AccessControlTag;
import developen.client.application.i18n.SystemPersonTag;
import developen.client.application.search.SystemPersonSearch;
import developen.client.application.widget.SystemPersonDBTree;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.ColapseAction;
import developen.client.framework.action.ExpandAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.mvc.SelectionTransformer;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBListView;
import developen.client.subject.mvc.PersonView;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Button;
import developen.common.framework.widget.ButtonPanel;
import developen.common.framework.widget.ButtonPanelAligment;
import developen.common.framework.widget.TabbedPane;
import developen.common.framework.widget.ToolBar;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;
import developen.common.subject.mvc.PersonAction;
import developen.common.subject.mvc.PersonCompany;
import developen.common.subject.mvc.PersonCompanyPK;
import developen.common.subject.mvc.SystemCompany;
import developen.common.subject.mvc.SystemPerson;

public abstract class SystemPersonView extends PersonView {


	private static final long serialVersionUID = -4160453605386298448L;

	private SystemPersonDBTree menuTree;

	private DBListView<PersonCompany> companyList;

	private JSplitPane systemPersonTab;

	private Button newPasswordButton;

	private NewPasswordAction newPasswordAction;

	private JPanel companyPanel;

	private ToolBar companyToolBar;

	private JPanel menuPanel;

	private ToolBar menuToolBar;

	private AddAction addCompanyAction;

	private RemoveAction removeCompanyAction;

	private ExpandAction expandAction;

	private ColapseAction colapseAction;


	public SystemPersonView(SystemPersonController controller) {

		super(controller);

	}


	public Tag getInternalFrameTitle() {

		return new SystemPersonTag();

	}


	public SystemPersonController getController() {

		return (SystemPersonController) super.getController();

	}


	public TabbedPane getTabbedPane(){


		TabbedPane t = super.getTabbedPane();

		t.add(getSystemPersonTab());

		return t;


	}


	public ButtonPanel getButtonPanel(){


		ButtonPanel b = super.getButtonPanel();

		b.add(getNewPasswordButton(), ButtonPanelAligment.LEFT);

		return b;


	}


	public JSplitPane getSystemPersonTab(){


		if (systemPersonTab==null){

			systemPersonTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getMenuPanel(), getCompanyPanel());

			systemPersonTab.setName(new AccessControlTag().toString());

			systemPersonTab.setDividerLocation((WIDTH/2)-20);

		}

		return systemPersonTab;


	}


	public JPanel getMenuPanel(){


		if (menuPanel == null){

			menuPanel = new JPanel(new BorderLayout());

			menuPanel.add(getMenuToolBar(), BorderLayout.NORTH);

			menuPanel.add(new JScrollPane(getMenuTree()), BorderLayout.CENTER);

		}

		return menuPanel;


	}


	public ToolBar getMenuToolBar(){


		if (menuToolBar == null){

			menuToolBar = new ToolBar();

			menuToolBar.add(getColapseAction());

			menuToolBar.add(getExpandAction());

			menuToolBar.setFloatable(false);

			menuToolBar.setBorder(BorderFactory.createEmptyBorder());

			menuToolBar.setFocusable(false);

		}

		return menuToolBar;


	}


	public SystemPersonDBTree getMenuTree(){


		if (menuTree == null){

			menuTree = new SystemPersonDBTree(

					getMenuHierarchy(), 

					SystemPersonController.ACTIONS_PROPERTY, 

					(SystemPersonController) getController());

			menuTree.setSelectionTransformer(new SelectionTransformer() {

				public List<Object> transform(List<Object> selection) throws Exception {

					List<Object> oldList = selection;

					List<Object> newList = new ArrayList<Object>();

					if (selection == null)

						return newList;

					else

						for (Object object : oldList) {

							PersonAction personAction = (PersonAction) object;

							newList.add(personAction.getIdentifier().getSystemAction().getIdentifier());

						}

					return newList;

				}

			});

			getController().addView(menuTree);

		}

		return menuTree;


	}

	public JPanel getCompanyPanel(){


		if (companyPanel == null){

			companyPanel = new JPanel(new BorderLayout());

			companyPanel.add(getCompanyToolBar(), BorderLayout.NORTH);

			companyPanel.add(new JScrollPane(getCompanyList()), BorderLayout.CENTER);

		}
		return companyPanel;


	}


	public ToolBar getCompanyToolBar(){


		if (companyToolBar == null){

			companyToolBar = new ToolBar();

			companyToolBar.add(getAddCompanyAction());

			companyToolBar.add(getRemoveCompanyAction());

			companyToolBar.setFloatable(false);

			companyToolBar.setBorder(BorderFactory.createEmptyBorder());

			companyToolBar.setFocusable(false);

		}

		return companyToolBar;


	}


	public DBListView<PersonCompany> getCompanyList(){


		if (companyList==null){

			companyList = new DBListView<PersonCompany>(

					new DefaultListModel<PersonCompany>(),

					SystemPersonController.COMPANIES_PROPERTY);

			getController().addView(companyList);

		}

		return companyList;


	}


	public Search getIdentifierSearch(){


		if (identifierSearch == null){

			identifierSearch = new SystemPersonSearch(true);

			identifierSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(

							((SystemPerson)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}


	public Button getNewPasswordButton(){


		if (newPasswordButton == null){

			newPasswordButton = new Button(getNewPasswordAction());

			newPasswordButton.setPreferredSize(new Dimension(110,26));

			newPasswordButton.setFocusable(false);

		}

		return newPasswordButton;


	}


	public NewPasswordAction getNewPasswordAction(){


		if (newPasswordAction == null){

			newPasswordAction = new NewPasswordAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {

					try {

						getController().newPassword();

					} catch (Exception exception) {

						Messenger.show(exception);

					}

				}

			};	

			getController().addView(newPasswordAction);

		}

		return newPasswordAction;


	}


	public AddAction getAddCompanyAction() {


		if (addCompanyAction==null){

			addCompanyAction = new AddAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {

					try {

						List<PersonCompany> allCompanies = new ArrayList<PersonCompany>();
						
						Session session = DPA.getSessionFactory().createSession();
						
						for (Object object : session.list(SystemCompany.class))

							allCompanies.add(new PersonCompany(new PersonCompanyPK(getController().getModel(), ((SystemCompany) object))));

						session.close();
						
						List<PersonCompany> popupMenuOptions = new ArrayList<PersonCompany>();

						for (PersonCompany company : allCompanies)

							popupMenuOptions.add(company);

						for (PersonCompany popupMenuCompany : allCompanies) {

							for (PersonCompany personCompany : getController().getModel().getCompanies()) {

								if (popupMenuCompany.equals(personCompany))

									popupMenuOptions.remove(popupMenuCompany);

							}

						}

						if (popupMenuOptions.size() > 0){

							JPopupMenu popupMenu = new JPopupMenu();

							for (PersonCompany personCompany : popupMenuOptions)

								popupMenu.add(new MyAction(personCompany));

							popupMenu.show(getCompanyToolBar(), 

									getCompanyToolBar().getX(), 

									getCompanyToolBar().getY() + getCompanyToolBar().getSize().height);

						}

					} catch (Exception e1) {

						Messenger.show(e1);

					} 

				}

			};

			getController().addView(addCompanyAction);

		}

		return addCompanyAction;


	}


	public RemoveAction getRemoveCompanyAction() {


		if (removeCompanyAction==null){

			removeCompanyAction = new RemoveAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& ((getController().getModel().getCompanies()!=null) &&

										(getController().getModel().getCompanies().size() > 0)));

					else

						if (e.getPropertyName().equals(SystemPersonController.COMPANIES_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<PersonCompany>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					List<PersonCompany> companiesOfPerson = getController().getModel().getCompanies();

					List<PersonCompany> companiesToRemove = getCompanyList().getSelectedValuesList();

					List<PersonCompany> companiesToSender = new ArrayList<PersonCompany>();

					for (PersonCompany personCompany : companiesOfPerson)

						companiesToSender.add(personCompany);

					for (PersonCompany personCompany : companiesOfPerson) 

						for (PersonCompany companyToRemove : companiesToRemove) 

							if (personCompany.equals(companyToRemove))

								companiesToSender.remove(personCompany);

					getController().changeCompaniesProperty(companiesToSender);


				}

			};

			getController().addView(removeCompanyAction);

		}

		return removeCompanyAction;


	}


	public ExpandAction getExpandAction() {


		if (expandAction==null){

			expandAction = new ExpandAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {

					for (int i = 0; i < getMenuTree().getRowCount(); i++) 

						getMenuTree().expandRow(i);

				}

			};	

			getController().addView(expandAction);

		}

		return expandAction;


	}


	public ColapseAction getColapseAction() {

		
		if (colapseAction==null){

			colapseAction = new ColapseAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {

					for (int i = getMenuTree().getRowCount() - 1; i >= 0; i--)

						getMenuTree().collapseRow(i);

				}

			};

			getController().addView(colapseAction);

		}

		return colapseAction;


	}


	public abstract Object[] getMenuHierarchy();

	
	class MyAction extends AbstractAction {


		private static final long serialVersionUID = -7819952502492061888L;
		
		private PersonCompany personCompany;
		

		public MyAction(PersonCompany company){

			
			this.putValue(AbstractAction.NAME, company.toString());
			
			setPersonCompany(company);
			

		}

		
		public String toString(){

			return getPersonCompany().toString();

		}

		
		public PersonCompany getPersonCompany() {

			return personCompany;

		}

		
		public void setPersonCompany(PersonCompany personCompany) {

			this.personCompany = personCompany;

		}

		
		public void actionPerformed(ActionEvent e) {

			
			List<PersonCompany> companiesOfPerson = new ArrayList<PersonCompany>();

			if (getController().getModel().getCompanies() != null)
				
				for (PersonCompany personCompany : getController().getModel().getCompanies())
					
					companiesOfPerson.add(personCompany);

			companiesOfPerson.add(getPersonCompany());
			
			getController().changeCompaniesProperty(companiesOfPerson);
			

		}

		
	}

	
}
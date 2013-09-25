package developen.client.commercial.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import developen.client.commercial.search.IpiSearch;
import developen.client.commercial.widget.DBIpiRuleTable;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.EditAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.i18n.QuestionOnBeforeDeleteTag;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.mvc.ListEditorAdapter;
import developen.client.framework.mvc.ListEditorEvent;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.IpiTag;
import developen.common.commercial.i18n.RulesTag;
import developen.common.commercial.mvc.Ipi;
import developen.common.commercial.mvc.IpiRule;
import developen.common.commercial.mvc.IpiRulePK;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.messenger.Question;
import developen.common.framework.messenger.SimplifiedQuestion;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.framework.widget.ToolBar;
import developen.common.persistence.type.AllwaysDiferentList;

public class IpiView extends EntryView {


	private static final long serialVersionUID = -977449749715417403L;

	public static int WIDTH = 600;

	public static int HEIGHT = 500;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private TabbedPane tabbedPane;

	private JPanel rulesTab;

	protected Search identifierSearch;

	private ToolBar rulesToolBar;

	private AddAction addAction;

	private EditAction editAction;

	private RemoveAction removeAction;

	private DBIpiRuleTable rulesTable;


	public IpiController getController() {

		return (IpiController) super.getController();

	}


	public IpiView(IpiController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH, HEIGHT));

	}


	public ExtendedPanel getNorthPanel(){


		ExtendedPanel l = super.getNorthPanel();

		DBRowPanel northPanel = new DBRowPanel();

		northPanel.add(getIdentifierField());

		northPanel.add(getDenominationField());

		l.add(northPanel);

		return l;


	}


	public ExtendedPanel getCenterPanel(){


		ExtendedPanel l = super.getCenterPanel();

		l.add(getTabbedPane());

		return l;


	}


	public TabbedPane getTabbedPane(){


		if (tabbedPane == null){

			tabbedPane = new TabbedPane();

			tabbedPane.add(getRulesTab());

			tabbedPane.setFocusable(false);

		}

		return tabbedPane;


	}


	public JPanel getRulesTab(){


		if (rulesTab == null){

			rulesTab = new JPanel(new BorderLayout());

			rulesTab.setName(new RulesTag().toString());

			rulesTab.add(getRulesToolBar(), BorderLayout.NORTH);

			rulesTab.add(getRulesTable(), BorderLayout.CENTER);

		}

		return rulesTab;


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getIdentifierField())

			getController().changeIdentifierProperty(getIdentifierField().getText().isEmpty() 

					? new Long(0) 

			: Long.valueOf(getIdentifierField().getText()));

		else

			if (event.getCheckable() == getDenominationField())

				getController().changeDenominationProperty(getDenominationField().getText());


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), IpiController.IDENTIFIER_PROPERTY);

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setSize(new Dimension(150, UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), IpiController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public Search getSearch(){


		if (identifierSearch==null){

			identifierSearch = new IpiSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((Ipi)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

	}


	public Tag getInternalFrameTitle() {

		return new IpiTag();

	}


	public DBIpiRuleTable getRulesTable(){


		if (rulesTable==null){

			rulesTable = new DBIpiRuleTable(IpiController.RULES_PROPERTY

					, getController().getModel()

					, getRemoveRuleAction()

					, getEditRuleAction()

					, getAddRuleAction()){

				private static final long serialVersionUID = 3200850609986680128L;

				public IpiRule getRowAt(int index) {
					
					return getController().getModel().getRules().get(index);
					
				}

			};

			rulesTable.addListEditorChangeListener(new ListEditorAdapter() {


				public void onIncluded(ListEditorEvent event) throws Exception {


					List<IpiRule> childs = getController().getModel().getRules();

					List<IpiRule> childsToSender = new AllwaysDiferentList<IpiRule>();

					for (IpiRule child : childs)

						childsToSender.add(child);

					IpiRule newValue = (IpiRule) event.getObject();

					IpiRulePK newValuePK = new IpiRulePK();

					newValuePK.setIpi(newValue.getIdentifier().getIpi());

					newValuePK.setCfop(newValue.getIdentifier().getCfop());

					newValuePK.setRule(newValue.getIdentifier().getRule());

					IpiRule child = new IpiRule();

					child.setIdentifier(newValuePK);

					child.setCst(newValue.getCst());

					child.setIpiAliquot(newValue.getIpiAliquot());

					child.setIpiStaff(newValue.getIpiStaff());

					childsToSender.add(child);

					getController().changeRulesProperty(childsToSender);


				}


				public void onUpdated(ListEditorEvent event) throws Exception {


					IpiRule newValue = (IpiRule) event.getObject();

					IpiRulePK newValuePK = new IpiRulePK();

					newValuePK.setIpi(newValue.getIdentifier().getIpi());

					newValuePK.setCfop(newValue.getIdentifier().getCfop());

					newValuePK.setRule(newValue.getIdentifier().getRule());

					IpiRule child = new IpiRule();

					child.setIdentifier(newValuePK);

					child.setCst(newValue.getCst());

					child.setIpiAliquot(newValue.getIpiAliquot());

					child.setIpiStaff(newValue.getIpiStaff());

					List<IpiRule> childs = getController().getModel().getRules();

					List<IpiRule> childsToSender = new AllwaysDiferentList<IpiRule>();

					for (IpiRule oldChild : childs)

						if (oldChild.hashCode()==child.hashCode())

							childsToSender.add(child);

						else

							childsToSender.add(oldChild);

					getController().changeRulesProperty(childsToSender);


				}


				public void onDeleted(ListEditorEvent event) throws Exception {


					IpiRule newValue = (IpiRule) event.getObject();

					IpiRulePK newValuePK = new IpiRulePK();

					newValuePK.setIpi(newValue.getIdentifier().getIpi());

					newValuePK.setCfop(newValue.getIdentifier().getCfop());

					newValuePK.setRule(newValue.getIdentifier().getRule());

					IpiRule child = new IpiRule();

					child.setIdentifier(newValuePK);

					child.setCst(newValue.getCst());

					child.setIpiAliquot(newValue.getIpiAliquot());

					child.setIpiStaff(newValue.getIpiStaff());

					List<IpiRule> childs = getController().getModel().getRules();

					List<IpiRule> childsToRemove = new ArrayList<IpiRule>();

					childsToRemove.add(child);

					List<IpiRule> childsToSender = new AllwaysDiferentList<IpiRule>();

					for (IpiRule childToSender : childs)

						childsToSender.add(childToSender);

					for (IpiRule myChild : childs) 

						for (IpiRule childToRemove : childsToRemove) 

							if (myChild.hashCode()==childToRemove.hashCode())

								childsToSender.remove(myChild);

					getController().changeRulesProperty(childsToSender);


				}


			});

			getController().addView(rulesTable);

			getController().addView(rulesTable.getTable());


		}

		return rulesTable;


	}


	public ToolBar getRulesToolBar(){


		if (rulesToolBar == null){

			rulesToolBar = new ToolBar();

			rulesToolBar.add(getEditRuleAction());

			rulesToolBar.add(getAddRuleAction());

			rulesToolBar.add(getRemoveRuleAction());

			rulesToolBar.setFloatable(false);

			rulesToolBar.setBorder(BorderFactory.createEmptyBorder());

			rulesToolBar.setFocusable(false);

		}

		return rulesToolBar;


	}


	public AddAction getAddRuleAction() {


		if (addAction==null){

			addAction = new AddAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING)

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getRulesTable().include();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(addAction);

		}

		return addAction;


	}


	public EditAction getEditRuleAction() {


		if (editAction==null){

			editAction = new EditAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getRules() != null) && (getController().getModel().getRules().size() > 0));

					else

						if (e.getPropertyName().equals(IpiController.RULES_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getRulesTable().edit();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(editAction);

		}

		return editAction;


	}


	public RemoveAction getRemoveRuleAction() {


		if (removeAction==null){

			removeAction = new RemoveAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getRules() != null) && (getController().getModel().getRules().size() > 0));

					else

						if (e.getPropertyName().equals(IpiController.RULES_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					if (Messenger.ask(

							new SimplifiedQuestion(

									new QuestionOnBeforeDeleteTag())).equals(

											Question.YES)) {

						List<IpiRule> childs = getController().getModel().getRules();

						List<IpiRule> childsToRemove = new ArrayList<IpiRule>();

						for(int i : getRulesTable().getSelectedRows())

							childsToRemove.add(getController().getModel().getRules().get(i));

						List<IpiRule> childsToSender = new ArrayList<IpiRule>();

						for (IpiRule child : childs)

							childsToSender.add(child);

						for (IpiRule child : childs) 

							for (IpiRule childToRemove : childsToRemove) 

								if (child.hashCode()==childToRemove.hashCode())

									childsToSender.remove(child);

						getController().changeRulesProperty(childsToSender);

					}


				}

			};

			getController().addView(removeAction);

		}

		return removeAction;


	}


}
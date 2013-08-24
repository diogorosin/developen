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

import developen.client.commercial.search.PisCofinsSearch;
import developen.client.commercial.widget.DBPisCofinsRuleTable;
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
import developen.common.commercial.i18n.PisCofinsTag;
import developen.common.commercial.i18n.RulesTag;
import developen.common.commercial.mvc.PisCofins;
import developen.common.commercial.mvc.PisCofinsRule;
import developen.common.commercial.mvc.PisCofinsRulePK;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.messenger.Question;
import developen.common.framework.messenger.SimplifiedQuestion;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.framework.widget.ToolBar;
import developen.common.persistence.type.AllwaysDiferentList;

public class PisCofinsView extends EntryView {


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

	private DBPisCofinsRuleTable rulesTable;


	public PisCofinsController getController() {

		return (PisCofinsController) super.getController();

	}


	public PisCofinsView(PisCofinsController controller) {

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

			identifierField = new DBIdentifierField(new IdentifierTag(), PisCofinsController.IDENTIFIER_PROPERTY);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setSize(new Dimension(150, 24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), PisCofinsController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(400, 24));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new PisCofinsSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((PisCofins)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

	}


	public Tag getInternalFrameTitle() {

		return new PisCofinsTag();

	}


	public DBPisCofinsRuleTable getRulesTable(){


		if (rulesTable==null){

			rulesTable = new DBPisCofinsRuleTable(PisCofinsController.RULES_PROPERTY

					, getController().getModel()

					, getRemoveRuleAction()

					, getEditRuleAction()

					, getAddRuleAction()){

				private static final long serialVersionUID = 5329213070647173989L;

				public PisCofinsRule getRowAt(int index) {

					return getController().getModel().getRules().get(index);

				}

			};

			rulesTable.addListEditorChangeListener(new ListEditorAdapter() {


				public void onIncluded(ListEditorEvent event) throws Exception {


					List<PisCofinsRule> childs = getController().getModel().getRules();

					List<PisCofinsRule> childsToSender = new AllwaysDiferentList<PisCofinsRule>();

					for (PisCofinsRule child : childs)

						childsToSender.add(child);

					PisCofinsRule newValue = (PisCofinsRule) event.getObject();

					PisCofinsRulePK newValuePK = new PisCofinsRulePK();

					newValuePK.setPisCofins(newValue.getIdentifier().getPisCofins());

					newValuePK.setCfop(newValue.getIdentifier().getCfop());

					newValuePK.setRule(newValue.getIdentifier().getRule());

					PisCofinsRule child = new PisCofinsRule();

					child.setIdentifier(newValuePK);

					child.setPisCst(newValue.getPisCst());

					child.setCofinsCst(newValue.getCofinsCst());

					child.setPisCumulative(newValue.getPisCumulative());

					child.setPisNonCumulative(newValue.getPisNonCumulative());

					child.setCofinsCumulative(newValue.getCofinsCumulative());

					child.setCofinsNonCumulative(newValue.getCofinsNonCumulative());

					childsToSender.add(child);

					getController().changeRulesProperty(childsToSender);


				}


				public void onUpdated(ListEditorEvent event) throws Exception {


					PisCofinsRule newValue = (PisCofinsRule) event.getObject();

					PisCofinsRulePK newValuePK = new PisCofinsRulePK();

					newValuePK.setPisCofins(newValue.getIdentifier().getPisCofins());

					newValuePK.setCfop(newValue.getIdentifier().getCfop());

					newValuePK.setRule(newValue.getIdentifier().getRule());

					PisCofinsRule child = new PisCofinsRule();

					child.setIdentifier(newValuePK);

					child.setPisCst(newValue.getPisCst());

					child.setCofinsCst(newValue.getCofinsCst());

					child.setPisCumulative(newValue.getPisCumulative());

					child.setPisNonCumulative(newValue.getPisNonCumulative());

					child.setCofinsCumulative(newValue.getCofinsCumulative());

					child.setCofinsNonCumulative(newValue.getCofinsNonCumulative());

					List<PisCofinsRule> childs = getController().getModel().getRules();

					List<PisCofinsRule> childsToSender = new AllwaysDiferentList<PisCofinsRule>();

					for (PisCofinsRule oldChild : childs)

						if (oldChild.hashCode()==child.hashCode())

							childsToSender.add(child);

						else

							childsToSender.add(oldChild);

					getController().changeRulesProperty(childsToSender);


				}


				public void onDeleted(ListEditorEvent event) throws Exception {


					PisCofinsRule newValue = (PisCofinsRule) event.getObject();

					PisCofinsRulePK newValuePK = new PisCofinsRulePK();

					newValuePK.setPisCofins(newValue.getIdentifier().getPisCofins());

					newValuePK.setCfop(newValue.getIdentifier().getCfop());

					newValuePK.setRule(newValue.getIdentifier().getRule());

					PisCofinsRule child = new PisCofinsRule();

					child.setIdentifier(newValuePK);

					child.setPisCst(newValue.getPisCst());

					child.setCofinsCst(newValue.getCofinsCst());

					child.setPisCumulative(newValue.getPisCumulative());

					child.setPisNonCumulative(newValue.getPisNonCumulative());

					child.setCofinsCumulative(newValue.getCofinsCumulative());

					child.setCofinsNonCumulative(newValue.getCofinsNonCumulative());

					List<PisCofinsRule> childs = getController().getModel().getRules();

					List<PisCofinsRule> childsToRemove = new ArrayList<PisCofinsRule>();

					childsToRemove.add(child);

					List<PisCofinsRule> childsToSender = new AllwaysDiferentList<PisCofinsRule>();

					for (PisCofinsRule childToSender : childs)

						childsToSender.add(childToSender);

					for (PisCofinsRule myChild : childs) 

						for (PisCofinsRule childToRemove : childsToRemove) 

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

			rulesToolBar.add(getAddRuleAction());

			rulesToolBar.add(getEditRuleAction());

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

						if (e.getPropertyName().equals(PisCofinsController.RULES_PROPERTY))

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

						if (e.getPropertyName().equals(PisCofinsController.RULES_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					if (Messenger.ask(

							new SimplifiedQuestion(

									new QuestionOnBeforeDeleteTag())).equals(

											Question.YES)) {

						List<PisCofinsRule> childs = getController().getModel().getRules();

						List<PisCofinsRule> childsToRemove = new ArrayList<PisCofinsRule>();

						for(int i : getRulesTable().getSelectedRows())

							childsToRemove.add(getController().getModel().getRules().get(i));

						List<PisCofinsRule> childsToSender = new ArrayList<PisCofinsRule>();

						for (PisCofinsRule child : childs)

							childsToSender.add(child);

						for (PisCofinsRule child : childs) 

							for (PisCofinsRule childToRemove : childsToRemove) 

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
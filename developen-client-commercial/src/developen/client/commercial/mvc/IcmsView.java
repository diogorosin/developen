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

import developen.client.commercial.search.IcmsSearch;
import developen.client.commercial.widget.DBIcmsRuleTable;
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
import developen.common.commercial.i18n.IcmsIcmsSTTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.RulesTag;
import developen.common.commercial.mvc.Icms;
import developen.common.commercial.mvc.IcmsRule;
import developen.common.commercial.mvc.IcmsRulePK;
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

public class IcmsView extends EntryView {


	private static final long serialVersionUID = 4187121811776341417L;

	public static int WIDTH = 600;

	public static int HEIGHT = 500;

	public static int C_WIDTH = 400;

	public static int C_HEIGHT = 24;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private TabbedPane tabbedPane;

	private JPanel rulesTab;

	protected Search identifierSearch;

	private ToolBar rulesToolBar;

	private AddAction addAction;

	private EditAction editAction;

	private RemoveAction removeAction;

	private DBIcmsRuleTable rulesTable;


	public IcmsController getController() {

		return (IcmsController) super.getController();

	}


	public IcmsView(IcmsController controller) {

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

			identifierField = new DBIdentifierField(new IdentifierTag(), IcmsController.IDENTIFIER_PROPERTY);

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setSize(new Dimension(150, UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), IcmsController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(C_WIDTH, UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public Search getSearch(){


		if (identifierSearch==null){

			identifierSearch = new IcmsSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((Icms)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

	}


	public Tag getInternalFrameTitle() {

		return new IcmsIcmsSTTag();

	}


	public DBIcmsRuleTable getRulesTable(){


		if (rulesTable==null){

			rulesTable = new DBIcmsRuleTable(IcmsController.RULES_PROPERTY

					, getController().getModel()

					, getRemoveRuleAction()

					, getEditRuleAction()

					, getAddRuleAction()){

				private static final long serialVersionUID = 3052110809137289237L;

				public IcmsRule getRowAt(int index) {

					return getController().getModel().getRules().get(index);

				}

			};

			rulesTable.addListEditorChangeListener(new ListEditorAdapter() {


				public void onIncluded(ListEditorEvent event) throws Exception {


					List<IcmsRule> childs = getController().getModel().getRules();

					List<IcmsRule> childsToSender = new AllwaysDiferentList<IcmsRule>();

					for (IcmsRule child : childs)

						childsToSender.add(child);

					IcmsRule newValue = (IcmsRule) event.getObject();

					IcmsRulePK newValuePK = new IcmsRulePK();

					newValuePK.setIcms(newValue.getIdentifier().getIcms());

					newValuePK.setFrom(newValue.getIdentifier().getFrom());

					newValuePK.setTo(newValue.getIdentifier().getTo());

					newValuePK.setRule(newValue.getIdentifier().getRule());

					IcmsRule child = new IcmsRule();

					child.setIdentifier(newValuePK);

					child.setCst(newValue.getCst());

					child.setCsosn(newValue.getCsosn());

					child.setIcmsAliquot(newValue.getIcmsAliquot());

					child.setIcmsReduction(newValue.getIcmsReduction());

					child.setIcmsAliquotReduced(newValue.getIcmsAliquotReduced());

					child.setIcmsAliquotCreditReusable(newValue.getIcmsAliquotCreditReusable());

					child.setIcmsSTMarckup(newValue.getIcmsSTMarckup());

					child.setIcmsSTReduction(newValue.getIcmsSTReduction());

					child.setIcmsSTStaff(newValue.getIcmsSTSTaff());

					child.setCfopGroup(newValue.getCfopGroup());

					childsToSender.add(child);

					getController().changeRulesProperty(childsToSender);


				}


				public void onUpdated(ListEditorEvent event) throws Exception {


					IcmsRule newValue = (IcmsRule) event.getObject();

					IcmsRulePK newValuePK = new IcmsRulePK();

					newValuePK.setIcms(newValue.getIdentifier().getIcms());

					newValuePK.setFrom(newValue.getIdentifier().getFrom());

					newValuePK.setTo(newValue.getIdentifier().getTo());

					newValuePK.setRule(newValue.getIdentifier().getRule());

					IcmsRule child = new IcmsRule();

					child.setIdentifier(newValuePK);

					child.setCst(newValue.getCst());

					child.setCsosn(newValue.getCsosn());

					child.setIcmsAliquot(newValue.getIcmsAliquot());

					child.setIcmsReduction(newValue.getIcmsReduction());

					child.setIcmsAliquotReduced(newValue.getIcmsAliquotReduced());

					child.setIcmsAliquotCreditReusable(newValue.getIcmsAliquotCreditReusable());

					child.setIcmsSTMarckup(newValue.getIcmsSTMarckup());

					child.setIcmsSTReduction(newValue.getIcmsSTReduction());

					child.setIcmsSTStaff(newValue.getIcmsSTSTaff());

					child.setCfopGroup(newValue.getCfopGroup());

					List<IcmsRule> childs = getController().getModel().getRules();

					List<IcmsRule> childsToSender = new AllwaysDiferentList<IcmsRule>();

					for (IcmsRule oldChild : childs)

						if (oldChild.hashCode()==child.hashCode())

							childsToSender.add(child);

						else

							childsToSender.add(oldChild);

					getController().changeRulesProperty(childsToSender);


				}


				public void onDeleted(ListEditorEvent event) throws Exception {


					IcmsRule newValue = (IcmsRule) event.getObject();

					IcmsRulePK newValuePK = new IcmsRulePK();

					newValuePK.setIcms(newValue.getIdentifier().getIcms());

					newValuePK.setFrom(newValue.getIdentifier().getFrom());

					newValuePK.setTo(newValue.getIdentifier().getTo());

					newValuePK.setRule(newValue.getIdentifier().getRule());

					IcmsRule child = new IcmsRule();

					child.setIdentifier(newValuePK);

					child.setCst(newValue.getCst());

					child.setCsosn(newValue.getCsosn());

					child.setIcmsAliquot(newValue.getIcmsAliquot());

					child.setIcmsReduction(newValue.getIcmsReduction());

					child.setIcmsAliquotReduced(newValue.getIcmsAliquotReduced());

					child.setIcmsAliquotCreditReusable(newValue.getIcmsAliquotCreditReusable());

					child.setIcmsSTMarckup(newValue.getIcmsSTMarckup());

					child.setIcmsSTReduction(newValue.getIcmsSTReduction());

					child.setIcmsSTStaff(newValue.getIcmsSTSTaff());

					child.setCfopGroup(newValue.getCfopGroup());

					List<IcmsRule> childs = getController().getModel().getRules();

					List<IcmsRule> childsToRemove = new ArrayList<IcmsRule>();

					childsToRemove.add(child);

					List<IcmsRule> childsToSender = new AllwaysDiferentList<IcmsRule>();

					for (IcmsRule childToSender : childs)

						childsToSender.add(childToSender);

					for (IcmsRule myChild : childs) 

						for (IcmsRule childToRemove : childsToRemove) 

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

						if (e.getPropertyName().equals(IcmsController.RULES_PROPERTY))

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

						if (e.getPropertyName().equals(IcmsController.RULES_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					if (Messenger.ask(

							new SimplifiedQuestion(

									new QuestionOnBeforeDeleteTag())).equals(

											Question.YES)) {

						List<IcmsRule> childs = getController().getModel().getRules();

						List<IcmsRule> childsToRemove = new ArrayList<IcmsRule>();

						for(int i : getRulesTable().getSelectedRows())

							childsToRemove.add(getController().getModel().getRules().get(i));

						List<IcmsRule> childsToSender = new ArrayList<IcmsRule>();

						for (IcmsRule unitMeasureConversion : childs)

							childsToSender.add(unitMeasureConversion);

						for (IcmsRule child : childs) 

							for (IcmsRule childToRemove : childsToRemove) 

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
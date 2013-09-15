package developen.client.finance.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import developen.client.finance.widget.DBConditionDayTable;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.EditAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.i18n.QuestionOnBeforeDeleteTag;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.mvc.ListEditorAdapter;
import developen.client.framework.mvc.ListEditorEvent;
import developen.client.framework.search.Search;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.finance.i18n.ActiveTag;
import developen.common.finance.i18n.ConditionDayTag;
import developen.common.finance.i18n.ConditionTag;
import developen.common.finance.i18n.DenominationTag;
import developen.common.finance.i18n.IdentifierTag;
import developen.common.finance.mvc.ConditionDay;
import developen.common.finance.mvc.ConditionDayPK;
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

public abstract class ConditionView extends EntryView {


	private static final long serialVersionUID = -5394176398244939089L;

	public static int WIDTH = 500;

	public static int HEIGHT = 500;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBCheckBox activeField;

	private TabbedPane tabbedPane;

	private JPanel conditionDayTab;

	protected Search identifierSearch;

	private ToolBar toolBar;

	private AddAction addConditionDayAction;

	private EditAction editConditionDayAction;

	private RemoveAction removeConditionDayAction;

	private DBConditionDayTable conditionDayTable;


	public ConditionController getController() {

		return (ConditionController) super.getController();

	}


	public ConditionView(ConditionController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH,HEIGHT));

	}


	public ExtendedPanel getNorthPanel(){


		ExtendedPanel l = super.getNorthPanel();

		DBRowPanel northPanel = new DBRowPanel();

		northPanel.add(getIdentifierField());

		northPanel.add(getDenominationField());
		
		northPanel.add(getActiveField());

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

			tabbedPane.add(getConditionDayTab());

			tabbedPane.setFocusable(false);

		}

		return tabbedPane;


	}


	public JPanel getConditionDayTab(){


		if (conditionDayTab == null){

			conditionDayTab = new JPanel(new BorderLayout());

			conditionDayTab.setName(new ConditionDayTag().toString());

			conditionDayTab.add(getToolBar(), BorderLayout.NORTH);

			conditionDayTab.add(getConditionDayTable(), BorderLayout.CENTER);

		}

		return conditionDayTab;


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

			identifierField = new DBIdentifierField(new IdentifierTag(), ConditionController.IDENTIFIER_PROPERTY);

			identifierField.setSearch(getSearch());

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setPreferredSize(new Dimension(75,24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), ConditionController.DENOMINATION_PROPERTY);

			denominationField.setCondition(new NeverEnabledCondition());
			
			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(300, 24));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBCheckBox getActiveField() {


		if (activeField == null){

			activeField = new DBCheckBox(new ActiveTag(), ConditionController.ACTIVE_PROPERTY);

			activeField.setSelected(false);

			activeField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeActiveProperty(activeField.isSelected());

				}

			});

			getController().addView(activeField);

		}

		return activeField;


	}

	
	public Tag getInternalFrameTitle() {

		return new ConditionTag();

	}


	public DBConditionDayTable getConditionDayTable(){


		if (conditionDayTable==null){

			conditionDayTable = new DBConditionDayTable(ConditionController.DAYS_PROPERTY

					, getController().getModel()

					, getRemoveConditionDayAction()

					, getEditConditionDayAction()

					, getAddConditionDayAction());

			conditionDayTable.addListEditorChangeListener(new ListEditorAdapter() {


				public void onIncluded(ListEditorEvent event) throws Exception {


					List<ConditionDay> conversionsOfUnitMeasure = getController().getModel().getDays();

					List<ConditionDay> conversionsToSender = new AllwaysDiferentList<ConditionDay>();

					for (ConditionDay unitMeasureConversion : conversionsOfUnitMeasure)

						conversionsToSender.add(unitMeasureConversion);

					ConditionDay newValue = (ConditionDay) event.getObject();

					ConditionDayPK unitMeasureConversionPK = new ConditionDayPK();

					unitMeasureConversionPK.setCondition(newValue.getIdentifier().getCondition());

					unitMeasureConversionPK.setDay(newValue.getIdentifier().getDay());

					ConditionDay unitMeasureConversion = new ConditionDay();

					unitMeasureConversion.setIdentifier(unitMeasureConversionPK);

					unitMeasureConversion.setFixedPercentage(newValue.getFixedPercentage());
					
					unitMeasureConversion.setValuePercentage(newValue.getValuePercentage());

					conversionsToSender.add(unitMeasureConversion);

					getController().changeDaysProperty(conversionsToSender);


				}


				public void onUpdated(ListEditorEvent event) throws Exception {


					ConditionDay newValue = (ConditionDay) event.getObject();

					ConditionDayPK unitMeasureConversionPK = new ConditionDayPK();

					unitMeasureConversionPK.setCondition(newValue.getIdentifier().getCondition());

					unitMeasureConversionPK.setDay(newValue.getIdentifier().getDay());

					ConditionDay newUnitMeasureConversion = new ConditionDay();

					newUnitMeasureConversion.setIdentifier(unitMeasureConversionPK);

					newUnitMeasureConversion.setFixedPercentage(newValue.getFixedPercentage());
					
					newUnitMeasureConversion.setValuePercentage(newValue.getValuePercentage());

					List<ConditionDay> conversionsOfUnitMeasure = getController().getModel().getDays();

					List<ConditionDay> conversionsToSender = new AllwaysDiferentList<ConditionDay>();

					for (ConditionDay oldUnitMeasureConversion : conversionsOfUnitMeasure)

						if (oldUnitMeasureConversion.hashCode()==newUnitMeasureConversion.hashCode())

							conversionsToSender.add(newUnitMeasureConversion);

						else

							conversionsToSender.add(oldUnitMeasureConversion);

					getController().changeDaysProperty(conversionsToSender);


				}


				public void onDeleted(ListEditorEvent event) throws Exception {


					ConditionDay newValue = (ConditionDay) event.getObject();

					ConditionDayPK unitMeasureConversionPK = new ConditionDayPK();

					unitMeasureConversionPK.setCondition(newValue.getIdentifier().getCondition());

					unitMeasureConversionPK.setDay(newValue.getIdentifier().getDay());

					ConditionDay newUnitMeasureConversion = new ConditionDay();

					newUnitMeasureConversion.setIdentifier(unitMeasureConversionPK);

					newUnitMeasureConversion.setFixedPercentage(newValue.getFixedPercentage());
					
					newUnitMeasureConversion.setValuePercentage(newValue.getValuePercentage());

					List<ConditionDay> conversionsOfUnitMeasure = getController().getModel().getDays();

					List<ConditionDay> conversionsToRemove = new ArrayList<ConditionDay>();

					conversionsToRemove.add(newUnitMeasureConversion);

					List<ConditionDay> conversionsToSender = new AllwaysDiferentList<ConditionDay>();

					for (ConditionDay unitMeasureConversion : conversionsOfUnitMeasure)

						conversionsToSender.add(unitMeasureConversion);

					for (ConditionDay unitMeasureConversion : conversionsOfUnitMeasure) 

						for (ConditionDay conversionToRemove : conversionsToRemove) 

							if (unitMeasureConversion.hashCode()==conversionToRemove.hashCode())

								conversionsToSender.remove(unitMeasureConversion);

					getController().changeDaysProperty(conversionsToSender);


				}


			});

			getController().addView(conditionDayTable);

			getController().addView(conditionDayTable.getTable());


		}

		return conditionDayTable;


	}


	public ToolBar getToolBar(){


		if (toolBar == null){

			toolBar = new ToolBar();

			toolBar.add(getAddConditionDayAction());

			toolBar.add(getEditConditionDayAction());

			toolBar.add(getRemoveConditionDayAction());

			toolBar.setFloatable(false);

			toolBar.setBorder(BorderFactory.createEmptyBorder());

			toolBar.setFocusable(false);

		}

		return toolBar;


	}


	public AddAction getAddConditionDayAction() {


		if (addConditionDayAction==null){

			addConditionDayAction = new AddAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING)

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getConditionDayTable().include();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(addConditionDayAction);

		}

		return addConditionDayAction;


	}


	public EditAction getEditConditionDayAction() {


		if (editConditionDayAction==null){

			editConditionDayAction = new EditAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getDays() != null) && (getController().getModel().getDays().size() > 0));

					else

						if (e.getPropertyName().equals(ConditionController.DAYS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getConditionDayTable().edit();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(editConditionDayAction);

		}

		return editConditionDayAction;


	}


	public RemoveAction getRemoveConditionDayAction() {


		if (removeConditionDayAction==null){

			removeConditionDayAction = new RemoveAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getDays() != null) && (getController().getModel().getDays().size() > 0));

					else

						if (e.getPropertyName().equals(ConditionController.DAYS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					if (Messenger.ask(

							new SimplifiedQuestion(

									new QuestionOnBeforeDeleteTag())).equals(

											Question.YES)) {

						List<ConditionDay> conversionsOfUnitMeasure = getController().getModel().getDays();

						List<ConditionDay> conversionsToRemove = new ArrayList<ConditionDay>();

						for(int i : getConditionDayTable().getSelectedRows())

							conversionsToRemove.add(getController().getModel().getDays().get(i));

						List<ConditionDay> conversionToSender = new ArrayList<ConditionDay>();

						for (ConditionDay unitMeasureConversion : conversionsOfUnitMeasure)

							conversionToSender.add(unitMeasureConversion);

						for (ConditionDay unitMeasureConversion : conversionsOfUnitMeasure) 

							for (ConditionDay conversionToRemove : conversionsToRemove) 

								if (unitMeasureConversion.hashCode()==conversionToRemove.hashCode())

									conversionToSender.remove(unitMeasureConversion);

						getController().changeDaysProperty(conversionToSender);

					}


				}

			};

			getController().addView(removeConditionDayAction);

		}

		return removeConditionDayAction;


	}


}

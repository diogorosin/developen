package developen.client.engineer.mvc;

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

import developen.client.engineer.search.UnitMeasureSearch;
import developen.client.engineer.widget.DBUnitMeasureConversionTable;
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
import developen.client.framework.widget.DBComboBox;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.engineer.i18n.AcronymTag;
import developen.common.engineer.i18n.ConversionsTag;
import developen.common.engineer.i18n.DenominationTag;
import developen.common.engineer.i18n.GroupTag;
import developen.common.engineer.i18n.IdentifierTag;
import developen.common.engineer.i18n.UnitMeasureTag;
import developen.common.engineer.mvc.UnitMeasure;
import developen.common.engineer.mvc.UnitMeasureConversion;
import developen.common.engineer.mvc.UnitMeasureConversionPK;
import developen.common.engineer.mvc.UnitMeasureGroup;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.messenger.Question;
import developen.common.framework.messenger.SimplifiedQuestion;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.framework.widget.ToolBar;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;
import developen.common.persistence.type.AllwaysDiferentList;

public class UnitMeasureView extends EntryView {


	private static final long serialVersionUID = -5394176398244939089L;

	public static int WIDTH = 500;

	public static int HEIGHT = 500;

	public static int C_WIDTH = 250;

	public static int C_HEIGHT = 24;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBTextField acronymField;

	private DBComboBox unitMeasureGroupComboBox;

	private TabbedPane tabbedPane;

	private JPanel conversionsTab;

	protected Search identifierSearch;

	private ToolBar toolBar;

	private AddAction addUnitMeasureConversionAction;

	private EditAction editUnitMeasureConversionAction;

	private RemoveAction removeUnitMeasureConversionAction;

	private DBUnitMeasureConversionTable unitMeasureConversionTable;


	public UnitMeasureController getController() {

		return (UnitMeasureController) super.getController();

	}


	public UnitMeasureView(UnitMeasureController controller) {

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

		northPanel.add(getAcronymField());
		
		northPanel.add(getUnitMeasureGroupComboBox());

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

			tabbedPane.add(getConversionsTab());

			tabbedPane.setFocusable(false);

		}

		return tabbedPane;


	}


	public JPanel getConversionsTab(){


		if (conversionsTab == null){

			conversionsTab = new JPanel(new BorderLayout());

			conversionsTab.setName(new ConversionsTag().toString());

			conversionsTab.add(getToolBar(), BorderLayout.NORTH);

			conversionsTab.add(getUnitMeasureConversionTable(), BorderLayout.CENTER);

		}

		return conversionsTab;


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getIdentifierField())

			getController().changeIdentifierProperty(getIdentifierField().getText().isEmpty() 

					? new Long(0) 

			: Long.valueOf(getIdentifierField().getText()));

		else

			if (event.getCheckable() == getDenominationField())

				getController().changeDenominationProperty(getDenominationField().getText());

			else

				if (event.getCheckable() == getAcronymField())

					getController().changeAcronymProperty(getAcronymField().getText());

		
	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), UnitMeasureController.IDENTIFIER_PROPERTY);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setColumns(6);

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), UnitMeasureController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(C_WIDTH, C_HEIGHT));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBTextField getAcronymField() {


		if (acronymField == null){

			acronymField = new DBTextField(new AcronymTag(), UnitMeasureController.ACRONYM_PROPERTY);

			acronymField.addCheckListener(this);

			acronymField.setColumns(6);

			getController().addView(acronymField);

		}

		return acronymField;


	}


	public DBComboBox getUnitMeasureGroupComboBox() {


		if (unitMeasureGroupComboBox==null){

			try {

				Session session = DPA.getSessionFactory().createSession();

				List<Object> list = session.list(UnitMeasureGroup.class);

				unitMeasureGroupComboBox = new DBComboBox(

						new GroupTag()

						, list.toArray()

						, UnitMeasureController.UNITMEASUREGROUP_PROPERTY);

				session.close();

			} catch (Exception e) {

				Messenger.show(e);

			}

			unitMeasureGroupComboBox.setPreferredSize(new Dimension(C_WIDTH/2, C_HEIGHT));

			unitMeasureGroupComboBox.setCondition(new EditingOrIncludingCondition());

			unitMeasureGroupComboBox.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					getController().changeUnitMeasureGroupProperty((UnitMeasureGroup)((DBComboBox)e.getSource()).getSelectedItem());


				}

			});

			getController().addView(unitMeasureGroupComboBox);

		}

		return unitMeasureGroupComboBox;


	}


	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new UnitMeasureSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((UnitMeasure)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

	}


	public Tag getInternalFrameTitle() {

		return new UnitMeasureTag();

	}


	public DBUnitMeasureConversionTable getUnitMeasureConversionTable(){


		if (unitMeasureConversionTable==null){

			unitMeasureConversionTable = new DBUnitMeasureConversionTable(UnitMeasureController.CONVERSIONS_PROPERTY

					, getController().getModel()

					, getRemoveUnitMeasureConversionAction()

					, getEditUnitMeasureConversionAction()

					, getAddUnitMeasureConversionAction());

			unitMeasureConversionTable.addListEditorChangeListener(new ListEditorAdapter() {


				public void onIncluded(ListEditorEvent event) throws Exception {


					List<UnitMeasureConversion> conversionsOfUnitMeasure = getController().getModel().getConversions();

					List<UnitMeasureConversion> conversionsToSender = new AllwaysDiferentList<UnitMeasureConversion>();

					for (UnitMeasureConversion unitMeasureConversion : conversionsOfUnitMeasure)

						conversionsToSender.add(unitMeasureConversion);

					UnitMeasureConversion newValue = (UnitMeasureConversion) event.getObject();

					UnitMeasureConversionPK unitMeasureConversionPK = new UnitMeasureConversionPK();

					unitMeasureConversionPK.setFromUnitMeasure(newValue.getIdentifier().getFromUnitMeasure());

					unitMeasureConversionPK.setToUnitMeasure(newValue.getIdentifier().getToUnitMeasure());

					UnitMeasureConversion unitMeasureConversion = new UnitMeasureConversion();

					unitMeasureConversion.setIdentifier(unitMeasureConversionPK);

					unitMeasureConversion.setValue(newValue.getValue());

					conversionsToSender.add(unitMeasureConversion);

					getController().changeConversionsProperty(conversionsToSender);


				}


				public void onUpdated(ListEditorEvent event) throws Exception {


					UnitMeasureConversion newValue = (UnitMeasureConversion) event.getObject();

					UnitMeasureConversionPK unitMeasureConversionPK = new UnitMeasureConversionPK();

					unitMeasureConversionPK.setFromUnitMeasure(newValue.getIdentifier().getFromUnitMeasure());

					unitMeasureConversionPK.setToUnitMeasure(newValue.getIdentifier().getToUnitMeasure());

					UnitMeasureConversion newUnitMeasureConversion = new UnitMeasureConversion();

					newUnitMeasureConversion.setIdentifier(unitMeasureConversionPK);

					newUnitMeasureConversion.setValue(newValue.getValue());

					List<UnitMeasureConversion> conversionsOfUnitMeasure = getController().getModel().getConversions();

					List<UnitMeasureConversion> conversionsToSender = new AllwaysDiferentList<UnitMeasureConversion>();

					for (UnitMeasureConversion oldUnitMeasureConversion : conversionsOfUnitMeasure)

						if (oldUnitMeasureConversion.hashCode()==newUnitMeasureConversion.hashCode())

							conversionsToSender.add(newUnitMeasureConversion);

						else

							conversionsToSender.add(oldUnitMeasureConversion);

					getController().changeConversionsProperty(conversionsToSender);


				}


				public void onDeleted(ListEditorEvent event) throws Exception {


					UnitMeasureConversion newValue = (UnitMeasureConversion) event.getObject();

					UnitMeasureConversionPK unitMeasureConversionPK = new UnitMeasureConversionPK();

					unitMeasureConversionPK.setFromUnitMeasure(newValue.getIdentifier().getFromUnitMeasure());

					unitMeasureConversionPK.setToUnitMeasure(newValue.getIdentifier().getToUnitMeasure());

					UnitMeasureConversion newUnitMeasureConversion = new UnitMeasureConversion();

					newUnitMeasureConversion.setIdentifier(unitMeasureConversionPK);

					newUnitMeasureConversion.setValue(newValue.getValue());

					List<UnitMeasureConversion> conversionsOfUnitMeasure = getController().getModel().getConversions();

					List<UnitMeasureConversion> conversionsToRemove = new ArrayList<UnitMeasureConversion>();

					conversionsToRemove.add(newUnitMeasureConversion);

					List<UnitMeasureConversion> conversionsToSender = new AllwaysDiferentList<UnitMeasureConversion>();

					for (UnitMeasureConversion unitMeasureConversion : conversionsOfUnitMeasure)

						conversionsToSender.add(unitMeasureConversion);

					for (UnitMeasureConversion unitMeasureConversion : conversionsOfUnitMeasure) 

						for (UnitMeasureConversion conversionToRemove : conversionsToRemove) 

							if (unitMeasureConversion.hashCode()==conversionToRemove.hashCode())

								conversionsToSender.remove(unitMeasureConversion);

					getController().changeConversionsProperty(conversionsToSender);


				}


			});

			getController().addView(unitMeasureConversionTable);

			getController().addView(unitMeasureConversionTable.getTable());


		}

		return unitMeasureConversionTable;


	}


	public ToolBar getToolBar(){


		if (toolBar == null){

			toolBar = new ToolBar();

			toolBar.add(getAddUnitMeasureConversionAction());

			toolBar.add(getEditUnitMeasureConversionAction());

			toolBar.add(getRemoveUnitMeasureConversionAction());

			toolBar.setFloatable(false);

			toolBar.setBorder(BorderFactory.createEmptyBorder());

			toolBar.setFocusable(false);

		}

		return toolBar;


	}


	public AddAction getAddUnitMeasureConversionAction() {


		if (addUnitMeasureConversionAction==null){

			addUnitMeasureConversionAction = new AddAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING)

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getUnitMeasureConversionTable().include();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(addUnitMeasureConversionAction);

		}

		return addUnitMeasureConversionAction;


	}


	public EditAction getEditUnitMeasureConversionAction() {


		if (editUnitMeasureConversionAction==null){

			editUnitMeasureConversionAction = new EditAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getConversions() != null) && (getController().getModel().getConversions().size() > 0));

					else

						if (e.getPropertyName().equals(UnitMeasureController.CONVERSIONS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getUnitMeasureConversionTable().edit();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(editUnitMeasureConversionAction);

		}

		return editUnitMeasureConversionAction;


	}


	public RemoveAction getRemoveUnitMeasureConversionAction() {


		if (removeUnitMeasureConversionAction==null){

			removeUnitMeasureConversionAction = new RemoveAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getConversions() != null) && (getController().getModel().getConversions().size() > 0));

					else

						if (e.getPropertyName().equals(UnitMeasureController.CONVERSIONS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					if (Messenger.ask(

							new SimplifiedQuestion(

									new QuestionOnBeforeDeleteTag())).equals(

											Question.YES)) {

						List<UnitMeasureConversion> conversionsOfUnitMeasure = getController().getModel().getConversions();

						List<UnitMeasureConversion> conversionsToRemove = new ArrayList<UnitMeasureConversion>();

						for(int i : getUnitMeasureConversionTable().getSelectedRows())

							conversionsToRemove.add(getController().getModel().getConversions().get(i));

						List<UnitMeasureConversion> conversionToSender = new ArrayList<UnitMeasureConversion>();

						for (UnitMeasureConversion unitMeasureConversion : conversionsOfUnitMeasure)

							conversionToSender.add(unitMeasureConversion);

						for (UnitMeasureConversion unitMeasureConversion : conversionsOfUnitMeasure) 

							for (UnitMeasureConversion conversionToRemove : conversionsToRemove) 

								if (unitMeasureConversion.hashCode()==conversionToRemove.hashCode())

									conversionToSender.remove(unitMeasureConversion);

						getController().changeConversionsProperty(conversionToSender);

					}


				}

			};

			getController().addView(removeUnitMeasureConversionAction);

		}

		return removeUnitMeasureConversionAction;


	}


}

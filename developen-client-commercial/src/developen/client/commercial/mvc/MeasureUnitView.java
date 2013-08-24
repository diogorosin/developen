package developen.client.commercial.mvc;

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

import developen.client.commercial.search.MeasureUnitSearch;
import developen.client.commercial.widget.DBMeasureUnitConversionTable;
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
import developen.common.commercial.i18n.AcronymTag;
import developen.common.commercial.i18n.ConversionsTag;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.GroupTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.MeasureUnitTag;
import developen.common.commercial.mvc.MeasureUnit;
import developen.common.commercial.mvc.MeasureUnitConversion;
import developen.common.commercial.mvc.MeasureUnitConversionPK;
import developen.common.commercial.mvc.MeasureUnitGroup;
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

public class MeasureUnitView extends EntryView {


	private static final long serialVersionUID = -5394176398244939089L;

	public static int WIDTH = 500;

	public static int HEIGHT = 500;

	public static int C_WIDTH = 250;

	public static int C_HEIGHT = 24;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBTextField acronymField;

	private DBComboBox groupComboBox;

	private TabbedPane tabbedPane;

	private JPanel conversionsTab;

	protected Search identifierSearch;

	private ToolBar toolBar;

	private AddAction addMeasureUnitConversionAction;

	private EditAction editMeasureUnitConversionAction;

	private RemoveAction removeMeasureUnitConversionAction;

	private DBMeasureUnitConversionTable measureUnitConversionTable;


	public MeasureUnitController getController() {

		return (MeasureUnitController) super.getController();

	}


	public MeasureUnitView(MeasureUnitController controller) {

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
		
		northPanel.add(getGroupComboBox());

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

			conversionsTab.add(getMeasureUnitConversionTable(), BorderLayout.CENTER);

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

			identifierField = new DBIdentifierField(new IdentifierTag(), MeasureUnitController.IDENTIFIER_PROPERTY);

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

			denominationField = new DBTextField(new DenominationTag(), MeasureUnitController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(C_WIDTH, C_HEIGHT));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBTextField getAcronymField() {


		if (acronymField == null){

			acronymField = new DBTextField(new AcronymTag(), MeasureUnitController.ACRONYM_PROPERTY);

			acronymField.addCheckListener(this);

			acronymField.setColumns(6);

			getController().addView(acronymField);

		}

		return acronymField;


	}


	public DBComboBox getGroupComboBox() {


		if (groupComboBox==null){

			try {

				Session session = DPA.getSessionFactory().createSession();

				List<Object> list = session.list(MeasureUnitGroup.class);

				groupComboBox = new DBComboBox(

						new GroupTag()

						, list.toArray()

						, MeasureUnitController.GROUP_PROPERTY);

				session.close();

			} catch (Exception e) {

				Messenger.show(e);

			}

			groupComboBox.setPreferredSize(new Dimension(C_WIDTH/2, C_HEIGHT));

			groupComboBox.setCondition(new EditingOrIncludingCondition());

			groupComboBox.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					getController().changeGroupProperty((MeasureUnitGroup)((DBComboBox)e.getSource()).getSelectedItem());


				}

			});

			getController().addView(groupComboBox);

		}

		return groupComboBox;


	}


	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new MeasureUnitSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((MeasureUnit)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

	}


	public Tag getInternalFrameTitle() {

		return new MeasureUnitTag();

	}


	public DBMeasureUnitConversionTable getMeasureUnitConversionTable(){


		if (measureUnitConversionTable==null){

			measureUnitConversionTable = new DBMeasureUnitConversionTable(MeasureUnitController.CONVERSIONS_PROPERTY

					, getController().getModel()

					, getRemoveMeasureUnitConversionAction()

					, getEditMeasureUnitConversionAction()

					, getAddMeasureUnitConversionAction());

			measureUnitConversionTable.addListEditorChangeListener(new ListEditorAdapter() {


				public void onIncluded(ListEditorEvent event) throws Exception {


					List<MeasureUnitConversion> conversionsOfUnitMeasure = getController().getModel().getConversions();

					List<MeasureUnitConversion> conversionsToSender = new AllwaysDiferentList<MeasureUnitConversion>();

					for (MeasureUnitConversion unitMeasureConversion : conversionsOfUnitMeasure)

						conversionsToSender.add(unitMeasureConversion);

					MeasureUnitConversion newValue = (MeasureUnitConversion) event.getObject();

					MeasureUnitConversionPK unitMeasureConversionPK = new MeasureUnitConversionPK();

					unitMeasureConversionPK.setFrom(newValue.getIdentifier().getFrom());

					unitMeasureConversionPK.setTo(newValue.getIdentifier().getTo());

					MeasureUnitConversion unitMeasureConversion = new MeasureUnitConversion();

					unitMeasureConversion.setIdentifier(unitMeasureConversionPK);

					unitMeasureConversion.setValue(newValue.getValue());

					conversionsToSender.add(unitMeasureConversion);

					getController().changeConversionsProperty(conversionsToSender);


				}


				public void onUpdated(ListEditorEvent event) throws Exception {


					MeasureUnitConversion newValue = (MeasureUnitConversion) event.getObject();

					MeasureUnitConversionPK unitMeasureConversionPK = new MeasureUnitConversionPK();

					unitMeasureConversionPK.setFrom(newValue.getIdentifier().getFrom());

					unitMeasureConversionPK.setTo(newValue.getIdentifier().getTo());

					MeasureUnitConversion newUnitMeasureConversion = new MeasureUnitConversion();

					newUnitMeasureConversion.setIdentifier(unitMeasureConversionPK);

					newUnitMeasureConversion.setValue(newValue.getValue());

					List<MeasureUnitConversion> conversionsOfUnitMeasure = getController().getModel().getConversions();

					List<MeasureUnitConversion> conversionsToSender = new AllwaysDiferentList<MeasureUnitConversion>();

					for (MeasureUnitConversion oldUnitMeasureConversion : conversionsOfUnitMeasure)

						if (oldUnitMeasureConversion.hashCode()==newUnitMeasureConversion.hashCode())

							conversionsToSender.add(newUnitMeasureConversion);

						else

							conversionsToSender.add(oldUnitMeasureConversion);

					getController().changeConversionsProperty(conversionsToSender);


				}


				public void onDeleted(ListEditorEvent event) throws Exception {


					MeasureUnitConversion newValue = (MeasureUnitConversion) event.getObject();

					MeasureUnitConversionPK unitMeasureConversionPK = new MeasureUnitConversionPK();

					unitMeasureConversionPK.setFrom(newValue.getIdentifier().getFrom());

					unitMeasureConversionPK.setTo(newValue.getIdentifier().getTo());

					MeasureUnitConversion newUnitMeasureConversion = new MeasureUnitConversion();

					newUnitMeasureConversion.setIdentifier(unitMeasureConversionPK);

					newUnitMeasureConversion.setValue(newValue.getValue());

					List<MeasureUnitConversion> conversionsOfUnitMeasure = getController().getModel().getConversions();

					List<MeasureUnitConversion> conversionsToRemove = new ArrayList<MeasureUnitConversion>();

					conversionsToRemove.add(newUnitMeasureConversion);

					List<MeasureUnitConversion> conversionsToSender = new AllwaysDiferentList<MeasureUnitConversion>();

					for (MeasureUnitConversion unitMeasureConversion : conversionsOfUnitMeasure)

						conversionsToSender.add(unitMeasureConversion);

					for (MeasureUnitConversion unitMeasureConversion : conversionsOfUnitMeasure) 

						for (MeasureUnitConversion conversionToRemove : conversionsToRemove) 

							if (unitMeasureConversion.hashCode()==conversionToRemove.hashCode())

								conversionsToSender.remove(unitMeasureConversion);

					getController().changeConversionsProperty(conversionsToSender);


				}


			});

			getController().addView(measureUnitConversionTable);

			getController().addView(measureUnitConversionTable.getTable());


		}

		return measureUnitConversionTable;


	}


	public ToolBar getToolBar(){


		if (toolBar == null){

			toolBar = new ToolBar();

			toolBar.add(getAddMeasureUnitConversionAction());

			toolBar.add(getEditMeasureUnitConversionAction());

			toolBar.add(getRemoveMeasureUnitConversionAction());

			toolBar.setFloatable(false);

			toolBar.setBorder(BorderFactory.createEmptyBorder());

			toolBar.setFocusable(false);

		}

		return toolBar;


	}


	public AddAction getAddMeasureUnitConversionAction() {


		if (addMeasureUnitConversionAction==null){

			addMeasureUnitConversionAction = new AddAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING)

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getMeasureUnitConversionTable().include();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(addMeasureUnitConversionAction);

		}

		return addMeasureUnitConversionAction;


	}


	public EditAction getEditMeasureUnitConversionAction() {


		if (editMeasureUnitConversionAction==null){

			editMeasureUnitConversionAction = new EditAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getConversions() != null) && (getController().getModel().getConversions().size() > 0));

					else

						if (e.getPropertyName().equals(MeasureUnitController.CONVERSIONS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getMeasureUnitConversionTable().edit();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(editMeasureUnitConversionAction);

		}

		return editMeasureUnitConversionAction;


	}


	public RemoveAction getRemoveMeasureUnitConversionAction() {


		if (removeMeasureUnitConversionAction==null){

			removeMeasureUnitConversionAction = new RemoveAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getConversions() != null) && (getController().getModel().getConversions().size() > 0));

					else

						if (e.getPropertyName().equals(MeasureUnitController.CONVERSIONS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					if (Messenger.ask(

							new SimplifiedQuestion(

									new QuestionOnBeforeDeleteTag())).equals(

											Question.YES)) {

						List<MeasureUnitConversion> conversionsOfUnitMeasure = getController().getModel().getConversions();

						List<MeasureUnitConversion> conversionsToRemove = new ArrayList<MeasureUnitConversion>();

						for(int i : getMeasureUnitConversionTable().getSelectedRows())

							conversionsToRemove.add(getController().getModel().getConversions().get(i));

						List<MeasureUnitConversion> conversionToSender = new ArrayList<MeasureUnitConversion>();

						for (MeasureUnitConversion unitMeasureConversion : conversionsOfUnitMeasure)

							conversionToSender.add(unitMeasureConversion);

						for (MeasureUnitConversion unitMeasureConversion : conversionsOfUnitMeasure) 

							for (MeasureUnitConversion conversionToRemove : conversionsToRemove) 

								if (unitMeasureConversion.hashCode()==conversionToRemove.hashCode())

									conversionToSender.remove(unitMeasureConversion);

						getController().changeConversionsProperty(conversionToSender);

					}


				}

			};

			getController().addView(removeMeasureUnitConversionAction);

		}

		return removeMeasureUnitConversionAction;


	}


}
package developen.client.commercial.widget;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import developen.client.commercial.factory.CommercialFormatFactory;
import developen.client.commercial.mvc.PisCofinsRuleController;
import developen.client.commercial.mvc.PisCofinsRuleView;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.EditAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.mvc.ListEditorAction;
import developen.client.framework.mvc.ListEditorListener;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBComponent;
import developen.client.framework.widget.DBTable;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.commercial.i18n.CfopTag;
import developen.common.commercial.i18n.FiscalRuleTag;
import developen.common.commercial.mvc.Cfop;
import developen.common.commercial.mvc.PisCofins;
import developen.common.commercial.mvc.PisCofinsRule;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.InternalFrame;
import developen.common.framework.widget.InternalFramePosition;
import developen.common.framework.widget.UneditableTableModel;


public abstract class DBPisCofinsRuleTable extends JComponent implements DBComponent{


	private static final long serialVersionUID = 877593865267407181L;

	public static final int CFOP_COLUMN_INDEX = 0;

	public static final int RULE_COLUMN_INDEX = 1;

	private UneditableTableModel tableModel;

	private DBTable<PisCofinsRule> table;

	private Column cfopColumn;

	private Column ruleColumn;

	private boolean fixedValue;

	private String propertyName;

	private Condition condition;

	private PisCofinsRule model;

	private PisCofinsRuleView view;

	private PisCofinsRuleController controller;

	private PisCofins pisCofins;

	private RemoveAction deleteAction;

	private EditAction editAction;

	private AddAction addAction;


	public DBPisCofinsRuleTable(String propertyName

			, PisCofins pisCofins

			, RemoveAction deleteAction

			, EditAction editAction

			, AddAction addAction){


		setPropertyName(propertyName);

		setPisCofins(pisCofins);

		setRemoveAction(deleteAction);

		setEditAction(editAction);

		setAddAction(addAction);

		model = new PisCofinsRule();

		controller = new PisCofinsRuleController();

		controller.setModel(model);

		view = new PisCofinsRuleView(controller);

		controller.addView(view);

		controller.addView(new View() {

			public void modelPropertyChanged(PropertyChangeEvent evt) {

				if (evt.getPropertyName().equals("ModelState")){

					if (evt.getNewValue().equals(ListEditorState.CANCELED)){

						Container internalFrame;

						internalFrame = getParent();

						while(internalFrame != null && !(internalFrame instanceof InternalFrame))

							internalFrame = internalFrame.getParent();

						((InternalFrame)internalFrame).setVisible(true);

					}

				}

			}

		});

		setLayout(new BorderLayout());

		add(new JScrollPane(getTable()), BorderLayout.CENTER);


	}


	public DBTable<PisCofinsRule> getTable(){


		if (table == null){

			table = new DBTable<PisCofinsRule>(getPropertyName(), getTableModel());

			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			table.getColumnModel().getColumn(CFOP_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.LEFT));

			table.getColumnModel().getColumn(CFOP_COLUMN_INDEX).setMinWidth(100);
			
			table.getColumnModel().getColumn(CFOP_COLUMN_INDEX).setMaxWidth(100);

			table.getColumnModel().getColumn(RULE_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.LEFT));

			table.getActionMap().put(ListEditorAction.DELETE, getRemoveAction());

			table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

					KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), 

					ListEditorAction.DELETE);

			table.getActionMap().put(ListEditorAction.EDIT, getEditAction());

			table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), 

					ListEditorAction.EDIT);

			table.getActionMap().put(ListEditorAction.INCLUDE, getAddAction());

			table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

					KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), 

					ListEditorAction.INCLUDE);

			table.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {

					if (evt.getClickCount() == 2)

						try {

							edit();

						} catch (Exception exception) {

							Messenger.show(exception);

						}

				}

			});


		}

		return table;


	}


	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel(){

				private static final long serialVersionUID = -4664169785828350283L;

				public Object getValueAt(int x, int y){

					Vector<?> row = (Vector<?>) this.dataVector.elementAt(x);

					if (y==CFOP_COLUMN_INDEX)

						return CommercialFormatFactory.formatCFOP(((Cfop)row.elementAt(CFOP_COLUMN_INDEX)).getIdentifier());

					else

						return row.elementAt(y);

				}

			};

			tableModel.addColumn(getCfopColumn());

			tableModel.addColumn(getRuleColumn());

		}

		return tableModel;


	}


	public Column getCfopColumn(){


		if (cfopColumn == null)

			cfopColumn = new Column(new CfopTag(), CFOP_COLUMN_INDEX);

		return cfopColumn;


	}


	public Column getRuleColumn(){


		if (ruleColumn == null)

			ruleColumn = new Column(new FiscalRuleTag(), RULE_COLUMN_INDEX);

		return ruleColumn;


	}


	public JDesktopPane getDesktopPane() {


		Container p;

		p = getParent();

		while(p != null && !(p instanceof JDesktopPane))

			p = p.getParent();

		return (JDesktopPane) p;


	}


	@SuppressWarnings("unchecked")
	public void modelPropertyChanged(PropertyChangeEvent event) {


		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName())){

			List<Object> oldList = (List<Object>) event.getNewValue();

			controller.setData(oldList);

		} else {

			if (event.getPropertyName().equals("ModelState")){

				if (event.getNewValue().equals(EntryState.CANCELED)

						|| event.getNewValue().equals(EntryState.CLEAN)

						|| event.getNewValue().equals(EntryState.REFRESHED)){

					if (view.isVisible()){

						view.setVisible(false);

						getDesktopPane().remove(view);

						if (event.getNewValue().equals(EntryState.CANCELED))

							view.dispose();

					}

				} 

			} 

		}


	}


	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}


	public String getPropertyName() {

		return propertyName;

	}


	public Condition getCondition() {


		if (condition==null)

			condition = new EditingOrIncludingCondition();

		return condition;


	}


	public void setCondition(Condition condition) {

		this.condition = condition;

	}


	public PisCofins getPisCofins() {

		return pisCofins;

	}


	public void setPisCofins(PisCofins pisCofins) {

		this.pisCofins = pisCofins;

	}


	public void addListEditorChangeListener(ListEditorListener listener){

		controller.addEditorListener(listener);

	}


	public void include() throws Exception{


		if (!view.isVisible()){

			getDesktopPane().add(view);

			view.setLocation(InternalFramePosition.CENTER);

		}

		model.getIdentifier().setPisCofins(null);

		controller.clear();

		controller.changePisCofinsProperty(getPisCofins());

		view.setVisible(true);


	}


	public void edit() throws Exception{


		if (getTable().getSelectedRow()==-1)

			return;

		if (!view.isVisible()){

			getDesktopPane().add(view);

			view.setLocation(InternalFramePosition.CENTER);

		}

		model.getIdentifier().setPisCofins(null);

		controller.clear();

		controller.changePisCofinsProperty(getPisCofins());

		controller.changeCfopProperty(getRowAt(getTable().getSelectedRow()).getIdentifier().getCfop());

		controller.changeRuleProperty(getRowAt(getTable().getSelectedRow()).getIdentifier().getRule());

		view.setVisible(true);


	}


	public int[] getSelectedRows(){

		return getTable().getSelectedRows(); 

	}


	public RemoveAction getRemoveAction() {

		return deleteAction;

	}


	public void setRemoveAction(RemoveAction deleteAction) {

		this.deleteAction = deleteAction;

	}


	public EditAction getEditAction() {

		return editAction;

	}


	public void setEditAction(EditAction editAction) {

		this.editAction = editAction;

	}


	public AddAction getAddAction() {

		return addAction;

	}


	public void setAddAction(AddAction addAction) {

		this.addAction = addAction;

	}


	public boolean isFixedValue() {

		return fixedValue;

	}


	public void setFixedValue(boolean fixedValue) {

		this.fixedValue = fixedValue;

	}


	public abstract PisCofinsRule getRowAt(int index);


}
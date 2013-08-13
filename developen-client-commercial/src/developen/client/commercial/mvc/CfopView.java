package developen.client.commercial.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import developen.client.commercial.search.CfopSearch;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextArea;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.CfopTag;
import developen.common.commercial.i18n.NoteTag;
import developen.common.commercial.mvc.Cfop;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.subject.i18n.DenominationTag;
import developen.common.subject.i18n.IdentifierTag;

public class CfopView extends EntryView {


	private static final long serialVersionUID = -5394176398244939089L;

	public static int WIDTH = 750;

	public static int HEIGHT = 350;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBTextArea noteField;

	private JPanel noteTab;

	private TabbedPane tabbedPane;

	protected Search identifierSearch;


	public CfopController getController() {

		return (CfopController) super.getController();

	}


	public CfopView(CfopController controller) {

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

			tabbedPane.add(getNoteTab());

			tabbedPane.setFocusable(false);

		}

		return tabbedPane;


	}


	public JPanel getNoteTab(){


		if (noteTab == null){

			noteTab = new JPanel(new BorderLayout());

			noteTab.setName(new NoteTag().toString());

			noteTab.add(new JScrollPane(getNoteField()), BorderLayout.CENTER);

		}

		return noteTab;


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

				if (event.getCheckable() == getNoteField())

					getController().changeNoteProperty(getNoteField().getText());


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), CfopController.IDENTIFIER_PROPERTY);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setPreferredSize(new Dimension(75,24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), CfopController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(500, 24));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBTextArea getNoteField() {


		if (noteField == null){

			noteField = new DBTextArea(new NoteTag(), CfopController.NOTE_PROPERTY);

			noteField.addCheckListener(this);
			
			noteField.setLineWrap(true);
			
			noteField.setAutoscrolls(true);
			
			getController().addView(noteField);

		}

		return noteField;


	}


	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new CfopSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((Cfop)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}


	public Tag getInternalFrameTitle() {

		return new CfopTag();

	}


}

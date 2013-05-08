package developen.client.subject.mvc;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import developen.client.framework.mvc.SearchController;
import developen.client.subject.factory.SubjectFormatFactory;
import developen.common.framework.mvc.SearchState;
import developen.common.framework.utils.Tag;
import developen.common.subject.i18n.CompanyTag;
import developen.common.subject.mvc.Company;

public class CompanySearchView extends SubjectSearchView {


	private static final long serialVersionUID = -1400375353828363381L;


	public CompanySearchView(SearchController controller) {


		super(controller);

		setSize(new Dimension(600,600));


	}

	@SuppressWarnings("unchecked")
	public void modelPropertyChanged(PropertyChangeEvent evt) {


		if (evt.getPropertyName().equals("ModelState")){

			SearchState newValue = (SearchState) evt.getNewValue();

			if ((newValue.equals(SearchState.CANCELED)) 

					|| (newValue.equals(SearchState.SELECTED))){

				getScrollPane().getVerticalScrollBar().setValue(0);

				setSearchFieldVisible(false);

				setVisible(false);

				getDesktopPane().remove(this);

				dispose();

			} else 

				if (newValue.equals(SearchState.BROWSING))

					if (getResultComponent() != null){

						SwingUtilities.invokeLater(new Runnable() {

							public void run() {

								getResultComponent().requestFocus();

							}

						});

					}

		} else 

			if (evt.getPropertyName().equals(SearchController.SEARCH_PROPERTY)){

				getSearchField().setText((String) evt.getNewValue());

				setSearchFieldVisible(false);

			} else {		

				if (evt.getPropertyName() == SearchController.RESULTED_ROWS_PROPERTY){

					final int[] selectedRows = getResultComponent().getSelectedRows();

					getResultComponent().clear();

					List<Company> list = (List<Company>) evt.getNewValue();

					if (evt.getNewValue() != null && list.size() > 0) {

						DefaultTableModel model = (DefaultTableModel) getResultComponent().getModel();

						for (Company c : list)

							model.addRow(new Object[]{

									c.getIdentifier(),

									SubjectFormatFactory.formatCNPJ(c.getCnpj()),

									c.getDenomination()

							});

						if (selectedRows.length > 0)

							for (int sel : selectedRows)

								if (sel <= getResultComponent().getRowCount())

									getResultComponent().addRowSelectionInterval(sel, sel);

						if (getResultComponent().getSelectedRow() == -1 

								&& getResultComponent().getModel().getRowCount() > 0)

							getResultComponent().setRowSelectionInterval(0, 0);

					}

				}

			}


	}


	public Tag getInternalFrameTitle() {

		return new CompanyTag(); 

	}


}
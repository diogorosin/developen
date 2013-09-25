package developen.client.commercial.mvc;

import java.awt.Dimension;

import developen.client.commercial.search.OutputCfopSearch;
import developen.client.commercial.search.OutputMacroSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBSearchField;
import developen.common.commercial.i18n.Cfop1Tag;
import developen.common.commercial.i18n.Cfop2Tag;
import developen.common.commercial.i18n.Cfop3Tag;
import developen.common.commercial.i18n.Cfop4Tag;
import developen.common.commercial.i18n.CfopsTag;
import developen.common.commercial.i18n.ExteriorTag;
import developen.common.commercial.i18n.InterstateTag;
import developen.common.commercial.i18n.OutputMacroTag;
import developen.common.commercial.i18n.StateTag;
import developen.common.commercial.mvc.OutputCfop;
import developen.common.commercial.mvc.OutputMacro;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;

public class OutputMacroView extends MacroView {


	private static final long serialVersionUID = -6224324717586281048L;

	private DBSearchField cfopState1Field;

	private DBSearchField cfopInterstate1Field;

	private DBSearchField cfopExterior1Field;

	private DBSearchField cfopState2Field;

	private DBSearchField cfopInterstate2Field;

	private DBSearchField cfopExterior2Field;

	private DBSearchField cfopState3Field;

	private DBSearchField cfopInterstate3Field;

	private DBSearchField cfopExterior3Field;

	private DBSearchField cfopState4Field;

	private DBSearchField cfopInterstate4Field;

	private DBSearchField cfopExterior4Field;
	
	private DBRowPanel fiscalTab;


	public OutputMacroView(OutputMacroController controller) {

		super(controller);

	}


	public OutputMacroController getController(){

		return (OutputMacroController) super.getController();		

	}


	public Tag getInternalFrameTitle() {

		return new OutputMacroTag();

	}

	
	public ExtendedPanel getCenterPanel(){

		
		ExtendedPanel l = super.getCenterPanel();

		l.add(getTabbedPane());

		return l;
		

	}

	
	public TabbedPane getTabbedPane(){

		
		TabbedPane p = super.getTabbedPane();

//		if (s == null){
//			
//			s = new JScrollPane(getFiscalTab());
//			
//			s.setName(new CfopsTag().toString());
//			
//			s.setAutoscrolls(true);
//
//			p.add(s);
//			
//		}
		
		p.add(getFiscalTab());
		
		return p;
		

	}

	
	public DBRowPanel getFiscalTab(){


		if (fiscalTab==null){

			fiscalTab = new DBRowPanel(100);

			fiscalTab.addSeparator(new Cfop1Tag());
			
			fiscalTab.add(getCfopState1Field());
			
			fiscalTab.add(getCfopInterstate1Field());
			
			fiscalTab.add(getCfopExterior1Field());

			fiscalTab.addSeparator(new Cfop2Tag());
			
			fiscalTab.add(getCfopState2Field());
			
			fiscalTab.add(getCfopInterstate2Field());
			
			fiscalTab.add(getCfopExterior2Field());
			
			fiscalTab.addSeparator(new Cfop3Tag());
			
			fiscalTab.add(getCfopState3Field());
			
			fiscalTab.add(getCfopInterstate3Field());
			
			fiscalTab.add(getCfopExterior3Field());
			
			fiscalTab.addSeparator(new Cfop4Tag());
			
			fiscalTab.add(getCfopState4Field());
			
			fiscalTab.add(getCfopInterstate4Field());
			
			fiscalTab.add(getCfopExterior4Field());

			fiscalTab.setName(new CfopsTag().toString());
			
		}

		return fiscalTab;


	}

	
	public void onCheck(CheckEvent event) throws Exception {


		super.onCheck(event);

		if (event.getCheckable() == getCfopState1Field()){

			try{

				getController().changeCfopState1Property((OutputCfop) getCfopState1Field().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getCfopState1Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		} else {

			if (event.getCheckable() == getCfopInterstate1Field()){

				try{

					getController().changeCfopInterstate1Property((OutputCfop) getCfopInterstate1Field().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getCfopInterstate1Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

				}

			} else {

				if (event.getCheckable() == getCfopExterior1Field()){

					try{

						getController().changeCfopExterior1Property((OutputCfop) getCfopExterior1Field().getSearch().findBy());

					} catch (ManyRecordsFoundException e) {

						getCfopExterior1Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

					}

				} else {

					if (event.getCheckable() == getCfopState2Field()){

						try{

							getController().changeCfopState2Property((OutputCfop) getCfopState2Field().getSearch().findBy());

						} catch (ManyRecordsFoundException e) {

							getCfopState2Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

						}

					} else {

						if (event.getCheckable() == getCfopInterstate2Field()){

							try{

								getController().changeCfopInterstate2Property((OutputCfop) getCfopInterstate2Field().getSearch().findBy());

							} catch (ManyRecordsFoundException e) {

								getCfopInterstate2Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

							}

						} else {

							if (event.getCheckable() == getCfopExterior2Field()){

								try{

									getController().changeCfopExterior2Property((OutputCfop) getCfopExterior2Field().getSearch().findBy());

								} catch (ManyRecordsFoundException e) {

									getCfopExterior2Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

								}

							} else {

								if (event.getCheckable() == getCfopState3Field()){

									try{

										getController().changeCfopState3Property((OutputCfop) getCfopState3Field().getSearch().findBy());

									} catch (ManyRecordsFoundException e) {

										getCfopState3Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

									}

								} else {

									if (event.getCheckable() == getCfopInterstate3Field()){

										try{

											getController().changeCfopInterstate3Property((OutputCfop) getCfopInterstate3Field().getSearch().findBy());

										} catch (ManyRecordsFoundException e) {

											getCfopInterstate3Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

										}

									} else {

										if (event.getCheckable() == getCfopExterior3Field()){

											try{

												getController().changeCfopExterior3Property((OutputCfop) getCfopExterior3Field().getSearch().findBy());

											} catch (ManyRecordsFoundException e) {

												getCfopExterior3Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

											}

										} else {

											if (event.getCheckable() == getCfopState4Field()){

												try{

													getController().changeCfopState4Property((OutputCfop) getCfopState4Field().getSearch().findBy());

												} catch (ManyRecordsFoundException e) {

													getCfopState4Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

												}

											} else {

												if (event.getCheckable() == getCfopInterstate4Field()){

													try{

														getController().changeCfopInterstate4Property((OutputCfop) getCfopInterstate4Field().getSearch().findBy());

													} catch (ManyRecordsFoundException e) {

														getCfopInterstate4Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

													}

												} else {

													if (event.getCheckable() == getCfopExterior4Field()){

														try{

															getController().changeCfopExterior4Property((OutputCfop) getCfopExterior4Field().getSearch().findBy());

														} catch (ManyRecordsFoundException e) {

															getCfopExterior4Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

														}

													}													
													
												}
												
											}
											
										}
									
									}
									
								}

							}

						}

					}					

				}			

			}			

		}


	}
	

	public Search getSearch(){


		if (identifierSearch == null){

			identifierSearch = new OutputMacroSearch(true);

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((OutputMacro)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}


	public DBSearchField getCfopState1Field() {


		if (cfopState1Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopState1Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopState1Field = new DBSearchField(new StateTag(), OutputMacroController.CFOP_STATE_1_PROPERTY);

			cfopState1Field.addCheckListener(this);

			cfopState1Field.setSearch(cfopSearch);

			cfopState1Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopState1Field);

		}

		return cfopState1Field;


	}


	public DBSearchField getCfopInterstate1Field() {


		if (cfopInterstate1Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopInterstate1Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopInterstate1Field = new DBSearchField(new InterstateTag(), OutputMacroController.CFOP_INTERSTATE_1_PROPERTY);

			cfopInterstate1Field.addCheckListener(this);

			cfopInterstate1Field.setSearch(cfopSearch);

			cfopInterstate1Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopInterstate1Field);

		}

		return cfopInterstate1Field;


	}


	public DBSearchField getCfopExterior1Field() {


		if (cfopExterior1Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopExterior1Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopExterior1Field = new DBSearchField(new ExteriorTag(), OutputMacroController.CFOP_EXTERIOR_1_PROPERTY);

			cfopExterior1Field.addCheckListener(this);

			cfopExterior1Field.setSearch(cfopSearch);

			cfopExterior1Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopExterior1Field);

		}

		return cfopExterior1Field;


	}


	public DBSearchField getCfopState2Field() {


		if (cfopState2Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopState2Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopState2Field = new DBSearchField(new StateTag(), OutputMacroController.CFOP_STATE_2_PROPERTY);

			cfopState2Field.addCheckListener(this);

			cfopState2Field.setSearch(cfopSearch);

			cfopState2Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopState2Field);

		}

		return cfopState2Field;


	}


	public DBSearchField getCfopInterstate2Field() {


		if (cfopInterstate2Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopInterstate2Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopInterstate2Field = new DBSearchField(new InterstateTag(), OutputMacroController.CFOP_INTERSTATE_2_PROPERTY);

			cfopInterstate2Field.addCheckListener(this);

			cfopInterstate2Field.setSearch(cfopSearch);

			cfopInterstate2Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopInterstate2Field);

		}

		return cfopInterstate2Field;


	}


	public DBSearchField getCfopExterior2Field() {


		if (cfopExterior2Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopExterior2Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopExterior2Field = new DBSearchField(new ExteriorTag(), OutputMacroController.CFOP_EXTERIOR_2_PROPERTY);

			cfopExterior2Field.addCheckListener(this);

			cfopExterior2Field.setSearch(cfopSearch);

			cfopExterior2Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopExterior2Field);

		}

		return cfopExterior2Field;


	}


	public DBSearchField getCfopState3Field() {


		if (cfopState3Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopState3Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopState3Field = new DBSearchField(new StateTag(), OutputMacroController.CFOP_STATE_3_PROPERTY);

			cfopState3Field.addCheckListener(this);

			cfopState3Field.setSearch(cfopSearch);

			cfopState3Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopState3Field);

		}

		return cfopState3Field;


	}


	public DBSearchField getCfopInterstate3Field() {


		if (cfopInterstate3Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopInterstate3Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopInterstate3Field = new DBSearchField(new InterstateTag(), OutputMacroController.CFOP_INTERSTATE_3_PROPERTY);

			cfopInterstate3Field.addCheckListener(this);

			cfopInterstate3Field.setSearch(cfopSearch);

			cfopInterstate3Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopInterstate3Field);

		}

		return cfopInterstate3Field;


	}


	public DBSearchField getCfopExterior3Field() {


		if (cfopExterior3Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopExterior3Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopExterior3Field = new DBSearchField(new ExteriorTag(), OutputMacroController.CFOP_EXTERIOR_3_PROPERTY);

			cfopExterior3Field.addCheckListener(this);

			cfopExterior3Field.setSearch(cfopSearch);

			cfopExterior3Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopExterior3Field);

		}

		return cfopExterior3Field;


	}


	public DBSearchField getCfopState4Field() {


		if (cfopState4Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopState4Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopState4Field = new DBSearchField(new StateTag(), OutputMacroController.CFOP_STATE_4_PROPERTY);

			cfopState4Field.addCheckListener(this);

			cfopState4Field.setSearch(cfopSearch);

			cfopState4Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopState4Field);

		}

		return cfopState4Field;


	}


	public DBSearchField getCfopInterstate4Field() {


		if (cfopInterstate4Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopInterstate4Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopInterstate4Field = new DBSearchField(new InterstateTag(), OutputMacroController.CFOP_INTERSTATE_4_PROPERTY);

			cfopInterstate4Field.addCheckListener(this);

			cfopInterstate4Field.setSearch(cfopSearch);

			cfopInterstate4Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopInterstate4Field);

		}

		return cfopInterstate4Field;


	}


	public DBSearchField getCfopExterior4Field() {


		if (cfopExterior4Field == null){

			OutputCfopSearch cfopSearch = new OutputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopExterior4Property((OutputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopExterior4Field = new DBSearchField(new ExteriorTag(), OutputMacroController.CFOP_EXTERIOR_4_PROPERTY);

			cfopExterior4Field.addCheckListener(this);

			cfopExterior4Field.setSearch(cfopSearch);

			cfopExterior4Field.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(cfopExterior4Field);

		}

		return cfopExterior4Field;


	}


}
package developen.client.commercial.mvc;

import java.awt.Dimension;

import developen.client.commercial.search.InputCfopSearch;
import developen.client.commercial.search.InputMacroSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.Cfop1Tag;
import developen.common.commercial.i18n.Cfop2Tag;
import developen.common.commercial.i18n.Cfop3Tag;
import developen.common.commercial.i18n.Cfop4Tag;
import developen.common.commercial.i18n.CfopsTag;
import developen.common.commercial.i18n.ExteriorTag;
import developen.common.commercial.i18n.InputMacroTag;
import developen.common.commercial.i18n.InterstateTag;
import developen.common.commercial.i18n.StateTag;
import developen.common.commercial.mvc.InputCfop;
import developen.common.commercial.mvc.InputMacro;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.TabbedPane;

public class InputMacroView extends MacroView {


	private static final long serialVersionUID = -6224324717586281048L;

	private DBTextField cfopState1Field;

	private DBTextField cfopInterstate1Field;

	private DBTextField cfopExterior1Field;

	private DBTextField cfopState2Field;

	private DBTextField cfopInterstate2Field;

	private DBTextField cfopExterior2Field;

	private DBTextField cfopState3Field;

	private DBTextField cfopInterstate3Field;

	private DBTextField cfopExterior3Field;

	private DBTextField cfopState4Field;

	private DBTextField cfopInterstate4Field;

	private DBTextField cfopExterior4Field;

	private DBRowPanel fiscalTab;


	public InputMacroView(InputMacroController controller) {

		super(controller);

	}


	public InputMacroController getController(){

		return (InputMacroController) super.getController();		

	}


	public Tag getInternalFrameTitle() {

		return new InputMacroTag();

	}


	public Search getIdentifierSearch(){


		if (identifierSearch == null){

			identifierSearch = new InputMacroSearch(true);

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((InputMacro)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}

	
	public TabbedPane getTabbedPane(){

		
		TabbedPane p = super.getTabbedPane();
		
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

				getController().changeCfopState1Property((InputCfop) getCfopState1Field().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getCfopState1Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		} else {

			if (event.getCheckable() == getCfopInterstate1Field()){

				try{

					getController().changeCfopInterstate1Property((InputCfop) getCfopInterstate1Field().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getCfopInterstate1Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

				}

			} else {

				if (event.getCheckable() == getCfopExterior1Field()){

					try{

						getController().changeCfopExterior1Property((InputCfop) getCfopExterior1Field().getSearch().findBy());

					} catch (ManyRecordsFoundException e) {

						getCfopExterior1Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

					}

				} else {

					if (event.getCheckable() == getCfopState2Field()){

						try{

							getController().changeCfopState2Property((InputCfop) getCfopState2Field().getSearch().findBy());

						} catch (ManyRecordsFoundException e) {

							getCfopState2Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

						}

					} else {

						if (event.getCheckable() == getCfopInterstate2Field()){

							try{

								getController().changeCfopInterstate2Property((InputCfop) getCfopInterstate2Field().getSearch().findBy());

							} catch (ManyRecordsFoundException e) {

								getCfopInterstate2Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

							}

						} else {

							if (event.getCheckable() == getCfopExterior2Field()){

								try{

									getController().changeCfopExterior2Property((InputCfop) getCfopExterior2Field().getSearch().findBy());

								} catch (ManyRecordsFoundException e) {

									getCfopExterior2Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

								}

							} else {

								if (event.getCheckable() == getCfopState3Field()){

									try{

										getController().changeCfopState3Property((InputCfop) getCfopState3Field().getSearch().findBy());

									} catch (ManyRecordsFoundException e) {

										getCfopState3Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

									}

								} else {

									if (event.getCheckable() == getCfopInterstate3Field()){

										try{

											getController().changeCfopInterstate3Property((InputCfop) getCfopInterstate3Field().getSearch().findBy());

										} catch (ManyRecordsFoundException e) {

											getCfopInterstate3Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

										}

									} else {

										if (event.getCheckable() == getCfopExterior3Field()){

											try{

												getController().changeCfopExterior3Property((InputCfop) getCfopExterior3Field().getSearch().findBy());

											} catch (ManyRecordsFoundException e) {

												getCfopExterior3Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

											}

										} else {

											if (event.getCheckable() == getCfopState4Field()){

												try{

													getController().changeCfopState4Property((InputCfop) getCfopState4Field().getSearch().findBy());

												} catch (ManyRecordsFoundException e) {

													getCfopState4Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

												}

											} else {

												if (event.getCheckable() == getCfopInterstate4Field()){

													try{

														getController().changeCfopInterstate4Property((InputCfop) getCfopInterstate4Field().getSearch().findBy());

													} catch (ManyRecordsFoundException e) {

														getCfopInterstate4Field().getSearch().openSearchViewWithoutReset(getDesktopPane());

													}

												} else {

													if (event.getCheckable() == getCfopExterior4Field()){

														try{

															getController().changeCfopExterior4Property((InputCfop) getCfopExterior4Field().getSearch().findBy());

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


	public DBTextField getCfopState1Field() {


		if (cfopState1Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopState1Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopState1Field = new DBTextField(new StateTag(), InputMacroController.CFOP_STATE_1_PROPERTY);

			cfopState1Field.addCheckListener(this);

			cfopState1Field.setSearch(cfopSearch);

			cfopState1Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopState1Field);

		}

		return cfopState1Field;


	}


	public DBTextField getCfopInterstate1Field() {


		if (cfopInterstate1Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopInterstate1Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopInterstate1Field = new DBTextField(new InterstateTag(), InputMacroController.CFOP_INTERSTATE_1_PROPERTY);

			cfopInterstate1Field.addCheckListener(this);

			cfopInterstate1Field.setSearch(cfopSearch);

			cfopInterstate1Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopInterstate1Field);

		}

		return cfopInterstate1Field;


	}


	public DBTextField getCfopExterior1Field() {


		if (cfopExterior1Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopExterior1Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopExterior1Field = new DBTextField(new ExteriorTag(), InputMacroController.CFOP_EXTERIOR_1_PROPERTY);

			cfopExterior1Field.addCheckListener(this);

			cfopExterior1Field.setSearch(cfopSearch);

			cfopExterior1Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopExterior1Field);

		}

		return cfopExterior1Field;


	}


	public DBTextField getCfopState2Field() {


		if (cfopState2Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopState2Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopState2Field = new DBTextField(new StateTag(), InputMacroController.CFOP_STATE_2_PROPERTY);

			cfopState2Field.addCheckListener(this);

			cfopState2Field.setSearch(cfopSearch);

			cfopState2Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopState2Field);

		}

		return cfopState2Field;


	}


	public DBTextField getCfopInterstate2Field() {


		if (cfopInterstate2Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopInterstate2Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopInterstate2Field = new DBTextField(new InterstateTag(), InputMacroController.CFOP_INTERSTATE_2_PROPERTY);

			cfopInterstate2Field.addCheckListener(this);

			cfopInterstate2Field.setSearch(cfopSearch);

			cfopInterstate2Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopInterstate2Field);

		}

		return cfopInterstate2Field;


	}


	public DBTextField getCfopExterior2Field() {


		if (cfopExterior2Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopExterior2Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopExterior2Field = new DBTextField(new ExteriorTag(), InputMacroController.CFOP_EXTERIOR_2_PROPERTY);

			cfopExterior2Field.addCheckListener(this);

			cfopExterior2Field.setSearch(cfopSearch);

			cfopExterior2Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopExterior2Field);

		}

		return cfopExterior2Field;


	}


	public DBTextField getCfopState3Field() {


		if (cfopState3Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopState3Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopState3Field = new DBTextField(new StateTag(), InputMacroController.CFOP_STATE_3_PROPERTY);

			cfopState3Field.addCheckListener(this);

			cfopState3Field.setSearch(cfopSearch);

			cfopState3Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopState3Field);

		}

		return cfopState3Field;


	}


	public DBTextField getCfopInterstate3Field() {


		if (cfopInterstate3Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopInterstate3Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopInterstate3Field = new DBTextField(new InterstateTag(), InputMacroController.CFOP_INTERSTATE_3_PROPERTY);

			cfopInterstate3Field.addCheckListener(this);

			cfopInterstate3Field.setSearch(cfopSearch);

			cfopInterstate3Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopInterstate3Field);

		}

		return cfopInterstate3Field;


	}


	public DBTextField getCfopExterior3Field() {


		if (cfopExterior3Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopExterior3Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopExterior3Field = new DBTextField(new ExteriorTag(), InputMacroController.CFOP_EXTERIOR_3_PROPERTY);

			cfopExterior3Field.addCheckListener(this);

			cfopExterior3Field.setSearch(cfopSearch);

			cfopExterior3Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopExterior3Field);

		}

		return cfopExterior3Field;


	}


	public DBTextField getCfopState4Field() {


		if (cfopState4Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopState4Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopState4Field = new DBTextField(new StateTag(), InputMacroController.CFOP_STATE_4_PROPERTY);

			cfopState4Field.addCheckListener(this);

			cfopState4Field.setSearch(cfopSearch);

			cfopState4Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopState4Field);

		}

		return cfopState4Field;


	}


	public DBTextField getCfopInterstate4Field() {


		if (cfopInterstate4Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopInterstate4Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopInterstate4Field = new DBTextField(new InterstateTag(), InputMacroController.CFOP_INTERSTATE_4_PROPERTY);

			cfopInterstate4Field.addCheckListener(this);

			cfopInterstate4Field.setSearch(cfopSearch);

			cfopInterstate4Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopInterstate4Field);

		}

		return cfopInterstate4Field;


	}


	public DBTextField getCfopExterior4Field() {


		if (cfopExterior4Field == null){

			InputCfopSearch cfopSearch = new InputCfopSearch();

			cfopSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCfopExterior4Property((InputCfop) event.getSelectedRows().get(0));

				}

			});

			cfopExterior4Field = new DBTextField(new ExteriorTag(), InputMacroController.CFOP_EXTERIOR_4_PROPERTY);

			cfopExterior4Field.addCheckListener(this);

			cfopExterior4Field.setSearch(cfopSearch);

			cfopExterior4Field.setPreferredSize(new Dimension(400,24));

			getController().addView(cfopExterior4Field);

		}

		return cfopExterior4Field;


	}


}
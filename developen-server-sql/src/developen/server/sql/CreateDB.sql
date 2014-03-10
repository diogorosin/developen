/* EXCLUI VIEWS */
\i script/DropViews.sql;

/* EXCLUI TABELAS */
\i script/DropTables.sql;

/* EXCLUI SEQUENCIAS */
\i script/DropSequences.sql;

/* CRIA SEQUENCIAS */
\i script/CreateSequences.sql;

/* CRIA TABELAS */
\i script/CreateTables.sql;

/* POPULA DADOS */ 
\i data/InsertCnae.sql;
\i data/InsertOrganization.sql;
\i data/InsertIdiom.sql;
\i data/InsertCountry.sql;
\i data/InsertState.sql;
\i data/InsertCity.sql;
\i data/InsertSystemAction.sql;
\i data/InsertSystemPerson.sql;
\i data/InsertSystemCompany.sql;
\i data/InsertMeasureUnitGroup.sql;
\i data/InsertMeasureUnit.sql;
\i data/InsertProgenyType.sql;
\i data/InsertCfop.sql;
\i data/InsertIcmsCst.sql;
\i data/InsertIcmsCsosn.sql;
\i data/InsertIpiCst.sql;
\i data/InsertRule.sql;
\i data/InsertPisCst.sql;
\i data/InsertCofinsCst.sql;
\i data/InsertFiscalDocumentType.sql; 

/* CRIA CONSTRAINS */
\i script/CreateConstrains.sql

/* CRIA INDICES */
\i script/CreateIndexes.sql

/* CRIA VIEW */
\i script/CreateViews.sql

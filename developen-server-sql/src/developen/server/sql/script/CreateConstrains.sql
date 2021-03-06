--SUBJECT--

ALTER TABLE "Rule" ADD CONSTRAINT "RulePK" PRIMARY KEY("identifier");

ALTER TABLE "Cnae" ADD CONSTRAINT "CnaePK" PRIMARY KEY("identifier");

ALTER TABLE "Idiom" ADD CONSTRAINT "IdiomPK" PRIMARY KEY("identifier");

ALTER TABLE "Country" ADD CONSTRAINT "CountryPK" PRIMARY KEY("identifier");

ALTER TABLE "State" ADD CONSTRAINT "StatePK" PRIMARY KEY("identifier");
ALTER TABLE "State" ADD CONSTRAINT "StateCountryFK" FOREIGN KEY("country") REFERENCES "Country"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "City" ADD CONSTRAINT "CityPK" PRIMARY KEY("identifier");
ALTER TABLE "City" ADD CONSTRAINT "CityStateFK" FOREIGN KEY("state") REFERENCES "State"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Organization" ADD CONSTRAINT "OrganizationPK" PRIMARY KEY("identifier");

ALTER TABLE "Subject" ADD CONSTRAINT "SubjectPK" PRIMARY KEY("identifier");

ALTER TABLE "Person" ADD CONSTRAINT "PersonPK" PRIMARY KEY("subject");
ALTER TABLE "Person" ADD CONSTRAINT "PersonSubjectFK" FOREIGN KEY("subject") REFERENCES "Subject"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "Company" ADD CONSTRAINT "CompanyPK" PRIMARY KEY("subject");
ALTER TABLE "Company" ADD CONSTRAINT "CompanySubjectFK" FOREIGN KEY("subject") REFERENCES "Subject"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "Company" ADD CONSTRAINT "CompanyCnaeFK" FOREIGN KEY("cnae") REFERENCES "Cnae"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Rg" ADD CONSTRAINT "RgPK" PRIMARY KEY("identifier");
ALTER TABLE "Rg" ADD CONSTRAINT "RgPersonFK" FOREIGN KEY("identifier") REFERENCES "Person"("subject") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "Rg" ADD CONSTRAINT "RgOrganizationFK" FOREIGN KEY("organization") REFERENCES "Organization"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Rg" ADD CONSTRAINT "RgStateFK" FOREIGN KEY("state") REFERENCES "State"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Ie" ADD CONSTRAINT "IePK" PRIMARY KEY("identifier");
ALTER TABLE "Ie" ADD CONSTRAINT "IeCompanyFK" FOREIGN KEY("identifier") REFERENCES "Company"("subject") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "Cpf" ADD CONSTRAINT "CpfPK" PRIMARY KEY("identifier");
ALTER TABLE "Cpf" ADD CONSTRAINT "CpfPersonFK" FOREIGN KEY("identifier") REFERENCES "Person"("subject") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "Cnpj" ADD CONSTRAINT "CnpjPK" PRIMARY KEY("identifier");
ALTER TABLE "Cnpj" ADD CONSTRAINT "CnpjCompanyFK" FOREIGN KEY("identifier") REFERENCES "Company"("subject") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "Address" ADD CONSTRAINT "AddressPK" PRIMARY KEY("identifier");
ALTER TABLE "Address" ADD CONSTRAINT "AddressSubjectFK" FOREIGN KEY("identifier") REFERENCES "Subject"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "Address" ADD CONSTRAINT "AddressCityFK" FOREIGN KEY("city") REFERENCES "City"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "SystemCompany" ADD CONSTRAINT "SystemCompanyPK" PRIMARY KEY("company");
ALTER TABLE "SystemCompany" ADD CONSTRAINT "SystemCompanyCompanyFK" FOREIGN KEY("company") REFERENCES "Company"("subject") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "SystemPerson" ADD CONSTRAINT "SystemPersonPK" PRIMARY KEY("person");
ALTER TABLE "SystemPerson" ADD CONSTRAINT "SystemPersonPersonFK" FOREIGN KEY("person") REFERENCES "Person"("subject") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "SystemPersonPreference" ADD CONSTRAINT "SystemPersonPreferencePK" PRIMARY KEY("identifier");
ALTER TABLE "SystemPersonPreference" ADD CONSTRAINT "SystemPersonPreferenceSystemPersonFK" FOREIGN KEY("identifier") REFERENCES "SystemPerson"("person") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "SystemPersonPreference" ADD CONSTRAINT "SystemPersonPreferenceIdiomFK" FOREIGN KEY("idiom") REFERENCES "Idiom"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "SystemPersonPreference" ADD CONSTRAINT "SystemPersonPreferenceLastLoggedSystemCompanyFK" FOREIGN KEY("lastLoggedSystemCompany") REFERENCES "SystemCompany"("company") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "SystemAction" ADD CONSTRAINT "SystemActionPK" PRIMARY KEY("identifier");

ALTER TABLE "SystemPersonSystemAction" ADD CONSTRAINT "SystemPersonSystemActionPK" PRIMARY KEY("systemPerson", "systemAction");
ALTER TABLE "SystemPersonSystemAction" ADD CONSTRAINT "SystemPersonSystemActionSystemPersonFK" FOREIGN KEY("systemPerson") REFERENCES "SystemPerson"("person") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "SystemPersonSystemAction" ADD CONSTRAINT "SystemPersonSystemActionSystemActionFK" FOREIGN KEY("systemAction") REFERENCES "SystemAction"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "SystemPersonSystemCompany" ADD CONSTRAINT "SystemPersonSystemCompanyPK" PRIMARY KEY("systemPerson", "systemCompany");
ALTER TABLE "SystemPersonSystemCompany" ADD CONSTRAINT "SystemPersonSystemCompanySystemPersonFK" FOREIGN KEY("systemPerson") REFERENCES "SystemPerson"("person") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "SystemPersonSystemCompany" ADD CONSTRAINT "SystemPersonSystemCompanySystemCompanyFK" FOREIGN KEY("systemCompany") REFERENCES "SystemCompany"("company") ON DELETE RESTRICT ON UPDATE CASCADE;


--FISCAL--


ALTER TABLE "Cfop" ADD CONSTRAINT "CfopPK" PRIMARY KEY("identifier");

ALTER TABLE "InputCfop" ADD CONSTRAINT "InputCfopPK" PRIMARY KEY("cfop");
ALTER TABLE "InputCfop" ADD CONSTRAINT "InputCfopCfopFK" FOREIGN KEY("cfop") REFERENCES "Cfop"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "OutputCfop" ADD CONSTRAINT "OutputCfopPK" PRIMARY KEY("cfop");
ALTER TABLE "OutputCfop" ADD CONSTRAINT "OutputCfopCfopFK" FOREIGN KEY("cfop") REFERENCES "Cfop"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;


--FINANCE--


ALTER TABLE "Condition" ADD CONSTRAINT "ConditionPK" PRIMARY KEY("identifier");

ALTER TABLE "ConditionDay" ADD CONSTRAINT "ConditionDayPK" PRIMARY KEY("condition", "day");
ALTER TABLE "ConditionDay" ADD CONSTRAINT "ConditionDayConditionPK" FOREIGN KEY("condition") REFERENCES "Condition"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "PaymentCondition" ADD CONSTRAINT "PaymentConditionPK" PRIMARY KEY("condition");
ALTER TABLE "PaymentCondition" ADD CONSTRAINT "PaymentConditionFK" FOREIGN KEY("condition") REFERENCES "Condition"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "ReceiptCondition" ADD CONSTRAINT "ReceiptConditionPK" PRIMARY KEY("condition");
ALTER TABLE "ReceiptCondition" ADD CONSTRAINT "ReceiptConditionFK" FOREIGN KEY("condition") REFERENCES "Condition"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;


--PRODUCTS AND SERVICES--


ALTER TABLE "PisCst" ADD CONSTRAINT "PisCstPK" PRIMARY KEY("identifier");

ALTER TABLE "CofinsCst" ADD CONSTRAINT "CofinsCstPK" PRIMARY KEY("identifier");

ALTER TABLE "PisCofins" ADD CONSTRAINT "PisCofinsPK" PRIMARY KEY("identifier");

ALTER TABLE "PisCofinsRule" ADD CONSTRAINT "PisCofinsRulePK" PRIMARY KEY("pisCofins", "cfop", "rule");
ALTER TABLE "PisCofinsRule" ADD CONSTRAINT "PisCofinsRuleCfopFK" FOREIGN KEY("cfop") REFERENCES "Cfop"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "PisCofinsRule" ADD CONSTRAINT "PisCofinsRuleRuleFK" FOREIGN KEY("rule") REFERENCES "Rule"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "PisCofinsRule" ADD CONSTRAINT "PisCofinsRulePisCstFK" FOREIGN KEY("pisCst") REFERENCES "PisCst"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "PisCofinsRule" ADD CONSTRAINT "PisCofinsRuleCofinsCstFK" FOREIGN KEY("cofinsCst") REFERENCES "CofinsCst"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "IpiCst" ADD CONSTRAINT "IpiCstPK" PRIMARY KEY("identifier");

ALTER TABLE "Ipi" ADD CONSTRAINT "IpiPK" PRIMARY KEY("identifier");

ALTER TABLE "IpiRule" ADD CONSTRAINT "IpiRulePK" PRIMARY KEY("ipi", "cfop", "rule");
ALTER TABLE "IpiRule" ADD CONSTRAINT "IpiRuleCfopFK" FOREIGN KEY("cfop") REFERENCES "Cfop"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "IpiRule" ADD CONSTRAINT "IpiRuleRuleFK" FOREIGN KEY("rule") REFERENCES "Rule"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "IpiRule" ADD CONSTRAINT "IpiRuleCstFK" FOREIGN KEY("cst") REFERENCES "IpiCst"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "IcmsCst" ADD CONSTRAINT "IcmsCstPK" PRIMARY KEY("identifier");

ALTER TABLE "IcmsCsosn" ADD CONSTRAINT "IcmsCsosnPK" PRIMARY KEY("identifier");

ALTER TABLE "Icms" ADD CONSTRAINT "IcmsPK" PRIMARY KEY("identifier");

ALTER TABLE "IcmsRule" ADD CONSTRAINT "IcmsRulePK" PRIMARY KEY("icms", "from", "to", "rule");
ALTER TABLE "IcmsRule" ADD CONSTRAINT "IcmsRuleFromFK" FOREIGN KEY("from") REFERENCES "State"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "IcmsRule" ADD CONSTRAINT "IcmsRuleToFK" FOREIGN KEY("to") REFERENCES "State"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "IcmsRule" ADD CONSTRAINT "IcmsRuleRuleFK" FOREIGN KEY("rule") REFERENCES "Rule"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "IcmsRule" ADD CONSTRAINT "IcmsRuleCstFK" FOREIGN KEY("cst") REFERENCES "IcmsCst"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "IcmsRule" ADD CONSTRAINT "IcmsRuleCsosnFK" FOREIGN KEY("csosn") REFERENCES "IcmsCsosn"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "IcmsRule" ADD CONSTRAINT "IcmsRuleCfopGroupCK" CHECK ("cfopGroup" IN (1,2,3,4)); 

ALTER TABLE "MeasureUnitGroup" ADD CONSTRAINT "MeasureUnitGroupPK" PRIMARY KEY("identifier");

ALTER TABLE "MeasureUnit" ADD CONSTRAINT "MeasureUnitPK" PRIMARY KEY("identifier");
ALTER TABLE "MeasureUnit" ADD CONSTRAINT "MeasureUnitGroupFK" FOREIGN KEY("group") REFERENCES "MeasureUnitGroup"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "MeasureUnitConversion" ADD CONSTRAINT "MeasureUnitConversionPK" PRIMARY KEY("from", "to");
ALTER TABLE "MeasureUnitConversion" ADD CONSTRAINT "MeasureUnitConversionFromFK" FOREIGN KEY("from") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "MeasureUnitConversion" ADD CONSTRAINT "MeasureUnitConversionToFK" FOREIGN KEY("to") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "ProgenyType" ADD CONSTRAINT "ProgenyTypePK" PRIMARY KEY("identifier");

ALTER TABLE "Progeny" ADD CONSTRAINT "ProgenyPK" PRIMARY KEY("identifier");
ALTER TABLE "Progeny" ADD CONSTRAINT "ProgenyProgenyTypeFK" FOREIGN KEY("progenyType") REFERENCES "ProgenyType"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Progeny" ADD CONSTRAINT "ProgenyIcmsFK" FOREIGN KEY("icms") REFERENCES "Icms"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Progeny" ADD CONSTRAINT "ProgenyPisCofinsFK" FOREIGN KEY("pisCofins") REFERENCES "PisCofins"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "ProductGroup" ADD CONSTRAINT "ProductGroupPK" PRIMARY KEY("identifier");

ALTER TABLE "ProductMark" ADD CONSTRAINT "ProductMarkPK" PRIMARY KEY("identifier");

ALTER TABLE "ProductLine" ADD CONSTRAINT "ProductLinePK" PRIMARY KEY("identifier");

ALTER TABLE "ProductModel" ADD CONSTRAINT "ProductModelPK" PRIMARY KEY("identifier");

ALTER TABLE "ProductDetail" ADD CONSTRAINT "ProductDetailPK" PRIMARY KEY("identifier");

ALTER TABLE "ProductFinish" ADD CONSTRAINT "ProductFinishPK" PRIMARY KEY("identifier");

ALTER TABLE "Product" ADD CONSTRAINT "ProductPK" PRIMARY KEY("progeny");
ALTER TABLE "Product" ADD CONSTRAINT "ProductProgenyFK" FOREIGN KEY("progeny") REFERENCES "Progeny"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductProductGroupFK" FOREIGN KEY("productGroup") REFERENCES "ProductGroup"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductProductMarkFK" FOREIGN KEY("productMark") REFERENCES "ProductMark"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductProductLineFK" FOREIGN KEY("productLine") REFERENCES "ProductLine"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductProductModelFK" FOREIGN KEY("productModel") REFERENCES "ProductModel"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductProductDetailFK" FOREIGN KEY("productDetail") REFERENCES "ProductDetail"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductProductFinishFK" FOREIGN KEY("productFinish") REFERENCES "ProductFinish"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductWidthUnitFK" FOREIGN KEY("widthUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductHeightUnitFK" FOREIGN KEY("heightUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductLengthUnitFK" FOREIGN KEY("lengthUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductContentUnitFK" FOREIGN KEY("contentUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductGrossWeightUnitFK" FOREIGN KEY("grossWeightUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductNetWeightUnitFK" FOREIGN KEY("netWeightUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductIpiFK" FOREIGN KEY("ipi") REFERENCES "Ipi"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "ProductPart" ADD CONSTRAINT "ProductPartPK" PRIMARY KEY("product", "part");
ALTER TABLE "ProductPart" ADD CONSTRAINT "ProductPartProductFK" FOREIGN KEY("product") REFERENCES "Product"("progeny") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "ProductPart" ADD CONSTRAINT "ProductPartPartFK" FOREIGN KEY("part") REFERENCES "Product"("progeny") ON DELETE RESTRICT ON UPDATE CASCADE;


--COMERCIAL--


ALTER TABLE "FiscalDocumentType" ADD CONSTRAINT "FiscalDocumentTypePK" PRIMARY KEY("identifier");

ALTER TABLE "Macro" ADD CONSTRAINT "MacroPK" PRIMARY KEY("identifier");
ALTER TABLE "Macro" ADD CONSTRAINT "MacroFiscalDocumentTypeFK" FOREIGN KEY("fiscalDocumentType") REFERENCES "FiscalDocumentType"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroPK" PRIMARY KEY("macro");
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroMacroFK" FOREIGN KEY("macro") REFERENCES "Macro"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopState1FK" FOREIGN KEY("cfopState1") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopInterstate1FK" FOREIGN KEY("cfopInterstate1") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopExterior1FK" FOREIGN KEY("cfopExterior1") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopState2FK" FOREIGN KEY("cfopState2") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopInterstate2FK" FOREIGN KEY("cfopInterstate2") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopExterior2FK" FOREIGN KEY("cfopExterior2") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopState3FK" FOREIGN KEY("cfopState3") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopInterstate3FK" FOREIGN KEY("cfopInterstate3") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopExterior3FK" FOREIGN KEY("cfopExterior3") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopState4FK" FOREIGN KEY("cfopState4") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopInterstate4FK" FOREIGN KEY("cfopInterstate4") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "InputMacro" ADD CONSTRAINT "InputMacroCfopExterior4FK" FOREIGN KEY("cfopExterior4") REFERENCES "InputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroPK" PRIMARY KEY("macro");
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroMacroFK" FOREIGN KEY("macro") REFERENCES "Macro"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopState1FK" FOREIGN KEY("cfopState1") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopInterstate1FK" FOREIGN KEY("cfopInterstate1") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopExterior1FK" FOREIGN KEY("cfopExterior1") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopState2FK" FOREIGN KEY("cfopState2") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopInterstate2FK" FOREIGN KEY("cfopInterstate2") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopExterior2FK" FOREIGN KEY("cfopExterior2") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopState3FK" FOREIGN KEY("cfopState3") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopInterstate3FK" FOREIGN KEY("cfopInterstate3") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopExterior3FK" FOREIGN KEY("cfopExterior3") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopState4FK" FOREIGN KEY("cfopState4") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopInterstate4FK" FOREIGN KEY("cfopInterstate4") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OutputMacro" ADD CONSTRAINT "OutputMacroCfopExterior4FK" FOREIGN KEY("cfopExterior4") REFERENCES "OutputCfop"("cfop") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Order" ADD CONSTRAINT "OrderPK" PRIMARY KEY("identifier");
ALTER TABLE "Order" ADD CONSTRAINT "OrderFromFK" FOREIGN KEY("from") REFERENCES "Subject"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Order" ADD CONSTRAINT "OrderToFK" FOREIGN KEY("to") REFERENCES "Subject"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "OrderItem" ADD CONSTRAINT "OrderItemPK" PRIMARY KEY("order","progeny");
ALTER TABLE "OrderItem" ADD CONSTRAINT "OrderItemOrderFK" FOREIGN KEY("order") REFERENCES "Order"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "OrderItem" ADD CONSTRAINT "OrderItemProgenyFK" FOREIGN KEY("progeny") REFERENCES "Progeny"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "InputOrder" ADD CONSTRAINT "InputOrderPK" PRIMARY KEY("order");
ALTER TABLE "InputOrder" ADD CONSTRAINT "InputOrderOrderFK" FOREIGN KEY("order") REFERENCES "Order"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "InputOrder" ADD CONSTRAINT "InputOrderInputMacroFK" FOREIGN KEY("inputMacro") REFERENCES "InputMacro"("macro") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "OutputOrder" ADD CONSTRAINT "OutputOrderPK" PRIMARY KEY("order");
ALTER TABLE "OutputOrder" ADD CONSTRAINT "OutputOrderOrderFK" FOREIGN KEY("order") REFERENCES "Order"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "OutputOrder" ADD CONSTRAINT "OutputOrderOutputMacroFK" FOREIGN KEY("outputMacro") REFERENCES "OutputMacro"("macro") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "SaleOrder" ADD CONSTRAINT "SaleOrderPK" PRIMARY KEY("outputOrder");
ALTER TABLE "SaleOrder" ADD CONSTRAINT "SaleOrderOutputOrderFK" FOREIGN KEY("outputOrder") REFERENCES "OutputOrder"("order") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "SaleOrder" ADD CONSTRAINT "SaleOrderSellerFK" FOREIGN KEY("seller") REFERENCES "SystemPerson"("person") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "PurchaseOrder" ADD CONSTRAINT "PurchaseOrderPK" PRIMARY KEY("inputOrder");
ALTER TABLE "PurchaseOrder" ADD CONSTRAINT "PurchaseOrderInputOrderFK" FOREIGN KEY("inputOrder") REFERENCES "InputOrder"("order") ON DELETE CASCADE ON UPDATE CASCADE;

DELETE FROM "MeasureUnit";

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (1, 'MILÌMETRO', 'MM', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (2, 'CENTÌMETRO', 'CM', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (3, 'DECÌMETRO', 'DM', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (4, 'METRO', 'M', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (5, 'DEC¬METRO', 'DAM', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (6, 'HECT”METRO', 'HM', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (7, 'QUIL‘METRO', 'KM', 1);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (01, 01, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (01, 02, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (01, 03, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (01, 04, 0.001);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (01, 05, 0.0001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (02, 01, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (02, 02, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (02, 03, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (02, 04, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (02, 05, 0.001);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (02, 06, 0.0001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (03, 01, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (03, 02, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (03, 03, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (03, 04, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (03, 05, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (03, 06, 0.001);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (03, 07, 0.0001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (04, 01, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (04, 02, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (04, 03, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (04, 04, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (04, 05, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (04, 06, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (04, 07, 0.001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (05, 01, 10000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (05, 02, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (05, 03, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (05, 04, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (05, 05, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (05, 06, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (05, 07, 0.01);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (06, 01, 100000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (06, 02, 10000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (06, 03, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (06, 04, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (06, 05, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (06, 06, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (06, 07, 0.1);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (07, 01, 1000000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (07, 02, 100000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (07, 03, 10000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (07, 04, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (07, 05, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (07, 06, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (07, 07, 1);

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (08, 'MILIGRAMA', 'MG', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (09, 'CENTIGRAMA', 'CG', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (10, 'DECIGRAMA', 'DG', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (11, 'GRAMA', 'G', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (12, 'DECAGRAMA', 'DAG', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (13, 'HECTOGRAMA', 'HG', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (14, 'QUILOGRAMA', 'KG', 2);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (08, 08, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (08, 09, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (08, 10, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (08, 11, 0.001);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (08, 12, 0.0001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (09, 08, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (09, 09, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (09, 10, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (09, 11, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (09, 12, 0.001);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (09, 13, 0.0001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (10, 08, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (10, 09, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (10, 10, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (10, 11, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (10, 12, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (10, 13, 0.001);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (10, 14, 0.0001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (11, 08, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (11, 09, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (11, 10, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (11, 11, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (11, 12, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (11, 13, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (11, 14, 0.001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (12, 08, 10000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (12, 09, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (12, 10, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (12, 11, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (12, 12, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (12, 13, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (12, 14, 0.01);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (13, 08, 100000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (13, 09, 10000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (13, 10, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (13, 11, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (13, 12, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (13, 13, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (13, 14, 0.1);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (14, 08, 1000000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (14, 09, 100000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (14, 10, 10000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (14, 11, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (14, 12, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (14, 13, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (14, 14, 1);

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (15, 'MILILITRO', 'ML', 3);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (16, 'CENTILITRO', 'CL', 3);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (17, 'DECILITRO', 'DL', 3);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (18, 'LITRO', 'L', 3);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (19, 'DECALITRO', 'DAL', 3);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (20, 'HECTOLITRO', 'HL', 3);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (21, 'QUILOLITRO', 'KL', 3);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (15, 15, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (15, 16, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (15, 17, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (15, 18, 0.001);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (15, 19, 0.0001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (16, 15, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (16, 16, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (16, 17, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (16, 18, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (16, 19, 0.001);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (16, 20, 0.0001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (17, 15, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (17, 16, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (17, 17, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (17, 18, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (17, 19, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (17, 20, 0.001);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (17, 21, 0.0001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (18, 15, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (18, 16, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (18, 17, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (18, 18, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (18, 19, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (18, 20, 0.01);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (18, 21, 0.001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (19, 15, 10000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (19, 16, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (19, 17, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (19, 18, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (19, 19, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (19, 20, 0.1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (19, 21, 0.01);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (20, 15, 100000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (20, 16, 10000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (20, 17, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (20, 18, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (20, 19, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (20, 20, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (20, 21, 0.1);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (21, 15, 1000000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (21, 16, 100000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (21, 17, 10000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (21, 18, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (21, 19, 100);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (21, 20, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (21, 21, 1);

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (22, 'MILISSEGUNDO', 'MS', 4);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (23, 'SEGUNDO', 'S', 4);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (24, 'MINUTO', 'MIN', 4);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (25, 'HORA', 'H', 4);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (22, 22, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (22, 23, 0.001);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (23, 22, 1000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (23, 23, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (23, 24, 0.02);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (24, 22, 60000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (24, 23, 10);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (24, 24, 1);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (24, 25, 0.016);

INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (25, 22, 3600000);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (25, 23, 3600);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (25, 24, 60);
INSERT INTO "MeasureUnitConversion" ("from", "to", "value") VALUES (25, 25, 1);

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (26, 'UNIDADE', 'UN', 5);
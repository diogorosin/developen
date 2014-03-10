CREATE VIEW "SubjectView" AS
SELECT s."identifier", s."denomination", s."active", CAST('F' AS CHAR(1)) AS type, CAST(cpf1."number" AS DECIMAL(15,0)) AS "cpfOrCnpj", c1."denomination" AS "city", st1."acronym" AS "state", co1."acronym" AS "country"
FROM "Person" p
INNER JOIN "Subject" s ON s."identifier" = p."subject"
LEFT OUTER JOIN "Cpf" cpf1 ON cpf1."identifier" = s."identifier"
LEFT OUTER JOIN "Address" a1 ON a1."identifier" = s."identifier"
LEFT OUTER JOIN "City" c1 ON c1."identifier" = a1."city"
LEFT OUTER JOIN "State" st1 ON st1."identifier" = c1."state"
LEFT OUTER JOIN "Country" co1 ON co1."identifier" = st1."country"
UNION ALL
SELECT s."identifier", s."denomination", s."active", CAST('J' AS CHAR(1)) AS type, CAST(cnpj2."number" AS DECIMAL(15,0)) AS "cpfOrCnpj", c2."denomination" AS "city", st2."acronym" AS "state", co2."acronym" AS "country"
FROM "Company" c
INNER JOIN "Subject" s ON s."identifier" = c."subject"
LEFT OUTER JOIN "Cnpj" cnpj2 ON cnpj2."identifier" = s."identifier"
LEFT OUTER JOIN "Address" a2 ON a2."identifier" = s."identifier"
LEFT OUTER JOIN "City" c2 ON c2."identifier" = a2."city"
LEFT OUTER JOIN "State" st2 ON st2."identifier" = c2."state"
LEFT OUTER JOIN "Country" co2 ON co2."identifier" = st2."country"
ORDER BY 2

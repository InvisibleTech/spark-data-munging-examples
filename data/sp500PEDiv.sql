DROP TABLE IF EXISTS sp500PEDiv;
CREATE TABLE sp500PEDiv(
  "Date" text NOT NULL PRIMARY KEY, 
  "SP500" double precision, 
  "Dividend" double precision,
  "Earnings" double precision,
  "Consumer Price Index" double precision,
  "Long Interest Rate" double precision,
  "Real Price" double precision,
  "Real Dividend" double precision,
  "Real Earnings" double precision,
  "PE10" double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sp500PEDiv
  OWNER TO <fill it in>;

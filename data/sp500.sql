CREATE TABLE sp500
(
  "Symbol" text,
  "Name" text,
  "Sector" text,
  "Price" double precision,
  "Dividend Yield" double precision,
  "Price/Earnings" double precision,
  "Earnings/Share" double precision,
  "Book Value" double precision,
  "52 week low" double precision,
  "52 week high" double precision,
  "Market Cap" double precision,
  "EBITDA" double precision,
  "Price/Sales" double precision,
  "Price/Book" double precision,
  "SEC Filings" text
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sp500
  OWNER TO <fill it in>;

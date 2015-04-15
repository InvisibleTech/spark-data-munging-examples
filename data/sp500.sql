DROP TABLE IF EXISTS sp500;
CREATE TABLE sp500
(
  symbol text,
  name text,
  sector text,
  price double precision,
  dividend_yield double precision,
  price_earnings double precision,
  earnings_share double precision,
  book_value double precision,
  _52_week_low double precision,
  _52_week_high double precision,
  market_cap double precision,
  ebitda double precision,
  price_sales double precision,
  price_book double precision,
  sec_filings text
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sp500
  OWNER TO <fill it in>;

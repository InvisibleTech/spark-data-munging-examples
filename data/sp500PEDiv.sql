DROP TABLE IF EXISTS sp500PEDiv;
CREATE TABLE sp500PEDiv(
  date text NOT NULL PRIMARY KEY, 
  sp500 double precision, 
  dividend double precision,
  earnings double precision,
  consumer_price_index double precision,
  long_interest_rate double precision,
  real_price double precision,
  real_dividend double precision,
  real_earnings double precision,
  p_e_10 double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sp500PEDiv
  OWNER TO <fill it in>;

// To make this work you need to start spark shell like this:
// ../bin/spark-shell --driver-class-path ../lib/postgresql-9.3-1103.jdbc41.jar
val jdbcDf = sqlContext.jdbc("jdbc:postgresql://localhost/sparkdemo?user=johnferguson", "sp500")

jdbcDf.registerTempTable("sp500")

// This fails right now, I think since names of columns are not properly escaped.
jdbcDf.show

val avgEPSAnon = sqlContext.sql("SELECT AVG(`Earnings/Share`) FROM sp500")
val avgEPSNamed = sqlContext.sql("SELECT AVG(`Earnings/Share`) as AvgCPI FROM sp500")
val avgEPSProg = jsonDf.agg(avg(jsonDf.col("Earnings/Share")))

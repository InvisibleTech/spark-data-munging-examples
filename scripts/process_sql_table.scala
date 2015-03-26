import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Connection
import org.apache.spark.rdd.JdbcRDD

import org.apache.spark.sql.catalyst.expressions.GenericMutableRow

import org.apache.spark.sql._

// To push jars to workers you need the path to the jar.  Could not get relative paths to work.
sc.addJar("file:////Users/johnferguson/Documents/Code/scala/spark-1.2.1-bin-hadoop2.4/lib/postgresql-9.3-1103.jdbc41.jar") 

// Convert
def extractValues(r: ResultSet) = {
  (r.getString(1), r.getDouble(4))
}

val connector: () => Connection = () => {
  Class.forName("org.postgresql.Driver");
  DriverManager.getConnection("jdbc:postgresql://localhost/sparkdemo?user=johnferguson")
}

// Because you cannot create the connection and pass it to the RDD for remote processing,
// we need to use the more roundabout way to let the worker process connect.
//
// Also, you need to help the shell help you by informing it about the driver jars needed:
// 
// ../bin/spark-shell --jars ../lib/postgresql-9.3-1103.jdbc41.jar
//
val jdbcRDD = new JdbcRDD( sc, connector, "SELECT * FROM sp500 LIMIT ? OFFSET ?", lowerBound=Integer.MAX_VALUE, upperBound=1, numPartitions = 2, mapRow = extractValues)

// You can cause work to happen by invoking the action: count
jdbcRDD.count 

// Okay - now we have a datasource, we can start using Spark SQL to do some work.
val sqlContext = new SQLContext(sc)
val sp500SchemaRDD = sqlContext.createSchemaRDD(jdbcRDD)
sp500SchemaRDD.registerTempTable("sp500")

sqlContext.sql("SELECT * FROM sp500").foreach(println)

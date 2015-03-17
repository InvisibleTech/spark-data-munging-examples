import java.sql.DriverManager
import java.sql.ResultSet
import org.apache.spark.rdd.JdbcRDD

// This should work, but I think I need some other jars.  Of course.
sc.addJar("file:////Users/johnferguson/Documents/Code/scala/spark-1.2.1-bin-hadoop2.4/lib/postgresql-9.3-1103.jdbc41.jar") 

// Because you cannot create the connection and pass it to the RDD for remote processing,
// we need to use the more roundabout way to let the worker process connect.
def createConnection() = {
  Class.forName("org.postgresql.Driver").newInstance();
  DriverManager.getConnection("jdbc:postgresql://localhost/sparkdemo?user=johnferguson")
}

def extractValues(r: ResultSet) = {
  (r.getInt(1), r.getString(2))
}

// NOTE:
// This code assumes you have added the correct Postgresql driver to your
// Spark classpath.  One simple way is to put it somewhere like Spark's lib folder
// and invoke it like this:
//
// 
//
val driver = Class.forName("org.postgresql.Driver").newInstance()

val conn = DriverManager.getConnection("jdbc:postgresql://localhost/sparkdemo?user=johnferguson")

val data = new JdbcRDD(sc, createConnection, "SELECT * FROM sp500 OFFSET ? LIMIT ?", lowerBound=Long.MaxValue, upperBound=0, numPartitions = 2, mapRow = extractValues)

println(data.collect().toList)
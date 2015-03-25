package org.invisibletech

import scala.collection.mutable.ArrayBuffer
import java.sql.ResultSetMetaData
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType

object MetaDataFacade {
  def names(meta : ResultSetMetaData): Array[String] = {
    val arr : ArrayBuffer[String] = ArrayBuffer()
    for(i <- 1 to meta.getColumnCount) {
      arr.append(meta.getColumnName(i))
    }
    
    arr.toArray
  }
  
  def createStringTypeSchema(names: Array[String]) : StructType  = {
    StructType(names.map { name => StructField(name, StringType, true)})
  }
}
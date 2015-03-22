package org.invisibletech

import scala.collection.mutable.ArrayBuffer
import java.sql.ResultSetMetaData

object MetaDataFacade {
  def names(meta : ResultSetMetaData): Array[String] = {
    val arr : ArrayBuffer[String] = ArrayBuffer()
    for(i <- 1 to meta.getColumnCount) {
      arr.append(meta.getColumnName(i))
    }
    
    arr.toArray
  }

}
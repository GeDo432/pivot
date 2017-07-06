package com.orthus.bruhns.pivot

/**
  * Root is invisible. It has a span of 0 columns and it's row-level is -1. You can think of
  * root as '''axis-title''', not as header for some or just one column or row.
  *
  * @param axisTitle The name to be displayed as header.
  */
final class Root (axisTitle: String) extends AbstractNode(axisTitle) {
  private[pivot] override def calculateSpan(includeInvisible:Boolean): Int = 0
  override def getLeftColumn: Int = 0
  override def getRightColumn: Int = 0
  override def getRow: Int = 0
  override def getColumn = 0;
  /**
    * @return The name to be displayed as header.
    */
  def getAxisTitle = axisTitle



}
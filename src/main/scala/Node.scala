package com.orthus.bruhns.pivot

object Node {

  /**
    * Factory method.
    * @param name Der Name
    * @param isVisible
    * @return Pre-configured node, that is not yet linked into a [[Tree]]
    */
  def apply(name: String,isVisible:Boolean=true):(AbstractNode) => Node = new Node(name, isVisible)(_)
}

/**
  * Node in the [[Tree pivot-tree]] - but not the [[Root root]].
  * @param name The name to be displayed as header.
  * @param isVisible Flag to hide this node.
  * @param parent Parent node, where this is a child of.
  */
final class Node private(name: String,
                         var isVisible:Boolean)(parent:AbstractNode) extends AbstractNode(name) {

  /**
    * @return Parent node - may be [[Root root]] or other [[Node nodes]]
    */
  def getParent:AbstractNode = parent


  private[pivot] override def calculateSpan(includeInvisible:Boolean):Int = {
    if (isVisible || includeInvisible) {
      1 + children
        .filter(_.isVisible || includeInvisible)
        .map(_.calculateSpan(includeInvisible))
        .sum
    } else {
      0
    }
  }

  override def getRightColumn:Int = {
    val left = getLeftColumn
    val span = calculateSpan(includeInvisible=false)
    left + span
  }

  override def getLeftColumn:Int = {
    parent.countColumnsLeftFrom(this, includeInvisible=false)
  }

  private[pivot] override def countColumnsLeftFrom(child: Node, includeInvisible:Boolean):Int = {
    val colsLeftOfMine = parent.countColumnsLeftFrom(this, includeInvisible)
    val colMe = if (isVisible || includeInvisible) 1 else 0
    val colsChildSiblings = super.countColumnsLeftFrom(child, includeInvisible)
    colsLeftOfMine + colMe + colsChildSiblings
  }

  override def getRow: Int = {
    1+parent.getRow
  }

  /**
    * @return The name to be displayed as header.
    */
  def getName = name

  override def getColumn: Int = 1 + parent.countColumnsLeftFrom(this, includeInvisible=true)
}

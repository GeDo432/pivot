package com.orthus.bruhns.pivot

object Node {

  /**
    * Factory method.
    *
    * @param name      the name
    * @param isVisible if this node shall be visible or not
    * @return Pre-configured node, that is not yet linked into a [[Tree]]
    */
  def apply(name: String, isVisible: Boolean = true): (AbstractNode) => Node = new Node(name, isVisible)(_)
}

/**
  * Node in the [[Tree pivot-tree]] - but not the [[Root root]].
  *
  * @param name      The name to be displayed as header.
  * @param isVisible Flag to hide this node.
  * @param parent    Parent node, where this is a child of.
  */
final class Node private(name: String,
                         var isVisible: Boolean)(parent: AbstractNode) extends AbstractNode(name) {

  /**
    * @return Parent node - may be [[Root root]] or other [[Node nodes]]
    */
  def getParent: AbstractNode = parent

  override def getRightColumn: Int = {
    val left = getLeftColumn
    val span = calculateSpan()
    left + span
  }

  override def getLeftColumn: Int = {
    parent.countColumnsLeftFrom(this, includeInvisible = true)
  }

  override def getRow: Int = {
    1 + parent.getRow
  }

  /**
    * @return The name to be displayed as header.
    */
  def getName: String = name

  override def getColumn: Int = 1 + parent.countColumnsLeftFrom(this, includeInvisible = true)

  private[pivot] override def calculateSpan(includeInvisible: Boolean): Int = {
    if (isVisible || includeInvisible) {
      1 + children
        .filter(_.isVisible || includeInvisible)
        .map(_.calculateSpan(includeInvisible))
        .sum
    } else {
      0
    }
  }

  private[pivot] override def countColumnsLeftFrom(child: Node, includeInvisible: Boolean): Int = {
    val colsLeftOfMine = parent.countColumnsLeftFrom(this, includeInvisible)
    val colMe = if (isVisible || includeInvisible) 1 else 0
    val colsChildSiblings = super.countColumnsLeftFrom(child, includeInvisible)
    colsLeftOfMine + colMe + colsChildSiblings
  }
}

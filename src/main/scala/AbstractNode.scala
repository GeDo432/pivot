package com.orthus.bruhns.pivot

/**
  * Node in the [[Tree pivot-tree]]
  *
  * @param name The name to be displayed as header.
  */
private[pivot] abstract class AbstractNode(name: String) {

  /**
    * sub-nodes, child-nodes
    */
  private[pivot] var children: List[Node] = Nil

  /**
    * @return True, if no child (sub-node) is present.
    */
  def isLeaf: Boolean = children.isEmpty

  /**
    * The row-index (if pivot-header-tree is located on top of pivot-table) of this node.
    * Index starts with 0 for [[Root root]] node. Invisible and visible nodes are counted.
    *
    * @return Row (level) of this node.
    * @see getColumn
    */
  def getRow: Int

  /**
    * A visible node (except the [[Root root-node]]) spans at least one column
    * (if pivot-header-tree is located on top of pivot-table) for itself.
    *
    * However, if this node has children, it also spans one column
    * for each child. In this case, this node's column contains per row
    * the ''summary'' of it's children column-values.
    *
    * Invisible nodes don't have a span.
    *
    * @return Number of columns, this node and it's children span.
    */
  def getSpan: Int = calculateSpan()

  /**
    * @return Index of the first spanned column (if pivot-header-tree is located on top of pivot-table). Index starts with 0.
    * @see getSpan
    */
  def getLeftColumn: Int

  /**
    * @return Index of the last spanned column (if pivot-header-tree is located on top of pivot-table). Index starts with 0.
    * @see getSpan
    */
  def getRightColumn: Int

  /**
    * @return List of sub-nodes (may be empty)
    */
  def getChildren: List[Node] = children

  /**
    * @return Column number. Index starts with 0 for [[Root root]] node. Invisible and visible nodes are counted.
    * @see getRow
    */
  def getColumn: Int

  /**
    * Add a child (sub-node) to this node.
    *
    * @param child sub-node
    */
  private[pivot] final def addChildren(child: Node*): Unit = {
    children = children ++ child
  }

  /**
    * A node (except the [[Root root-node]]) spans at least one column
    * (if pivot-header-tree is located on top of pivot-table) for itself.
    *
    * However, if this node has children, it also spans one column
    * for each child. In this case, this node's column contains per row
    * the ''summary'' of it's children column-values.
    *
    * @param includeInvisible Wether or not to include invisible nodes in the calculation
    * @return Number of columns, this node and it's children span.
    */
  private[pivot] def calculateSpan(includeInvisible: Boolean = false): Int

  /**
    * Sums up the spans of of nodes, that are located left of the given one.
    *
    * @param child Sub-node. Must be child of this - otherwise an AssertionError will be thrown.
    * @return Number of columns left from the given child-node.
    * @see getSpan
    */
  private[pivot] def countColumnsLeftFrom(child: Node, includeInvisible: Boolean): Int = {
    assert(children.contains(child))
    children
      .takeWhile(_ != child)
      .map(_.calculateSpan(includeInvisible))
      .sum
  }


}
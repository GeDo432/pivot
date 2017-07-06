package com.orthus.bruhns.pivot



/**
  * The tree holds the [[Root]] Node.
  */
class Tree(axisTitle: String) {

  val root = new Root(axisTitle)

  /**
    * Method to build a tree structure. Usage example:
    * {{{
    * val tree = Tree("x-axis")
    * val parent:Root = tree.root
    * val node11 = tree.addNode(parent, Node("1 1"))
    * }}}
    * @param parent Parent for the new node. May be root-node, which this tree holds as public value.
    * @param node The node to append to the parent. You can obtain the [[Node]] by the apply-function in node's compangnion object.
    * @return node, linked in this tree.
    */
  def addNode(parent: AbstractNode, node: (AbstractNode) => Node): Node = {
    val newNode = node(parent)
    parent.addChildren(newNode)
    newNode
  }
}

object Tree {
  def apply(axisTitle: String) = new Tree(axisTitle)
}




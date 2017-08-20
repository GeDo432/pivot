package com.orthus.bruhns

/**
  * This package contains composable header-tree-structure and a value-array. Headers can be set to the left
  * (y-axis) and top (x-axis) of the two-dimensional value-array. If you collapse a node in the tree, all the
  * child-nodes will be hidden too. Child-nodes are rendered beneath their parent, so that - if the parent node
  * can describe an aggregation - you can see the aggregation and the parts of it at the same time.
  * {{{
  *   |Col|  0              |  1          |  2            |  3            |  4          |  5            |  6            |
  *   |Row|-----------------+-------------+---------------+---------------+-------------+---------------+---------------+
  *   |  0|Root (invisible)                                                                                             |
  *   |  1|                 |Top Header 1  Span=3                         |Top Header 2  Span=3                         |
  *   |  2|                 |             |Sub Header 1.1 |Sub Header 1.2 |             |Sub Header 2.1 |Sub Header 2.2 |
  * }}}
  *
  *
  * {{{
  *   val tree = Tree("x-axis")
  *   val parent:Root = tree.root
  *   val node11 = tree.addNode(parent, Node("1 1"))
  * }}}
  *
  * @version 1.0
  * @author Frank Lemke
  */
package object pivot {

}


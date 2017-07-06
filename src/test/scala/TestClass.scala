package com.orthus.bruhns.pivot
import org.scalatest._

class TestClass extends FreeSpec {

  "An empty pivot tree" - {
    val axisTitle = "x-axis"
    val tree = Tree(axisTitle)
    "should contain the root" in {
      assert(tree.root.getAxisTitle== axisTitle)
    }
    "should span 0 columns" in {
      assert(tree.root.getSpan == 0)
    }
    "has no children" in {
      assert(tree.root.isLeaf)
    }
    "can take the first visible node" in {
      val node = tree.addNode(tree.root, Node("1st node"))
      assert(node.isVisible)
      assert(node.getParent == tree.root)
    }
  }

  "A big tree" - {

    val tree = Tree("X-Achse")
    val root:Root = tree.root
    val node11 = tree.addNode(root, Node("1 1"))
    val node22 = tree.addNode(node11, Node("2 2"))
    val node23 = tree.addNode(node11, Node("2 3"))
    val node14 = tree.addNode(root, Node("1 4"))
    val node15 = tree.addNode(root, Node("1 5"))
    val node26 = tree.addNode(node15, Node("2 6"))
    val node37 = tree.addNode(node26, Node("3 7"))
    val node38 = tree.addNode(node26, Node("3 8"))
    val node29 = tree.addNode(node15, Node("2 9"))
    val node3A = tree.addNode(node29, Node("3 A"))
    val node3B = tree.addNode(node29, Node("3 B"))

    "calculates the row" in {
      assertResult(0)(root.getRow)
      assertResult(1)(node11.getRow)
      assertResult(2)(node22.getRow)
      assertResult(2)(node23.getRow)
      assertResult(1)(node14.getRow)
      assertResult(1)(node15.getRow)
      assertResult(2)(node26.getRow)
      assertResult(3)(node37.getRow)
      assertResult(3)(node38.getRow)
      assertResult(2)(node29.getRow)
      assertResult(3)(node3A.getRow)
      assertResult(3)(node3B.getRow)
    }
    "calculates the column" in {
      assertResult(0)(root.getColumn)
      assertResult(1)(node11.getColumn)
      assertResult(2)(node22.getColumn)
      assertResult(3)(node23.getColumn)
      assertResult(4)(node14.getColumn)
      assertResult(5)(node15.getColumn)
      assertResult(6)(node26.getColumn)
      assertResult(7)(node37.getColumn)
      assertResult(8)(node38.getColumn)
      assertResult(9)(node29.getColumn)
      assertResult(10)(node3A.getColumn)
      assertResult(11)(node3B.getColumn)
    }
    "calculates the isLeaf-Flag" in {
      assert(!node11.isLeaf)
      assert(node22.isLeaf)
      assert(node23.isLeaf)
      assert(node14.isLeaf)
      assert(!node15.isLeaf)
      assert(!node26.isLeaf)
      assert(node37.isLeaf)
      assert(node38.isLeaf)
      assert(!node29.isLeaf)
      assert(node3A.isLeaf)
      assert(node3B.isLeaf)
    }
    "calculates the span-value" in {
      assertResult(3)(node11.getSpan)
      assertResult(1)(node22.getSpan)
      assertResult(1)(node23.getSpan)
      assertResult(1)(node14.getSpan)
      assertResult(7)(node15.getSpan)
      assertResult(3)(node26.getSpan)
      assertResult(1)(node37.getSpan)
      assertResult(1)(node38.getSpan)
      assertResult(3)(node29.getSpan)
      assertResult(1)(node3A.getSpan)
      assertResult(1)(node3B.getSpan)
    }
    "calculates the left column" in {
      assertResult(0)(node11.getLeftColumn)
      assertResult(1)(node22.getLeftColumn)
      assertResult(2)(node23.getLeftColumn)
      assertResult(3)(node14.getLeftColumn)
      assertResult(4)(node15.getLeftColumn)
      assertResult(5)(node26.getLeftColumn)
      assertResult(6)(node37.getLeftColumn)
      assertResult(7)(node38.getLeftColumn)
      assertResult(8)(node29.getLeftColumn)
      assertResult(9)(node3A.getLeftColumn)
      assertResult(10)(node3B.getLeftColumn)
    }
    "calculates the right column" in {
      List(node11, node22, node23, node14, node15, node26, node37, node38, node29, node3A, node3B).foreach(node =>
        assertResult(node.getLeftColumn + node.getSpan)(node.getRightColumn))
    }
    "calculates left column with invisible tree-parts" in {
      node37.isVisible=false
      node38.isVisible=false

      assertResult(0)(node11.getLeftColumn)
      assertResult(1)(node22.getLeftColumn)
      assertResult(2)(node23.getLeftColumn)
      assertResult(3)(node14.getLeftColumn)
      assertResult(4)(node15.getLeftColumn)
      assertResult(5)(node26.getLeftColumn)
      assertResult(6)(node37.getLeftColumn)
      assertResult(6)(node38.getLeftColumn)
      assertResult(6)(node29.getLeftColumn)
      assertResult(7)(node3A.getLeftColumn)
      assertResult(8)(node3B.getLeftColumn)
    }

  }
}

public class TreeNode {                                     // Klash TreeNode
    
    private Point item;                                     // To Point pou periexei to TreeNode
    private TreeNode l = null;                              // To aristero paidi tou TreeNode (arxikopoieitai me null)
    private TreeNode r = null;                              // To deksi paidi tou TreeNode (arxikopoieitai me null)

    TreeNode(Point item){                                   // Kataskeuasths TreeNode
        this.item = item;                                   // Arxikopoiei to item me auto pou dinei o xrhsths
    }

    Point getItem(){                                        // Methodos getItem()
        return item;                                        // Epistrefei to item
    }

    TreeNode getL(){                                        // Methodos getL()
        return l;                                           // Epistrefei to aristero paidi tou TreeNode
    }

    TreeNode getR(){                                        // Methodos getR()
        return r;                                           // Epistrefei to deksi paidi tou TreeNode
    }

    void setL(TreeNode l){                                  // Methodos setL()
        this.l = l;                                         // Orizei to aristero paidi tou TreeNode
    }

    void setR(TreeNode r){                                  // Methodos setR()
        this.r = r;                                         // Orizei to deksi paidi tou TreeNode
    }
}
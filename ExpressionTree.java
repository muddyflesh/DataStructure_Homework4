import javax.tools.Tool;

public class ExpressionTree{

    private Node root;

    public ExpressionTree(){
        root = null;
    }

    public class Node {
	
        public Object element;
        
        public Node leftChild;
        public Node rightChild;
        
        public Node (Object o) {
            this (o, null, null);
        }
        
        public Node (Object o, Node l, Node r) {
            element = o;
            leftChild = l;
            rightChild = r;
        }
        
        public String toString() {
            return element.toString();
        }


    }

    public ExpressionTree convert(String postfix){
        String [] operationlist = postfix.split(" ");
        LinkedStack<ExpressionTree> toolstack = new LinkedStack<ExpressionTree>();
        for(int i = 0; i<operationlist.length; i++){
            if(operationlist[i].equals("+")||operationlist[i].equals("-")||operationlist[i].equals("*")||operationlist[i].equals("/")||operationlist[i].equals("^")){
                ExpressionTree rightNumber = new ExpressionTree();
                ExpressionTree leftNumber = new ExpressionTree();
                rightNumber.root = toolstack.pop().root;
                leftNumber.root = toolstack.pop().root;
                Node newOperatorRoot = new Node(operationlist[i], leftNumber.root, rightNumber.root);
                ExpressionTree newOperator = new ExpressionTree();
                newOperator.root = newOperatorRoot;
                toolstack.push(newOperator);
            }
            else{
                Node newNumberRoot = new Node(Integer.parseInt(operationlist[i]), null, null);
                ExpressionTree newNumber = new ExpressionTree();
                newNumber.root = newNumberRoot;
                toolstack.push(newNumber);
            }
        }
        ExpressionTree postfixTree = new ExpressionTree();
        postfixTree = toolstack.pop();
        return postfixTree;
        
    }

    public void prefix(){
        System.out.print("Prefix: ");
        prefix(root);
        System.out.println("");
    }

    private void prefix(Node t){
        if (t == null){ return; }
        System.out.print(t.element);
        prefix(t.leftChild);
        prefix(t.rightChild);
    }


    public void infix(){
        System.out.print("Infix: ");
        infix(root);
        System.out.println("");
    }

    private void infix(Node t){
        if (t.leftChild != null){
            System.out.print("(");
            infix(t.leftChild);
        }
        System.out.print(t.element);
        if (t.rightChild != null){
            infix(t.rightChild);
            System.out.print(")");
            
        }
    }

    public void postfix(){
        System.out.print("Postfix: ");
        postfix(root);
        System.out.println("");
    }

    public void postfix(Node t){
        if (t == null){ return; }
        postfix(t.leftChild);
        postfix(t.rightChild);
        System.out.print(t.element);
    }   


}

import java.util.HashMap;
import java.util.Map;

public class Testing1 {

    public static String ArrayChallenge(String[] strArr) {

        Map<Integer, Node> map = new HashMap<>();

        for (int i = 0; i < strArr.length; i++) {

            Node n = new Node(strArr[i]);

            map.put(i, n);

        }

        Node root = map.get(0);
        int parent = -1;
        Node pNode = null;
        Node cNode = null;


        for (int i=1; i < strArr.length; i++) {

            parent = (i-1)/2;
            pNode = map.get(parent);
            cNode = map.get(i);

            if (pNode.left == null) {
                pNode.left = cNode;
            } else
            if (pNode.right == null) {
                pNode.right = cNode;
            }



        }


        if (mirror(root.left, root.right)) {
            return "true";
        } else {
            return "false";
        }



        // code goes here
        //return strArr[0];
    }

    private static boolean mirror(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 != null && n2 != null &&
                n1.data.equals(n2.data)
        ) {
            return mirror(n1.left, n1.right) && mirror(n1.right, n1.left);
        }

        return false;
    }

    public static void main (String[] args) {
        // keep this function call here
        String[] arr = {"10", "2", "2", "#", "1", "1", "#"};
        System.out.print(ArrayChallenge(arr));
    }

}

class Node {
    String data;

    Node left;
    Node right;

    Node (String data) {
        this.data = data;
    }
}
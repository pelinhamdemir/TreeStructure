import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tree<Item> {
    private Node root;

    public Tree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node findNode(Node node, int key) {
        if (node.key == key) {
            return node;
        }

        for (int i = 0; i < node.children.size(); i++) {
            Node child = (Node)node.children.get(i);
            Node result = findNode(child, key);
            if (result != null) {
                return result;
            }
        }

        return null;
    }
    public boolean CheckAncestor( int id,int id2){
        Node<Item> node1 = findNodeById(root, id);
        Node<Item> node2 = findNodeById(root, id2);
        if (node1 == null || node2 == null) {
            return false;
        }
        return CheckAncestor2(node1, node2);
 }

    public boolean CheckAncestor2(Node<Item> ancestor, Node<Item> node) {
        if (node == null) {
            return false;
        }

        if (node == ancestor) {
            return true;
        }

        ArrayList<Node<Item>> children = ancestor.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node<Item> child = children.get(i);
            if (CheckAncestor2(child, node)) {
                return true;
            }
        }

        return false;
    }

    public boolean CheckDescendant(int id1, int id2) {
        Node<Item> node1 = findNodeById(root, id1);
        Node<Item> node2 = findNodeById(root, id2);
        if (node1 == null || node2 == null) {
            return false;
        }
        return CheckDescendant2(node1, node2);
    }

    public boolean CheckDescendant2(Node<Item> descendant, Node<Item> node) {
        if (node == null) {
            return false;
        }

        if (node == descendant) {
            return true;
        }

        ArrayList<Node<Item>> children = node.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node<Item> child = children.get(i);
            if (CheckDescendant2(descendant, child)) {
                return true;
            }
        }

        return false;
    }

    public boolean CheckSiblings(int id1, int id2) {
        Node<Item> node1 = findNodeById(root, id1);
        Node<Item> node2 = findNodeById(root, id2);
        if (node1 == null || node2 == null) {
            return false;
        }
        return CheckSiblings2(node1, node2);
    }

    public boolean CheckSiblings2(Node<Item> node1, Node<Item> node2) {
        Node<Item> parent1 = getParent(node1);
        Node<Item> parent2=getParent(node2);

        if (parent1 == null || parent2 == null) {
            return false;
        }

        ArrayList<Node<Item>> siblings = parent1.getChildren();
        for (Node<Item> sibling : siblings) {
            if (sibling != node1 && sibling == node2) {
                return true;
            }
        }

        return false;
    }

    public Node<Item> getParent(Node<Item> node) {
        if (node == root) {
            return null;
        }

        return findParent(root, node);
    }

    public Node<Item> findParent(Node<Item> current, Node<Item> target) {
        for (Node<Item> child : current.children) {
            if (child == target) {
                return current;
            }

            Node<Item> parent = findParent(child, target);
            if (parent != null) {
                return parent;
            }
        }

        return null;
    }
    public void printAllDescendants(int id) {
        Node<Item> node = findNodeById(root, id);
        if (node == null) {
            System.out.println("Node not found.");
            return;
        }

        printDescendants(node);
    }

    public void printDescendants(Node<Item> node) {
        ArrayList<Node<Item>> children = node.getChildren();
        int numChildren = children.size();

        for (int i = 0; i < numChildren; i++) {
            Node<Item> child = children.get(i);
            System.out.print(child.getValue());

            if (i < numChildren - 1) {
                System.out.print(", ");
            }

            printDescendants(child);
        }
    }


    public Node<Item> findNodeById(Node<Item> currentNode, int id) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.getKey() == id) {
            return currentNode;
        }

        ArrayList<Node<Item>> children = currentNode.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node<Item> child = children.get(i);
            Node<Item> result = findNodeById(child, id);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    public void findFirstOldestCommonRelative(int id1, int id2) {
        Node<Item> node1 = findNodeById(root, id1);
        Node<Item> node2 = findNodeById(root, id2);

        if (node1 == null || node2 == null) {
            System.out.println("Nodes not found.");
            return;
        }

        Node<Item> commonRelative = findOldestCommonRelative(node1, node2);

        if (commonRelative != null) {
            System.out.println(commonRelative.getValue());
        } else {
            System.out.println("Oldest common relative not found.");
        }


    }

    public void Quit(){
        System.exit(0);
    }
    public Node<Item> findOldestCommonRelative(Node<Item> node1, Node<Item> node2) {
        if (node1 == node2) {
            return null;
        }

        ArrayList<Node<Item>> ancestors1 = getAllAncestors(node1);
        ArrayList<Node<Item>> ancestors2 = getAllAncestors(node2);

        for (Node<Item> ancestor : ancestors1) {
            if (ancestor != node1 && ancestors2.contains(ancestor)) {
                return ancestor;
            }
        }

        return null;
    }
    public ArrayList<Node<Item>> getAllAncestors(Node<Item> node) {
        ArrayList<Node<Item>> ancestors = new ArrayList<>();

        while (node != null) {
            ancestors.add(node);
            node = getParent(node);
        }

        return ancestors;
    }

    public void addNode(int parentKey, int newKey,Item value) {
        if (root == null) {
            if (parentKey == -1) {
                root = new Node(newKey,value);
            } else {
                throw new IllegalArgumentException("Parent node not found.");
            }
            return;
        }

        Node parentNode = findNode(root, parentKey);
        if (parentNode != null) {
            parentNode.children.add(new Node(newKey,value));
        } else {
            throw new IllegalArgumentException("Parent node not found.");
        }
    }
    public void createFamily(String filename) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            addNode(-1,0,(Item)"Adem");
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String[] splitTokens = tokens[0].split(" ");
                String parentName = splitTokens[0];
                int parentkey = Integer.parseInt(splitTokens[1]);
                String[] splitTokens2 = tokens[1].split(" ");
                String childName = splitTokens2[0];
                int childkey = Integer.parseInt(splitTokens2[1]);
                addNode(parentkey,childkey,(Item)childName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }



    public class Node<Item>
    {
        private int key;
        private Item value;
        private ArrayList<Node<Item>> children;

        public Node(int key,Item value) {
            this.key = key;
            this.children = new ArrayList<>();
            this.value=value;
        }

        public int getKey() {
            return key;
        }

        public Item getValue() {
            return value;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void addChild(Node<Item> child) {
            children.add(child);
        }

        public void setValue(Item value) {
            this.value = value;
        }

        public ArrayList<Node<Item>> getChildren() {
            return children;
        }
    }

}




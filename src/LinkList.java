/**
 * @author lixinkun
 * date: 2019-04-01 16:25
 */
public class LinkList {

    private Node createList() {
        Node node = new Node(-1);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return node;
    }

    private void printNode(Node node) {
        while (node.next != null) {
            node = node.next;
            System.out.println(node.n);
        }
    }

    public void reverseNode(Node head) {
        Node p = head.next;
        Node q = head.next.next;
        Node t = null;
        while (q != null) {
            t = q.next;
            q.next = p;
            p = q;
            q = t;
        }
        head.next.next = null;
        head.next = p;
    }


    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        Node head = linkList.createList();
        linkList.printNode(head);
        linkList.reverseNode(head);
        linkList.printNode(head);
    }

    class Node {
        int n;
        Node next;

        public Node(int n) {
            this.n = n;
        }
    }
}

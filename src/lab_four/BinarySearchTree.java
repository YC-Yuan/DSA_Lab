package lab_four;

import lab_two.MyQueue;
import sun.invoke.empty.Empty;
import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

class EmptyBSTException extends Exception {
    public EmptyBSTException(String message) {
        super(message);
    }
}

class DuplicateKeyException extends Exception {
    public DuplicateKeyException(String message) {
        super(message);
    }
}

class NoSuchKeyException extends Exception {
    public NoSuchKeyException(String message) {
        super(message);
    }
}

/**
 * You may use {@link java.util.Stack},
 * or use MyStack in lab_two if you like.
 */
public class BinarySearchTree {
    private TreeNode root = null;
    private int size = 0;

    /**
     * default constructor
     */
    public BinarySearchTree() {
    }

    /**
     * todo: init from an array
     *
     * @param array init
     * @throws DuplicateKeyException should not have duplicate keys
     */
    public BinarySearchTree(int[] array) throws DuplicateKeyException {
        HashSet<Integer> set = new HashSet<Integer>();
        for (Integer integer : array
        ) {
            set.add(integer);
        }
        if (set.size() != array.length) throw new DuplicateKeyException("Key duplicated!");
        for (int i : array
        ) {
            insert(i);
        }
    }

    /**
     * todo: init from an Integer Object array
     *
     * @param array init
     * @throws DuplicateKeyException should not have duplicate keys
     */
    public BinarySearchTree(Integer[] array) throws DuplicateKeyException {
        HashSet<Integer> set = new HashSet<Integer>(Arrays.asList(array));
        if (set.size() != array.length) throw new DuplicateKeyException("Key duplicated!");
        for (int i : array
        ) {
            insert(i);
        }
    }

    /**
     * todo: Whether have this key or not
     *
     * @param key k
     * @return yes or no
     */
    public boolean hasKey(int key) {
        if (size == 0) return false;
        else {
            TreeNode tempNode = root;
            if (tempNode.val == key) return true;
            while (true) {
                if (key < tempNode.val) {
                    if (tempNode.left == null) return false;
                    if (tempNode.left.val == key) return true;
                    tempNode = tempNode.left;
                } else {
                    if (tempNode.right == null) return false;
                    if (tempNode.right.val == key) return true;
                    tempNode = tempNode.right;
                }
            }
        }
    }

    private TreeNode[] getKey(int key) {
        if (size == 0) return null;
        else {
            TreeNode tempNode = root;
            if (tempNode.val == key) return new TreeNode[]{root, root};
            while (true) {
                if (key < tempNode.val) {
                    if (tempNode.left == null) return null;
                    if (tempNode.left.val == key) return new TreeNode[]{tempNode.left, tempNode};
                    tempNode = tempNode.left;
                } else {
                    if (tempNode.right == null) return null;
                    if (tempNode.right.val == key) return new TreeNode[]{tempNode.left, tempNode};
                    tempNode = tempNode.right;
                }
            }
        }
    }

    /**
     * todo: insert a new key into the BST
     *
     * @param x key
     * @throws DuplicateKeyException have no duplicate key
     */
    public void insert(int x) throws DuplicateKeyException {
        if (size == 0) {
            root = new TreeNode(x);
            size++;
            return;
        }
        if (hasKey(x)) throw new DuplicateKeyException("Empty in insert");
        TreeNode tempNode = root;
        TreeNode oldNode = new TreeNode(-667);
        while (tempNode.val != oldNode.val) {
            if (x < tempNode.val) {//小于当前节点，查看有无左儿子
                if (tempNode.left == null) {
                    size++;
                    tempNode.left = new TreeNode(x);
                    return;
                }
                oldNode = tempNode;
                tempNode = tempNode.left;
            } else {//大于当前节点
                if (tempNode.right == null) {
                    size++;
                    tempNode.right = new TreeNode(x);
                    return;
                }
                oldNode = tempNode;
                tempNode = tempNode.right;
            }
        }
    }

    /**
     * todo: remove an item
     *
     * @param x key
     * @throws EmptyBSTException  remove an item from an empty BST
     * @throws NoSuchKeyException item not found
     */
    public void remove(int x) throws EmptyBSTException, NoSuchKeyException {
        if (size == 0) throw new EmptyBSTException("Empty running remove");
        if (size == 1) exchangeToDelete(root, root);
        else {
            System.out.println("try to remove x:" + x + " from:" + inorder(true));
            TreeNode[] keys = getKey(x);
            if (keys == null) throw new NoSuchKeyException("No key:" + x);
            else exchangeToDelete(keys[0], keys[1]);
        }
        System.out.println("result:" + inorder(true) + " size:" + size);
    }

    private void exchangeToDelete(TreeNode node, TreeNode parent) throws EmptyBSTException {
        if (node.left == null && node.right == null) {
            //删掉
            size--;
            if (node == root) root = null;
            else if (parent.left == node) parent.left = null;
            else if (parent.right == node) parent.right = null;
        } else {
            if (node.left != null && node.right != null) {//有二子,与有子树左叶子交换并删除
                node.val = min(node.right).val;
                removeMin(node.right);
            } else if (node.right == null) {//只有左边有
                size--;
                if (parent.left == node) parent.left = node.left;
                else if (parent.right == node) parent.right = node.left;
            } else {
                size--;
                if (parent.left == node) parent.left = node.right;
                else if (parent.right == node) parent.right = node.right;
            }
        }
    }


    /**
     * todo: preorder traversal
     *
     * @param recur using recursive or loop
     * @return sequence of traversal
     */
    public List<Integer> preorder(boolean recur) {
        List<Integer> integers = new ArrayList<Integer>();
        if (recur) {
            preRecur(integers, root);
            return integers;
        } else {
            Stack<TreeNode> stack=new Stack<>();
            if (root!=null) stack.push(root);
            while(!stack.isEmpty()){
                TreeNode node = stack.pop();
                if (node.right != null) stack.add(node.right);
                if (node.left != null) stack.add(node.left);
                integers.add(node.val);
            }
            return integers;
        }
    }

    private void preRecur(List<Integer> integers, TreeNode node) {
        integers.add(node.val);
        if (node.left != null) preRecur(integers, node.left);
        if (node.right != null) preRecur(integers, node.right);
    }

    /**
     * todo: Inorder traversal
     *
     * @param recur using recursive or loop
     * @return sequence
     */
    public List<Integer> inorder(boolean recur) {
        List<Integer> integers = new ArrayList<Integer>();
        if (recur) {
            InRecur(integers, root);
        } else {
            Stack<TreeNode> stack=new Stack<>();
            if (root!=null) stack.push(root);
            while(!stack.isEmpty()){
                TreeNode node = stack.pop();
                if (node.right==null&&node.left==null) integers.add(node.val);
                else{
                    if (node.right != null) stack.add(node.right);
                    stack.add(new TreeNode(node.val));
                    if (node.left != null) stack.add(node.left);
                }
            }
        }
        return integers;
    }

    private void InRecur(List<Integer> integers, TreeNode node) {
        if (node.left != null) InRecur(integers, node.left);
        integers.add(node.val);
        if (node.right != null) InRecur(integers, node.right);
    }

    /**
     * todo: Postorder traversal
     *
     * @param recur recursive or loop
     * @return sequence
     */
    public List<Integer> postorder(boolean recur) {
        List<Integer> integers = new ArrayList<Integer>();
        if (recur) {
            PoRecur(integers, root);
            return integers;
        } else {
            Stack<TreeNode> stack=new Stack<>();
            if (root!=null) stack.push(root);
            while(!stack.isEmpty()){
                TreeNode node = stack.pop();
                if (node.right==null&&node.left==null) integers.add(node.val);
                else{
                    stack.add(new TreeNode(node.val));
                    if (node.right != null) stack.add(node.right);
                    if (node.left != null) stack.add(node.left);
                }
            }
            return integers;
        }
    }

    private void PoRecur(List<Integer> integers, TreeNode node) {
        if (node.left != null) PoRecur(integers, node.left);
        if (node.right != null) PoRecur(integers, node.right);
        integers.add(node.val);
    }

    /**
     * todo: Level order traversal
     * From depth 1, 2, ..., to depth n.
     *
     * @return sequence
     */
    public List<Integer> levelOrder() {
        if (root == null) return new ArrayList<Integer>();
        TreeNode tempNode = root;
        List<Integer> integers = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedBlockingQueue<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            tempNode = queue.poll();
            integers.add(tempNode.val);
            if (tempNode.left != null) queue.offer(tempNode.left);
            if (tempNode.right != null) queue.offer(tempNode.right);
        }
        return integers;
    }

    private void leRecur(List<Integer> integers, TreeNode node) {

    }

    /**
     * Max value
     *
     * @return max
     * @throws EmptyBSTException may be empty
     */
    public int max() throws EmptyBSTException {
        TreeNode m = max(this.root);
        return m.val;
    }

    /**
     * Min value
     *
     * @return min
     * @throws EmptyBSTException may be empty
     */
    public int min() throws EmptyBSTException {
        TreeNode m = min(this.root);
        return m.val;
    }

    /**
     * Max node
     *
     * @param node node
     * @return max node
     * @throws EmptyBSTException may be empty
     */
    private TreeNode max(TreeNode node) throws EmptyBSTException {
        if (node == null)
            throw new EmptyBSTException("BST is empty");
        if (node.right == null)
            return node;
        return max(node.right);
    }

    /**
     * Min node
     *
     * @param node node
     * @return min node
     * @throws EmptyBSTException may be empty
     */
    private TreeNode min(TreeNode node) throws EmptyBSTException {
        if (node == null)
            throw new EmptyBSTException("BST is empty");
        if (node.left == null)
            return node;
        return min(node.left);
    }

    /**
     * remove min val and return min value
     *
     * @return min value
     * @throws EmptyBSTException may be empty
     */
    public int removeMin() throws EmptyBSTException {
        int m = this.min();
        this.root = this.removeMin(this.root);
        return m;
    }

    /**
     * remove min node and return root node
     *
     * @param node node
     * @return root
     */
    private TreeNode removeMin(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            TreeNode right = node.right;
            node.right = null;
            this.size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * remove max value and return it
     *
     * @return max value
     * @throws EmptyBSTException may be empty
     */
    public int removeMax() throws EmptyBSTException {
        int m = this.max();
        this.root = this.removeMax(this.root);
        return m;
    }

    /**
     * remove max node and return root node
     *
     * @param node node
     * @return renewed node
     */
    private TreeNode removeMax(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            TreeNode left = node.left;
            node.left = null;
            this.size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * todo: find the kth smallest value
     *
     * @param k kth
     * @return kth smallest
     * @throws EmptyBSTException may be empty
     */
    public int kthSmallest(int k) throws EmptyBSTException, DuplicateKeyException {
        if (size == 0) throw new EmptyBSTException("Empty running kSmall");
        Stack<Integer> ints = new Stack<>();
        for (int i = 0; i < k - 1; i++) {
            ints.add(removeMin());
        }
        int answer = min();
        for (int i = 0; i < k - 1; i++) {
            insert(ints.pop());
        }
        return answer;
    }


    /**
     * todo
     *
     * @return depth of BST
     */
    public int depth() {
        int depth = depth(root);
        return depth;
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }


    /**
     * todo: find the closest value, if there are two or more closest values, pick the smallest one.
     *
     * @param target target value
     * @return closest value
     */
    public int closestSmall(double target) throws EmptyBSTException {
        List<Integer> inorder = inorder(true);
        System.out.println("preorder = " + inorder + " target:" + target);
        int small = 0;
        int big = 20000;
        for (Integer integer : inorder
        ) {
            if (integer < target) small = integer;
            if (integer > target) big = Math.min(integer, big);
        }
        if ((target - small) > (big - target)) {
            System.out.println("big:" + big + " small:" + small);
            return big;
        } else {
            System.out.println("small:" + small + " big:" + big);
            return small;
        }
    }


    /**
     * the size of BST, i.e., how many items
     *
     * @return size of BST
     */
    public int size() {
        return this.size;
    }

}

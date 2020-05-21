/*
 * @program: 2020517
 * @description
 * @author: mrs.yang
 * @create: 2020 -05 -17 15 :40
 */
class Node{
    public char val;
    public Node left;
    public Node right;
    public Node(char val){
        this.val=val;
    }
}
    public class TestDemo {
             //创建二叉树
           public Node build(){
            Node A=new Node('A');
            Node B=new Node('B');
            Node C=new Node('C');
            Node D=new Node('D');
            Node E=new Node('E');
            Node F=new Node('F');
            Node G=new Node('G');
            Node H=new Node('H');
            A.left=B;
            A.right=C;
            B.left=D;
            B.right=E;
            E.right=H;
            C.left=F;
            C.right=G;
            return A;
        }
        //前序遍历
        public void preorderTree(Node root){
              if(root==null){
                  return;
              }
              //根=》左=》右
            System.out.print(root.val+" ");
            preorderTree(root.left);
            preorderTree(root.right);
        }
        //中序遍历
        public void inorderTree(Node root){
               if(root==null){
                   return;
               }
            inorderTree(root.left);
            System.out.print(root.val+" ");
            inorderTree(root.right);
        }
        //后序遍历
        public void deoderTree(Node root){
               if(root==null){
                   return;
               }
            deoderTree(root.left);
            deoderTree(root.right);
            System.out.print(root.val+" ");
        }
    //遍历思路求节点个数
        static int size=0;
        public int getSize1(Node root){
              if(root==null){
                  return 0;
              }
            size++;
            getSize1(root.left);
            getSize1(root.right);
            return size;
        }
        //子问题思路求节点个数
        public int getSize2(Node root){
            if(root==null){
                return 0;
            }
            return getSize2(root.left)+getSize2(root.right)+1;
        }
        //遍历思路求叶子节点个数
        static int size1=0;
        public int getLeafSize(Node root){
            if(root==null){
                return 0;
            } else if(root.left==null&&root.right==null){
                size1++;
            }
            getLeafSize(root.left);
            getLeafSize(root.right);
            return size1;
        }
        //子问题思路求叶子节点个数
        public int getLeafSize1(Node root){
            if(root==null){
                return 0;
            }else if(root.left==null&&root.right==null){
                return 1;
            }else {
                return getLeafSize1(root.left) + getLeafSize1(root.right);
            }
        }
        //求第K层节点个数
        public int getLeavesSize(Node root,int k){
            if(root==null){
                return 0;
            }else if(k==1){
                return 1;
            }else{
               return  getLeavesSize(root.left,k-1)+getLeavesSize(root.right,k-1);
            }
        }
        //求二叉树的高度
        public int getHeight(Node root){
            //左右子树的最大高度+1（根节点）
            if(root==null){
                return 0;
            }
            return Math.max(getHeight(root.left),getHeight(root.right))+1;
        }
        //查找value所在的节点，没有返回空
        public Node find(Node root,char value){
            //递归终止条件
            if(root==null){
                return null;
            }
            //1.判断根节点是否为value
            if(root.val==value){
                return root;
            }
            //2.递归左边，递归完全判断返回值
            Node left=find(root.left,value);
            if(left!=null){
                return left;
            }
            //3.递归右边，递归完全判断返回值
            Node right=find(root.right,value);
            if(right!=null){
                return right;
            }
            return null;
        }
        //判断两棵树是否相同
        public boolean isSameTree(Node p,Node q){
            //只要左右子树中其中一个不为空，则不相同
            if((p!=null&&q==null)||(p==null&&q!=null)){
                return false;
            }
            //两个都为空则为真，也就是递归终止条件，p q左右子树值相同且都为空
            if(p==null&&q==null){
                return true;
            }
            //判断Pq的值是否相同
            if(p.val!=q.val){
                return false;
            }
            //根节点值相同,递归左右子树，每递归一次判断上述条件是否满足
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }
        //t是否是S的子树
        public boolean isSubTree(Node s,Node t){
            //s和t只要有一个为空即不满足条件
            if(t==null||s==null){
                return false;
            }
            //1.判断T和s是否相同，即t是否是s的本身，即为终止条件
            if(isSameTree(t,s)){
                return true;
            }
            //2.不是本身，再递归判断t是否是s左树本身
            if(isSubTree(s.left,t)){
                return true;
            }
            //2.再递归判断是否是s右子树本身
            if(isSubTree(s.right,t)){
                return true;
            }
            //都不满足即为假
            return false;
        }
        //是否为平衡树一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。
        public boolean isBalance(Node root){
         //如果根节点为空，则是平衡树
            if(root==null){
                return true;
            }
            //并且需要递归左右子树都满足高度差绝对值《=1
            return Math.abs(getHeight(root.left)-getHeight(root.right))<=1 &&
                    isBalance(root.left)&&isBalance(root.right);
        }
        //判断是否为对称树
        public boolean isSymmetric(Node root){
            if(root==null){
                return true;
            }
            //判断左右子树是否对称
            return isSymmetricChild(root.left,root.right);
        }
        public boolean isSymmetricChild(Node leftTree,Node rightTree){
          //只要左右子树其中一个为空即不满足
            if((leftTree.left!=null&&rightTree==null)
                    ||(leftTree.right!=null&&rightTree.left==null)){
                return false;
            }
            //两个都为空，为真
            if(leftTree==null && rightTree==null){
                return true;
            }
            //左右子树节点的值必须相同
            if(leftTree.val!=rightTree.val){
                return false;
            }
            return isSymmetricChild(leftTree.left,rightTree.right)
                    &&isSymmetricChild(leftTree.right,rightTree.left);
        }
        public static void main(String[] args) {
            TestDemo test=new TestDemo();
            Node root=test.build();
            test.preorderTree(root);
            System.out.println();
            System.out.println(test.getSize1(root));
            System.out.println(test.getSize2(root));
            System.out.println(test.getLeafSize(root));
            System.out.println(test.getHeight(root));
            System.out.println(test.getHeight(root));
            System.out.println(test.find(root, 'F').val);
        }
}

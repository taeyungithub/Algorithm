
class BTreeNode {
    int[] keys; // 키 배열
    int t; // 최소 차수 (degree)
    BTreeNode[] children; // 자식 노드 배열
    int n; // 현재 키 개수
    boolean leaf; // 리프 노드 여부

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }

    // 삽입을 위한 헬퍼 함수, 노드가 가득차지 않았을 때
    public void insertNonFull(int key) {
        int i = n - 1;

        if (leaf) {
            // 리프 노드인 경우: 키를 삽입할 위치를 찾고 정렬 상태를 유지
            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            n += 1;
        } else {
            // 내부 노드인 경우: 자식 노드로 내려가서 삽입
            while (i >= 0 && keys[i] > key) {
                i--;
            }
            i++;

            if (children[i].n == 2 * t - 1) {
                // 자식 노드가 가득 찬 경우 분할
                splitChild(i, children[i]);

                if (keys[i] < key) {
                    i++;
                }
            }
            children[i].insertNonFull(key);
        }
    }

    // 자식 노드를 분할하는 함수
    public void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;

        // y의 후반부 키들을 z로 이동
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        if (!y.leaf) {
            // y의 자식 노드들도 z로 이동
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }

        y.n = t - 1;

        // 새로운 자식 z를 자식 배열에 추가
        for (int j = n; j >= i + 1; j--) {
            children[j + 1] = children[j];
        }
        children[i + 1] = z;

        // y의 중간 키를 부모 노드로 이동
        for (int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }
        keys[i] = y.keys[t - 1];

        n += 1;
    }

    // 키를 검색하는 함수
    public BTreeNode search(int key) {
        int i = 0;
        while (i < n && key > keys[i]) {
            i++;
        }

        if (i < n && keys[i] == key) {
            return this; // 키를 찾음
        }

        if (leaf) {
            return null; // 리프 노드까지 내려갔으나 키를 찾지 못함
        }

        return children[i].search(key); // 자식 노드에서 검색
    }

    // 노드에서 키를 삭제하는 함수
    public void remove(int key) {
        int idx = findKey(key);

        if (idx < n && keys[idx] == key) {
            if (leaf) {
                removeFromLeaf(idx);
            } else {
                removeFromNonLeaf(idx);
            }
        } else {
            if (leaf) {
                System.out.println("키 " + key + "가 트리에 없습니다.");
                return;
            }

            boolean flag = (idx == n);

            if (children[idx].n < t) {
                fill(idx);
            }

            if (flag && idx > n) {
                children[idx - 1].remove(key);
            } else {
                children[idx].remove(key);
            }
        }
    }

    // 해당 키가 노드에서 몇 번째 위치에 있는지 찾는 함수
    private int findKey(int key) {
        int idx = 0;
        while (idx < n && keys[idx] < key) {
            idx++;
        }
        return idx;
    }

    // 리프 노드에서 키를 삭제하는 함수
    private void removeFromLeaf(int idx) {
        for (int i = idx + 1; i < n; i++) {
            keys[i - 1] = keys[i];
        }
        n--;
    }

    // 내부 노드에서 키를 삭제하는 함수
    private void removeFromNonLeaf(int idx) {
        int key = keys[idx];

        if (children[idx].n >= t) {
            int pred = getPredecessor(idx);
            keys[idx] = pred;
            children[idx].remove(pred);
        } else if (children[idx + 1].n >= t) {
            int succ = getSuccessor(idx);
            keys[idx] = succ;
            children[idx + 1].remove(succ);
        } else {
            merge(idx);
            children[idx].remove(key);
        }
    }

    // 노드의 이전 키를 찾는 함수
    private int getPredecessor(int idx) {
        BTreeNode cur = children[idx];
        while (!cur.leaf) {
            cur = cur.children[cur.n];
        }
        return cur.keys[cur.n - 1];
    }

    // 노드의 다음 키를 찾는 함수
    private int getSuccessor(int idx) {
        BTreeNode cur = children[idx + 1];
        while (!cur.leaf) {
            cur = cur.children[0];
        }
        return cur.keys[0];
    }

    // 자식이 키 개수가 부족할 때 채워주는 함수
    private void fill(int idx) {
        if (idx != 0 && children[idx - 1].n >= t) {
            borrowFromPrev(idx);
        } else if (idx != n && children[idx + 1].n >= t) {
            borrowFromNext(idx);
        } else {
            if (idx != n) {
                merge(idx);
            } else {
                merge(idx - 1);
            }
        }
    }

    // 이전 자식으로부터 키를 빌려오는 함수
    private void borrowFromPrev(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx - 1];

        for (int i = child.n - 1; i >= 0; --i) {
            child.keys[i + 1] = child.keys[i];
        }

        if (!child.leaf) {
            for (int i = child.n; i >= 0; --i) {
                child.children[i + 1] = child.children[i];
            }
        }

        child.keys[0] = keys[idx - 1];

        if (!leaf) {
            child.children[0] = sibling.children[sibling.n];
        }

        keys[idx - 1] = sibling.keys[sibling.n - 1];

        child.n += 1;
        sibling.n -= 1;
    }

    // 다음 자식으로부터 키를 빌려오는 함수
    private void borrowFromNext(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];

        child.keys[child.n] = keys[idx];

        if (!child.leaf) {
            child.children[child.n + 1] = sibling.children[0];
        }

        keys[idx] = sibling.keys[0];

        for (int i = 1; i < sibling.n; ++i) {
            sibling.keys[i - 1] = sibling.keys[i];
        }

        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; ++i) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }

        child.n += 1;
        sibling.n -= 1;
    }

    // 자식을 병합하는 함수
    private void merge(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];

        child.keys[t - 1] = keys[idx];

        for (int i = 0; i < sibling.n; ++i) {
            child.keys[i + t] = sibling.keys[i];
        }

        if (!child.leaf) {
            for (int i = 0; i <= sibling.n; ++i) {
                child.children[i + t] = sibling.children[i];
            }
        }

        for (int i = idx + 1; i < n; ++i) {
            keys[i - 1] = keys[i];
        }

        for (int i = idx + 2; i <= n; ++i) {
            children[i - 1] = children[i];
        }

        child.n += sibling.n + 1;
        n--;

    }

    // B-트리를 순회하여 키를 출력하는 함수 (중위 순회)
    public void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            if (!leaf) {
                children[i].traverse();
            }
            System.out.print(" " + keys[i]);
        }

        if (!leaf) {
            children[i].traverse();
        }
    }
}

class BTree {
    BTreeNode root; // 루트 노드
    int t; // 최소 차수

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    // 키 삽입 함수
    public void insert(int key) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root);

                int i = 0;
                if (s.keys[0] < key) {
                    i++;
                }
                s.children[i].insertNonFull(key);

                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    // 키 삭제 함수
    public void remove(int key) {
        if (root == null) {
            System.out.println("트리가 비어 있습니다.");
            return;
        }

        root.remove(key);

        if (root.n == 0) {
            if (root.leaf) {
                root = null;
            } else {
                root = root.children[0];
            }
        }
    }

    // 키 검색 함수
    public BTreeNode search(int key) {
        return root == null ? null : root.search(key);
    }

    // 트리 순회 출력
    public void traverse() {
        if (root != null) {
            root.traverse();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BTree btree = new BTree(3); // 최소 차수 t=3인 B-트리 생성
//        최소 키 개수: t−1=2 (최소 2개의 키)
//        최대 키 개수: 2t−1=5 (최대 5개의 키)
//        최소 자식 개수: t=3 (최소 3개의 자식)
//        최대 자식 개수: 2t=6 (최대 6개의 자식)

        // 키 삽입
        btree.insert(10);
        btree.insert(20);
        btree.insert(5);
        btree.insert(6);
        btree.insert(12);
        btree.insert(30);
        btree.insert(7);
        btree.insert(17);
        btree.insert(3);
        btree.insert(8);
        btree.insert(25);
        btree.insert(40);

//        6    10     20
//     /    |      \       \
//  (3 5)  (7 8)  (12 17) (25 30 40)

        System.out.println("B-트리 순회 결과:");
        btree.traverse();
        System.out.println();

        // 키 검색
        int keyToSearch = 6;
        BTreeNode node = btree.search(keyToSearch);
        if (node != null) {
            System.out.println("키 " + keyToSearch + "가 트리에 존재합니다.");
        } else {
            System.out.println("키 " + keyToSearch + "가 트리에 없습니다.");
        }

        // 키 삭제
        btree.remove(6);
        btree.remove(17);
        System.out.println("6, 17을 삭제한 후 B-트리 순회 결과:");
        btree.traverse();
    }
}

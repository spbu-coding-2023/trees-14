

## About Library

This library for Kotlin is an implementation of 3 types of binary search trees:
1. Just a binary search tree (binarySearchTree) - a tree without any balancing
2. Red-black search tree (RedBlackTree)
3. AVL tree (AVLTree)

## How to use

to init trees, you only need to specify the key type, since the value in one tree can be of different types.
```
val tree: binarySearchTree<Key>
```

for example

```
val tree = binarySearchTree<String>()
```

All trees supports basic methods:

- Add values and keys as nodes to the tree:

  `tree.add(key, value)`

if there is already a node with such a key in the tree, then the value of this node will change to a new one.

- Delete node from the tree by key:

  `tree.delete(key)`

- Find node by key:

  `tree.find(key)`

- returns a list of pairs (key, value) 

  `val listOfAllNodes = tree.iter()`
- Hint! Way to iterate in tree:

  `for (node in tree.iter()) { ...`

- ...and a little more!



## License

 Distributed under the MIT License. Read`LICENSE`for more information

## Contacts

This library is finaly made by:
* Ruslan Nafikov (tg @Nafikov67) - binarySearchTree
* Oleg Chabykin (tg @Ddttyg) - RedBlackTree
* Matvey Nazdruhin (tg @bikakuka) - AVLTree



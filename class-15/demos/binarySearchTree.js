'use strict';

function BinarySearchTree() {
  this.root = null;
}

function Node(value, left, right) {
  this.value = value;
  this.left = left;
  this.right = right;
}

BinarySearchTree.prototype.add = function(value){
  if(!this.root) return this.root = new Node(value, null, null);
  let node = this.root;
  while(true){
    if (value < node.value) {
      if(!node.left) return node.left = new Node(value, null, null);
      node = node.left;
    }
    else if (value === node.value) return;
    else {
      if (!node.right) return node.right = new Node(value, null, null);
      node = node.right;
    }
  }

};


// ======================== Breadth First
BinarySearchTree.prototype.breadthFirstTraversalIterative = function () {
  if (!this.root) return 'aaaah nothing to traverse';
  const arrQueue = [];
  arrQueue.push(this.root);
  while (arrQueue.length > 0) {
    const node = arrQueue.shift();
    console.log(node.value);
    if (node.left) arrQueue.push(node.left);
    if (node.right) arrQueue.push(node.right);
  }
};

// ==================== Depth First
BinarySearchTree.prototype.inOrderRecursiveDepthFirstTraversal = function () {
  this._inOrderRecursiveDepthFirstTraversal(this.root);
};

BinarySearchTree.prototype._inOrderRecursiveDepthFirstTraversal = function (node) {
  if (!node) return; // base case
  this._inOrderRecursiveDepthFirstTraversal(node.left); // do left stuff (log left ==lower)
  console.log(node.value); // work I want to do // do me stuff (log me)
  this._inOrderRecursiveDepthFirstTraversal(node.right); // do right stuff (log right == higher)
};


const books = new BinarySearchTree();

books.add('Harry Potter');
books.add('Dune');
books.add('Children Of The Mind');
books.add('An Unexpected Journey');
books.add('Things Fall Apart');
books.add('Cracking the Coding Interview');
books.add('Html and CSS');
books.add('Song of Ice and Fire');
books.add('War and Peace');
books.add('Brothers Karimazov');
books.add('Green Eggs and Ham');
books.add('The Giving Tree');
books.add('Eye Spy');
books.add('Where\'s Waldo');
books.add('Series of Unfortunate Events');

books.inOrderRecursiveDepthFirstTraversal();

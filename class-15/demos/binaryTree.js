'use strict';

function BinaryTree(){
  this.root = null;
}

function Node(value, left, right){
  this.value = value;
  this.left = left;
  this.right = right;
}





// ======================== Breadth First
BinaryTree.prototype.breadthFirstTraversalIterative = function(){
  if(!this.root) return 'aaaah nothing to traverse';
  const arrQueue = [];
  arrQueue.push(this.root);
  while(arrQueue.length > 0){
    const node = arrQueue.shift();
    console.log(node.value);
    if(node.left) arrQueue.push(node.left);
    if(node.right) arrQueue.push(node.right);
  }
};



// If we think of a LinkedList as a UnaryTree, this is its breadthFirstTraversal
// LinkedList.prototype.breadthFirstTraversalIterative = function () {
//   if (!this.head) return 'aaaah nothing to traverse';
//   const arrQueue = [];
//   arrQueue.push(this.head);
//   while (arrQueue.length > 0) {
//     const node = arrQueue.shift();
//     console.log(node.value);
//     if (node.next) arrQueue.push(node.next);
//   }

//   let current = this.head;
//   while (current) {
//     console.log(current.value);
//     current = current.next;
//   }
// };

// BinaryTree.prototype.toString = function(){
//   let str = '';

// };

// ================= Depth First Traversals

// Iterative preOrder
BinaryTree.prototype.preOrderIterativeDepthFirstTraversal = function(){
  if(!this.root) return 'aaaah nothing to traverse';
  const stackArr = [this.root];
  while(stackArr.length > 0){
    const node = stackArr.pop();
    console.log(node.value);
    if(node.right) stackArr.push(node.right);
    if(node.left) stackArr.push(node.left);
  }
};

BinaryTree.prototype.preOrderRecursiveDepthFirstTraversal = function(){
  this._preOrderRecursiveDepthFirstTraversal(this.root);
};

BinaryTree.prototype._preOrderRecursiveDepthFirstTraversal = function(node){
  if(!node) return; // base case
  console.log(node.value); // work I want to do
  this._preOrderRecursiveDepthFirstTraversal(node.left);
  this._preOrderRecursiveDepthFirstTraversal(node.right);
};

BinaryTree.prototype.inOrderRecursiveDepthFirstTraversal = function () {
  this._inOrderRecursiveDepthFirstTraversal(this.root);
};

BinaryTree.prototype._inOrderRecursiveDepthFirstTraversal = function (node) {
  if (!node) return; // base case
  this._inOrderRecursiveDepthFirstTraversal(node.left); // do left stuff (log left ==lower)
  console.log(node.value); // work I want to do // do me stuff (log me)
  this._inOrderRecursiveDepthFirstTraversal(node.right); // do right stuff (log right == higher)
};

BinaryTree.prototype.postOrderRecursiveDepthFirstTraversal = function () {
  this._postOrderRecursiveDepthFirstTraversal(this.root);
};

BinaryTree.prototype._postOrderRecursiveDepthFirstTraversal = function (node) {
  if (!node) return; // base case
  this._postOrderRecursiveDepthFirstTraversal(node.left); // do left stuff (log left ==lower)
  this._postOrderRecursiveDepthFirstTraversal(node.right); // do right stuff (log right == higher)
  console.log(node.value); // work I want to do // do me stuff (log me)
};





const classRoomMilitary = new BinaryTree();

classRoomMilitary.root = new Node('General Andy', null, null);

classRoomMilitary.root.left = new Node('Chief Leaundrae', null, null);
classRoomMilitary.root.right = new Node('Chief Amelia', null, null);

classRoomMilitary.root.right.left = new Node('Technician Ed', new Node('Plebian James', null, null), null);

classRoomMilitary.root.left.left = new Node('Technician Victor', null, null);

console.log('=======breadth first');
classRoomMilitary.breadthFirstTraversalIterative();
console.log('=======depth first stack');
classRoomMilitary.preOrderIterativeDepthFirstTraversal();
console.log('=======depth first recursive in order');
classRoomMilitary.inOrderRecursiveDepthFirstTraversal();

console.log('=======depth first recursive pre order');
classRoomMilitary.preOrderRecursiveDepthFirstTraversal();

console.log('=======depth first recursive post order');
classRoomMilitary.postOrderRecursiveDepthFirstTraversal();

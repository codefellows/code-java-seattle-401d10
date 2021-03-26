'use strict';

console.log('lets have some fun with a linked list');

function LinkedList(){
  this.head = null;
  this.tail = null;
}

LinkedList.prototype.insert = function(valuePotato){
  if(this.head === null) {
    const newNode = new Node(valuePotato);
    this.head = newNode;
    this.tail = newNode;
  } else {
    const newNode = new Node(valuePotato);
    const secondNode = this.head;
    this.head = newNode;
    newNode.next = secondNode;
  }

  // const newNode = new Node(valuePotato);
  // newNode.next = this.head;
  // this.head = newNode;
  // if(!this.tail) this.tail = newNode;
};

LinkedList.prototype.toStringIterative = function(){
  let str = '';
  let currentNode = this.head;
  while(currentNode !== null){
    str += currentNode.value + ',';
    currentNode = currentNode.next;
  }
  str += 'null';
  return str;
};

// Recursion: a function that calls itself
LinkedList.prototype.recursiveToString = function(){
  return this._recursiveToString(this.head);
};

LinkedList.prototype._recursiveToString = function (nodeWeAreOn) {

  // base case: when the problem is the smallest it will ever be
  // if the node is null, return "null"
  // work
  //
  // recursive part (when we call our own function);
  if(nodeWeAreOn === null) return 'null';
  return '' + nodeWeAreOn.value + ' -> ' + this._recursiveToString(nodeWeAreOn.next);
};

function Node(value) {
  this.value = value; // this should be of type int or String for today's lab
  this.next = null;
}

const linky = new LinkedList();

linky.insert('victor');
linky.insert('amelia');
console.log(linky);
console.log(linky.toStringIterative());
console.log(linky.recursiveToString());


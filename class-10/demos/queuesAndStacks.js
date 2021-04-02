function Queue(){
  this._storedStuff = []; // this should be private in java
}

// (both a methodName and a concept)
// Queue's add : enQueue  : add to the end with push
Queue.prototype.enQueue = function(value){ // insert a value, (parameter of the value)
  this._storedStuff.push(value);
};

// Queue's remove : deQueue : remove from the fromt with shift
Queue.prototype.deQueue = function () { // insert a value, (parameter of the value)
  if (this._storedStuff.length === 0) throw new Error('NoSuchElementException');
  return this._storedStuff.shift();
};

Queue.prototype.peek = function(){
  if(this._storedStuff.length) return this._storedStuff[0];
  return null;
};


function Stack(){
  this._storedStuff = [];
}

// Stack's add : push : add to the end with push
Stack.prototype.push = function(value){
  this._storedStuff.push(value);
};
// Stack's remove : pop : remove from the end with pop
Stack.prototype.pop = function(){
  return this._storedStuff.pop();
};


const rollerCoasterLine = new Queue();
rollerCoasterLine.enQueue('Andy');
rollerCoasterLine.enQueue('James');
rollerCoasterLine.enQueue('John');
rollerCoasterLine.enQueue('Cristian');
console.log(rollerCoasterLine.deQueue());
rollerCoasterLine.enQueue('Leaundrae');

console.log(rollerCoasterLine.deQueue());
console.log(rollerCoasterLine.deQueue());
console.log(rollerCoasterLine.deQueue());
console.log(rollerCoasterLine.deQueue()); // Java is a vengeful god, we want to throw errors if the behavior is impossible
if(rollerCoasterLine.peek()){
  console.log(rollerCoasterLine.deQueue());
} else {
  console.log('queue was empty oh well noone wants to ride today anymore');
}
// console.log(rollerCoasterLine.deQueue());
// console.log(rollerCoasterLine.deQueue());
// console.log(rollerCoasterLine.deQueue());
// console.log(rollerCoasterLine.deQueue());
// console.log(rollerCoasterLine.deQueue());


// Iterating all the way through a queue
rollerCoasterLine.enQueue('Andy');
rollerCoasterLine.enQueue('James');
rollerCoasterLine.enQueue('John');
rollerCoasterLine.enQueue('Cristian');
rollerCoasterLine.enQueue('Leaundrae');

while(rollerCoasterLine.peek() !== null){
  console.log(rollerCoasterLine.deQueue());
}

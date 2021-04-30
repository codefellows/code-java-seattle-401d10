function HashTable(size){
  this.buckets = new Array(size); // buckets is potato: means a collection of the buckets
  for(let i = 0; i < size; i++){
    this.buckets[i] = new LinkedList();
  }
}

//                        number, thing to store (3, 'pizza')
HashTable.prototype.add = function (key, value){
  // const indexToInsertAt = key % this.buckets.length; // x's is 5 : 3 % 5 == 3
  const indexToInsertAt = this.hash(key); // x's is 5 : 3 % 5 == 3
  const linkedListAtThatIndex = this.buckets[indexToInsertAt];
  const storageObject = new StorageObject(key, value); // {key: 3, value: 'pizza }
  linkedListAtThatIndex.add(storageObject);
};

HashTable.prototype.hash = function(anything){
  const stringAnything = '' + anything; // 9 -> '9' {} -> [Object object]
  let hash =0;
  // turn the string into a num;
  for(let i = 0; i < stringAnything.length; i++){
    let num = stringAnything.charCodeAt(i);
    // console.log('charcodeat', i, num);
    hash += num;
    hash = hash * 31;
    hash = hash % 100000001;
    // console.log('hash so far', hash);
  }
  // console.log('string turned into a num', hash);
  // Do some math

  // at the end of the math
  hash = hash % this.buckets.length;
  // return an index within the array length
  return hash;
};

HashTable.prototype.get = function(key){
  const indexToFindAt = this.hash(key);
  const linkedListAtThatIndex = this.buckets[indexToFindAt];
  let current = linkedListAtThatIndex.head;
  while(current){
    if(current.value.key === key){
      return current.value.value;
    }
    current = current.next;
  }
  return null;
};

HashTable.prototype.contains = function (key) {
  const thingInThere = this.get(key);
  if(thingInThere !== null) return true;
  return false;
};

HashTable.prototype.toString = function(){
  let out = '';
  for(let i = 0; i < this.buckets.length; i++){
    let line = '';
    let current = this.buckets[i].head;
    while(current){
      line += current.value.key + ':' + current.value.value + ',';
      current = current.next;
    }
    if(line === '') line = 'empty';
    out += line + '\n';
  }
  return out;
};



function StorageObject(keyPotato, value){
  this.key = keyPotato;
  this.value = value;
}

function LinkedList(){
  this.head = null;
}
//           {key: 9, value: dragon}
LinkedList.prototype.add = function(object){
// given a linked list, see if a value is in the linked list
  let current = this.head;
  while (current) {
    if (current.value.key === object.key) {
      current.value.value = object.value;
      return;
    }
    current = current.next;
  }

  this.head = new Node(object, this.head);
};

function Node(value, next){
  this.value = value;
  this.next = next;
}


const x = new HashTable(10);
x.add(9, 'dragon');
x.add(9, 'dragon');
x.add(9, 'dragon');
x.add(9, 'kraken');
x.add(1, 'unicorn');
x.add(4, 'twinkie');
x.add(3, 'pizza');

x.add('nicholas', 'teacher');
x.add('racecar', 'lambo');
x.add('dog', 'ginger');
x.add('spaceship', 'falcon');

console.log(x.toString());


console.log(x.get(9));
console.log(x.get(4));
console.log(x.get(1));
console.log(x.get(3));
console.log(x.get(99));
console.log(x.get(0));
console.log(x.get(2));
console.log(x.get(20));


console.log(x.contains(9));
console.log(x.contains(4));
console.log(x.contains(1));
console.log(x.contains(3));
console.log(x.contains(99));
console.log(x.contains(0));
console.log(x.contains(2));
console.log(x.contains(20));

console.log(x.hash('nicholas'));
console.log(x.hash('a'));
console.log(x.hash('ab'));
console.log(x.hash('ba'));
console.log(x.hash('c'));



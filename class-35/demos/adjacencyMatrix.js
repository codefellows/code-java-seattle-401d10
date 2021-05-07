function Graph(){
  this.vertexes = [];// ['nicholas', 'amelia', 'cristian'] // columns are friendship, rows are friends
  this.adjacencyMatrix = [];
  /*
   [
    [0, 0, 0],
    [0, 0, 0]
    [0, 0, 0]
   ]
  */
}

Graph.prototype.addVertex = function(value){
  this.vertexes.push(value);
  this.adjacencyMatrix.forEach(row => row.push(0));
  this.adjacencyMatrix.push([]);
  for(let i = 0; i < this.adjacencyMatrix.length; i++){
    this.adjacencyMatrix[this.adjacencyMatrix.length -1].push(0);
  }
};
//                                 friendlyFriend, recievingFriend, friendshiplevel
//                                 nicholas, amelia, 3
Graph.prototype.addEdge = function(value1, value2, weight){
  const index1 = this.vertexes.indexOf(value1); // 0
  const index2 = this.vertexes.indexOf(value2); // 1
  this.adjacencyMatrix[index1][index2] = weight;
};

Graph.prototype.getNeighbors = function (value1) {
  const index1 = this.vertexes.indexOf(value1); // 0
  const neighbors = [];
  this.adjacencyMatrix[index1].forEach((val, personIndex) => {
    if(val) neighbors.push(this.vertexes[personIndex] + ' strength: ' + this.adjacencyMatrix[index1][personIndex]);
  });
  return neighbors;
};

const friendsWithFriendshipStrength = new Graph();
friendsWithFriendshipStrength.addVertex('nich');
friendsWithFriendshipStrength.addVertex('amelia');
friendsWithFriendshipStrength.addVertex('cristian');

friendsWithFriendshipStrength.addEdge('nich', 'amelia', 3);
friendsWithFriendshipStrength.addEdge('amelia', 'nich', 3);
friendsWithFriendshipStrength.addEdge('amelia', 'cristian', 3);
friendsWithFriendshipStrength.addEdge('cristian', 'amelia', 3);
friendsWithFriendshipStrength.addEdge('nich', 'cristian', 3);
friendsWithFriendshipStrength.addEdge('cristian', 'nich', 3);

console.log(friendsWithFriendshipStrength);

console.log(friendsWithFriendshipStrength.getNeighbors('nich'));


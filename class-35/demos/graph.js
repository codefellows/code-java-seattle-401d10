function Graph(){
  this.vertexes = []; //basically just nodes
  this.edges = []; // the left, right or next of a tree or linked list : the relationship between vertexes /(nodes);
}

Graph.prototype.addVertex = function(value){
  const vertex = new Vertex(value);
  this.vertexes.push(vertex);
  return vertex;
};

Graph.prototype.addEdge = function(vertex1, vertex2){
  const edge = new Edge(vertex1, vertex2);
  this.edges.push(edge);
  vertex1.edges.push(edge);
  vertex2.edges.push(edge);
};

Graph.prototype.depthFirstTraversal = function(startingPosition){ // this should be a Vertex
  // could just start at this.vertexes[0]
  const stack = [startingPosition];
  const set = new Set();
  set.add(startingPosition);

  while(stack.length > 0){
    const vertex = stack.pop(); // sea
    console.log(vertex.value);
    vertex.edges.forEach(edge => { // [{v1: sea v2: van}, {v1: lax, v2: sea}]

      if (!set.has(edge.vertex2)) {
        stack.push(edge.vertex2);
        set.add(edge.vertex2);
      }
      if (!set.has(edge.vertex1)) {
        stack.push(edge.vertex1);
        set.add(edge.vertex1);
      }

    });
  }
};

function Vertex(value){
  this.value = value;
  this.edges = [];
}

function Edge(vertex1, vertex2){
  this.vertex1 = vertex1;
  this.vertex2 = vertex2;
}

const citiesWithAirportRoutes = new Graph();
const sea = citiesWithAirportRoutes.addVertex('SEA');
const van = citiesWithAirportRoutes.addVertex('VAN');
const lax = citiesWithAirportRoutes.addVertex('LAX');
const sfo = citiesWithAirportRoutes.addVertex('SFO');
const den = citiesWithAirportRoutes.addVertex('DEN');
const pdx = citiesWithAirportRoutes.addVertex('PDX');
const fai = citiesWithAirportRoutes.addVertex('FAI');
const kul = citiesWithAirportRoutes.addVertex('KUL');

citiesWithAirportRoutes.addEdge(sea, van);
citiesWithAirportRoutes.addEdge(sea, sfo);
citiesWithAirportRoutes.addEdge(sea, lax);
citiesWithAirportRoutes.addEdge(lax, sfo);
citiesWithAirportRoutes.addEdge(lax, fai);
citiesWithAirportRoutes.addEdge(lax, den);
citiesWithAirportRoutes.addEdge(den, pdx);


citiesWithAirportRoutes.depthFirstTraversal(sea);
